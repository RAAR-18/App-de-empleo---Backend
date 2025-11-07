package com.procol.vacante.entidad;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "tipos_contratos")
@Entity(name = "vacante_TipoContrato")
public class TipoContrato implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_contrato")
    private Integer idTipoContrato;

    @Column(name = "nombre_tipo_contrato", nullable = false, length = 150)
    private String nombreTipoContrato;

    public TipoContrato() {
    }

    public TipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public TipoContrato(Integer idTipoContrato, String nombreTipoContrato) {
        this.idTipoContrato = idTipoContrato;
        this.nombreTipoContrato = nombreTipoContrato;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public String getNombreTipoContrato() {
        return nombreTipoContrato;
    }

    public void setNombreTipoContrato(String nombreTipoContrato) {
        this.nombreTipoContrato = nombreTipoContrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoContrato != null ? idTipoContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoContrato)) {
            return false;
        }
        TipoContrato other = (TipoContrato) object;
        return !((this.idTipoContrato == null && other.idTipoContrato != null) || (this.idTipoContrato != null && !this.idTipoContrato.equals(other.idTipoContrato)));
    }

    @Override
    public String toString() {
        return "TipoContrato["
                + "idTipoContrato=" + idTipoContrato
                + ", nombreTipoContrato=" + nombreTipoContrato
                + ']';
    }

}
