package com.procol.vacante.entidad;

import com.procol.vacante.entidad.pk.InteresesPK;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "intereses")
@Entity(name = "vacante_Intereses")
public class Intereses implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    protected InteresesPK interesesPK;

    @Column(name = "tipo_interes", nullable = false)
    private short tipoInteres;

    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)

    private Empresa empresa;
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario usuario;

    public Intereses() {
    }

    public Intereses(InteresesPK interesesPK) {
        this.interesesPK = interesesPK;
    }

    public Intereses(InteresesPK interesesPK, short tipoInteres) {
        this.interesesPK = interesesPK;
        this.tipoInteres = tipoInteres;
    }

    public Intereses(int idEmpresa, int idUsuario) {
        this.interesesPK = new InteresesPK(idEmpresa, idUsuario);
    }

    public InteresesPK getInteresesPK() {
        return interesesPK;
    }

    public void setInteresesPK(InteresesPK interesesPK) {
        this.interesesPK = interesesPK;
    }

    public short getTipoInteres() {
        return tipoInteres;
    }

    public void setTipoInteres(short tipoInteres) {
        this.tipoInteres = tipoInteres;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresas) {
        this.empresa = empresas;
    }

    public Usuario getUsuarios() {
        return usuario;
    }

    public void setUsuarios(Usuario usuarios) {
        this.usuario = usuarios;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (interesesPK != null ? interesesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Intereses)) {
            return false;
        }
        Intereses other = (Intereses) object;
        return !((this.interesesPK == null && other.interesesPK != null) || (this.interesesPK != null && !this.interesesPK.equals(other.interesesPK)));
    }

    @Override
    public String toString() {
        return "empresaId=" + (interesesPK != null ? interesesPK.getIdEmpresa() : null)
                + ", usuarioId=" + (interesesPK != null ? interesesPK.getIdUsuario() : null)
                + ", tipoInteres=" + tipoInteres;
    }
}
