package com.procol.chat.dto;

import java.time.Instant;

/**
 * DTO para la respuesta de un mensaje
 * Representa un mensaje con toda su información para enviar al cliente
 */
public class MensajeResponse {
    private Long id;
    private Long postulacionId;
    private Long usuarioId;
    private String texto;
    private Instant fecha;
    private short estado;

    public MensajeResponse() {}
    
    public MensajeResponse(Long id, Long postulacionId, Long usuarioId, String texto, Instant fecha, short estado) {
        this.id = id; 
        this.postulacionId = postulacionId; 
        this.usuarioId = usuarioId;
        this.texto = texto; 
        this.fecha = fecha; 
        this.estado = estado;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getPostulacionId() { return postulacionId; }
    public void setPostulacionId(Long postulacionId) { this.postulacionId = postulacionId; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    
    public Instant getFecha() { return fecha; }
    public void setFecha(Instant fecha) { this.fecha = fecha; }
    
    public short getEstado() { return estado; }
    public void setEstado(short estado) { this.estado = estado; }
}



