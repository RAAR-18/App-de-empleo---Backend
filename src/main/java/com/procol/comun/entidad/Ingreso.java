package com.procol.comun.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotNull;

@Table(name = "ingresos")
@Entity(name = "comun_Ingreso")
public class Ingreso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ingreso")
    private Integer idIngreso;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Acceso idUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_ingreso", nullable = false)
    private LocalDateTime fechaIngreso;

    public Ingreso() {
    }

    public Ingreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public Ingreso(Integer idIngreso, LocalDateTime fechaIngreso) {
        this.idIngreso = idIngreso;
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdIngreso() {
        return idIngreso;
    }

    public void setIdIngreso(Integer idIngreso) {
        this.idIngreso = idIngreso;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Acceso getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Acceso idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idIngreso != null ? idIngreso.hashCode() : 0);
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
        Ingreso other = (Ingreso) obj;
        return idIngreso != null && idIngreso.equals(other.idIngreso);
    }

    @Override
    public String toString() {
        return "Ingreso[ idIngreso=" + idIngreso + " idUsuario=" + idUsuario + "]";
    }

}
