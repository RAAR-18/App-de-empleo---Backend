package com.procol.telefono.repositorio;

import com.procol.telefono.entidad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("telefono_UsuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByDocumentoUsuario(String documentoUsuario);
}
