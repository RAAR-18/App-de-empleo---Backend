package com.procol.seguridad.dto;

public class CredencialesDTO {

    private String correoAcceso;
    private String claveAcceso;

    public CredencialesDTO() {
    }

    public CredencialesDTO(String correoAcceso, String claveAcceso) {
        this.correoAcceso = correoAcceso;
        this.claveAcceso = claveAcceso;
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

}
