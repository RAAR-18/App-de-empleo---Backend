package com.procol.registropublico.repositorio;

import com.procol.registropublico.entidad.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository("registropublico_UsuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByDocumentoUsuario(String documentoUsuario);
}
