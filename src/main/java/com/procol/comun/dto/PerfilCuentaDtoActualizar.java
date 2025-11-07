package com.procol.comun.dto;

public class PerfilCuentaDtoActualizar {

    private Integer idUsuario;
    private String correoAcceso;
    private String claveAccesoActual;
    private String claveAccesoNueva;

    public PerfilCuentaDtoActualizar() {
    }

    public PerfilCuentaDtoActualizar(
            Integer idUsuario, String correoAcceso, String claveAccesoActual, String claveAccesoNueva
    ) {
        this.idUsuario = idUsuario;
        this.correoAcceso = correoAcceso;
        this.claveAccesoActual = claveAccesoActual;
        this.claveAccesoNueva = claveAccesoNueva;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getClaveAccesoActual() {
        return claveAccesoActual;
    }

    public void setClaveAccesoActual(String claveAccesoActual) {
        this.claveAccesoActual = claveAccesoActual;
    }

    public String getClaveAccesoNueva() {
        return claveAccesoNueva;
    }

    public void setClaveAccesoNueva(String claveAccesoNueva) {
        this.claveAccesoNueva = claveAccesoNueva;
    }

}
