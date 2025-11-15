package com.procol.perfil.servicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.archivo.RutaArchivo;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.entidad.Archivo;
import com.procol.perfil.repositorio.ArchivoRepositorio;
import com.procol.perfil.utilidad.mapeador.ArchivoMapeador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service("perfil_ArchivoEliminarServicio")
public class ArchivoEliminarServicio extends OperacionCrudImple<Archivo, Integer> {

    private final ArchivoRepositorio archivoRepositorio;
    private final ArchivoMapeador archivoMapeador;
    private final RutaArchivo  rutaArchivo;

    public ArchivoEliminarServicio(
            ArchivoRepositorio archivoRepositorio,
            ArchivoMapeador archivoMapeador,
            RutaArchivo rutaArchivo,
            BusquedaServicio<Archivo, Integer> busquedaServicioArchivo
            ) {
        super(busquedaServicioArchivo);
        this.archivoRepositorio = archivoRepositorio;
        this.archivoMapeador = archivoMapeador;
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    protected JpaRepository<Archivo, Integer> getRepositorio() {
        return archivoRepositorio;
    }

    public ArchivoDTO eliminarArchivo(Integer idArchivo) {
        Archivo objAchivo = buscarPorId(idArchivo);
        eliminarRegistroBD(idArchivo);
        eliminarArchivoDisco(objAchivo.getNombrePrivadoArchivo());
        return archivoMapeador.desdeEntidad(objAchivo);
    }

    private void eliminarRegistroBD(Integer idArchivo) {
        boolean resultado = eliminar(idArchivo);
        if (!resultado) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR + idArchivo;
            throw new ExcepcionNegocio(msg);
        }
    }

    private void eliminarArchivoDisco(String nombrePrivado) {
        String rutaArchivos = rutaArchivo.getDocumentos();

        if (rutaArchivos == null || rutaArchivos.isBlank()) {
            throw new ExcepcionNegocio("Ruta de archivos no confiurada");
        }

        Path rutaCompleta = Paths.get(rutaArchivos).resolve(nombrePrivado);

        try {
            Files.deleteIfExists(rutaCompleta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el archivo: " + nombrePrivado);
        }
    }
}
