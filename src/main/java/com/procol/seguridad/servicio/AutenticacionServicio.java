package com.procol.seguridad.servicio;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import com.procol.infraestructura.constante.ConstEstadoRegistro;
import com.procol.infraestructura.excepcion.ExcepcionSeguridad;

import com.procol.seguridad.entidad.Acceso;
import com.procol.seguridad.entidad.Ingreso;
import com.procol.seguridad.dto.AccesoDetalleDTO;
import com.procol.seguridad.entidad.UsuarioRol;
import com.procol.seguridad.repositorio.AccesoRepositorio;
import com.procol.seguridad.repositorio.IngresoRepositorio;
import com.procol.seguridad.repositorio.UsuarioRolRepositorio;
import com.procol.empresa.repositorio.RelUsuarioEmpresaRepositorio;
import com.procol.empresa.entidad.RelUsuarioEmpresa;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AutenticacionServicio implements UserDetailsService {

    private final AccesoRepositorio accesoRepositorio;
    private final IngresoRepositorio ingresoRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;
    private final RelUsuarioEmpresaRepositorio relUsuarioEmpresaRepositorio;

    public record DatosAcceso(
            Integer idUsuario,
            List<String> roles,
            String nombres,
            String apellidos,
            String uuid,
            Integer empresaId
            ) {

    }

    public AutenticacionServicio(
            AccesoRepositorio accesoRepositorio,
            IngresoRepositorio ingresoRepositorio,
            UsuarioRolRepositorio usuarioRolRepositorio,
            RelUsuarioEmpresaRepositorio relUsuarioEmpresaRepositorio
    ) {
        this.accesoRepositorio = accesoRepositorio;
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.relUsuarioEmpresaRepositorio = relUsuarioEmpresaRepositorio;
    }

    private Acceso obtenerAccesoPorCorreo(String correo) {
        return accesoRepositorio.findByCorreoAcceso(correo)
                .orElseThrow(() -> new ExcepcionSeguridad("El usuario no fue encontrado"));
    }

    private List<UsuarioRol> obtenerTodosLosRolesDelUsuario(Integer idUsuario) {
        return usuarioRolRepositorio.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Acceso acceso = accesoRepositorio.findByCorreoAcceso(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        List<UsuarioRol> roles = obtenerTodosLosRolesDelUsuario(acceso.getIdUsuario());

        List<SimpleGrantedAuthority> autoridades = roles.stream()
                .filter(ur -> ur.getRol().getEstadoRol() == ConstEstadoRegistro.ACTIVO)
                .map(ur -> new SimpleGrantedAuthority("ROLE_" + ur.getRol().getNombreRol()))
                .toList();

        return new AccesoDetalleDTO(acceso, autoridades);
    }

    public DatosAcceso obtenerDatosAcceso(String correo) {
        Acceso acceso = obtenerAccesoPorCorreo(correo);
        List<UsuarioRol> roles = obtenerTodosLosRolesDelUsuario(acceso.getIdUsuario());

        boolean todosActivos = roles.stream()
                .allMatch(ur -> ur.getRol().getEstadoRol() == ConstEstadoRegistro.ACTIVO);

        if (!todosActivos) {
            throw new ExcepcionSeguridad("El usuario tiene roles inactivos");
        }

        List<String> nombresRoles = roles.stream()
                .map(ur -> ur.getRol().getNombreRol()).toList();

        String nombres = acceso.getUsuario().getNombresUsuario();
        String apellidos = acceso.getUsuario().getApellidosUsuario();
        String uuid = actualizarUuidAcceso(acceso).toString();

        // Obtener empresaId si el usuario está relacionado con alguna empresa
        Integer empresaId = relUsuarioEmpresaRepositorio
                .findFirstByRelUsuarioEmpresaPK_IdUsuario(acceso.getIdUsuario())
                .map(relacion -> relacion.getRelUsuarioEmpresaPK().getIdEmpresa())
                .orElse(null);

        registrarIngreso(acceso);

        return new DatosAcceso(acceso.getIdUsuario(), nombresRoles, nombres, apellidos, uuid, empresaId);
    }

    @Transactional
    private UUID actualizarUuidAcceso(Acceso acceso) {
        UUID nuevoUuid = UUID.randomUUID();
        acceso.setUuidAcceso(nuevoUuid.toString());
        accesoRepositorio.save(acceso);
        return nuevoUuid;
    }

    @Transactional
    private void registrarIngreso(Acceso acceso) {
        Ingreso ingreso = new Ingreso();
        ingreso.setIdUsuario(acceso);
        ingreso.setFechaIngreso(LocalDateTime.now());
        ingresoRepositorio.save(ingreso);
    }

}
