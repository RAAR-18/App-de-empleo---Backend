package com.procol.vacante.entidad;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;

@Table(name = "ubicaciones")
@Entity(name = "vacante_Ubicacion")
public class Ubicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    @JoinColumn(name = "id_padre_ubicacion", referencedColumnName = "id_ubicacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ubicacion idPadreUbicacion;

    @Column(name = "nombre_ubicacion", nullable = false, length = 150)
    private String nombreUbicacion;

    @Column(name = "id_dane_ubicacion", nullable = false, length = 30)
    private String idDaneUbicacion;

    @Column(name = "longitud_ubicacion", nullable = false, length = 30)
    private String longitudUbicacion;

    @Column(name = "latitud_ubicacion", nullable = false, length = 30)
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
        return "Ubicacion[ "
                + "idUbicacion= " + idUbicacion
                + "idPadreUbicacion= " + (idPadreUbicacion != null ? idPadreUbicacion.getIdUbicacion() : "null")
                + ", nombreUbicacion= " + nombreUbicacion
                + ", idDaneUbicacion= " + idDaneUbicacion
                + ", longitudUbicacion= " + longitudUbicacion
                + ", latitudUbicacion= " + latitudUbicacion
                + " ]";
    }

}
