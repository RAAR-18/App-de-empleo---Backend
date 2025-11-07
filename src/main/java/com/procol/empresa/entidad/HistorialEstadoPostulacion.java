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

@Table(name = "historial_estados_postulaciones")
@Entity(name = "empresa_HistorialEstadoPostulacion")
public class HistorialEstadoPostulacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_historial_postulacion")
    private Integer idHistorialPostulacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_historial_postulacion", nullable = false)
    private LocalDateTime fechaHistorialPostulacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "detalle_historial_postulacion", nullable = false, columnDefinition = "TEXT")
    private String detalleHistorialPostulacion;

    @JoinColumn(name = "id_estado_postulacion", referencedColumnName = "id_estado_postulacion")
    @ManyToOne(optional = false)
    private EstadoPostulacion idEstadoPostulacion;

    @JoinColumn(name = "id_postulacion", referencedColumnName = "id_postulacion")
    @ManyToOne(optional = false)
    private Postulacion idPostulacion;

    public HistorialEstadoPostulacion() {
    }

    public HistorialEstadoPostulacion(Integer idHistorialPostulacion) {
        this.idHistorialPostulacion = idHistorialPostulacion;
    }

    public HistorialEstadoPostulacion(Integer idHistorialPostulacion, LocalDateTime fechaHistorialPostulacion, String detalleHistorialPostulacion) {
        this.idHistorialPostulacion = idHistorialPostulacion;
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
    }

    public Integer getIdHistorialPostulacion() {
        return idHistorialPostulacion;
    }

    public void setIdHistorialPostulacion(Integer idHistorialPostulacion) {
        this.idHistorialPostulacion = idHistorialPostulacion;
    }

    public LocalDateTime getFechaHistorialPostulacion() {
        return fechaHistorialPostulacion;
    }

    public void setFechaHistorialPostulacion(LocalDateTime fechaHistorialPostulacion) {
        this.fechaHistorialPostulacion = fechaHistorialPostulacion;
    }

    public String getDetalleHistorialPostulacion() {
        return detalleHistorialPostulacion;
    }

    public void setDetalleHistorialPostulacion(String detalleHistorialPostulacion) {
        this.detalleHistorialPostulacion = detalleHistorialPostulacion;
    }

    public EstadoPostulacion getIdEstadoPostulacion() {
        return idEstadoPostulacion;
    }

    public void setIdEstadoPostulacion(EstadoPostulacion idEstadoPostulacion) {
        this.idEstadoPostulacion = idEstadoPostulacion;
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
        hash += (idHistorialPostulacion != null ? idHistorialPostulacion.hashCode() : 0);
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
        HistorialEstadoPostulacion other = (HistorialEstadoPostulacion) obj;
        return idHistorialPostulacion != null && idHistorialPostulacion.equals(other.idHistorialPostulacion);
    }

    @Override
    public String toString() {
        return "HistorialEstadoPostulacion[ idHistorialPostulacion="
                + idHistorialPostulacion + ", idPostulacion=" + idPostulacion + " ]";
    }

}
