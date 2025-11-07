package com.procol.empresa.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.NotNull;

import com.procol.chat.entidad.Mensaje;

@Table(name = "postulaciones")
@Entity(name = "empresa_Postulacion")
public class Postulacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_postulacion")
    private Integer idPostulacion;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_postulacion", nullable = false)
    private LocalDateTime fechaPostulacion;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario idUsuario;

    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    @ManyToOne(optional = false)
    private Vacante idVacante;

    // Relación con mensajes del chat
    @OneToMany(mappedBy = "idPostulacion")
    private Set<Mensaje> mensajes = new LinkedHashSet<>();

    public Postulacion() {
    }

    public Postulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Postulacion(Integer idPostulacion, LocalDateTime fechaPostulacion) {
        this.idPostulacion = idPostulacion;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Integer getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(Integer idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDateTime fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    public Set<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Set<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPostulacion != null ? idPostulacion.hashCode() : 0);
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
        Postulacion other = (Postulacion) obj;
        return idPostulacion != null && idPostulacion.equals(other.idPostulacion);
    }

    @Override
    public String toString() {
        return "Postulacion[ idPostulacion=" + idPostulacion + ",fechaPostulacion=" + fechaPostulacion + " ]";
    }

}
