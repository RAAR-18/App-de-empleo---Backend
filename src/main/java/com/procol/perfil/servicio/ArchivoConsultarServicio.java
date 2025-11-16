package com.procol.perfil.servicio;

import com.procol.infraestructura.constante.ConstGrupoArchivo;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.archivo.RutaArchivo;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.entidad.Archivo;
import com.procol.perfil.repositorio.ArchivoRepositorio;
import com.procol.perfil.utilidad.mapeador.ArchivoMapeador;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service("perfil_ArchivoConsultarServicio")
public class ArchivoConsultarServicio extends OperacionCrudImple<Archivo, Integer> {

    private final ArchivoRepositorio archivoRepositorio;
    private final ArchivoMapeador archivoMapeador;
    private final RutaArchivo rutaArchivo;

    public ArchivoConsultarServicio(
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

    @Transactional(readOnly = true)
    public ArchivoDTO obtenerCVUsuario(Integer idUsuario) {
        System.out.println("=== DEBUG BACKEND ===");
        System.out.println("Buscando CV para usuario: " + idUsuario);
        System.out.println("Buscando grupo: " + ConstGrupoArchivo.HOJA_VIDA);

        Optional<Archivo> archivoOpt = archivoRepositorio
                .findCVMasReciente(idUsuario, ConstGrupoArchivo.HOJA_VIDA);

        System.out.println("¿Encontrado?: " + archivoOpt.isPresent());

        if (archivoOpt.isEmpty()) {
            System.out.println("No se encontró CV");
            return null;
        }

        Archivo archivo = archivoOpt.get();
        System.out.println("Archivo encontrado: " + archivo.getNombrePublicoArchivo());

        return archivoMapeador.desdeEntidad(archivo);
    }


    public Resource obtenerRecursoArchivo(String nombrePrivado) {
        try {
            String rutaArchivos = rutaArchivo.getDocumentos();

            if (rutaArchivos == null || rutaArchivos.isBlank()) {
                throw new ExcepcionNegocio("Ruta de archivos no configurada");
            }

            Path rutaCompleta = Paths.get(rutaArchivos)
                    .resolve(nombrePrivado)
                    .normalize();

            Resource resource = new UrlResource(rutaCompleta.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new ExcepcionNegocio("Archivo no encontrado: " + nombrePrivado);
            }

            return resource;
        } catch (ExcepcionNegocio e) {
            throw e;
        } catch (Exception e) {
            throw new ExcepcionNegocio("Error al obtener el archivo: " + e.getMessage());
        }
    }

    public String determinarTipoContenido(String nombreArchivo) {
        if (nombreArchivo == null) {
            return "application/octet-stream";
        }

        String nombreLower = nombreArchivo.toLowerCase();

        if (nombreLower.endsWith(".pdf")) {
            return "application/pdf";
        } else if (nombreLower.endsWith(".docx")) {
            return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
        } else if (nombreLower.endsWith(".doc")) {
            return "application/msword";
        } else if (nombreLower.endsWith(".xlsx")) {
            return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if (nombreLower.endsWith(".xls")) {
            return "application/vnd.ms-excel";
        } else if (nombreLower.endsWith(".jpg") || nombreLower.endsWith(".jpeg")) {
            return "image/jpeg";
        } else if (nombreLower.endsWith(".png")) {
            return "image/png";
        } else if (nombreLower.endsWith(".txt")) {
            return "text/plain";
        }

        return "application/octet-stream";
    }
}