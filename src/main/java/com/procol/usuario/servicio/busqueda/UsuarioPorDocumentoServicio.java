package com.procol.usuario.servicio.busqueda;

import java.util.Optional;

import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.repositorio.UsuarioRepositorio;

import org.springframework.stereotype.Service;

@Service
public class UsuarioPorDocumentoServicio {

    private final UsuarioRepositorio usuarioRepositorio;

    public UsuarioPorDocumentoServicio(UsuarioRepositorio usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public Optional<Usuario> buscar(String documento) {
        if (documento == null || documento.trim().isEmpty()) {
            return null;
        }
        return usuarioRepositorio.findByDocumentoUsuario(documento.trim());
    }
}
