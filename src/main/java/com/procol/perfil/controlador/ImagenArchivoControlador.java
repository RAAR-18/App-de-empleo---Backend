package com.procol.perfil.controlador;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/imagenes")
public class ImagenArchivoControlador {

    @Value("${archivo.ruta.imagenes:C:/procol/imagenes/}")
    private String rutaImagenes;

    @GetMapping("/{nombreArchivo:.+}")
    public ResponseEntity<Resource> servirImagen(@PathVariable String nombreArchivo) {
        try {
            // Resolver ruta del archivo
            Path rutaArchivo = Paths.get(rutaImagenes).resolve(nombreArchivo).normalize();
            Resource recurso = new UrlResource(rutaArchivo.toUri());

            // Verificar que el archivo existe y se puede leer
            if (!recurso.exists() || !recurso.isReadable()) {
                System.err.println("Archivo no encontrado o no legible: " + rutaArchivo);
                return ResponseEntity.notFound().build();
            }

            String contentType = "application/octet-stream";
            try {
                contentType = Files.probeContentType(rutaArchivo);
                if (contentType == null) {
                    if (nombreArchivo.endsWith(".png")) {
                        contentType = "image/png";
                    } else if (nombreArchivo.endsWith(".jpg") || nombreArchivo.endsWith(".jpeg")) {
                        contentType = "image/jpeg";
                    } else if (nombreArchivo.endsWith(".webp")) {
                        contentType = "image/webp";
                    } else if (nombreArchivo.endsWith(".gif")) {
                        contentType = "image/gif";
                    }
                }
            } catch (Exception e) {
                System.err.println("No se pudo determinar content type: " + e.getMessage());
            }

            System.out.println("Sirviendo imagen: " + nombreArchivo + " (" + contentType + ")");

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recurso.getFilename() + "\"")
                    .header(HttpHeaders.CACHE_CONTROL, "max-age=3600")
                    .body(recurso);

        } catch (Exception e) {
            System.err.println("Error al servir imagen: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}