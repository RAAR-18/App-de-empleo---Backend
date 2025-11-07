package com.procol.comun.dto;

public class RolDtoCabecera {

    private int idRol;
    private String nombreRol;

    public RolDtoCabecera() {
    }

    public RolDtoCabecera(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
