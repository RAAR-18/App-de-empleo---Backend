package com.procol.perfil.controlador;

import com.procol.perfil.servicio.ArchivoConsultarServicio;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/perfil/archivo")
public class ArchivoDescargarControlador {

    private final ArchivoConsultarServicio archivoConsultarServicio;

    public ArchivoDescargarControlador(ArchivoConsultarServicio archivoConsultarServicio) {
        this.archivoConsultarServicio = archivoConsultarServicio;
    }

    @GetMapping("/descargar/{nombrePrivado}")
    public ResponseEntity<Resource> descargarArchivo(@PathVariable String nombrePrivado) {
        Resource resource = archivoConsultarServicio.obtenerRecursoArchivo(nombrePrivado);
        String contentType = archivoConsultarServicio.determinarTipoContenido(nombrePrivado);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}