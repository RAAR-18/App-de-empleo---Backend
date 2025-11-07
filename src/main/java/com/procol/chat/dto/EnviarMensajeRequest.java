package com.procol.chat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO para la solicitud de envío de mensaje
 * Utilizado tanto en REST como en WebSocket
 */
public class EnviarMensajeRequest {
    @NotNull 
    private Long postulacionId;
    
    @NotNull 
    private Long usuarioId;   // quien envía
    
    @NotBlank 
    private String texto;

    public Long getPostulacionId() { return postulacionId; }
    public void setPostulacionId(Long postulacionId) { this.postulacionId = postulacionId; }
    
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
}



