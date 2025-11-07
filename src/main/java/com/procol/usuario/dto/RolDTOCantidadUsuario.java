package com.procol.usuario.dto;

public class RolDTOCantidadUsuario {

    private Integer idRol;
    private String nombreRol;
    private Short estadoRol;
    private Long cantidadUsuarios;

    public RolDTOCantidadUsuario(Integer idRol, String nombreRol, Short estadoRol, Long cantidadUsuarios) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
        this.cantidadUsuarios = cantidadUsuarios;
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

    public Long getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(Long cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }

    @Override
    public String toString() {
        return "RolDTOCantidadUsuario["
                + "idRol=" + idRol
                + ", nombreRol=" + nombreRol
                + ", estadoRol=" + estadoRol
                + ", cantidadUsuarios=" + cantidadUsuarios
                + ']';
    }

}
