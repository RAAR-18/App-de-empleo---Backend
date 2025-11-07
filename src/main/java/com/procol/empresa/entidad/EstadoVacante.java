package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "estados_vacantes")
@Entity(name = "empresa_EstadoVacante")
public class EstadoVacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_vacante")
    private Integer idEstadoVacante;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_estado_vacante", nullable = false)
    private String nombreEstadoVacante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "orden_estado_vacante", nullable = false)
    private short ordenEstadoVacante;

    public EstadoVacante() {
    }

    public EstadoVacante(Integer idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
    }

    public EstadoVacante(Integer idEstadoVacante, String nombreEstadoVacante, short ordenEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
        this.nombreEstadoVacante = nombreEstadoVacante;
        this.ordenEstadoVacante = ordenEstadoVacante;
    }

    public Integer getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public void setIdEstadoVacante(Integer idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
    }

    public String getNombreEstadoVacante() {
        return nombreEstadoVacante;
    }

    public void setNombreEstadoVacante(String nombreEstadoVacante) {
        this.nombreEstadoVacante = nombreEstadoVacante;
    }

    public short getOrdenEstadoVacante() {
        return ordenEstadoVacante;
    }

    public void setOrdenEstadoVacante(short ordenEstadoVacante) {
        this.ordenEstadoVacante = ordenEstadoVacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoVacante != null ? idEstadoVacante.hashCode() : 0);
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
        EstadoVacante other = (EstadoVacante) obj;
        return idEstadoVacante != null && idEstadoVacante.equals(other.idEstadoVacante);
    }

    @Override
    public String toString() {
        return "EstadoVacante[ idEstadoVacante=" + idEstadoVacante + ", nombreEstadoVacante= " + nombreEstadoVacante + " ]";
    }

}
