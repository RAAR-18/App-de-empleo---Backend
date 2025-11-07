package com.procol.empresa.repositorio;

import com.procol.empresa.entidad.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("empresa_UsuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

}
