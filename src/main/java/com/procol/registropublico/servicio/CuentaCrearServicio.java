package com.procol.registropublico.servicio;

import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;

import com.procol.registropublico.entidad.Rol;
import com.procol.registropublico.entidad.Acceso;
import com.procol.registropublico.entidad.Usuario;
import com.procol.registropublico.entidad.Ingreso;
import com.procol.registropublico.entidad.Ubicacion;
import com.procol.registropublico.entidad.UsuarioRol;
import com.procol.registropublico.dto.CuentaDTOCrear;
import com.procol.infraestructura.constante.ConstRegistroCuenta;
import com.procol.registropublico.entidad.pk.UsuarioRolPK;
import com.procol.registropublico.repositorio.RolRepositorio;
import com.procol.registropublico.repositorio.AccesoRepositorio;
import com.procol.registropublico.repositorio.IngresoRepositorio;
import com.procol.registropublico.repositorio.UsuarioRepositorio;
import com.procol.registropublico.repositorio.UsuarioRolRepositorio;

import com.procol.seguridad.dto.RespuestaJwtDTO;
import com.procol.seguridad.servicio.AutenticacionManualServicio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CuentaCrearServicio extends OperacionCrudImple<Usuario, Integer> {

    // Repositorio obligatorio
    private final UsuarioRepositorio usuarioRepositorio;

    // Otros repositorios
    private final RolRepositorio rolRepositorio;
    private final AccesoRepositorio accesoRepositorio;
    private final IngresoRepositorio ingresoRepositorio;
    private final UsuarioRolRepositorio usuarioRolRepositorio;

    //Servicios adicionales
    private final BCryptPasswordEncoder codificadorDeClaves;
    private final AutenticacionManualServicio autenticacionManual;

    public CuentaCrearServicio(
            UsuarioRepositorio usuarioRepositorio,
            RolRepositorio rolRepositorio,
            AccesoRepositorio accesoRepositorio,
            IngresoRepositorio ingresoRepositorio,
            UsuarioRolRepositorio usuarioRolRepositorio,
            BCryptPasswordEncoder codificadorDeClaves,
            AutenticacionManualServicio autenticacionManual,
            BusquedaServicio<Usuario, Integer> BusquedaServicioUsuario
    ) {
        super(BusquedaServicioUsuario);
        this.usuarioRepositorio = usuarioRepositorio;
        this.rolRepositorio = rolRepositorio;
        this.accesoRepositorio = accesoRepositorio;
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioRolRepositorio = usuarioRolRepositorio;
        this.codificadorDeClaves = codificadorDeClaves;
        this.autenticacionManual = autenticacionManual;
    }

    @Override
    protected JpaRepository<Usuario, Integer> getRepositorio() {
        return usuarioRepositorio;
    }

    @Transactional
    public RespuestaJwtDTO crearCuenta(CuentaDTOCrear dto) {
        completarCamposFaltantes(dto);
        validarCorreo(dto.getCorreoAcceso());
        validarTelefono(dto.getTelefonoAcceso());
        validarDocumento(dto.getDocumentoUsuario());

        Usuario usuario = crearUsuario(dto);
        Acceso acceso = crearAcceso(dto, usuario);
        registrarIngreso(acceso);
        Rol rol = asignarRolPorDefecto(usuario);

        return generarJwt(usuario, dto, acceso, rol);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void completarCamposFaltantes(CuentaDTOCrear dto) {
        String documentoUsuario = "XXX_" + UUID.randomUUID().toString();
        Short tipoDocumentoUsuario = ConstRegistroCuenta.TIPO_DOCUMENTO_DEFECTO;
        
        dto.setDocumentoUsuario(documentoUsuario);
        dto.setEstadoUsuario(ConstRegistroCuenta.ESTADO_CUENTA);
        dto.setIdubicacion(ConstRegistroCuenta.ID_UBICACION);
        dto.setTipoDocumentoUsuario(tipoDocumentoUsuario);
    }

    private void validarCorreo(String correo) {
        if (accesoRepositorio.findByCorreoAcceso(correo).isPresent()) {
            throw new ExcepcionNegocio("Correo ya registrado: " + correo);
        }
    }

    private void validarTelefono(String telefono) {
        if (accesoRepositorio.findByTelefonoAcceso(telefono).isPresent()) {
            throw new ExcepcionNegocio("Teléfono ya registrado: " + telefono);
        }
    }
    
    private void validarDocumento(String documento) {
        if (usuarioRepositorio.findByDocumentoUsuario(documento).isPresent()) {
            throw new ExcepcionNegocio("Documento ya registrado: " + documento);
        }
    }
    
    private Usuario crearUsuario(CuentaDTOCrear dto) {
        Usuario usuario = new Usuario();
        usuario.setTipoDocumentoUsuario(dto.getTipoDocumentoUsuario());
        usuario.setDocumentoUsuario(dto.getDocumentoUsuario());
        usuario.setNombresUsuario(dto.getNombresUsuario());
        usuario.setApellidosUsuario(dto.getApellidosUsuario());
        usuario.setEstadoUsuario(dto.getEstadoUsuario());
        usuario.setIdUbicacion(new Ubicacion(dto.getIdubicacion()));
        return usuarioRepositorio.save(usuario);
    }

    private Acceso crearAcceso(CuentaDTOCrear dto, Usuario usuario) {
        Acceso acceso = new Acceso();
        acceso.setUsuario(usuario);
        acceso.setTelefonoAcceso(dto.getTelefonoAcceso());
        acceso.setCorreoAcceso(dto.getCorreoAcceso());
        acceso.setClaveAcceso(codificadorDeClaves.encode(dto.getClaveAcceso()));
        acceso.setUuidAcceso(UUID.randomUUID().toString());
        return accesoRepositorio.save(acceso);
    }

    private void registrarIngreso(Acceso acceso) {
        Ingreso ingreso = new Ingreso();
        ingreso.setIdUsuario(acceso);
        ingreso.setFechaIngreso(LocalDateTime.now());
        ingresoRepositorio.save(ingreso);
    }

    private Rol asignarRolPorDefecto(Usuario usuario) {
        Rol rol = rolRepositorio.findById(ConstRegistroCuenta.ASPIRANTE)
                .orElseThrow(() -> new ExcepcionNegocio("Rol por defecto no encontrado"));

        UsuarioRolPK pk = new UsuarioRolPK(rol.getIdRol(), usuario.getIdUsuario());
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuarioRolPK(pk);
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        usuarioRolRepositorio.save(usuarioRol);
        return rol;
    }

    private RespuestaJwtDTO generarJwt(
            Usuario usuario, CuentaDTOCrear dto, Acceso acceso, Rol rol
    ) {
        List<String> roles = List.of(rol.getNombreRol());
        // Al crear una cuenta nueva, el usuario no tiene empresa asignada todavía
        return autenticacionManual.autenticar(
                usuario.getIdUsuario(),
                dto.getCorreoAcceso(),
                dto.getNombresUsuario(),
                dto.getApellidosUsuario(),
                roles,
                acceso.getUuidAcceso(),
                null  // empresaId es null para cuentas nuevas
        );
    }
    // *************************************************************************
}
