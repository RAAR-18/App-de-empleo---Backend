package com.procol.perfil.controlador;

import com.procol.infraestructura.constante.ConstImagenCategoria;
import com.procol.infraestructura.constante.ConstImagenCuenta;
import com.procol.infraestructura.dto.ArchivoDtoMetadato;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.ArchivoRegla;
import com.procol.perfil.dto.ImagenDTO;
import com.procol.perfil.dto.ImagenDTOCrear;
import com.procol.perfil.servicio.ImagenCrearServicio;
import com.procol.perfil.servicio.ImagenPortafolioConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController("perfil_ImagenPortafolioCrearControlador")
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/perfil/imagen-portafolio")
public class ImagenPortafolioCrearControlador {

    private static final long TAMANIO_MAXIMO_BYTES = 9 * 1024 * 1024;
    private static final List<String> TIPO_ARCHIVO_PERMITIDO = List.of(
            "image/jpeg", "image/png", "image/jpg"
    );
    private static final int LIMITE_IMAGENES_PORTAFOLIO = 5;

    private final ImagenCrearServicio imagenCrearServicio;
    private final ImagenPortafolioConsultarServicio consultarServicio;

    public ImagenPortafolioCrearControlador(
            ImagenCrearServicio imagenCrearServicio,
            ImagenPortafolioConsultarServicio consultarServicio
    ) {
        this.imagenCrearServicio = imagenCrearServicio;
        this.consultarServicio = consultarServicio;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> nuevaImagen(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("idUsuario") Integer idUsuario,
            @RequestParam(value = "nombreProyecto", required = false, defaultValue = "Mi Proyecto") String nombreProyecto
    ) {
        // Verificar límite de imágenes
        long cantidadActual = consultarServicio.contarImagenesPortafolio(idUsuario);
        if (cantidadActual >= LIMITE_IMAGENES_PORTAFOLIO) {
            throw new ExcepcionNegocio(
                    "Has alcanzado el límite máximo de " + LIMITE_IMAGENES_PORTAFOLIO +
                            " imágenes en tu portafolio. Elimina alguna para agregar una nueva."
            );
        }

        ArchivoRegla.verificar(archivo, TIPO_ARCHIVO_PERMITIDO, TAMANIO_MAXIMO_BYTES);
        ArchivoDtoMetadato metadato = ArchivoRegla.extraerMetadatos(archivo);

        ImagenDTOCrear dto = new ImagenDTOCrear();
        dto.setArchivo(archivo);
        dto.setIdUsuario(idUsuario);
        dto.setNombrePublicoImagen(nombreProyecto);
        dto.setNombrePrivadoImagen(idUsuario + "_" + metadato.getNombrePrivado());
        dto.setTipoImagen(metadato.getTipoMime());
        dto.setTamanioImagen(metadato.getTamanio());
        dto.setFavoritaImagen(ConstImagenCuenta.NO_FAVORITA);
        dto.setCategoria(ConstImagenCategoria.PORTAFOLIO);

        ImagenDTO respuesta = imagenCrearServicio.agregarImagen(dto);
        return RespuestaHttp.ok(
                "Imagen agregada al portafolio. " +
                        (LIMITE_IMAGENES_PORTAFOLIO - (cantidadActual + 1)) + " espacios restantes.",
                respuesta
        );
    }
}