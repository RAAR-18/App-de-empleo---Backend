package com.procol.perfil.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class RelUsuarioTalentoDTOAgregar {

    private Integer idUsuario;
    private Integer idTalento;

    @Min(1)
    @Max(2)
    private Short nivelDominio;

    public RelUsuarioTalentoDTOAgregar() {}

    public RelUsuarioTalentoDTOAgregar(Integer idUsuario, Integer idTalento,  Short nivelDominio) {
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
