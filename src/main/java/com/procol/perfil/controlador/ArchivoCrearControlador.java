package com.procol.perfil.controlador;

import com.procol.infraestructura.constante.ConstGrupoArchivo;
import com.procol.infraestructura.dto.ArchivoDtoMetadato;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.ArchivoRegla;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.dto.ArchivoDTOCrear;
import com.procol.perfil.servicio.ArchivoCrearServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController()
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/perfil/archivo")
public class ArchivoCrearControlador {

    private static final long TAMANIO_MAXIMO_BYTES = 10 * 1024 * 1024;
    private static final List<String> TIPO_ARCHIVO_PERMITIDO = List.of(
            "application/pdf",                // PDF
            "application/msword",             // DOC
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // DOCX
            "application/vnd.ms-excel",       // XLS
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // XLSX
            "image/jpeg",                     // JPG/JPEG (por si suben certificados escaneados)
            "image/png",                      // PNG
            "text/plain"                      // TXT
    );

    private final ArchivoCrearServicio archivoCrearServicio;

    public ArchivoCrearControlador(ArchivoCrearServicio archivoCrearServicio) {
        this.archivoCrearServicio = archivoCrearServicio;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> nuevoArchivo(
            @RequestParam("archivo") MultipartFile archivo,
            @RequestParam("idUsuario") Integer idUsuario
    ) {
        ArchivoRegla.verificar(archivo, TIPO_ARCHIVO_PERMITIDO, TAMANIO_MAXIMO_BYTES);

        ArchivoDtoMetadato metadato = ArchivoRegla.extraerMetadatos(archivo);

        ArchivoDTOCrear dto = new ArchivoDTOCrear();
        dto.setArchivo(archivo);
        dto.setIdUsuario(idUsuario);
        dto.setNombrePublicoArchivo(metadato.getNombrePublico());
        dto.setNombrePrivadoArchivo(idUsuario + "_" + metadato.getNombrePrivado());
        dto.setTipoArchivo(metadato.getTipoMime());
        dto.setTamanioArchivo(metadato.getTamanio());
        dto.setGrupoArchivo(ConstGrupoArchivo.HOJA_VIDA);


        ArchivoDTO respuesta = archivoCrearServicio.agregarArchivo(dto);

        return RespuestaHttp.ok("Archivo agregado exitosamente", respuesta);
    }
}
