package com.procol.telefono.repositorio;

import com.procol.telefono.entidad.CambioTelefono;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository("telefono_CambioTelefonoRepositorio")
public interface CambioTelefonoRepositorio extends JpaRepository<CambioTelefono, Long> {

    @Query("SELECT ct FROM telefono_CambiosTelefono ct " +
            "WHERE ct.idUsuario = :idUsuario " +
            "AND ct.completado = false " +
            "AND ct.fechaExpiracion > :ahora " +
            "ORDER BY ct.fechaCreacion DESC")
    Optional<CambioTelefono> findProcesoActivoByUsuario(
            @Param("idUsuario") Integer idUsuario,
            @Param("ahora") OffsetDateTime ahora
    );

    @Query("SELECT CASE WHEN COUNT(ct) > 0 THEN true ELSE false END " +
            "FROM telefono_CambiosTelefono ct " +
            "WHERE ct.idUsuario = :idUsuario " +
            "AND ct.completado = false " +
            "AND ct.fechaExpiracion > :ahora")
    boolean existeProcesoActivoByUsuario(
            @Param("idUsuario") Integer idUsuario,
            @Param("ahora") OffsetDateTime ahora
    );

}
