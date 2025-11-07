package com.procol.usuario.dto;

public class RolDTO {

    private Integer idRol;
    private String nombreRol;
    private Short estadoRol;

    public RolDTO(Integer idRol, String nombreRol, Short estadoRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public Short getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(Short estadoRol) {
        this.estadoRol = estadoRol;
    }

    @Override
    public String toString() {
        return "RolDTO["
                + "idRol=" + idRol
                + ", nombreRol=" + nombreRol
                + ", estadoRol=" + estadoRol
                + "]";
    }

}
