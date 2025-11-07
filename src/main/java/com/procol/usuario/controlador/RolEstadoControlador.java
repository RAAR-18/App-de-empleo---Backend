package com.procol.usuario.controlador;

import com.procol.usuario.dto.RolDTO;
import com.procol.usuario.servicio.RolEstadoServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/rol")
public class RolEstadoControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final RolEstadoServicio rolEstadoServicio;

    public RolEstadoControlador(
            RolEstadoServicio rolEstadoServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.rolEstadoServicio = rolEstadoServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PatchMapping("/estado/{codTipoRol}/{nuevoEstado}")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable("codTipoRol") Integer idRol,
            @PathVariable("nuevoEstado") Short nuevoEstado
    ) {
        Verificar.estadoRegistro(nuevoEstado);
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        RolDTO objActualizado = rolEstadoServicio.cambiarEstadoRol(idEjecutor, idRol, nuevoEstado);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ACTUALIZADO, objActualizado);
    }

}
