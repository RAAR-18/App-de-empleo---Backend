package com.procol.usuario.dto;

import java.util.List;

public class UsuarioRolDTORespuesta {

    private Integer idUsuario;
    private List<RolDTOSinEstado> rolesAsignados;

    public UsuarioRolDTORespuesta(Integer idUsuario, List<RolDTOSinEstado> rolesAsignados) {
        this.idUsuario = idUsuario;
        this.rolesAsignados = rolesAsignados;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<RolDTOSinEstado> getRolesAsignados() {
        return rolesAsignados;
    }

    public void setRolesAsignados(List<RolDTOSinEstado> rolesAsignados) {
        this.rolesAsignados = rolesAsignados;
    }

    @Override
    public String toString() {
        return "UsuarioRolAsignadoDTO["
                + "idUsuario=" + idUsuario
                + ", rolesAsignados=" + rolesAsignados
                + "]";
    }

}
