package com.procol.perfil.dto;

public class RelUsuarioTalentoDTO {

    private Integer idUsuario;
    private Integer idTalento;
    private String nombreTalento;
    private Short nivelDominio; // 1 = basico, 2 = intermedio, 3 = avanzado

    public RelUsuarioTalentoDTO() {}

    public RelUsuarioTalentoDTO(Integer idUsuario, Integer idTalento, String nombreTalento, Short nivelDominio) {
        this.idUsuario = idUsuario;
        this.idTalento = idTalento;
        this.nombreTalento = nombreTalento;
        this.nivelDominio = nivelDominio;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTalento() {
        return idTalento;
    }

    public void setIdTalento(Integer idTalento) {
        this.idTalento = idTalento;
    }

    public String getNombreTalento() {
        return nombreTalento;
    }

    public void setNombreTalento(String nombreTalento) {
        this.nombreTalento = nombreTalento;
    }

    public Short getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(Short nivelDominio) {
        this.nivelDominio = nivelDominio;
    }
}