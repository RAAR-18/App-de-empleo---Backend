package com.procol.vacante.entidad;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;

@Table(name = "estados_vacantes")
@Entity(name = "vacante_EstadoVacante")
public class EstadoVacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_vacante")
    private Integer idEstadoVacante;

    @Column(name = "nombre_estado_vacante", nullable = false, length = 100)
    private String nombreEstadoVacante;

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
        return "EstadoVacante["
                + "idEstadoVacante=" + idEstadoVacante
                + ", nombreEstadoVacante='" + nombreEstadoVacante
                + ", ordenEstadoVacante=" + ordenEstadoVacante
                + ']';
    }
}
