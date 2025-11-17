package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.ImagenDTO;
import com.procol.perfil.servicio.ImagenConsultarServicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("perfil_ImagenConsultarControlador")
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/imagen")
public class ImagenConsultarControlador {

    private final ImagenConsultarServicio imagenConsultarServicio;

    @Value("${servidor.url.base:http://localhost:3210}")
    private String urlBase;

    public ImagenConsultarControlador(ImagenConsultarServicio imagenConsultarServicio) {
        this.imagenConsultarServicio = imagenConsultarServicio;
    }

    @GetMapping("/foto-perfil/{idUsuario}")
    public ResponseEntity<?> obtenerFotoPerfil(@PathVariable Integer idUsuario) {
        ImagenDTO imagen = imagenConsultarServicio.obtenerFotoPerfilUsuario(idUsuario);

        if (imagen != null) {
            String urlImagen = urlBase + "/imagenes/" + imagen.getNombrePrivadoImagen();
            return RespuestaHttp.ok("Foto de perfil obtenida",
                    Map.of("urlImagen", urlImagen)
            );
        }

        return RespuestaHttp.ok("No tiene foto de perfil", null);
    }
}