package com.procol.vacante.controlador;

import com.procol.vacante.dto.VacanteDTOConsulta;
import com.procol.vacante.servicio.VacanteConsultarServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vacancy")
public class VacanteConsultarControlador {

    private final VacanteConsultarServicio vacanteConsultarServicio;

    public VacanteConsultarControlador(VacanteConsultarServicio vacanteConsultarServicio) {
        this.vacanteConsultarServicio = vacanteConsultarServicio;
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> consultarVacantes(
            @RequestParam(defaultValue = "v.id_vacante") String campoOrden,
            @RequestParam(defaultValue = "ASC") String orden) {

        List<VacanteDTOConsulta> resultado
                = vacanteConsultarServicio.consultaVacantes(campoOrden, orden);

        if (resultado.isEmpty()) {
            return RespuestaHttp.sinContenido("No hay vacantes registradas");
        }
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resultado);
    }
}
