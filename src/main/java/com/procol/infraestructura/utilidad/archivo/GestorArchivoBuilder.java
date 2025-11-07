package com.procol.infraestructura.utilidad.archivo;

import java.nio.file.Paths;

import com.procol.infraestructura.constante.ConstTipoArchivo;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GestorArchivoBuilder {

    private final RutaArchivo rutaArchivo;

    public GestorArchivoBuilder(RutaArchivo rutas) {
        this.rutaArchivo = rutas;
    }

    public GestorArchivoInstancia crear(
            ConstTipoArchivo tipo,
            String nombreArchivo,
            String respaldo,
            MultipartFile archivoMultipart
    ) {
        String rutaBase = switch (tipo) {
            case DOCUMENTO ->
                rutaArchivo.getDocumentos();
            case COMPRIMIDO ->
                rutaArchivo.getComprimidos();
            case IMAGEN_VACANTE ->
                rutaArchivo.getImagenesVacantes();
            default ->
                rutaArchivo.getImagenes();
        };

        return new GestorArchivoInstancia(
                Paths.get(rutaBase),
                nombreArchivo,
                respaldo,
                archivoMultipart
        );
    }
}
