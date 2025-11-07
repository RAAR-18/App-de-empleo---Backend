package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "estados_postulaciones")
@Entity(name = "empresa_EstadoPostulacion")
public class EstadoPostulacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_estado_postulacion")
    private Integer idEstadoPostulacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_estado_postulacion", nullable = false)
    private String nombreEstadoPostulacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "orden_estado_postulacion", nullable = false)
    private short ordenEstadoPostulacion;

    public EstadoPostulacion() {
    }

    public EstadoPostulacion(Integer idEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
    }

    public EstadoPostulacion(Integer idEstadoPostulacion, String nombreEstadoPostulacion, short ordenEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
        this.nombreEstadoPostulacion = nombreEstadoPostulacion;
        this.ordenEstadoPostulacion = ordenEstadoPostulacion;
    }

    public Integer getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public void setIdEstadoPostulacion(Integer idEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
    }

    public String getNombreEstadoPostulacion() {
        return nombreEstadoPostulacion;
    }

    public void setNombreEstadoPostulacion(String nombreEstadoPostulacion) {
        this.nombreEstadoPostulacion = nombreEstadoPostulacion;
    }

    public short getOrdenEstadoPostulacion() {
        return ordenEstadoPostulacion;
    }

    public void setOrdenEstadoPostulacion(short ordenEstadoPostulacion) {
        this.ordenEstadoPostulacion = ordenEstadoPostulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadoPostulacion != null ? idEstadoPostulacion.hashCode() : 0);
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
        EstadoPostulacion other = (EstadoPostulacion) obj;
        return idEstadoPostulacion != null && idEstadoPostulacion.equals(other.idEstadoPostulacion);
    }

    @Override
    public String toString() {
        return "EstadoPostulacion[ idEstadoPostulacion=" + idEstadoPostulacion + ", nombreEstadoPostulacion=" + nombreEstadoPostulacion + " ]";
    }

}
