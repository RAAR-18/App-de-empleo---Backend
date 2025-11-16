package com.procol.perfil.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;

@Table(name = "talentos")
@Entity(name = "perfil-Talento")
public class Talento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_talento")
    private Integer idTalento;

    @Size(max = 100)
    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100, unique = true)
    private String nombre;

    public Talento() {}

    public Talento(Integer idTalento, String nombre) {
        this.idTalento = idTalento;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdTalento() {
        return idTalento;
    }

    public void setIdTalento(Integer idTalento) {
        this.idTalento = idTalento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTalento != null ? idTalento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Talento)) return false;
        Talento other = (Talento) object;
        return idTalento != null && idTalento.equals(other.idTalento);
    }

    @Override
    public String toString() {
        return "Talento[ idTalento=" + idTalento + " nombre=" + nombre + " ]";
    }
}