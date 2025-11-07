package com.procol.usuario.controlador;

import com.procol.usuario.dto.UsuarioDTO;
import com.procol.usuario.dto.UsuarioDTOCrear;
import com.procol.usuario.servicio.UsuarioCrearServicio;

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
@RequestMapping("/usuario")
public class UsuarioCrearControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final UsuarioCrearServicio usuarioCrearServicio;

    public UsuarioCrearControlador(
            ContextoSeguridad contextoSeguridad,
            UsuarioCrearServicio usuarioCrearServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.usuarioCrearServicio = usuarioCrearServicio;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> nuevoUsuario(@RequestBody UsuarioDTOCrear dto) {
        int idEjecutor = contextoSeguridad.getIdUsuario();

        UsuarioDTO respuesta = usuarioCrearServicio.crearUsuario(idEjecutor, dto);
        return RespuestaHttp.ok("Usuario creado", respuesta);
    }

}
