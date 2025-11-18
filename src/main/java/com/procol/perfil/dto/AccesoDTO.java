package com.procol.perfil.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccesoDTO {

    @JsonIgnore
    private UsuarioDTO idUsuario;
    private String correoAcceso;
    @JsonIgnore
    private String claveAcceso;
    private String uuidAcceso;

    public AccesoDTO() {}

    public AccesoDTO(UsuarioDTO  idUsuario, String correoAcceso, String claveAcceso, String uuidAcceso) {
        this.idUsuario = idUsuario;
        this.correoAcceso = correoAcceso;
        this.claveAcceso = claveAcceso;
        this.uuidAcceso = uuidAcceso;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getUuidAcceso() {
        return uuidAcceso;
    }

    public void setUuidAcceso(String uuidAcceso) {
        this.uuidAcceso = uuidAcceso;
    }

    @Override
    public String toString() {
        return "AccesoDTO{" +
                "idUsuario=" + idUsuario +
                ", correoAcceso='" + correoAcceso + '\'' +
                ", claveAcceso='" + claveAcceso + '\'' +
                ", uuidAcceso='" + uuidAcceso + '\'' +
                '}';
    }
}
