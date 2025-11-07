package com.procol.chat.dto;

/**
 * DTO para eventos de escritura (typing)
 * Indica cuando un usuario está escribiendo un mensaje
 */
public class TypingEvent {
    private Long postulacionId;
    private Long userId;
    private boolean typing;
    
    public TypingEvent() {}
    
    public TypingEvent(Long p, Long u, boolean t) { 
        this.postulacionId = p; 
        this.userId = u; 
        this.typing = t; 
    }
    
    public Long getPostulacionId() { return postulacionId; }
    public Long getUserId() { return userId; }
    public boolean isTyping() { return typing; }
    public void setPostulacionId(Long v) { this.postulacionId = v; }
    public void setUserId(Long v) { this.userId = v; }
    public void setTyping(boolean v) { this.typing = v; }
}



