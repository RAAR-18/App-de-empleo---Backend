package com.procol.comun.servicio;

import com.procol.comun.entidad.Usuario;
import com.procol.comun.entidad.Ubicacion;
import com.procol.comun.dto.PerfilInfoDtoActualizar;
import com.procol.comun.repositorio.AccesoRepositorio;
import com.procol.comun.repositorio.UbicacionRepositorio;
import com.procol.comun.repositorio.UsuarioRepositorio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilInfoActualizarServicio extends OperacionCrudImple<Usuario, Integer> {

    // Repositorio obligatorio
    private final UsuarioRepositorio usuarioRepositorio;
    private final AccesoRepositorio accesoRepositorio;
    private final UbicacionRepositorio ubicacionRepositorio;

    // Servicios o repositorios adicionales
    private final BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion;

    public PerfilInfoActualizarServicio(
            UsuarioRepositorio usuarioRepositorio,
            AccesoRepositorio accesoRepositorio,
            UbicacionRepositorio ubicacionRepositorio,
            BusquedaServicio<Ubicacion, Integer> busquedaServicioUbicacion,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario
    ) {
        super(busquedaServicioUsuario);
        this.usuarioRepositorio = usuarioRepositorio;
        this.accesoRepositorio = accesoRepositorio;
        this.ubicacionRepositorio = ubicacionRepositorio;
        this.busquedaServicioUbicacion = busquedaServicioUbicacion;
    }

    @Override
    protected JpaRepository<Usuario, Integer> getRepositorio() {
        return usuarioRepositorio;
    }

    @Transactional
    public Boolean actualizarInfoUsuario(PerfilInfoDtoActualizar dto) {
        Ubicacion objUbicacion = busquedaServicioUbicacion.PorId(ubicacionRepositorio, dto.getIdUbicacion());
        int filasUsuario = usuarioRepositorio.actualizarInfoUsuario(
                dto.getNombresUsuario(),
                dto.getApellidosUsuario(),
                dto.getTipoDocumentoUsuario(),
                dto.getDocumentoUsuario(),
                objUbicacion,
                dto.getIdUsuario()
        );

        int filasAcceso = accesoRepositorio.actualizarTelefonoAcceso(
                dto.getTelefonoAcceso(),
                dto.getIdUsuario()
        );
        return filasUsuario > 0 && filasAcceso > 0;
    }

}
