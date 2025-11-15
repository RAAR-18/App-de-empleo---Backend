package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("perfil_ArchivoRepositorio")
public interface ArchivoRepositorio extends JpaRepository<Archivo, Integer> {

    @Query("SELECT a FROM perfil_Archivo a WHERE a.idUsuario.idUsuario = :idUsuario ORDER BY a.fechaSubida DESC")
    List<Archivo> findByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT a FROM perfil_Archivo a WHERE a.idUsuario.idUsuario = :idUsuario AND a.tipoArchivo = :tipo ORDER BY a.fechaSubida DESC")
    List<Archivo> findByUsuarioIdAndTipo(@Param("idUsuario") Integer idUsuario,
                                         @Param("tipo") String tipo);

    @Query("SELECT COUNT(a) FROM perfil_Archivo a WHERE a.idUsuario.idUsuario = :idUsuario")
    Long countByUsuarioId(@Param("idUsuario") Integer idUsuario);

    @Query("DELETE FROM perfil_Archivo a WHERE a.idUsuario.idUsuario = :idUsuario")
    void deleteByUsuarioId(@Param("idUsuario") Integer idUsuario);
}
