package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "ubicaciones")
@Entity(name = "empresa_Ubicacion")
public class Ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @JoinColumn(name = "id_padre_ubicacion", referencedColumnName = "id_ubicacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ubicacion idPadreUbicacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_ubicacion", nullable = false)
    private String nombreUbicacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id_dane_ubicacion")
    private String idDaneUbicacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "longitud_ubicacion")
    private String longitudUbicacion;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "latitud_ubicacion")
    private String latitudUbicacion;

    public Ubicacion() {
    }

    public Ubicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Ubicacion(Integer idUbicacion, String nombreUbicacion, String idDaneUbicacion, String longitudUbicacion, String latitudUbicacion) {
        this.idUbicacion = idUbicacion;
        this.nombreUbicacion = nombreUbicacion;
        this.idDaneUbicacion = idDaneUbicacion;
        this.longitudUbicacion = longitudUbicacion;
        this.latitudUbicacion = latitudUbicacion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getIdDaneUbicacion() {
        return idDaneUbicacion;
    }

    public void setIdDaneUbicacion(String idDaneUbicacion) {
        this.idDaneUbicacion = idDaneUbicacion;
    }

    public String getLongitudUbicacion() {
        return longitudUbicacion;
    }

    public void setLongitudUbicacion(String longitudUbicacion) {
        this.longitudUbicacion = longitudUbicacion;
    }

    public String getLatitudUbicacion() {
        return latitudUbicacion;
    }

    public void setLatitudUbicacion(String latitudUbicacion) {
        this.latitudUbicacion = latitudUbicacion;
    }

    public Ubicacion getIdPadreUbicacion() {
        return idPadreUbicacion;
    }

    public void setIdPadreUbicacion(Ubicacion idPadreUbicacion) {
        this.idPadreUbicacion = idPadreUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUbicacion != null ? idUbicacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        return !((this.idUbicacion == null && other.idUbicacion != null) || (this.idUbicacion != null && !this.idUbicacion.equals(other.idUbicacion)));
    }

    @Override
    public String toString() {
        return "Ubicacion[ idUbicacion=" + idUbicacion + ", nombreUbicacion= " + nombreUbicacion + " ]";
    }

}
