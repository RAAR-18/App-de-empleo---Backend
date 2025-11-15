package com.procol.perfil.dto;

public class RelUsuarioTalentoDTOEditar {

    private Integer idUsuario;
    private Integer idTalento;
    private Short nivelDominio;

    public RelUsuarioTalentoDTOEditar() {
    }

    public RelUsuarioTalentoDTOEditar(Integer idUsuario, Integer idTalento, Short nivelDominio) {
        this.idUsuario = idUsuario;
        this.idTalento = idTalento;
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

    public Short getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(Short nivelDominio) {
        this.nivelDominio = nivelDominio;
    }
}
