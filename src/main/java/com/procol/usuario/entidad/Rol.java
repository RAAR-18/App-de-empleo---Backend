package com.procol.usuario.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "roles")
@Entity(name = "usuario_Rol")
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rol")
    private Integer idRol;

    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 150)
    @Column(name = "nombre_rol", nullable = false, unique = true)
    private String nombreRol;

    @NotNull
    @Basic(optional = false)
    @Column(name = "estado_rol")
    private short estadoRol;

    public Rol() {
    }

    public Rol(Integer idRol) {
        this.idRol = idRol;
    }

    public Rol(Integer idRol, String nombreRol, short estadoRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.estadoRol = estadoRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public short getEstadoRol() {
        return estadoRol;
    }

    public void setEstadoRol(short estadoRol) {
        this.estadoRol = estadoRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        return !((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol)));
    }

    @Override
    public String toString() {
        return "Rol["
                + "idRol=" + idRol
                + ", nombreRol=" + nombreRol
                + ", estadoRol=" + estadoRol
                + " ]";
    }

}
