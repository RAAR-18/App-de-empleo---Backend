package com.procol.chat.repositorio;

import com.procol.chat.entidad.Mensaje;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

/**
 * Repositorio para la entidad Mensaje
 * Proporciona consultas específicas para el módulo de chat
 */
public interface MensajeRepositorio extends JpaRepository<Mensaje, Long> {

    /**
     * Obtiene todos los mensajes de una postulación ordenados por fecha ascendente
     */
    List<Mensaje> findByIdPostulacionOrderByFechaAsc(Long idPostulacion);

    /**
     * Obtiene los chats resumen extendido paginado con búsqueda
     * Retorna información completa del chat incluyendo último mensaje, no leídos, etc.
     */
    @Query(value = """
        SELECT
            p.id_postulacion                                   AS postulacionId,

            -- último mensaje y fecha
            (SELECT m.texto_mensaje
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               ORDER BY m.fecha_mensaje DESC
               LIMIT 1)                                         AS ultimoMensaje,

            (SELECT m.fecha_mensaje
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               ORDER BY m.fecha_mensaje DESC
               LIMIT 1)                                         AS fechaUltimoMensaje,

            -- no leídos para este user
            (SELECT COUNT(*)
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               AND m.id_usuario_responde <> :userId
               AND m.estado_mensaje = 1)                      AS noLeidos,

            -- Datos de vacante
            v.titulo_vacante                                   AS vacanteTitulo,
            v.estado_vacante                                   AS vacanteEstado,

            -- Datos de postulación
            p.estado_postulacion                               AS postulacionEstado,
            ep.nombre_estado_postulacion                       AS postulacionEstadoNombre,

            -- Nombre de la contraparte (empresa si yo soy el candidato; candidato si yo soy reclutador)
            CASE
               WHEN :userId = p.id_usuario THEN COALESCE(e.nombre_empresa, 'Empresa')
               ELSE COALESCE(u.nombres_usuario, 'Usuario')
            END                                                AS contraparteNombre,

            CASE
               WHEN :userId = p.id_usuario THEN 'empresa'
               ELSE 'usuario'
            END                                                AS contraparteTipo

        FROM postulaciones p
        JOIN vacantes v         ON v.id_vacante = p.id_vacante
        LEFT JOIN estados_postulaciones ep ON ep.id_estado_postulacion = p.estado_postulacion
        LEFT JOIN empresas e    ON e.id_empresa = v.id_empresa
        LEFT JOIN usuarios u    ON u.id_usuario = p.id_usuario  -- candidato
        WHERE (p.id_usuario = :userId OR v.id_usuario = :userId)
          AND (:searchTerm IS NULL OR :searchTerm = '' OR 
               CASE
                  WHEN :userId = p.id_usuario THEN LOWER(e.nombre_empresa)
                  ELSE LOWER(u.nombres_usuario)
               END LIKE LOWER(CONCAT('%', :searchTerm, '%')))
        ORDER BY fechaUltimoMensaje DESC NULLS LAST
        LIMIT :pageSize OFFSET :offset
        """, nativeQuery = true)
    List<Object[]> findChatsResumenExtendidoPag(
        @Param("userId") Long userId,
        @Param("searchTerm") String searchTerm,
        @Param("pageSize") int pageSize,
        @Param("offset") int offset);

    /**
     * Obtiene todos los chats resumen extendido para un usuario
     */
    @Query(value = """
        SELECT
            p.id_postulacion                                   AS postulacionId,

            -- último mensaje y fecha
            (SELECT m.texto_mensaje
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               ORDER BY m.fecha_mensaje DESC
               LIMIT 1)                                         AS ultimoMensaje,

            (SELECT m.fecha_mensaje
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               ORDER BY m.fecha_mensaje DESC
               LIMIT 1)                                         AS fechaUltimoMensaje,

            -- no leídos para este user
            (SELECT COUNT(*)
               FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               AND m.id_usuario_responde <> :userId
               AND m.estado_mensaje = 1)                      AS noLeidos,

            -- Datos de vacante
            v.titulo_vacante                                   AS vacanteTitulo,
            v.estado_vacante                                   AS vacanteEstado,

            -- Datos de postulación
            p.estado_postulacion                               AS postulacionEstado,
            ep.nombre_estado_postulacion                       AS postulacionEstadoNombre,

            -- Nombre de la contraparte (empresa si yo soy el candidato; candidato si yo soy reclutador)
            CASE
               WHEN :userId = p.id_usuario THEN COALESCE(e.nombre_empresa, 'Empresa')
               ELSE COALESCE(u.nombres_usuario, 'Usuario')
            END                                                AS contraparteNombre,

            CASE
               WHEN :userId = p.id_usuario THEN 'empresa'
               ELSE 'usuario'
            END                                                AS contraparteTipo

        FROM postulaciones p
        JOIN vacantes v         ON v.id_vacante = p.id_vacante
        LEFT JOIN estados_postulaciones ep ON ep.id_estado_postulacion = p.estado_postulacion
        LEFT JOIN empresas e    ON e.id_empresa = v.id_empresa
        LEFT JOIN usuarios u    ON u.id_usuario = p.id_usuario  -- candidato
        WHERE p.id_usuario = :userId
           OR v.id_usuario = :userId
        ORDER BY fechaUltimoMensaje DESC NULLS LAST
        """, nativeQuery = true)
    List<Object[]> findChatsResumenExtendido(@Param("userId") Long userId);

    /**
     * Obtiene mensajes de una postulación paginados ordenados por fecha descendente
     */
    Page<Mensaje> findByIdPostulacionOrderByFechaDesc(Long idPostulacion, Pageable pageable);

