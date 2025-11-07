package com.procol.comun.servicio;

import java.util.UUID;
import java.util.Optional;

import com.procol.comun.entidad.Acceso;
import com.procol.comun.dto.PerfilCuentaDtoActualizar;
import com.procol.comun.entidad.Usuario;
import com.procol.comun.repositorio.AccesoRepositorio;
import com.procol.comun.repositorio.UsuarioRepositorio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class PerfilCuentaActualizarServicio extends OperacionCrudImple<Acceso, Integer> {

    // Repositorio obligatorio
    private final AccesoRepositorio accesoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;

    // Servicios o repositorios adicionales
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PerfilCuentaActualizarServicio(
            AccesoRepositorio accesoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            BusquedaServicio<Acceso, Integer> busquedaServicioAcceso
    ) {
        super(busquedaServicioAcceso);
        this.accesoRepositorio = accesoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected JpaRepository<Acceso, Integer> getRepositorio() {
        return accesoRepositorio;
    }

    @Transactional
    public Boolean actualizarInfoUsuario(PerfilCuentaDtoActualizar dto) {
        // *********************************************************************        
        // NO se usa BusquedaServicio<T, ID> porque es OneToOne y queda detached
        // *********************************************************************
        Usuario objUsuario = usuarioRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ExcepcionNegocio("Usuario NO encontrado: " + dto.getIdUsuario()));
        // *********************************************************************

        Optional<Acceso> accesoPorCorreo = accesoRepositorio.findByCorreoAcceso(dto.getCorreoAcceso());
        Acceso accesoExistente = buscarPorId(dto.getIdUsuario());

        if (accesoPorCorreo.isPresent()) {
            Acceso encontrado = accesoPorCorreo.get();
            if (accesoExistente == null || !encontrado.getIdUsuario().equals(accesoExistente.getIdUsuario())) {
                throw new ExcepcionNegocio("Correo ya registrado: " + dto.getCorreoAcceso());
            }
        }

//        int filaCorreo = accesoRepositorio.actualizarCorreoAcceso(
//                dto.getCorreoAcceso(),
//                dto.getIdUsuario());
//
//        bCryptPasswordEncoder.encode(dto.getClaveAccesoNueva());
//        int filaCorreoClave = accesoRepositorio.actualizarCorreoYClaveAcceso(
//                dto.getCorreoAcceso(),
//                dto.getClaveAccesoNueva(),
//                dto.getIdUsuario()
//        );
        Acceso accesoFinal = (accesoExistente == null)
                ? construirAcceso(objUsuario, dto)
                : actualizarAcceso(accesoExistente, dto);

        Acceso accesoPersistido = agregar(accesoFinal);
        return true;
//        return filaCorreo > 0 && filaCorreoClave > 0;
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private Acceso construirAcceso(Usuario objUsuario, PerfilCuentaDtoActualizar dto) {
        Acceso accesoNuevo = new Acceso();
        String telefonoAcceso = "XXX_" + UUID.randomUUID().toString();

        accesoNuevo.setUsuario(objUsuario);
        accesoNuevo.setCorreoAcceso(dto.getCorreoAcceso());
        accesoNuevo.setClaveAcceso(bCryptPasswordEncoder.encode(dto.getClaveAccesoNueva()));
        accesoNuevo.setTelefonoAcceso(telefonoAcceso);
        accesoNuevo.setUuidAcceso(UUID.randomUUID().toString());
        return  agregar(accesoNuevo);
    }

    private Acceso actualizarAcceso(Acceso accesoExistente, PerfilCuentaDtoActualizar dto) {
        accesoExistente.setCorreoAcceso(dto.getCorreoAcceso());
        accesoExistente.setClaveAcceso(bCryptPasswordEncoder.encode(dto.getClaveAccesoNueva()));
        accesoExistente.setUuidAcceso(UUID.randomUUID().toString());
        return accesoExistente;
    }
    // *************************************************************************

}
