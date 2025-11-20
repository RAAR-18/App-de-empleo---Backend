package com.procol.telefono.dto;

public class AccesoDtoRespuesta {

    private Integer idUsuario;
    private String telefonoAcceso;

    public AccesoDtoRespuesta() {
    }

    public AccesoDtoRespuesta(
            Integer idUsuario,
            String telefonoAcceso
    ) {
        this.idUsuario = idUsuario;
        this.telefonoAcceso = telefonoAcceso;
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
}