    /**
     * Marca como leídos todos los mensajes de una postulación para un usuario específico
     */
    @Modifying
    @Query("""
       UPDATE Mensaje m SET m.estado = 2
       WHERE m.idPostulacion = :postulacionId
         AND m.idUsuarioResponde <> :userId
         AND m.estado = 1
    """)
    int marcarComoLeidos(@Param("postulacionId") Long postulacionId, @Param("userId") Long userId);

    /**
     * Obtiene el historial de mensajes antes de una fecha específica
     */
    @Query("""
       SELECT m FROM Mensaje m
       WHERE m.idPostulacion = :postulacionId AND m.fecha < :antesDe
       ORDER BY m.fecha DESC
    """)
    List<Mensaje> historico(@Param("postulacionId") Long postulacionId, @Param("antesDe") Instant antesDe);

    /**
     * Cuenta los mensajes no leídos para un usuario en un chat específico
     */
    @Query("""
      SELECT COUNT(m) FROM Mensaje m
      WHERE m.idPostulacion = :postId
         AND m.idUsuarioResponde <> :userId
         AND m.estado = 1
   """)
    long countNoLeidos(Long postId, Long userId);

    /**
     * Obtiene chats resumen filtrados por vacante
     */
    @Query(value = """
        SELECT
            p.id_postulacion                              AS postulacionId,
            lm.texto_mensaje                              AS ultimoMensaje,
            lm.fecha_mensaje                              AS fechaUltimoMensaje,
            (SELECT COUNT(*) FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               AND m.id_usuario_responde <> :userId
               AND m.estado_mensaje = 1)                 AS noLeidos,
            v.titulo_vacante                              AS vacanteTitulo,
            v.estado_vacante                              AS vacanteEstado,
            p.estado_postulacion                          AS postulacionEstado,
            ep.nombre_estado_postulacion                  AS postulacionEstadoNombre,
            u.nombres_usuario                             AS contraparteNombre,
            'usuario'                                     AS contraparteTipo
        FROM postulaciones p
        JOIN vacantes v   ON v.id_vacante = p.id_vacante
        LEFT JOIN estados_postulaciones ep ON ep.id_estado_postulacion = p.estado_postulacion
        JOIN usuarios u   ON u.id_usuario = p.id_usuario
        LEFT JOIN LATERAL (
           SELECT m.texto_mensaje, m.fecha_mensaje
              FROM mensajes m
              WHERE m.id_postulacion = p.id_postulacion
              ORDER BY m.fecha_mensaje DESC
              LIMIT 1
        ) lm ON true
        WHERE v.id_vacante = :vacanteId
        ORDER BY lm.fecha_mensaje DESC NULLS LAST
        """, nativeQuery = true)
    List<Object[]> findChatsResumenExtendidoPorVacante(@Param("userId") Long userId, @Param("vacanteId") Long vacanteId);

    /**
     * Obtiene chats resumen filtrados por empresa
     */
    @Query(value = """
        SELECT
            p.id_postulacion AS postulacionId,
            lm.texto_mensaje AS ultimoMensaje,
            lm.fecha_mensaje AS fechaUltimoMensaje,
            (SELECT COUNT(*) FROM mensajes m
               WHERE m.id_postulacion = p.id_postulacion
               AND m.id_usuario_responde <> :userId
               AND m.estado_mensaje = 1)                     AS noLeidos,
            v.titulo_vacante                                  AS vacanteTitulo,
            v.estado_vacante                                  AS vacanteEstado,
            p.estado_postulacion                              AS postulacionEstado,
            ep.nombre_estado_postulacion                      AS postulacionEstadoNombre,
            u.nombres_usuario                                 AS contraparteNombre, -- siempre el candidato
            'usuario'                                         AS contraparteTipo
        FROM postulaciones p
        JOIN vacantes v   ON v.id_vacante = p.id_vacante
        LEFT JOIN estados_postulaciones ep ON ep.id_estado_postulacion = p.estado_postulacion
        JOIN usuarios u   ON u.id_usuario = p.id_usuario
        LEFT JOIN LATERAL (
           SELECT m.texto_mensaje, m.fecha_mensaje
           FROM mensajes m
           WHERE m.id_postulacion = p.id_postulacion
           ORDER BY m.fecha_mensaje DESC
           LIMIT 1
        ) lm ON true
        WHERE v.id_empresa = :empresaId
        ORDER BY lm.fecha_mensaje DESC NULLS LAST
        """, nativeQuery = true)
    List<Object[]> findChatsResumenExtendidoPorEmpresa(@Param("empresaId") Long empresaId,
                                             @Param("userId") Long userId);

    /**
     * Cuenta el total de chats para paginación
     */
    @Query(value = """
        SELECT COUNT(DISTINCT p.id_postulacion)
        FROM postulaciones p
        JOIN vacantes v         ON v.id_vacante = p.id_vacante
        LEFT JOIN estados_postulaciones ep ON ep.id_estado_postulacion = p.estado_postulacion
        LEFT JOIN empresas e    ON e.id_empresa = v.id_empresa
        LEFT JOIN usuarios u    ON u.id_usuario = p.id_usuario
        WHERE (p.id_usuario = :userId OR v.id_usuario = :userId)
          AND (:searchTerm IS NULL OR :searchTerm = '' OR 
               CASE
                  WHEN :userId = p.id_usuario THEN LOWER(e.nombre_empresa)
                  ELSE LOWER(u.nombres_usuario)
               END LIKE LOWER(CONCAT('%', :searchTerm, '%')))
        """, nativeQuery = true)
    long countChatsResumenPag(@Param("userId") Long userId, @Param("searchTerm") String searchTerm);
}



