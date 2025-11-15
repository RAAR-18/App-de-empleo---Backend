package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.dto.RelUsuarioTalentoDTOEditar;
import com.procol.perfil.servicio.RelUsuarioTalentoEditarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoEditarControlador {

    private final RelUsuarioTalentoEditarServicio relUsuarioTalentoEditarServicio;

    public TalentoEditarControlador(RelUsuarioTalentoEditarServicio relUsuarioTalentoEditarServicio) {
        this.relUsuarioTalentoEditarServicio = relUsuarioTalentoEditarServicio;
    }

    @PutMapping("/editar-nivel")
    public ResponseEntity<?> editarNivel(@RequestBody RelUsuarioTalentoDTOEditar dto) {
        RelUsuarioTalentoDTO respuesta = relUsuarioTalentoEditarServicio.editarNivelDominio(dto);
        return RespuestaHttp.ok("Talento editado correctamente al usuario", respuesta);
    }
}
