package com.procol.comun.controlador;

import com.procol.comun.dto.PerfilInfoDtoActualizar;
import com.procol.comun.servicio.PerfilInfoActualizarServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comun/perfil")
public class PerfilInfoActualizarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final PerfilInfoActualizarServicio perfilInfoActualizarServicio;

    public PerfilInfoActualizarControlador(
            PerfilInfoActualizarServicio perfilInfoActualizarServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.perfilInfoActualizarServicio = perfilInfoActualizarServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PutMapping("/info-usuario")
    public ResponseEntity<?> actualizarInfoBasicaUsuario(@RequestBody PerfilInfoDtoActualizar dto) {
        Integer idUsuarioSesion = contextoSeguridad.getIdUsuario();
        dto.setIdUsuario(idUsuarioSesion);

        if (perfilInfoActualizarServicio.actualizarInfoUsuario(dto)) {
            return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ACTUALIZADO, true);
        } else {
            return RespuestaHttp.personalizado(HttpStatus.BAD_REQUEST, ConstMensajeRespuesta.REGISTRO_ACTUALIZADO_ERROR, false);
        }
    }

}
