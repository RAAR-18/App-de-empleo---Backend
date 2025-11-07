package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Table(
        name = "historial_estados_vacantes",
        indexes = @Index(name = "idx_historial_estado_vacante", columnList = "id_vacante")
)
@Entity(name = "vacante_HistorialEstadoVacante")
public class HistorialEstadoVacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial_estado_vacante")
    private Integer idHistorialEstadoVacante;

    @NotNull
    @Column(name = "fecha_historial_estado_vacante", nullable = false)
    private OffsetDateTime fechaHistorialEstadoVacante;

    @JoinColumn(name = "id_estado_vacante", referencedColumnName = "id_estado_vacante")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoVacante idEstadoVacante;

    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vacante idVacante;

    public HistorialEstadoVacante() {
    }

    public HistorialEstadoVacante(Integer idHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
    }

    public HistorialEstadoVacante(
            Integer idHistorialEstadoVacante, OffsetDateTime fechaHistorialEstadoVacante
    ) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
        this.fechaHistorialEstadoVacante = fechaHistorialEstadoVacante;
    }

    public Integer getIdHistorialEstadoVacante() {
        return idHistorialEstadoVacante;
    }

    public void setIdHistorialEstadoVacante(Integer idHistorialEstadoVacante) {
        this.idHistorialEstadoVacante = idHistorialEstadoVacante;
    }

    public OffsetDateTime getFechaHistorialEstadoVacante() {
        return fechaHistorialEstadoVacante;
    }

    public void setFechaHistorialEstadoVacante(OffsetDateTime fechaHistorialEstadoVacante) {
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
    public boolean equals(Object object) {
        if (!(object instanceof HistorialEstadoVacante)) {
            return false;
        }
        HistorialEstadoVacante other = (HistorialEstadoVacante) object;
        return !((this.idHistorialEstadoVacante == null && other.idHistorialEstadoVacante != null) || (this.idHistorialEstadoVacante != null && !this.idHistorialEstadoVacante.equals(other.idHistorialEstadoVacante)));
    }

    @Override
    public String toString() {
        return "idHistorialEstadoVacante=" + idHistorialEstadoVacante
                + ", fecha=" + fechaHistorialEstadoVacante
                + ", estadoVacanteId=" + (idEstadoVacante != null ? idEstadoVacante.getIdEstadoVacante() : null)
                + ", vacanteId=" + (idVacante != null ? idVacante.getIdVacante() : null);
    }
}
