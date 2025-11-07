package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Table(name = "jornadas")
@Entity(name = "vacante_Jornada")
public class Jornada implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jornada")
    private Integer idJornada;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_jornada", nullable = false, length = 150)
    private String nombreJornada;

    public Jornada() {
    }

    public Jornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Jornada(Integer idJornada, String nombreJornada) {
        this.idJornada = idJornada;
        this.nombreJornada = nombreJornada;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public void setNombreJornada(String nombreJornada) {
        this.nombreJornada = nombreJornada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJornada != null ? idJornada.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Jornada)) {
            return false;
        }
        Jornada other = (Jornada) object;
        return !((this.idJornada == null && other.idJornada != null) || (this.idJornada != null && !this.idJornada.equals(other.idJornada)));
    }

    @Override
    public String toString() {
        return "idJornada=" + idJornada + ", nombreJornada=" + nombreJornada;
    }

}
