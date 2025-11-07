package com.procol.seguridad.repositorio;

import java.util.List;

import com.procol.seguridad.entidad.UsuarioRol;
import com.procol.seguridad.entidad.pk.UsuarioRolPK;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("seguridad_UsuarioRolRepositorio")
public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, UsuarioRolPK> {

    List<UsuarioRol> findByUsuario_IdUsuario(Integer idUsuario);
}
