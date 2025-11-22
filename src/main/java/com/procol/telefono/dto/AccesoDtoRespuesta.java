package com.procol.telefono.dto;

public class AccesoDtoRespuesta {

    private Integer idUsuario;
    private String telefonoAcceso;
    private String correoAcceso;
    private Short estadoCorreoVerificado;

    public AccesoDtoRespuesta() {
    }

    public AccesoDtoRespuesta(
            Integer idUsuario,
            String telefonoAcceso,
            String correoAcceso,
            Short estadoCorreoVerificado
    ) {
        this.idUsuario = idUsuario;
        this.telefonoAcceso = telefonoAcceso;
        this.correoAcceso = correoAcceso;
        this.estadoCorreoVerificado = estadoCorreoVerificado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public Short getEstadoCorreoVerificado() {
        return estadoCorreoVerificado;
    }

    public void setEstadoCorreoVerificado(Short estadoCorreoVerificado) {
        this.estadoCorreoVerificado = estadoCorreoVerificado;
    }
}
