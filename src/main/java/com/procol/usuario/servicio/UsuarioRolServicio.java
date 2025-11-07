package com.procol.usuario.servicio;

import java.util.List;
import java.util.Set;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.procol.usuario.entidad.Rol;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.entidad.UsuarioRol;
import com.procol.usuario.dto.RolDTOSinEstado;
import com.procol.usuario.entidad.pk.UsuarioRolPK;
import com.procol.usuario.dto.UsuarioRolDTORespuesta;
import com.procol.usuario.dto.UsuarioRolDTOActualizacion;
import com.procol.usuario.repositorio.UsuarioRepositorio;
import com.procol.usuario.repositorio.UsuarioRolRepositorio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioRolServicio extends OperacionCrudImple<UsuarioRol, UsuarioRolPK> {

    // Repositorio obligatorio
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;

    public UsuarioRolServicio(
            AuditoriaServicio auditoriaServicio,
            UsuarioRolRepositorio usuarioRolRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            BusquedaServicio<UsuarioRol, UsuarioRolPK> busquedaServicioUsuarioRol,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario
    ) {
        super(busquedaServicioUsuarioRol);
        this.auditoriaServicio = auditoriaServicio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
    }

    @Override
    protected JpaRepository<UsuarioRol, UsuarioRolPK> getRepositorio() {
        return usuarioRolRepositorio;
    }

    @Transactional
    public UsuarioRolDTORespuesta actualizarRolUsuario(Integer idEjecutor, UsuarioRolDTOActualizacion dto) {
        Integer idUsuario = dto.getIdUsuario();
        Set<Integer> rolesNuevos = Optional.ofNullable(dto.getIdRol()).orElse(Set.of());

        busquedaServicioUsuario.PorId(usuarioRepositorio, idUsuario);
        usuarioRolRepositorio.borrarRolesDeUnUsuario(idUsuario);

        String detalleAuditoria = "Usuario quedó sin roles";
        List<RolDTOSinEstado> listaRolesAsignados = List.of();

        if (!rolesNuevos.isEmpty()) {
            // Versión resumida
            // List<UsuarioRol> nuevosUsuarioRol = rolesNuevos.stream()
            //       .map(idRol -> construirPK(idRol, idUsuario)).collect(Collectors.toList());

            List<UsuarioRol> nuevosUsuarioRol = new ArrayList<>();
            for (Integer idRol : rolesNuevos) {
                nuevosUsuarioRol.add(construirPK(idRol, idUsuario));
            }

            agregarTodos(nuevosUsuarioRol);
            listaRolesAsignados = crearResumenRolesAsignados(idUsuario);
            detalleAuditoria = armarDetalleAuditoria(idUsuario, listaRolesAsignados);
        }
        registroAuditoria(idEjecutor, UsuarioRol.class.getName(), idUsuario, detalleAuditoria);

        return new UsuarioRolDTORespuesta(idUsuario, listaRolesAsignados);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private String armarDetalleAuditoria(
            Integer idUsuario, List<RolDTOSinEstado> listaRoles
    ) {
        Integer cantRoles = listaRoles.size();
        List<String> detalle = listaRoles.stream()
                .map(dto -> dto.getNombreRol() + " (" + dto.getIdRol() + ")")
                .collect(Collectors.toList());

        String txtDetalle = String.join(", ", detalle);
        return "Al usuario " + idUsuario + " se le asignaron " + cantRoles + " roles: " + txtDetalle;
    }

    private UsuarioRol construirPK(Integer idRol, Integer idUsuario) {
        UsuarioRolPK PK = new UsuarioRolPK(idRol, idUsuario);
        UsuarioRol usuRol = new UsuarioRol();
        usuRol.setUsuarioRolPK(PK);
        return usuRol;
    }

    private List<RolDTOSinEstado> crearResumenRolesAsignados(Integer idUsuario) {
        List<Rol> rolesAsignados = usuarioRolRepositorio.rolesPorUsuario(idUsuario);
        List<RolDTOSinEstado> lista = new ArrayList<>();
        for (Rol objRol : rolesAsignados) {
            lista.add(new RolDTOSinEstado(objRol.getIdRol(), objRol.getNombreRol()));
        }
        return lista;
    }

    private void registroAuditoria(
            Integer idEjecutor, String nombreEntidad, Integer usuarioAfectado, String detalle
    ) {
        auditoriaServicio.registrar(
                idEjecutor,
                nombreEntidad,
                usuarioAfectado,
                TipoCambio.CREAR,
                detalle
        );
    }
    // *************************************************************************

}
