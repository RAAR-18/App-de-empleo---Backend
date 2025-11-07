package com.procol.mensajeria.dto.mensaje;

public class MensajeDto {

    private String to;
    private String text;

    public MensajeDto() {
    }

    public MensajeDto(String to, String text) {
        this.to = to;
        this.text = text;
    }

    // getters y setters
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
