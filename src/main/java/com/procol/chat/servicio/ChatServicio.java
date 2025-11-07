package com.procol.chat.servicio;

import com.procol.chat.dto.ChatResumen;
import com.procol.chat.dto.ChatResumenPagina;
import com.procol.chat.dto.EnviarMensajeRequest;
import com.procol.chat.dto.InboxEvent;
import com.procol.chat.dto.MensajeResponse;
import com.procol.chat.entidad.Mensaje;
import com.procol.chat.repositorio.MensajeRepositorio;
import com.procol.empresa.repositorio.PostulacionRepositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servicio principal del módulo de chat
 * Maneja la lógica de negocio para mensajería, notificaciones y consultas de chats
 */
@Service
public class ChatServicio {

    private static final Logger log = LoggerFactory.getLogger(ChatServicio.class);

    private final MensajeRepositorio repo;
    private final SimpMessagingTemplate ws;
    private final PostulacionRepositorio postulacionRepo;

    private static final ZoneId BOGOTA = ZoneId.of("America/Bogota");

    public ChatServicio(MensajeRepositorio repo, SimpMessagingTemplate ws, PostulacionRepositorio postulacionRepo) {
        this.repo = repo;
        this.ws = ws;
        this.postulacionRepo = postulacionRepo;
    }

    /**
     * Envía un mensaje y notifica a los participantes del chat
     * 
     * @param req Solicitud con los datos del mensaje
     * @return Respuesta con el mensaje guardado
     */
    public MensajeResponse enviar(EnviarMensajeRequest req) {

        Instant instant = Instant.now();
        ZonedDateTime zdt = instant.atZone(BOGOTA);
        Mensaje m = new Mensaje(
                req.getPostulacionId(),
                req.getUsuarioId(),
                req.getTexto(),
                zdt.toInstant(),
                (short)1 // no leído
        );
        Mensaje guardado = repo.save(m);

        MensajeResponse dto = new MensajeResponse(
                guardado.getId(),
                guardado.getIdPostulacion(),
                guardado.getIdUsuarioResponde(),
                guardado.getTexto(),
                guardado.getFecha(),
                guardado.getEstado()
        );

        // Notificar a todos los suscritos al tópico del hilo
        ws.convertAndSend("/topic/chat/" + guardado.getIdPostulacion(), dto);


        List<Object[]> row = postulacionRepo.empresaYCandidatoPorPostulacion(guardado.getIdPostulacion());
    
        Long empresaId = null;
        Long candidatoId = null;
        if (row != null && !row.isEmpty()) {
            Object[] r = row.get(0); // [id_empresa, candidato_id]
            empresaId = r[0] == null ? null : ((Number) r[0]).longValue();
            candidatoId = r[1] == null ? null : ((Number) r[1]).longValue();
        }
        // Conteo "global/simple" de no leídos (modelo actual)
        // (si luego haces lectura por-usuario, aquí lo ajustamos)
        long noLeidosParaCandidato = (candidatoId != null)
            ? repo.countNoLeidos(guardado.getIdPostulacion(), candidatoId) : 0L;

        var evt = new InboxEvent(
            guardado.getIdPostulacion(),
            guardado.getIdUsuarioResponde(),
            recortar(guardado.getTexto(), 120),
            guardado.getFecha(),
            noLeidosParaCandidato  // para empresa es orientativo; el front refresca por REST
        );

        // a) CANDIDATO: su inbox por usuario (si no es el emisor)
        if (candidatoId != null && !candidatoId.equals(guardado.getIdUsuarioResponde())) {
            ws.convertAndSend("/topic/inbox/user/" + candidatoId, evt);
        }

        // b) EMPRESA: inbox compartido por empresa (si existe)
        if (empresaId != null) {
            ws.convertAndSend("/topic/inbox/company/" + empresaId, evt);
        }

        return dto;
    }


    /**
     * Recorta un texto a un máximo de caracteres
     */
    private String recortar(String s, int n) {
        if (s == null) return "";
        return s.length() <= n ? s : s.substring(0, n - 1) + "…";
    }

    /**
     * Obtiene todos los mensajes de una postulación ordenados por fecha ascendente
     */
    @Transactional(readOnly = true)
    public List<MensajeResponse> obtenerMensajes(Long postulacionId) {
        List<Mensaje> lista = repo.findByIdPostulacionOrderByFechaAsc(postulacionId);
        List<MensajeResponse> out = new ArrayList<>();
        for (Mensaje m : lista) {
            out.add(new MensajeResponse(m.getId(), m.getIdPostulacion(), m.getIdUsuarioResponde(),
                    m.getTexto(), m.getFecha(), m.getEstado()));
        }
        return out;
    }


