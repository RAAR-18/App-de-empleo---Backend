package com.procol.usuario.repositorio;

import java.util.List;

import com.procol.usuario.entidad.Rol;
import com.procol.usuario.entidad.UsuarioRol;
import com.procol.usuario.entidad.pk.UsuarioRolPK;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("usuario_UsuarioRolRepositorio")
public interface UsuarioRolRepositorio extends JpaRepository<UsuarioRol, UsuarioRolPK> {

    @Modifying
    @Query("DELETE FROM usuario_UsuarioRol ur "
            + "WHERE ur.usuario.idUsuario = :idUsuario")
    void borrarRolesDeUnUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT ur.rol FROM usuario_UsuarioRol ur WHERE ur.usuario.idUsuario = :idUsuario")
    List<Rol> rolesPorUsuario(@Param("idUsuario") Integer idUsuario);

}
