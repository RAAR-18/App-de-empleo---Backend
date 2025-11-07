package com.procol.usuario.dto;

public class AccesoDTOCrear {

    private Integer idUsuario;
    private String correoAcceso;
    private String claveAcceso;
    private String uuidAcceso;

    public AccesoDTOCrear() {
    }

    public AccesoDTOCrear(
            Integer idUsuario, String correoAcceso, String claveAcceso, String uuidAcceso
    ) {
        this.idUsuario = idUsuario;
        this.correoAcceso = correoAcceso;
        this.claveAcceso = claveAcceso;
        this.uuidAcceso = uuidAcceso;
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
        return "AccesoDTOCrear["
                + "idUsuario=" + idUsuario
                + ", correoAcceso=" + correoAcceso
                + ", claveAcceso=" + claveAcceso
                + ", uuidAcceso=" + uuidAcceso
                + "]";
    }

}
