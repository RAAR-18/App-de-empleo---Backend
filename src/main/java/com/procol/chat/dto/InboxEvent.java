package com.procol.chat.dto;

import java.time.Instant;

/**
 * DTO para eventos de inbox (bandeja de entrada)
 * Notifica a los usuarios sobre nuevos mensajes en sus chats
 */
public class InboxEvent {
    private Long postulacionId;
    private Long fromUserId;
    private String preview;
    private Instant fecha;
    private long noLeidos;

    public InboxEvent() {}

    public InboxEvent(Long postulacionId, Long fromUserId, String preview, Instant fecha, long noLeidos) {
        this.postulacionId = postulacionId;
        this.fromUserId = fromUserId;
        this.preview = preview;
        this.fecha = fecha;
        this.noLeidos = noLeidos;
    }
    
    public Long getPostulacionId() { return postulacionId; }
    public void setPostulacionId(Long postulacionId) { this.postulacionId = postulacionId; }
    
    public Long getFromUserId() { return fromUserId; }
    public void setFromUserId(Long fromUserId) { this.fromUserId = fromUserId; }
    
    public String getPreview() { return preview; }
    public void setPreview(String preview) { this.preview = preview; }
    
    public Instant getFecha() { return fecha; }
    public void setFecha(Instant fecha) { this.fecha = fecha; }
    
    public long getNoLeidos() { return noLeidos; }
    public void setNoLeidos(long noLeidos) { this.noLeidos = noLeidos; }    
}



