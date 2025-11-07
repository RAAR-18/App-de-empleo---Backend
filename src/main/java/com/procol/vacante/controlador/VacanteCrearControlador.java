package com.procol.vacante.controlador;

import com.procol.vacante.dto.VacanteDTOCrear;
import com.procol.vacante.dto.VacanteDTORespuesta;
import com.procol.vacante.dto.AnuncioDTOMetadato;
import com.procol.vacante.servicio.VacanteCrearServicio;

import com.procol.infraestructura.dto.ArchivoDtoMetadato;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.ArchivoRegla;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/vacancy")
public class VacanteCrearControlador {

    private static final long TAMANIO_MAXIMO_BYTES = 9 * 1024 * 1024;
    private static final List<String> TIPOS_ARCHIVO_PERMITIDOS = List.of(
            "image/jpeg", "image/png", "image/jpg"
    );

    private final ContextoSeguridad contextoSeguridad;
    private final VacanteCrearServicio vacanteCrearServicio;

    public VacanteCrearControlador(
            VacanteCrearServicio vacanteCrearServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.vacanteCrearServicio = vacanteCrearServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PostMapping("/add")
    public ResponseEntity<?> nuevaVacante(
            @ModelAttribute VacanteDTOCrear dto
    ) {
        int idEjecutor = contextoSeguridad.getIdUsuario();

        MultipartFile archivo = dto.getArchivo();
        ArchivoRegla.verificar(archivo, TIPOS_ARCHIVO_PERMITIDOS, TAMANIO_MAXIMO_BYTES);

        ArchivoDtoMetadato metadato = ArchivoRegla.extraerMetadatos(archivo);
        AnuncioDTOMetadato dtoArchivo = new AnuncioDTOMetadato(
                metadato.getNombrePublico(),
                metadato.getNombrePrivado(),
                metadato.getTipoMime(),
                metadato.getTamanio()
        );

        VacanteDTORespuesta respuesta = vacanteCrearServicio.crearVacante(idEjecutor, dto, dtoArchivo);

        return RespuestaHttp.ok("Vacante agregada", respuesta);
    }
}
