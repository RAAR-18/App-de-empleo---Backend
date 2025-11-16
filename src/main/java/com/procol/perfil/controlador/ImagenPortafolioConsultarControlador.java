package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.ImagenDTO;
import com.procol.perfil.servicio.ImagenPortafolioConsultarServicio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("perfil_ImagenPortafolioConsultarControlador")
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/portafolio")
public class ImagenPortafolioConsultarControlador {

    private final ImagenPortafolioConsultarServicio consultarServicio;

    @Value("${servidor.url.base:http://localhost:3210}")
    private String urlBase;

    public ImagenPortafolioConsultarControlador(ImagenPortafolioConsultarServicio consultarServicio) {
        this.consultarServicio = consultarServicio;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> obtenerPortafolio(@PathVariable Integer idUsuario) {
        List<ImagenDTO> imagenes = consultarServicio.obtenerPortafolio(idUsuario);

        List<Map<String, Object>> imagenesConUrl = imagenes.stream()
                .map(img -> {
                    Map<String, Object> mapa = new HashMap<>();
                    mapa.put("idImagen", img.getIdImagen());
                    mapa.put("nombrePublico", img.getNombrePublicoImagen());
                    mapa.put("urlImagen", urlBase + "/imagenes/" + img.getNombrePrivadoImagen());
                    mapa.put("tipo", img.getTipoImagen());
                    return mapa;
                })
                .collect(Collectors.toList());

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("imagenes", imagenesConUrl);
        respuesta.put("totalProyectos", imagenes.size());
        respuesta.put("limiteMaximo", 5);

        return RespuestaHttp.ok("Portafolio obtenido", respuesta);
    }
}