package com.procol.perfil.servicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.perfil.dto.DatosBasicosDTO;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import com.procol.perfil.utilidad.mapeador.DatosBasicosMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_DatosBasicosConsultarServicio")
public class DatosBasicosConsultarServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final DatosBasicosMapeador datosBasicosMapeador;

    public DatosBasicosConsultarServicio(
            UsuarioRepositorio usuarioRepositorio,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            DatosBasicosMapeador datosBasicosMapeador
    ) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.datosBasicosMapeador = datosBasicosMapeador;
    }

    @Transactional(readOnly = true)
    public DatosBasicosDTO obtenerDatosBasicos(Integer idUsuario) {
        Usuario usuario = busquedaServicioUsuario.PorId(usuarioRepositorio, idUsuario);
        return datosBasicosMapeador.desdeEntidad(usuario);
    }
}
