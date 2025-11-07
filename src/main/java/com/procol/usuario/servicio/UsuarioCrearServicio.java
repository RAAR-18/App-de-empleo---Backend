package com.procol.usuario.servicio;

import java.util.Optional;

import com.procol.usuario.dto.UsuarioDTO;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.entidad.Ubicacion;
import com.procol.usuario.dto.UsuarioDTOCrear;
import com.procol.usuario.repositorio.UsuarioRepositorio;
import com.procol.usuario.repositorio.UbicacionRepositorio;
import com.procol.usuario.utilidad.mapeador.UsuarioMapeador;
import com.procol.usuario.servicio.busqueda.UsuarioPorDocumentoServicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioCrearServicio extends OperacionCrudImple<Usuario, Integer> {

    // Repositorio obligatorio
    private final UsuarioRepositorio usuarioRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final UbicacionRepositorio ubicacionRepositorio;
    private final BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion;
    
    private final UsuarioMapeador usuarioMapeador;
    private final UsuarioPorDocumentoServicio usuarioPorDocumentoServicio;
    
    public UsuarioCrearServicio(
            UsuarioRepositorio usuarioRepositorio,
            AuditoriaServicio auditoriaServicio,
            UsuarioMapeador usuarioMapeador,
            UbicacionRepositorio ubicacionRepositorio,
            UsuarioPorDocumentoServicio usuarioPorDocumentoServicio,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion
    ) {
        super(busquedaServicioUsuario);
        this.auditoriaServicio = auditoriaServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.ubicacionRepositorio = ubicacionRepositorio;
        this.usuarioPorDocumentoServicio = usuarioPorDocumentoServicio;
        this.busquedaServicioUbicacion = busquedaServicioUbicacion;
        this.usuarioMapeador = usuarioMapeador;
    }
    
    @Override
    protected JpaRepository<Usuario, Integer> getRepositorio() {
        return usuarioRepositorio;
    }
    
    @Transactional
    public UsuarioDTO crearUsuario(Integer idEjecutor, UsuarioDTOCrear dto) {
        Optional<Usuario> objTmp = usuarioPorDocumentoServicio.buscar(dto.getDocumentoUsuario());
        
        verificarExistenciaDocumento(objTmp);
        Ubicacion objUbicacion = busquedaServicioUbicacion.PorId(ubicacionRepositorio, dto.getIdUbicacion());
        
        Usuario objUsuario = new Usuario();
        objUsuario.setIdUbicacion(objUbicacion);
        objUsuario.setTipoDocumentoUsuario(dto.getTipoDocumentoUsuario());
        objUsuario.setDocumentoUsuario(dto.getDocumentoUsuario());
        objUsuario.setNombresUsuario(dto.getNombresUsuario());
        objUsuario.setApellidosUsuario(dto.getApellidosUsuario());
        objUsuario.setEstadoUsuario(dto.getEstadoUsuario());
        
        Usuario objUsuarioNuevo = agregar(objUsuario);
        registroAuditoria(idEjecutor, objUsuarioNuevo);
        
        return usuarioMapeador.desdeEntidad(objUsuarioNuevo);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void verificarExistenciaDocumento(Optional<Usuario> documentoExiste) {
        if (documentoExiste.isPresent()) {
            String msg = "Ya existe el documento: " + documentoExiste.get().getDocumentoUsuario();
            throw new ExcepcionNegocio(msg);
        }
    }
    
    private void registroAuditoria(Integer idEjecutor, Usuario obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdUsuario(),
                TipoCambio.CREAR,
                obj.toString()
        );
    }
    // *************************************************************************

}
