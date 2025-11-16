package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("perfil_ArchivoRepositorio")
public interface ArchivoRepositorio extends JpaRepository<Archivo, Integer> {

    @Query(value = "SELECT * FROM archivos " +
            "WHERE id_usuario = :idUsuario " +
            "AND grupo_archivo = :grupoArchivo " +
            "ORDER BY fecha_subida DESC " +
            "LIMIT 1",
            nativeQuery = true)
    Optional<Archivo> findCVMasReciente(
            @Param("idUsuario") Integer idUsuario,
            @Param("grupoArchivo") Integer grupoArchivo
    );

    List<Archivo> findByIdUsuarioIdUsuarioAndGrupoArchivo(Integer idUsuario, Integer grupoArchivo);

}
