package com.procol.telefono.servicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;
import com.procol.mensajeria.api.MensajeServicio;
import com.procol.mensajeria.dto.mensaje.MensajeDto;
import com.procol.mensajeria.dto.mensaje.MensajeDtoPeticion;
import com.procol.mensajeria.dto.mensaje.MensajeDtoRespuesta;
import com.procol.telefono.dto.*;
import com.procol.telefono.entidad.Acceso;
import com.procol.telefono.entidad.CambioTelefono;
import com.procol.telefono.repositorio.CambioTelefonoRepositorio;
import com.procol.telefono.repositorio.AccesoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class CambioTelefonoServicio {

    private static final int MINUTOS_EXPIRACION = 15;

    private final CambioTelefonoRepositorio cambioTelefonoRepositorio;
    private final AccesoRepositorio accesoRepositorio;
    private final MensajeServicio mensajeServicio;

    public CambioTelefonoServicio(
            CambioTelefonoRepositorio cambioTelefonoRepositorio,
            AccesoRepositorio accesoRepositorio,
            MensajeServicio mensajeServicio
    ) {
        this.cambioTelefonoRepositorio = cambioTelefonoRepositorio;
        this.accesoRepositorio = accesoRepositorio;
        this.mensajeServicio = mensajeServicio;
    }

    @Transactional
    public IniciarCambioTelefonoDtoRespuesta iniciarCambioTelefono(
            IniciarCambioTelefonoDtoPeticion peticion
    ) {
        validarFormatoTelefono(peticion.getTelefonoNuevo());

        Acceso acceso = accesoRepositorio.findById(peticion.getIdUsuario())
                .orElseThrow(() -> new ExcepcionNegocio("Usuario no encontrado"));

        String telefonoAnterior = acceso.getTelefonoAcceso();
        String telefonoNuevo = peticion.getTelefonoNuevo();

        if (telefonoAnterior.equals(telefonoNuevo)) {
            throw new ExcepcionValidacion("El teléfono nuevo debe ser diferente al actual");
        }

        Optional<Acceso> telefonoEnUso = accesoRepositorio.findByTelefonoAcceso(telefonoNuevo);
        if (telefonoEnUso.isPresent()) {
            throw new ExcepcionValidacion("El teléfono ya está registrado en otra cuenta");
        }

        OffsetDateTime ahora = OffsetDateTime.now(ZoneOffset.UTC);

        boolean existeProceso = cambioTelefonoRepositorio.existeProcesoActivoByUsuario(
                peticion.getIdUsuario(), ahora
        );

        if (existeProceso) {
            throw new ExcepcionNegocio(
                    "Ya existe un proceso de cambio de teléfono activo. " +
                            "Por favor, complétalo o espera a que expire."
            );
        }

        String pinAnterior = generarPin();
        String pinNuevo = generarPin();

        CambioTelefono cambioTelefono = new CambioTelefono(
                peticion.getIdUsuario(),
                telefonoAnterior,
                telefonoNuevo,
                pinAnterior,
                pinNuevo,
                ahora,
                ahora.plusMinutes(MINUTOS_EXPIRACION)
        );

        cambioTelefono = cambioTelefonoRepositorio.save(cambioTelefono);

        boolean smsEnviado = enviarSms(
                telefonoAnterior,
                "Código de verificación para cambio de teléfono: " + pinAnterior
        );

        return new IniciarCambioTelefonoDtoRespuesta(
                cambioTelefono.getIdCambioTelefono(),
                enmascarrarTelefono(telefonoAnterior),
                enmascarrarTelefono(telefonoNuevo),
                smsEnviado,
                smsEnviado
                        ? "Código enviado a tu teléfono anterior"
                        : "Error al enviar el código. Intenta nuevamente."
        );
    }

    @Transactional
    public VerificarPinAnteriorDtoRespuesta verificarPinAnterior(
            VerificarPinAnteriorDtoPeticion peticion
    ) {
        OffsetDateTime ahora = OffsetDateTime.now(ZoneOffset.UTC);

        CambioTelefono cambioTelefono = cambioTelefonoRepositorio
                .findById(peticion.getIdCambioTelefono())
                .orElseThrow(() -> new ExcepcionNegocio("Proceso de cambio no encontrado"));

        if (ahora.isAfter(cambioTelefono.getFechaExpiracion())) {
            throw new ExcepcionNegocio("El proceso ha expirado. Inicia uno nuevo.");
        }

        if (cambioTelefono.getCompletado()) {
            throw new ExcepcionNegocio("Este proceso ya fue completado");
        }

        if (cambioTelefono.getVerificadoAnterior()) {
            return new VerificarPinAnteriorDtoRespuesta(
                    cambioTelefono.getIdCambioTelefono(),
                    true,
                    false,
                    "El teléfono anterior ya fue verificado. Verifica el código del teléfono nuevo."
            );
        }

        if (cambioTelefono.getIntentosAnterior() >= cambioTelefono.getMaxIntentos()) {
            throw new ExcepcionNegocio(
                    "Has excedido el número máximo de intentos. Inicia un nuevo proceso."
            );
        }

        cambioTelefono.setIntentosAnterior((short) (cambioTelefono.getIntentosAnterior() + 1));

        if (!cambioTelefono.getPinAnterior().equals(peticion.getPinAnterior())) {
            cambioTelefonoRepositorio.save(cambioTelefono);

            int intentosRestantes = cambioTelefono.getMaxIntentos() - cambioTelefono.getIntentosAnterior();
            throw new ExcepcionValidacion(
                    "Código incorrecto. Te quedan " + intentosRestantes + " intento(s)."
            );
        }

        cambioTelefono.setVerificadoAnterior(true);
        cambioTelefonoRepositorio.save(cambioTelefono);

        boolean smsEnviado = enviarSms(
                cambioTelefono.getTelefonoNuevo(),
                "Código de verificación para tu nuevo teléfono: " + cambioTelefono.getPinNuevo()
        );

        return new VerificarPinAnteriorDtoRespuesta(
                cambioTelefono.getIdCambioTelefono(),
                true,
                smsEnviado,
                smsEnviado
                        ? "Código verificado. Ahora verifica el código enviado a tu nuevo teléfono."
                        : "Código verificado, pero hubo un error al enviar el SMS al nuevo teléfono."
        );
    }

    @Transactional
    public VerificarPinNuevoDtoRespuesta verificarPinNuevo(
            VerificarPinNuevoDtoPeticion peticion
    ) {
        OffsetDateTime ahora = OffsetDateTime.now(ZoneOffset.UTC);

        CambioTelefono cambioTelefono = cambioTelefonoRepositorio
                .findById(peticion.getIdCambioTelefono())
                .orElseThrow(() -> new ExcepcionNegocio("Proceso de cambio no encontrado"));

        if (ahora.isAfter(cambioTelefono.getFechaExpiracion())) {
            throw new ExcepcionNegocio("El proceso ha expirado. Inicia uno nuevo.");
        }

        if (cambioTelefono.getCompletado()) {
            throw new ExcepcionNegocio("Este proceso ya fue completado");
        }

        if (!cambioTelefono.getVerificadoAnterior()) {
            throw new ExcepcionNegocio("Primero debes verificar el código del teléfono anterior");
        }

        if (cambioTelefono.getVerificadoNuevo()) {
            throw new ExcepcionNegocio("El teléfono nuevo ya fue verificado");
        }

        if (cambioTelefono.getIntentosNuevo() >= cambioTelefono.getMaxIntentos()) {
            throw new ExcepcionNegocio(
                    "Has excedido el número máximo de intentos. Inicia un nuevo proceso."
            );
        }

        cambioTelefono.setIntentosNuevo((short) (cambioTelefono.getIntentosNuevo() + 1));

        if (!cambioTelefono.getPinNuevo().equals(peticion.getPinNuevo())) {
            cambioTelefonoRepositorio.save(cambioTelefono);

            int intentosRestantes = cambioTelefono.getMaxIntentos() - cambioTelefono.getIntentosNuevo();
            throw new ExcepcionValidacion(
                    "Código incorrecto. Te quedan " + intentosRestantes + " intento(s)."
            );
        }

        cambioTelefono.setVerificadoNuevo(true);
        cambioTelefono.setCompletado(true);
        cambioTelefonoRepositorio.save(cambioTelefono);

        Acceso acceso = accesoRepositorio.findById(cambioTelefono.getIdUsuario())
                .orElseThrow(() -> new ExcepcionNegocio("Usuario no encontrado"));

        acceso.setTelefonoAcceso(cambioTelefono.getTelefonoNuevo());
        accesoRepositorio.save(acceso);

        return new VerificarPinNuevoDtoRespuesta(
                cambioTelefono.getIdCambioTelefono(),
                true,
                true,
                cambioTelefono.getTelefonoNuevo(),
                "Teléfono actualizado exitosamente"
        );
    }

    // Metodos privados

    private void validarFormatoTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new ExcepcionValidacion("El teléfono no puede estar vacío");
        }
        try {
            Long.parseLong(telefono);
        } catch (NumberFormatException e) {
            throw new ExcepcionValidacion("El teléfono debe contener solo números");
        }

        if (telefono.length() < 10 || telefono.length() > 15) {
            throw new ExcepcionValidacion("El teléfono debe tener entre 10 y 15 dígitos");
        }
    }

    private String generarPin() {
        int numero = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(numero);
    }

    private boolean enviarSms(String telefono, String mensaje) {
        try {
            MensajeDto smsMensajeObj = new MensajeDto();
            smsMensajeObj.setTo(telefono);
            smsMensajeObj.setText(mensaje);

            MensajeDtoPeticion peticion = new MensajeDtoPeticion();
            peticion.setMessages(List.of(smsMensajeObj));

            MensajeDtoRespuesta respuesta = mensajeServicio.enviarSms(peticion);
            return respuesta.exito();
        } catch (Exception e) {
            // Log del error
            System.err.println("Error al enviar SMS: " + e.getMessage());
            return false;
        }
    }

    private String enmascarrarTelefono(String telefono) {
        if (telefono == null || telefono.length() < 4) {
            return telefono;
        }

        int longitudVisible = 4;
        String parteVisible = telefono.substring(telefono.length() - longitudVisible);
        String asteriscos = "*".repeat(telefono.length() - longitudVisible);

        return asteriscos + parteVisible;
    }
}
