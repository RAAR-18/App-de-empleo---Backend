package com.procol.registropublico.repositorio;

import com.procol.registropublico.entidad.UsuarioRol;
import com.procol.registropublico.entidad.pk.UsuarioRolPK;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("registropublico_UsuarioRolRepositorio")
public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, UsuarioRolPK> {

}
