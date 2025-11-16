package com.procol.perfil.servicio;

import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;
import com.procol.infraestructura.utilidad.archivo.RutaArchivo;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.dto.ArchivoDTOCrear;
import com.procol.perfil.entidad.Archivo;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.repositorio.ArchivoRepositorio;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import com.procol.perfil.utilidad.mapeador.ArchivoMapeador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service("perfil_ArchivoCrearServicio")
public class ArchivoCrearServicio extends OperacionCrudImple<Archivo, Integer> {

    private final ArchivoRepositorio archivoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final ArchivoMapeador archivoMapeador;
    private final GestorArchivoBuilder gestorArchivoBuilder;
    private final RutaArchivo rutaArchivo;

    public ArchivoCrearServicio(
            ArchivoRepositorio archivoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            ArchivoMapeador archivoMapeador,
            GestorArchivoBuilder gestorArchivoBuilder,
            RutaArchivo rutaArchivo,
            BusquedaServicio<Archivo, Integer> busquedaServicioArchivo,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario
    ) {
        super(busquedaServicioArchivo);
        this.archivoRepositorio = archivoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.archivoMapeador = archivoMapeador;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
        this.rutaArchivo = rutaArchivo;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
    }

    @Override
    protected JpaRepository<Archivo, Integer> getRepositorio() {
        return archivoRepositorio;
    }

    @Transactional
    public ArchivoDTO agregarArchivo(ArchivoDTOCrear dto) {
        Usuario objUsuario = busquedaServicioUsuario.PorId(usuarioRepositorio, dto.getIdUsuario());

        eliminarCVsAnteriores(dto.getIdUsuario(), dto.getGrupoArchivo());

        guardarArchivoFisico(dto);
        Archivo objNuevo = construirEntidadArchivo(objUsuario, dto);
        Archivo objArchivoFinal = agregar(objNuevo);

        return archivoMapeador.desdeEntidad(objArchivoFinal);
    }

    private void guardarArchivoFisico(ArchivoDTOCrear dto) {
        GestorArchivoInstancia instancia = gestorArchivoBuilder.crear(
                ConstTipoArchivo.DOCUMENTO,
                dto.getNombrePrivadoArchivo(),
                null,
                dto.getArchivo()
        );

        boolean exito = instancia.guardar();
        if (!exito) {
            throw new ExcepcionNegocio("No se pudo guardar el archivo físicamente");
        }
    }

    private Archivo construirEntidadArchivo(Usuario usuario, ArchivoDTOCrear dto) {
        Archivo archivo = new Archivo();
        archivo.setIdUsuario(usuario);
        archivo.setNombrePublicoArchivo(dto.getNombrePublicoArchivo());
        archivo.setNombrePrivadoArchivo(dto.getNombrePrivadoArchivo());
        archivo.setTipoArchivo(dto.getTipoArchivo());
        archivo.setTamanioArchivo(dto.getTamanioArchivo());
        archivo.setGrupoArchivo(dto.getGrupoArchivo());
        return archivo;
    }

    private void eliminarCVsAnteriores(Integer idUsuario, Integer grupoArchivo) {
        List<Archivo> archivosAnteriores = archivoRepositorio
                .findByIdUsuarioIdUsuarioAndGrupoArchivo(idUsuario, grupoArchivo);

        if (archivosAnteriores.isEmpty()) {
            return;
        }

        for (Archivo archivo : archivosAnteriores) {
            eliminarRegistroBD(archivo);
            eliminarArchivoDisco(archivo.getNombrePrivadoArchivo());
        }
    }

    private void eliminarRegistroBD(Archivo archivo) {
        try {
            archivoRepositorio.delete(archivo);
        } catch (Exception e) {
            String msg = "Error al eliminar registro BD del archivo: " + archivo.getIdArchivo();
            throw new ExcepcionNegocio(msg);
        }
    }

    private void eliminarArchivoDisco(String nombrePrivado) {
        String rutaArchivos = rutaArchivo.getDocumentos();

        if (rutaArchivos == null || rutaArchivos.isBlank()) {
            throw new ExcepcionNegocio("Ruta de archivos no configurada");
        }

        Path rutaCompleta = Paths.get(rutaArchivos).resolve(nombrePrivado);

        try {
            Files.deleteIfExists(rutaCompleta);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo eliminar el archivo: " + nombrePrivado);
        }
    }
}