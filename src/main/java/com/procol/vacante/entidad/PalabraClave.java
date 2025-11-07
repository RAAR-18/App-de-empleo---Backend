package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Table(name = "palabras_claves")
@Entity(name = "vacante_PalabraClave")
public class PalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_palabra_clave")
    private Integer idPalabraClave;

    @JoinColumn(name = "id_area", referencedColumnName = "id_area")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Area idArea;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "texto_palabra_clave", nullable = false, length = 150)
    private String textoPalabraClave;

    public PalabraClave() {
    }

    public PalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    public PalabraClave(Integer idPalabraClave, String textoPalabraClave) {
        this.idPalabraClave = idPalabraClave;
        this.textoPalabraClave = textoPalabraClave;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    public String getTextoPalabraClave() {
        return textoPalabraClave;
    }

    public void setTextoPalabraClave(String textoPalabraClave) {
        this.textoPalabraClave = textoPalabraClave;
    }

    public Area getIdArea() {
        return idArea;
    }

    public void setIdArea(Area idArea) {
        this.idArea = idArea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPalabraClave != null ? idPalabraClave.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PalabraClave)) {
            return false;
        }
        PalabraClave other = (PalabraClave) object;
        return !((this.idPalabraClave == null && other.idPalabraClave != null) || (this.idPalabraClave != null && !this.idPalabraClave.equals(other.idPalabraClave)));
    }

    @Override
    public String toString() {
        return "PalabraClave["
                + "idPalabraClave=" + idPalabraClave
                + ", areaId=" + (idArea != null ? idArea.getIdArea() : null)
                + ", textoPalabraClave='" + textoPalabraClave + ']';
    }

}
