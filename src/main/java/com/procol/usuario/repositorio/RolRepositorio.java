package com.procol.usuario.repositorio;

import com.procol.usuario.entidad.Rol;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository("usuario_RolRepositorio")
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

    @Modifying
    @Transactional
    @Query("update usuario_Rol r set r.estadoRol = :nuevoEstado where r.idRol = :codigo")
    int cambiarEstado(@Param("codigo") Integer idRol, @Param("nuevoEstado") Short codEstado);

}
