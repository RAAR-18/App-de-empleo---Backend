package com.procol.chat.dto;

import java.time.Instant;

/**
 * DTO que representa el resumen de un chat
 * Contiene información sobre el último mensaje, cantidad de no leídos, y datos de la postulación
 */
public class ChatResumen {
    private Long postulacionId;
    private String ultimoMensaje;
    private Instant fechaUltimoMensaje;
    private long noLeidos;
    private String vacanteTitulo;
    private Integer vacanteEstado;
    private Integer postulacionEstado;       // Estado de la postulación
    private String postulacionEstadoNombre;  // Nombre del estado de la postulación

    private String contraparteNombre;       // empresa si el user es candidato; candidato si el user es reclutador
    private String contraparteTipo;         // "empresa" | "usuario"

    public ChatResumen() {}
    
    public ChatResumen(Long postulacionId, String ultimoMensaje, Instant fechaUltimoMensaje, long noLeidos,
                       String vacanteTitulo, Integer vacanteEstado, Integer postulacionEstado, String postulacionEstadoNombre,
                       String contraparteNombre, String contraparteTipo) {
        this.postulacionId = postulacionId;
        this.ultimoMensaje = ultimoMensaje;
        this.fechaUltimoMensaje = fechaUltimoMensaje;
        this.noLeidos = noLeidos;
        this.vacanteTitulo = vacanteTitulo;
        this.vacanteEstado = vacanteEstado;
        this.postulacionEstado = postulacionEstado;
        this.postulacionEstadoNombre = postulacionEstadoNombre;
        this.contraparteNombre = contraparteNombre;
        this.contraparteTipo = contraparteTipo;
    }
    
    public Long getPostulacionId() { return postulacionId; }
    public void setPostulacionId(Long postulacionId) { this.postulacionId = postulacionId; }
    public String getUltimoMensaje() { return ultimoMensaje; }  
    public void setUltimoMensaje(String ultimoMensaje) { this.ultimoMensaje = ultimoMensaje; }
    public Instant getFechaUltimoMensaje() { return fechaUltimoMensaje; }
    public void setFechaUltimoMensaje(Instant fechaUltimoMensaje) { this.fechaUltimoMensaje = fechaUltimoMensaje; }
    public long getNoLeidos() { return noLeidos; }
    public void setNoLeidos(long noLeidos) { this.noLeidos = noLeidos; }
    public String getVacanteTitulo() { return vacanteTitulo; }
    public void setVacanteTitulo(String vacanteTitulo) { this.vacanteTitulo = vacanteTitulo; }
    public Integer getVacanteEstado() { return vacanteEstado; }
    public void setVacanteEstado(Integer vacanteEstado) { this.vacanteEstado = vacanteEstado; }
    public Integer getPostulacionEstado() { return postulacionEstado; }
    public void setPostulacionEstado(Integer postulacionEstado) { this.postulacionEstado = postulacionEstado; }
    public String getPostulacionEstadoNombre() { return postulacionEstadoNombre; }
    public void setPostulacionEstadoNombre(String postulacionEstadoNombre) { this.postulacionEstadoNombre = postulacionEstadoNombre; }
    public String getContraparteNombre() { return contraparteNombre; }
    public void setContraparteNombre(String contraparteNombre) { this.contraparteNombre = contraparteNombre; }
    public String getContraparteTipo() { return contraparteTipo; }
    public void setContraparteTipo(String contraparteTipo) { this.contraparteTipo = contraparteTipo; }
}



