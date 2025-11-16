package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.TalentoEstadisticasDTO;
import com.procol.perfil.servicio.TalentoEstadisticasServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoEstadisticasControlador {

    private final TalentoEstadisticasServicio talentoEstadisticasServicio;

    public TalentoEstadisticasControlador(TalentoEstadisticasServicio talentoEstadisticasServicio) {
        this.talentoEstadisticasServicio = talentoEstadisticasServicio;
    }

    @GetMapping("/estadisticas/{idUsuario}")
    public ResponseEntity<?> obtenerEstadisticas(@PathVariable Integer idUsuario) {
        TalentoEstadisticasDTO estadisticas = talentoEstadisticasServicio.obtenerEstadisticas(idUsuario);
        return RespuestaHttp.ok("Estadísticas obtenidas correctamente", estadisticas);
    }
}