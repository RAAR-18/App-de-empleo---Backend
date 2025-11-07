package com.procol.empresa.dto;

public class UsuarioEmpresaDTO {

    private Integer idUsuario;
    private Integer idEmpresa;
    private Short permisoRelUsuarioEmpresa;

    public UsuarioEmpresaDTO() {
    }

    public UsuarioEmpresaDTO(Integer idUsuario, Integer idEmpresa, Short permisoRelUsuarioEmpresa) {
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Short getPermisoRelUsuarioEmpresa() {
        return permisoRelUsuarioEmpresa;
    }

    public void setPermisoRelUsuarioEmpresa(Short permisoRelUsuarioEmpresa) {
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    @Override
    public String toString() {
        return "UsuarioEmpresaDTO["
                + "idUsuario=" + idUsuario
                + ", idEmpresa=" + idEmpresa
                + ", permisoRelUsuarioEmpresa=" + permisoRelUsuarioEmpresa
                + "]";
    }

}
