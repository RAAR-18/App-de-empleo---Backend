package com.procol.correo.controlador;

import com.procol.correo.Api.VerificacionCorreoServicio;
import com.procol.correo.dto.EnviarCodigoVerficiacion;
import com.procol.correo.dto.VerificacionCorreo;
import com.procol.correo.dto.VerificarCodigo;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/verificacion-correo")
public class VerificacionCorreoControlador {

    private final VerificacionCorreoServicio verificacionCorreoServicio;

    public VerificacionCorreoControlador(VerificacionCorreoServicio verificacionCorreoServicio) {
        this.verificacionCorreoServicio = verificacionCorreoServicio;
    }

    @PostMapping("/enviar-codigo")
    public ResponseEntity<?> enviarCodigoVerificacion(
            @Valid @RequestBody EnviarCodigoVerficiacion request
    ) {
        VerificacionCorreo resultado = verificacionCorreoServicio.enviarCodigoVerificacion(request);

        if (resultado.exito()) {
            return RespuestaHttp.ok(resultado.mensaje(), resultado);
        } else {
            return RespuestaHttp.error(resultado.mensaje(), 400);
        }
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificarCodigo(
            @Valid @RequestBody VerificarCodigo request
    ) {
        VerificacionCorreo resultado = verificacionCorreoServicio.verificarCodigo(request);

        if (resultado.exito()) {
            return RespuestaHttp.ok(resultado.mensaje(), resultado);
        } else {
            return RespuestaHttp.error(resultado.mensaje(), 400);
        }
    }

    @GetMapping("/estado")
    public ResponseEntity<?> obtenerEstadoVerificacion(@RequestParam String correo) {
        VerificacionCorreo resultado = verificacionCorreoServicio.obtenerEstadoVerificacion(correo);

        if (resultado.exito()) {
            return RespuestaHttp.ok(resultado.mensaje(), resultado);
        } else {
            return RespuestaHttp.error(resultado.mensaje(), 404);
        }
    }
}
