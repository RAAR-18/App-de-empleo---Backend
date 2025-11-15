package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.PerfilDTO;
import com.procol.perfil.servicio.PerfilConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/consultar")
public class PerfilConsultarControlador {

    private final PerfilConsultarServicio perfilConsultarServicio;

    public PerfilConsultarControlador(PerfilConsultarServicio perfilConsultarServicio) {
        this.perfilConsultarServicio = perfilConsultarServicio;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> obtenerPerfil(@PathVariable Integer idUsuario) {
        PerfilDTO respuesta = perfilConsultarServicio.obtenerPerfil(idUsuario);
        return RespuestaHttp.ok("Perfil Usuario cargado",  respuesta);
    }
}
