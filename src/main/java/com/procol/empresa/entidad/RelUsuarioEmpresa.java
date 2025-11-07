package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.UniqueConstraint;

import jakarta.validation.constraints.NotNull;
import com.procol.empresa.entidad.pk.RelUsuarioEmpresaPK;

@Table(
        name = "rel_usuarios_empresas",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_empresa"})
)
@Entity(name = "empresa_RelUsuarioEmpresa")
public class RelUsuarioEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected RelUsuarioEmpresaPK relUsuarioEmpresaPK;

    @Basic(optional = false)
    @NotNull
    @Column(name = "permiso_rel_usuario_empresa")
    private short permisoRelUsuarioEmpresa;

    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresa empresa;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usuario usuario;

    public RelUsuarioEmpresa() {
    }

    public RelUsuarioEmpresa(RelUsuarioEmpresaPK relUsuarioEmpresaPK) {
        this.relUsuarioEmpresaPK = relUsuarioEmpresaPK;
    }

    public RelUsuarioEmpresa(RelUsuarioEmpresaPK relUsuarioEmpresaPK, short permisoRelUsuarioEmpresa) {
        this.relUsuarioEmpresaPK = relUsuarioEmpresaPK;
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    public RelUsuarioEmpresa(int idUsuario, int idEmpresa) {
        this.relUsuarioEmpresaPK = new RelUsuarioEmpresaPK(idUsuario, idEmpresa);
    }

    public RelUsuarioEmpresaPK getRelUsuarioEmpresaPK() {
        return relUsuarioEmpresaPK;
    }

    public void setRelUsuarioEmpresaPK(RelUsuarioEmpresaPK relUsuarioEmpresaPK) {
        this.relUsuarioEmpresaPK = relUsuarioEmpresaPK;
    }

    public short getPermisoRelUsuarioEmpresa() {
        return permisoRelUsuarioEmpresa;
    }

    public void setPermisoRelUsuarioEmpresa(short permisoRelUsuarioEmpresa) {
        this.permisoRelUsuarioEmpresa = permisoRelUsuarioEmpresa;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (relUsuarioEmpresaPK != null ? relUsuarioEmpresaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RelUsuarioEmpresa other = (RelUsuarioEmpresa) obj;
        return relUsuarioEmpresaPK != null && relUsuarioEmpresaPK.equals(other.relUsuarioEmpresaPK);
    }

    @Override
    public String toString() {
        return "RelUsuarioEmpresa[ relUsuarioEmpresaPK=" + relUsuarioEmpresaPK + " ]";
    }

}
