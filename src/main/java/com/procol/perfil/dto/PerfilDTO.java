package com.procol.perfil.dto;

public class PerfilDTO {

    private Integer idUsuario;
    private String nombreCompleto;
    private String profesion;
    private String ubicacion;

    public PerfilDTO() {
    }

    public PerfilDTO(Integer idUsuario, String nombreCompleto, String profesion, String ubicacion) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.profesion = profesion;
        this.ubicacion = ubicacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "PerfilDTO["
                + "idUsuario=" + idUsuario
                + ", nombreCompleto='" + nombreCompleto + '\''
                + ", profesion='" + profesion + '\''
                + ", ubicacion='" + ubicacion + '\''
                + ']';
    }
}