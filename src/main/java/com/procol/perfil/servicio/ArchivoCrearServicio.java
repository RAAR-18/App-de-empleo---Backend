package com.procol.perfil.servicio;

import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;
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

@Service("perfil_ArchivoCrearServicio")
public class ArchivoCrearServicio extends OperacionCrudImple<Archivo, Integer> {

    private final ArchivoRepositorio archivoRepositorio;

    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final ArchivoMapeador archivoMapeador;

    private final GestorArchivoBuilder gestorArchivoBuilder;

    public ArchivoCrearServicio(
            ArchivoRepositorio archivoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            ArchivoMapeador archivoMapeador,
            GestorArchivoBuilder gestorArchivoBuilder,
            BusquedaServicio<Archivo, Integer> busquedaServicioArchivo,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario
    ) {
        super(busquedaServicioArchivo);
        this.archivoRepositorio = archivoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.archivoMapeador = archivoMapeador;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
    }

    @Override
    protected JpaRepository<Archivo, Integer> getRepositorio() {
        return archivoRepositorio;
    }

    @Transactional
    public ArchivoDTO agregarArchivo(ArchivoDTOCrear dto) {
        Usuario objUsuario = busquedaServicioUsuario.PorId(usuarioRepositorio, dto.getIdUsuario());

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

        Archivo objNuevo = new Archivo();
        objNuevo.setIdUsuario(objUsuario);
        objNuevo.setNombrePublicoArchivo(dto.getNombrePublicoArchivo());
        objNuevo.setNombrePrivadoArchivo(dto.getNombrePrivadoArchivo());
        objNuevo.setTipoArchivo(dto.getTipoArchivo());
        objNuevo.setTamanioArchivo(dto.getTamanioArchivo());

        Archivo objArchivoFinal = agregar(objNuevo);
        return archivoMapeador.desdeEntidad(objArchivoFinal);
    }
}