    /**
     * Convierte una entidad Mensaje a su DTO de respuesta
     */
    private MensajeResponse toDto(Mensaje m) {
        return new MensajeResponse(
            m.getId(),
            m.getIdPostulacion(),
            m.getIdUsuarioResponde(),
            m.getTexto(),
            m.getFecha(),
            m.getEstado()
        );
    }

    /**
     * Obtiene mensajes de una postulación paginados
     */
    public List<MensajeResponse> paginaOffset(Long postId, int page, int size) {
        Page<Mensaje> p = repo.findByIdPostulacionOrderByFechaDesc(postId, PageRequest.of(page, size));
        // Para pintar ASC (de viejo a nuevo) invierte:
        List<Mensaje> content = p.getContent();
        return content.stream().map(this::toDto).toList();
    }


    /**
     * Marca como leídos los mensajes de una postulación para un usuario
     */
    @Transactional
    public int marcarLeidos(Long postulacionId, Long usuarioId) {
        return repo.marcarComoLeidos(postulacionId, usuarioId);
    }

    /**
     * Lista todos los chats de un usuario, opcionalmente filtrados por vacante o empresa
     */
    @Transactional(readOnly = true)
    public List<ChatResumen> listarChats(Long userId, Long vacanteId, Long empresaId /* opcional */) {
        List<Object[]> rows;
        if (vacanteId != null && vacanteId >= 0) {
            log.info("ENTRANDO A LA VACANTE----------------------------------------------");
            rows = repo.findChatsResumenExtendidoPorVacante(userId, vacanteId);
        } else if (empresaId != null && empresaId >= 0) {
            log.info("ENTRANDO A LA EMPRESA----------------------------------------------");
            rows = repo.findChatsResumenExtendidoPorEmpresa(empresaId, userId);
        } else {
            rows = repo.findChatsResumenExtendido(userId);
        }
        if (rows == null || rows.isEmpty()) {
            return Collections.emptyList();
        }
    return rows.stream().map(r -> new ChatResumen(
                ((Number) r[0]).longValue(),                 // postulacionId
                (String) r[1],                               // ultimoMensaje
                convertirAInstant(r[2]),                     // fechaUltimoMensaje
                r[3] == null ? 0L  : ((Number) r[3]).longValue(),               // noLeidos
                (String) r[4],                               // vacanteTitulo
                r[5] == null ? null : ((Number) r[5]).intValue(),               // vacanteEstado
                r[6] == null ? null : ((Number) r[6]).intValue(),               // postulacionEstado
                (String) r[7],                               // postulacionEstadoNombre
                (String) r[8],                               // contraparteNombre
                (String) r[9]                                // contraparteTipo
        )).toList();
    }

    /**
     * Lista chats de un usuario de forma paginada con búsqueda
     */
    @Transactional(readOnly = true)
    public ChatResumenPagina listarChatsPaginado(Long userId, String searchTerm, int page, int size) {
        // Normalizar searchTerm
        String term = (searchTerm == null || searchTerm.trim().isEmpty()) ? null : searchTerm.trim();
        
        // Calcular offset
        int offset = page * size;
        
        // Obtener chats paginados
        List<Object[]> rows = repo.findChatsResumenExtendidoPag(userId, term, size, offset);
        
        // Contar total
        long total = repo.countChatsResumenPag(userId, term);
        
        // Mapear a DTOs
        List<ChatResumen> chats = rows.stream().map(r -> new ChatResumen(
            ((Number) r[0]).longValue(),
            (String) r[1],
            convertirAInstant(r[2]),
            r[3] == null ? 0L : ((Number) r[3]).longValue(),
            (String) r[4],
            r[5] == null ? null : ((Number) r[5]).intValue(),
            r[6] == null ? null : ((Number) r[6]).intValue(),
            (String) r[7],
            (String) r[8],
            (String) r[9]
        )).toList();
        
        return new ChatResumenPagina(chats, total, page, size);
    }

    /**
     * Convierte un objeto (Timestamp o Instant) a Instant
     * Maneja la conversión desde consultas nativas que pueden devolver cualquiera de los dos tipos
     */
    private Instant convertirAInstant(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Instant) {
            return (Instant) obj;
        }
        if (obj instanceof java.sql.Timestamp) {
            return ((java.sql.Timestamp) obj).toInstant();
        }
        if (obj instanceof java.util.Date) {
            return ((java.util.Date) obj).toInstant();
        }
        throw new IllegalArgumentException("No se puede convertir " + obj.getClass().getName() + " a Instant");
    }
}



