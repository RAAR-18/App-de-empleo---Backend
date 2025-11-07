package com.procol.registropublico.servicio;

import com.procol.registropublico.entidad.PinTemporal;
import com.procol.registropublico.dto.PinTemporalDtoRespuesta;
import com.procol.registropublico.repositorio.AccesoRepositorio;
import com.procol.registropublico.repositorio.PinTemporalRepositorio;

import com.procol.infraestructura.constante.ConstPinMensaje;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import com.procol.mensajeria.api.MensajeServicio;
import com.procol.mensajeria.dto.mensaje.MensajeDto;
import com.procol.mensajeria.dto.mensaje.MensajeDtoPeticion;
import com.procol.mensajeria.dto.mensaje.MensajeDtoRespuesta;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.time.Duration;
import java.time.ZoneOffset;
import java.time.OffsetDateTime;

@Service
public class PinServicio extends OperacionCrudImple<PinTemporal, Long> {

    private final PinTemporalRepositorio pinTemporalRepositorio;
    private final AccesoRepositorio accesoRepositorio;
    private final MensajeServicio mensajeServicio;

    public PinServicio(
            PinTemporalRepositorio pinTemporalRepositorio,
            AccesoRepositorio accesoRepositorio,
            MensajeServicio mensajeServicio,
            BusquedaServicio<PinTemporal, Long> busquedaServicioPinTemporal
    ) {
        super(busquedaServicioPinTemporal);
        this.pinTemporalRepositorio = pinTemporalRepositorio;
        this.accesoRepositorio = accesoRepositorio;
        this.mensajeServicio = mensajeServicio;
    }

    @Override
    protected JpaRepository<PinTemporal, Long> getRepositorio() {
        return pinTemporalRepositorio;
    }

    @Transactional
    public PinTemporalDtoRespuesta solicitarPin(String telefono) {
        accesoRepositorio.findByTelefonoAcceso(telefono)
                .ifPresent(a -> {
                    throw new ExcepcionNegocio("Teléfono ya registrado: " + telefono);
                });

        Long telefonoNumerico = Long.valueOf(telefono);
        Optional<PinTemporal> pinTemporalOpt = pinTemporalRepositorio.findById(telefonoNumerico);

        PinTemporal pinTemporal;
        OffsetDateTime ahora = OffsetDateTime.now(ZoneOffset.UTC);
        boolean enviarSms;

        if (pinTemporalOpt.isEmpty()) {
            pinTemporal = new PinTemporal(
                    telefonoNumerico,
                    generarPin(),
                    ahora,
                    (short) 1,
                    ConstPinMensaje.INTENTOS,
                    ConstPinMensaje.MINUTOS_BLOQUEO
            );
            pinTemporal = agregar(pinTemporal);
            enviarSms = true;
        } else {
            pinTemporal = pinTemporalOpt.get();
            if (pinTemporal.getIntentoPinTemporal() < pinTemporal.getTotalIntentosPinTemporal()) {
                OffsetDateTime desbloqueo = pinTemporal.getFechaCreacionPinTemporal()
                        .plusMinutes(pinTemporal.getMinutosSuspensionPinTemporal());

                ahora = OffsetDateTime.now(ZoneOffset.UTC);

                Duration diferencia = Duration.between(ahora, desbloqueo);
                long minutos = diferencia.toMinutes();
                long segundos = diferencia.getSeconds() % 60;
                String mensaje = String.format("Bloqueado por: %d:%02d minutos", minutos, segundos);

                if (ahora.isBefore(desbloqueo)) {
                    throw new ExcepcionNegocio(mensaje);
                } else {
                    short nuevosIntentos = (short) (pinTemporal.getIntentoPinTemporal() + 1);
                    pinTemporal.setIntentoPinTemporal(nuevosIntentos);
                }
            } else {
                pinTemporal.setIntentoPinTemporal((short) 1);
            }

            pinTemporal.setValorPinTemporal(generarPin());
            pinTemporal.setFechaCreacionPinTemporal(ahora);
            pinTemporalRepositorio.save(pinTemporal);
            enviarSms = true;
        }

        boolean smsEnviado = false;

        if (enviarSms) {
            MensajeDto smsMensajeObj = new MensajeDto();
            smsMensajeObj.setTo(telefono);
            smsMensajeObj.setText("Pin de acceso Swallow: " + pinTemporal.getValorPinTemporal());

            MensajeDtoPeticion peticion = new MensajeDtoPeticion();
            peticion.setMessages(List.of(smsMensajeObj));

            MensajeDtoRespuesta respuesta = mensajeServicio.enviarSms(peticion);

            smsEnviado = respuesta.exito();
        }

        return new PinTemporalDtoRespuesta(
                pinTemporal.getIdTelefonoPinTemporal(),
                pinTemporal.getValorPinTemporal(),
                pinTemporal.getFechaCreacionPinTemporal(),
                pinTemporal.getIntentoPinTemporal(),
                ConstPinMensaje.INTENTOS,
                ConstPinMensaje.MINUTOS_BLOQUEO,
                smsEnviado
        );
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private String generarPin() {
        int numero = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(numero);
    }
}
