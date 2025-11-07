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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.NotNull;

@Table(name = "citas")
@Entity(name = "empresa_Cita")
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cita")
    private Integer idCita;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_cita", nullable = false)
    private LocalDateTime fechaCita;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cita", nullable = false)
    private short estadoCita;

    @JoinColumn(name = "id_postulacion", referencedColumnName = "id_postulacion")
    @ManyToOne(optional = false)
    private Postulacion idPostulacion;

    public Cita() {
    }

    public Cita(Integer idCita) {
        this.idCita = idCita;
    }

    public Cita(Integer idCita, LocalDateTime fechaCita, short estadoCita) {
        this.idCita = idCita;
        this.fechaCita = fechaCita;
        this.estadoCita = estadoCita;
    }

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDateTime fechaCita) {
        this.fechaCita = fechaCita;
    }

    public short getEstadoCita() {
        return estadoCita;
    }

    public void setEstadoCita(short estadoCita) {
        this.estadoCita = estadoCita;
    }

    public Postulacion getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Postulacion idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCita != null ? idCita.hashCode() : 0);
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
        Cita other = (Cita) obj;
        return idCita != null && idCita.equals(other.idCita);
    }

    @Override
    public String toString() {
        return "Citas[ idCita=" + idCita + ", idPostulacion=" + idPostulacion + " ]";
    }

}
