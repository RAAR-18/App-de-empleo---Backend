package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.servicio.RelUsuarioTalentoEliminarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoEliminarControlador {

    private final RelUsuarioTalentoEliminarServicio relUsuarioTalentoEliminarServicio;

    public TalentoEliminarControlador(RelUsuarioTalentoEliminarServicio relUsuarioTalentoEliminarServicio) {
        this.relUsuarioTalentoEliminarServicio = relUsuarioTalentoEliminarServicio;
    }

    @DeleteMapping("/eliminar/{idUsuario}/{idTalento}")
    public ResponseEntity<?> eliminarTalento(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idTalento)
    {
        relUsuarioTalentoEliminarServicio.eliminarTalento(idUsuario, idTalento);
        return RespuestaHttp.ok("Talento eliminado correctamente", null);
    }
}
