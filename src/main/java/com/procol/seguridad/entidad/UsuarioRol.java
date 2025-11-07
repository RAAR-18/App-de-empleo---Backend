package com.procol.seguridad.entidad;

import java.io.Serializable;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.UniqueConstraint;

import com.procol.seguridad.entidad.pk.UsuarioRolPK;

@Table(name = "usuarios_roles",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"id_usuario", "id_rol"})
        })
@Entity(name = "seguridad_UsuarioRol")
public class UsuarioRol implements Serializable {

    @EmbeddedId
    private UsuarioRolPK usuarioRolPK;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    public UsuarioRol() {
    }

    public UsuarioRol(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public UsuarioRol(int idRol, int idUsuario) {
        this.usuarioRolPK = new UsuarioRolPK(idRol, idUsuario);
    }

    public UsuarioRolPK getUsuarioRolPK() {
        return usuarioRolPK;
    }

    public void setUsuarioRolPK(UsuarioRolPK usuarioRolPK) {
        this.usuarioRolPK = usuarioRolPK;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        return (usuarioRolPK != null ? usuarioRolPK.hashCode() : 0);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UsuarioRol other)) {
            return false;
        }
        return this.usuarioRolPK != null && this.usuarioRolPK.equals(other.usuarioRolPK);
    }

    @Override
    public String toString() {
        return "UsuarioRol{" + "usuarioRolPK=" + usuarioRolPK + '}';
    }

}
