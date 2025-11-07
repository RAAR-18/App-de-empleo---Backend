package com.procol.empresa.entidad.pk;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class RelUsuarioEmpresaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    public RelUsuarioEmpresaPK() {
    }

    public RelUsuarioEmpresaPK(int idUsuario, int idEmpresa) {
        this.idUsuario = idUsuario;
        this.idEmpresa = idEmpresa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idEmpresa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RelUsuarioEmpresaPK other = (RelUsuarioEmpresaPK) obj;
        return Objects.equals(idUsuario, other.idUsuario)
                && Objects.equals(idEmpresa, other.idEmpresa);
    }

    @Override
    public String toString() {
        return "RelUsuariosEmpresasPK[ idUsuario=" + idUsuario + ", idEmpresa=" + idEmpresa + " ]";
    }

}
