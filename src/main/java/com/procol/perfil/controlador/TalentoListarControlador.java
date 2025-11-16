package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.servicio.RelUsuarioTalentoListarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoListarControlador {

    private final RelUsuarioTalentoListarServicio relUsuarioTalentoListarServicio;

    public TalentoListarControlador(RelUsuarioTalentoListarServicio relUsuarioTalentoListarServicio) {
        this.relUsuarioTalentoListarServicio = relUsuarioTalentoListarServicio;
    }

    @GetMapping("/listar/{idUsuario}")
    public ResponseEntity<?> listarTalentosPorUsuario(@PathVariable Integer idUsuario) {
        List<RelUsuarioTalentoDTO> talentos = relUsuarioTalentoListarServicio.listarTalentosPorUsuario(idUsuario);
        return RespuestaHttp.ok("Competencias obtenidas correctamente", talentos);
    }
}