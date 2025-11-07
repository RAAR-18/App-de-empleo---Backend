package com.procol.usuario.repositorio;

import java.util.Optional;

import com.procol.usuario.entidad.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("usuario_UsuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByDocumentoUsuario(String documentoUsuario);

}
