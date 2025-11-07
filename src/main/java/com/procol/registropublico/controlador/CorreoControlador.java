package com.procol.registropublico.controlador;

import com.procol.mensajeria.api.CorreoServicio;
import com.procol.mensajeria.dto.correo.CorreoDtoPeticion;
import com.procol.mensajeria.dto.correo.CorreoDtoRespuesta;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class CorreoControlador {

    private final CorreoServicio correoServicio;

    public CorreoControlador(CorreoServicio correoServicio) {
        this.correoServicio = correoServicio;
    }

    @PostMapping("/send-welcome-email")
    public ResponseEntity<?> enviarBienvenida(@RequestBody CorreoDtoPeticion dto) {
        CorreoDtoRespuesta resultado = correoServicio.enviarCorreo(dto);

        if (resultado.exito()) {
            return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, resultado);

        } else {
            return RespuestaHttp.error(resultado.mensaje(), 500);
        }

    }

}
