package com.procol.perfil.servicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.perfil.dto.UsuarioDTO;
import com.procol.auditoria.servicio.AuditoriaServicio;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.perfil.dto.DatosBasicosDTOActualizar;
import com.procol.perfil.entidad.Ubicacion;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.repositorio.UbicacionRepositorio;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import com.procol.perfil.utilidad.mapeador.UsuarioMapeador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("perfil_DatosBasicosActualizarServicio")
public class UsuarioDatosBasicosActualizarServicio extends OperacionCrudImple<Usuario, Integer> {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UbicacionRepositorio ubicacionRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion;
    private final UsuarioMapeador usuarioMapeador;
    private final AuditoriaServicio auditoriaServicio;

    public UsuarioDatosBasicosActualizarServicio(
            UsuarioRepositorio usuarioRepositorio,
            UbicacionRepositorio ubicacionRepositorio,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion,
            UsuarioMapeador usuarioMapeador,
            AuditoriaServicio auditoriaServicio
    ) {
        super(busquedaServicioUsuario);
        this.usuarioRepositorio = usuarioRepositorio;
        this.ubicacionRepositorio = ubicacionRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.busquedaServicioUbicacion = busquedaServicioUbicacion;
        this.usuarioMapeador = usuarioMapeador;
        this.auditoriaServicio = auditoriaServicio;
    }

    @Override
    protected JpaRepository<Usuario, Integer> getRepositorio() {
        return usuarioRepositorio;
    }

    @Transactional
    public UsuarioDTO actualizarUsuario(Integer idEjecutor, DatosBasicosDTOActualizar dto) {
        Usuario usuario = busquedaServicioUsuario.PorId(usuarioRepositorio, dto.getIdUsuario());

        if (usuario == null) {
            throw new ExcepcionNegocio("No se encontró el usuario con id: " + dto.getIdUsuario());
        }

        if (dto.getDocumentoUsuario() != null) {
            Optional<Usuario> existeDocumento = usuarioRepositorio.findByDocumentoUsuario(dto.getDocumentoUsuario());
            if (existeDocumento.isPresent() && !existeDocumento.get().getIdUsuario().equals(dto.getIdUsuario())) {
                throw new ExcepcionNegocio("El documento " + dto.getDocumentoUsuario() + " ya pertenece a otro usuario.");
            }
            usuario.setDocumentoUsuario(dto.getDocumentoUsuario());
        }

        if (dto.getNombresUsuario() != null && !dto.getNombresUsuario().isBlank()) {
            usuario.setNombresUsuario(dto.getNombresUsuario());
        }

        if (dto.getApellidosUsuario() != null && !dto.getApellidosUsuario().isBlank()) {
            usuario.setApellidosUsuario(dto.getApellidosUsuario());
        }

        if (dto.getProfesion() != null && !dto.getProfesion().isBlank()) {
            usuario.setProfesion(dto.getProfesion());
        }

        if (dto.getIdUbicacion() != null) {
            Ubicacion ubicacion = busquedaServicioUbicacion.PorId(ubicacionRepositorio, dto.getIdUbicacion());
            usuario.setIdUbicacion(ubicacion);
        }

        Usuario usuarioActualizado = actualizar(usuario);

       registroAuditoria(idEjecutor, usuarioActualizado);

        return usuarioMapeador.desdeEntidad(usuarioActualizado);
    }

    private void registroAuditoria(Integer idEjecutor, Usuario obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdUsuario(),
                TipoCambio.ACTUALIZAR,
                obj.toString()
        );
    }
}
