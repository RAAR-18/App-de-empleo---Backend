package com.procol.empresa.repositorio;

import com.procol.empresa.entidad.Postulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Postulacion
 * Incluye métodos específicos utilizados por el módulo de chat
 */
@Repository
public interface PostulacionRepositorio extends JpaRepository<Postulacion, Integer> {
    
    /**
     * Obtiene los IDs de empresa y candidato para una postulación específica
     * Utilizado por el módulo de chat para notificar a los participantes
     * 
     * @param postId ID de la postulación
     * @return Lista con un arreglo [empresa_id, candidato_id]
     */
    @Query(value = """
        SELECT
            v.id_empresa         AS empresa_id,
            p.id_usuario         AS candidato_id
        FROM postulaciones p
        JOIN vacantes v ON v.id_vacante = p.id_vacante
        WHERE p.id_postulacion = :postId
        LIMIT 1
        """, nativeQuery = true)
    List<Object[]> empresaYCandidatoPorPostulacion(@Param("postId") Long postId);

    /**
     * Obtiene los IDs de candidato y reclutador para una postulación específica
     * Utilizado por el módulo de chat para identificar a los participantes
     * 
     * @param postId ID de la postulación
     * @return Lista con un arreglo [candidato_id, reclutador_id]
     */
    @Query(value = """
        SELECT 
            p.id_usuario   AS candidato_id, 
            v.id_usuario   AS reclutador_id
        FROM postulaciones p
        JOIN vacantes v ON v.id_vacante = p.id_vacante
        WHERE p.id_postulacion = :postId
        LIMIT 1
        """, nativeQuery = true)
    List<Object[]> participantes(@Param("postId") Long postId);
}

