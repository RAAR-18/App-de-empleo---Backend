package com.procol.empresa.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.NotNull;

@Table(name = "historial_estados_vacantes")
@Entity(name = "empresa_HistorialEstadoVacante")
public class HistorialEstadoVacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_estado_vacante")
    private Integer idHistorialEstadoVacante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_historial_estado_vacante", nullable = false)
    private LocalDateTime fechaHistorialEstadoVacante;

    @JoinColumn(name = "id_estado_vacante", referencedColumnName = "id_estado_vacante")
    @ManyToOne(optional = false)
    private EstadoVacante idEstadoVacante;

    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    @ManyToOne(optional = false)
    private Vacante idVacante;

    public HistorialEstadoVacante() {
    }

    public HistorialEstadoVacante(Integer idHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
    }

    public HistorialEstadoVacante(Integer idHistorialEstadoVacante, LocalDateTime fechaHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

    public Integer getIdHistorialEstadoVacante() {
        return idHistorialEstadoVacante;
    }

    public void setIdHistorialEstadoVacante(Integer idHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
    }

    public LocalDateTime getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public void setFechaHistorialEstadoVacante(LocalDateTime fechaHistorialEstadoVacante) {
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

    public EstadoVacante getIdEstadoVacante() {
        return idEstadoVacante;
    }

    public void setIdEstadoVacante(EstadoVacante idEstadoVacante) {
        this.idEstadoVacante = idEstadoVacante;
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
        hash += (idHistorialEstadoVacante != null ? idHistorialEstadoVacante.hashCode() : 0);
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
        HistorialEstadoVacante other = (HistorialEstadoVacante) obj;
        return idHistorialEstadoVacante != null && idHistorialEstadoVacante.equals(other.idHistorialEstadoVacante);
    }

    @Override
    public String toString() {
        return "HistorialEstadoVacante[ idHistorialEstadoVacante=" + idHistorialEstadoVacante + ", fechaHistorialEstadoVacante=" + fechaHistorialEstadoVacante + " ]";
    }

}
