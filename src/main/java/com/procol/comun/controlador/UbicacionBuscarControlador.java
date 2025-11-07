package com.procol.comun.controlador;

import java.util.List;

import com.procol.comun.dto.UbicacionDTOAutocompletado;
import com.procol.comun.servicio.UbicacionBuscarServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comun/ubicacion")
public class UbicacionBuscarControlador {

    private final UbicacionBuscarServicio ubicacionBuscarServicio;

    public UbicacionBuscarControlador(
            UbicacionBuscarServicio ubicacionBuscarServicio
    ) {
        this.ubicacionBuscarServicio = ubicacionBuscarServicio;
    }

    @GetMapping("/autocompletado")
    public ResponseEntity<?> obtenerUbicaciones(@RequestParam("texto") String textoBusqueda) {
        if (textoBusqueda == null || textoBusqueda.trim().length() < 3) {
            throw new ExcepcionValidacion("Mínimo 3 caracteres para buscar ubicaciones");
        }
        List<UbicacionDTOAutocompletado> ubicaciones = ubicacionBuscarServicio.obtenerUbicacionesPorTexto(textoBusqueda.trim());
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, ubicaciones);
    }

}
