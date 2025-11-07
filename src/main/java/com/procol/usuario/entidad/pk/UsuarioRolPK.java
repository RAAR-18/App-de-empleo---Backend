package com.procol.usuario.entidad.pk;

import java.util.Objects;
import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuarioRolPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_rol")
    private Integer idRol;

    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    public UsuarioRolPK() {
    }

    public UsuarioRolPK(int idRol, int idUsuario) {
        this.idRol = idRol;
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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
        hash += (int) idRol;
        hash += (int) idUsuario;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioRolPK)) {
            return false;
        }
        UsuarioRolPK other = (UsuarioRolPK) object;
        if (!Objects.equals(this.idRol, other.idRol)) {
            return false;
        }
        return Objects.equals(this.idUsuario, other.idUsuario);
    }

    @Override
    public String toString() {
        return "UsuarioRolPK["
                + "idRol=" + idRol
                + ", idUsuario=" + idUsuario
                + "] ";
    }

}
