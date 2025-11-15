package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.UsuarioDTO;
import com.procol.perfil.dto.DatosBasicosDTOActualizar;
import com.procol.perfil.servicio.UsuarioDatosBasicosActualizarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/usuario/datos-basicos")
public class UsuarioDatosBasicosActualizarControlador {

    private final UsuarioDatosBasicosActualizarServicio usuarioDatosBasicosActualizarServicio;

    public UsuarioDatosBasicosActualizarControlador(UsuarioDatosBasicosActualizarServicio usuarioDatosBasicosActualizarServicio) {
        this.usuarioDatosBasicosActualizarServicio = usuarioDatosBasicosActualizarServicio;
    }

    @PutMapping("/actualizar/{idEjecutor}")
    public ResponseEntity<?> actualizarUsuario(
            @PathVariable Integer idEjecutor,
            @RequestBody DatosBasicosDTOActualizar dto
    ) {
        UsuarioDTO usuarioActualizado = usuarioDatosBasicosActualizarServicio.actualizarUsuario(idEjecutor, dto);

        return RespuestaHttp.ok("Usuario actualizado",  usuarioActualizado);
    }
}
