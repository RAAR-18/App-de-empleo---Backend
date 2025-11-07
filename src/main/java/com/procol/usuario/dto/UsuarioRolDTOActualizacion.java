package com.procol.usuario.dto;

import java.util.Set;

public class UsuarioRolDTOActualizacion {

    private Integer idUsuario;
    private Set<Integer> idRol;

    public UsuarioRolDTOActualizacion(Integer idUsuario, Set<Integer> idRol) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Set<Integer> getIdRol() {
        return idRol;
    }

    public void setIdRol(Set<Integer> idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioRolActualizacionDTO["
                + "idUsuario=" + idUsuario
                + ", idRol=" + idRol
                + "]";
    }

}
