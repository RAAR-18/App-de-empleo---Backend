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
import jakarta.persistence.JoinColumns;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


@Table(name = "vacantes")
@Entity(name = "empresa_Vacante")
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_vacante")
    private Integer idVacante;

    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    @ManyToOne(optional = false)
    private Ubicacion idUbicacion;

    @JoinColumns({
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")})
    @ManyToOne(optional = false)
    private RelUsuarioEmpresa relUsuarioEmpresa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "titulo_vacante", nullable = false)
    private String tituloVacante;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "detalle_vacante", nullable = false)
    private String detalleVacante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_inicio_vacante", nullable = false)
    private LocalDateTime fechaInicioVacante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_fin_vacante", nullable = false)
    private LocalDateTime fechaFinVacante;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_vacante", nullable = false)
    private short estadoVacante;

    public Vacante() {
    }

    public Vacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Vacante(Integer idVacante, String tituloVacante, String detalleVacante, LocalDateTime fechaInicioVacante, LocalDateTime fechaFinVacante, short estadoVacante) {
        this.idVacante = idVacante;
        this.tituloVacante = tituloVacante;
        this.detalleVacante = detalleVacante;
        this.fechaInicioVacante = fechaInicioVacante;
        this.fechaFinVacante = fechaFinVacante;
        this.estadoVacante = estadoVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public void setDetalleVacante(String detalleVacante) {
        this.detalleVacante = detalleVacante;
    }

    public LocalDateTime getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public void setFechaInicioVacante(LocalDateTime fechaInicioVacante) {
        this.fechaInicioVacante = fechaInicioVacante;
    }

    public LocalDateTime getFechaFinVacante() {
        return fechaFinVacante;
    }

    public void setFechaFinVacante(LocalDateTime fechaFinVacante) {
        this.fechaFinVacante = fechaFinVacante;
    }

    public short getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(short estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public RelUsuarioEmpresa getRelUsuarioEmpresa() {
        return relUsuarioEmpresa;
    }

    public void setRelUsuarioEmpresa(RelUsuarioEmpresa relUsuarioEmpresa) {
        this.relUsuarioEmpresa = relUsuarioEmpresa;
    }

    public Ubicacion getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacion idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacante != null ? idVacante.hashCode() : 0);
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
        Vacante other = (Vacante) obj;
        return idVacante != null && idVacante.equals(other.idVacante);
    }

    @Override
    public String toString() {
        return "Vacante[ idVacante=" + idVacante + ", tituloVacante=" + tituloVacante + " ]";
    }

}
