package com.procol.chat.dto;

import java.util.List;

/**
 * DTO para respuesta paginada de chats
 * Contiene la lista de chats, información de paginación y metadatos
 */
public class ChatResumenPagina {
    private List<ChatResumen> chats;
    private long totalElementos;
    private int paginaActual;
    private int tamanoPagina;
    private int totalPaginas;
    private boolean tieneMas;

    public ChatResumenPagina() {}

    public ChatResumenPagina(List<ChatResumen> chats, long totalElementos, int paginaActual, int tamanoPagina) {
        this.chats = chats;
        this.totalElementos = totalElementos;
        this.paginaActual = paginaActual;
        this.tamanoPagina = tamanoPagina;
        this.totalPaginas = (int) Math.ceil((double) totalElementos / tamanoPagina);
        this.tieneMas = paginaActual < totalPaginas - 1;
    }

    public List<ChatResumen> getChats() {
        return chats;
    }

    public void setChats(List<ChatResumen> chats) {
        this.chats = chats;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public void setPaginaActual(int paginaActual) {
        this.paginaActual = paginaActual;
    }

    public int getTamanoPagina() {
        return tamanoPagina;
    }

    public void setTamanoPagina(int tamanoPagina) {
        this.tamanoPagina = tamanoPagina;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }

    public boolean isTieneMas() {
        return tieneMas;
    }

    public void setTieneMas(boolean tieneMas) {
        this.tieneMas = tieneMas;
    }
}



