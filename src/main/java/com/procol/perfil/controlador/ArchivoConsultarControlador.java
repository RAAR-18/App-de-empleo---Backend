package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.servicio.ArchivoConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/perfil/archivo")
public class ArchivoConsultarControlador {

    private final ArchivoConsultarServicio archivoConsultarServicio;

    public ArchivoConsultarControlador(ArchivoConsultarServicio archivoConsultarServicio) {
        this.archivoConsultarServicio = archivoConsultarServicio;
    }

    @GetMapping("/cv/{idUsuario}")
    public ResponseEntity<?> obtenerCV(@PathVariable Integer idUsuario) {
        ArchivoDTO archivo = archivoConsultarServicio.obtenerCVUsuario(idUsuario);

        if (archivo == null) {
            return RespuestaHttp.sinContenido("No se encontró CV para el usuario");
        }

        return RespuestaHttp.ok("CV encontrado", archivo);
    }
}