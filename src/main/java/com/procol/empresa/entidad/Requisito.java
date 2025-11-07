package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


@Table(name = "requisitos")
@Entity(name = "empresa_Requisito")
public class Requisito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_requisito")
    private Integer idRequisito;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "titulo_requisito", nullable = false)
    private String tituloRequisito;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "detalle_requisito", nullable = false, columnDefinition = "TEXT")
    private String detalleRequisito;

    @Basic(optional = false)
    @NotNull
    @Column(name = "orden_requisito")
    private short ordenRequisito;

    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    @ManyToOne(optional = false)
    private Vacante idVacante;

    public Requisito() {
    }

    public Requisito(Integer idRequisito) {
        this.idRequisito = idRequisito;
    }

    public Requisito(Integer idRequisito, String tituloRequisito, String detalleRequisito, short ordenRequisito) {
        this.idRequisito = idRequisito;
        this.tituloRequisito = tituloRequisito;
        this.detalleRequisito = detalleRequisito;
        this.ordenRequisito = ordenRequisito;
    }

    public Integer getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Integer idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getTituloRequisito() {
        return tituloRequisito;
    }

    public void setTituloRequisito(String tituloRequisito) {
        this.tituloRequisito = tituloRequisito;
    }

    public String getDetalleRequisito() {
        return detalleRequisito;
    }

    public void setDetalleRequisito(String detalleRequisito) {
        this.detalleRequisito = detalleRequisito;
    }

    public short getOrdenRequisito() {
        return ordenRequisito;
    }

    public void setOrdenRequisito(short ordenRequisito) {
        this.ordenRequisito = ordenRequisito;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisito != null ? idRequisito.hashCode() : 0);
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
        Requisito other = (Requisito) obj;
        return idRequisito != null && idRequisito.equals(other.idRequisito);
    }

    @Override
    public String toString() {
        return "Requisito[ idRequisito=" + idRequisito + ", tituloRequisito=" + tituloRequisito + " ]";
    }

}
