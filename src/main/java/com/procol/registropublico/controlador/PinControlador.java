package com.procol.registropublico.controlador;

import com.procol.infraestructura.excepcion.ExcepcionValidacion;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.registropublico.dto.PinTemporalDtoPeticion;
import com.procol.registropublico.dto.PinTemporalDtoRespuesta;
import com.procol.registropublico.servicio.PinServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class PinControlador {

    private final PinServicio preRegistroServicio;

    public PinControlador(
            PinServicio preRegistroServicio
    ) {
        this.preRegistroServicio = preRegistroServicio;
    }

    @PostMapping("/pin")
    public ResponseEntity<?> obtenerPin(@RequestBody PinTemporalDtoPeticion dto) {
        try {
            Long.valueOf(dto.getTelefonoAcceso());
        } catch (NumberFormatException ex) {
            throw new ExcepcionValidacion("Teléfono incorrecto: " + dto.getTelefonoAcceso());
        }

        PinTemporalDtoRespuesta respuesta = preRegistroServicio.solicitarPin(dto.getTelefonoAcceso());

        return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, respuesta);
    }

}
