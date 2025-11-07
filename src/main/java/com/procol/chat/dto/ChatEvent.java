package com.procol.chat.dto;

/**
 * DTO para eventos del chat que se envían a través de WebSocket
 * Puede ser un mensaje o un evento de escritura (typing)
 */
public class ChatEvent {
    private ChatEventKind kind;
    private MensajeResponse message; // si kind = MESSAGE
    private TypingEvent typing;      // si kind = TYPING

    public ChatEvent() {}

    private ChatEvent(ChatEventKind kind, MensajeResponse message, TypingEvent typing) {
        this.kind = kind; 
        this.message = message; 
        this.typing = typing;
    }

    public static ChatEvent message(MensajeResponse m) {
        return new ChatEvent(ChatEventKind.MESSAGE, m, null);
    }
    
    public static ChatEvent typing(TypingEvent t) {
        return new ChatEvent(ChatEventKind.TYPING, null, t);
    }

    public ChatEventKind getKind() { return kind; }
    public MensajeResponse getMessage() { return message; }
    public TypingEvent getTyping() { return typing; }
}



