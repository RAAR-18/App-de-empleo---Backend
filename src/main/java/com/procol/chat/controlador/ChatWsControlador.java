package com.procol.chat.controlador;

import com.procol.chat.dto.ChatEvent;
import com.procol.chat.dto.TypingEvent;
import com.procol.chat.dto.EnviarMensajeRequest;
import com.procol.chat.dto.MensajeResponse;
import com.procol.chat.servicio.ChatServicio;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.*;
import org.springframework.stereotype.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador WebSocket para el módulo de chat
 * Maneja eventos en tiempo real como envío de mensajes y notificaciones de escritura
 */
@Controller
public class ChatWsControlador {

    private static final Logger log = LoggerFactory.getLogger(ChatWsControlador.class);
    private final ChatServicio servicio;

    public ChatWsControlador(ChatServicio servicio) {
        this.servicio = servicio;
    }

    

    /**
     * Endpoint WebSocket para enviar mensajes
     * Cliente envía a: /app/chat/{postulacionId}/send
     * Broker envía a:  /topic/chat/{postulacionId}
     */
    @MessageMapping("/chat/{postulacionId}/send")
    @SendTo("/topic/chat/{postulacionId}")
    public ChatEvent enviar(@DestinationVariable Long postulacionId,
                            @Valid @Payload EnviarMensajeRequest body) {
        body.setPostulacionId(postulacionId);
        log.info("WS recibido: post={}, user={}, texto='{}'",
                body.getPostulacionId(), body.getUsuarioId(), body.getTexto());

        // Guarda en DB y devuelve el DTO del mensaje
        MensajeResponse resp = servicio.enviar(body);

        log.info("Guardado id={} fecha={}", resp.getId(), resp.getFecha());

        // Envolvemos como EVENTO de tipo MESSAGE en el MISMO tópico
        return ChatEvent.message(resp);
    }

    /**
     * Endpoint WebSocket para notificaciones de escritura (typing)
     * Cliente envía a: /app/chat/{postulacionId}/typing  (no persiste)
     * Broker envía a:  /topic/chat/{postulacionId}
     */
    @MessageMapping("/chat/{postulacionId}/typing")
    @SendTo("/topic/chat/{postulacionId}")
    public ChatEvent typing(@DestinationVariable Long postulacionId,
                            @Payload TypingEvent ev) {
        // Asegurar que venga el postId en el evento (por si el cliente no lo manda)
        if (ev.getPostulacionId() == null) {
            ev.setPostulacionId(postulacionId);
        }
        log.debug("TYPING postId={}, userId={}, typing={}",
                ev.getPostulacionId(), ev.getUserId(), ev.isTyping());

        // Envolvemos como EVENTO de tipo TYPING en el MISMO tópico
        return ChatEvent.typing(ev);
    }
}



