package com.procol.usuario.controlador;

import com.procol.usuario.servicio.UsuarioRolServicio;
import com.procol.usuario.dto.UsuarioRolDTOActualizacion;

import com.procol.usuario.dto.UsuarioRolDTORespuesta;
import com.procol.seguridad.utilidad.ContextoSeguridad;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/rol")
public class UsuarioRolControlador {

    private final ContextoSeguridad contexto;
    private final UsuarioRolServicio usuarioRolServicio;

    public UsuarioRolControlador(
            ContextoSeguridad contexto,
            UsuarioRolServicio usuarioRolServicio
    ) {
        this.contexto = contexto;
        this.usuarioRolServicio = usuarioRolServicio;
    }

    @PostMapping("/usuroles")
    public ResponseEntity<?> asignarRolesAUsuario(@RequestBody UsuarioRolDTOActualizacion dto) {
        int idEjecutor = contexto.getIdUsuario();

        UsuarioRolDTORespuesta respuesta = usuarioRolServicio.
                actualizarRolUsuario(idEjecutor, dto);
        return RespuestaHttp.ok("Roles asignados correctamente", respuesta);
    }

}
