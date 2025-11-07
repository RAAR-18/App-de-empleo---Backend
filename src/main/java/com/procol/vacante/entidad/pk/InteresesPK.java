package com.procol.vacante.entidad.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class InteresesPK implements Serializable {

    @NotNull
    @Column(name = "id_empresa")
    private Integer idEmpresa;

    @NotNull
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public InteresesPK() {
    }

    public InteresesPK(int idEmpresa, int idUsuario) {
        this.idEmpresa = idEmpresa;
        this.idUsuario = idUsuario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idEmpresa;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof InteresesPK)) {
            return false;
        }
        InteresesPK other = (InteresesPK) object;
        if (this.idEmpresa != other.idEmpresa) {
            return false;
        }
        return this.idUsuario == other.idUsuario;
    }

    @Override
    public String toString() {
        return "InteresesPK[ idEmpresa=" + idEmpresa + ", idUsuario=" + idUsuario + " ]";
    }

}
