package com.procol.perfil.servicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.perfil.dto.PerfilDTO;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import com.procol.perfil.utilidad.mapeador.PerfilMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_PerfilConsultarServicio")
public class PerfilConsultarServicio {

    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final PerfilMapeador perfilMapeador;

    public PerfilConsultarServicio(
            UsuarioRepositorio usuarioRepositorio,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            PerfilMapeador perfilMapeador
    ) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.perfilMapeador = perfilMapeador;
    }

    @Transactional(readOnly = true)
    public PerfilDTO obtenerPerfil(Integer idUsuario) {
        Usuario usuario = busquedaServicioUsuario.PorId(usuarioRepositorio, idUsuario);
        return perfilMapeador.desdeEntidad(usuario);
    }
}