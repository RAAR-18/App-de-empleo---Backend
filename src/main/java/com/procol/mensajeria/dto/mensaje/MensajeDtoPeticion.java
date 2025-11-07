package com.procol.mensajeria.dto.mensaje;

import java.util.List;

public class MensajeDtoPeticion {

    private boolean priority = true;
    private boolean certificate = true;
    private String sendDate = "Now";
    private boolean flash = false;
    private List<MensajeDto> messages;

    public MensajeDtoPeticion() {
    }

    public MensajeDtoPeticion(List<MensajeDto> messages) {
        this.messages = messages;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }

    public List<MensajeDto> getMessages() {
        return messages;
    }

    public void setMessages(List<MensajeDto> messages) {
        this.messages = messages;
    }
}
