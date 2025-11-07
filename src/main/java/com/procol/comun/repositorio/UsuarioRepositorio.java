package com.procol.comun.repositorio;

import com.procol.comun.entidad.Usuario;
import com.procol.comun.entidad.Ubicacion;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("comun_UsuarioRepositorio")
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    @Modifying
    @Query("UPDATE comun_Usuario u SET u.nombresUsuario = :nombres, u.apellidosUsuario = :apellidos, "
            + "u.tipoDocumentoUsuario = :tipoDoc, u.documentoUsuario = :documento, "
            + "u.idUbicacion = :ubicacion "
            + "WHERE u.idUsuario = :id")
    int actualizarInfoUsuario(
            @Param("nombres") String nombres,
            @Param("apellidos") String apellidos,
            @Param("tipoDoc") short tipoDoc,
            @Param("documento") String documento,
            @Param("ubicacion") Ubicacion ubicacion,
            @Param("id") Integer idUsuario
    );

}
