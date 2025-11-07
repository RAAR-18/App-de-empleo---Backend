package com.procol.comun.repositorio;

import java.util.Optional;

import com.procol.comun.entidad.Acceso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("comun_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    @Modifying
    @Query("UPDATE comun_Acceso a SET a.telefonoAcceso = :telefono WHERE a.idUsuario = :idUsuario")
    int actualizarTelefonoAcceso(@Param("telefono") String telefono, @Param("idUsuario") Integer idUsuario
    );

    @Modifying
    @Query("UPDATE comun_Acceso a SET a.correoAcceso = :correoAcceso WHERE a.idUsuario = :idUsuario")
    int actualizarCorreoAcceso(String correoAcceso, Integer idUsuario);

    @Modifying
    @Query("UPDATE comun_Acceso a SET a.correoAcceso = :correoAcceso, a.claveAcceso = :claveAcceso WHERE a.idUsuario = :idUsuario")
    int actualizarCorreoYClaveAcceso(String correoAcceso, String claveAcceso, Integer idUsuario);

}
