package com.procol.usuario.dto;

public class RolDTOSinEstado {

    private Integer idRol;
    private String nombreRol;

    public RolDTOSinEstado(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
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

    @Override
    public String toString() {
        return "RolDTOSinEstado["
                + "idRol=" + idRol
                + ", nombreRol=" + nombreRol
                + "]";
    }

}
