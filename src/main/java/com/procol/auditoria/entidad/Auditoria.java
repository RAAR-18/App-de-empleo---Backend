package com.procol.auditoria.entidad;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "auditorias")
@Entity(name = "auditoria_Auditoria")
public class Auditoria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre_entidad_auditoria", nullable = false)
    private String nombreEntidadAuditoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_referencia_auditoria")
    private int idReferenciaAuditoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "id_usuario_auditoria")
    private int idUsuarioAuditoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_auditoria", nullable = false)
    private LocalDateTime fechaAuditoria;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "tipo_cambio_auditoria", nullable = false)
    private String tipoCambioAuditoria;

    @Basic(optional = false)
    @NotNull
    @Column(name = "comentario_auditoria", nullable = false, columnDefinition = "TEXT")
    private String comentarioAuditoria;

    public Auditoria() {
    }

    public Auditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public Auditoria(Integer idAuditoria, String nombreEntidadAuditoria, int idReferenciaAuditoria, int idUsuarioAuditoria, LocalDateTime fechaAuditoria, String tipoCambioAuditoria, String comentarioAuditoria) {
        this.idAuditoria = idAuditoria;
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
        this.idReferenciaAuditoria = idReferenciaAuditoria;
        this.idUsuarioAuditoria = idUsuarioAuditoria;
        this.fechaAuditoria = fechaAuditoria;
        this.tipoCambioAuditoria = tipoCambioAuditoria;
        this.comentarioAuditoria = comentarioAuditoria;
    }

    public Integer getIdAuditoria() {
        return idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    public String getNombreEntidadAuditoria() {
        return nombreEntidadAuditoria;
    }

    public void setNombreEntidadAuditoria(String nombreEntidadAuditoria) {
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
    }

    public int getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public void setIdReferenciaAuditoria(int idReferenciaAuditoria) {
        this.idReferenciaAuditoria = idReferenciaAuditoria;
    }

    public int getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public void setIdUsuarioAuditoria(int idUsuarioAuditoria) {
        this.idUsuarioAuditoria = idUsuarioAuditoria;
    }

    public LocalDateTime getFechaAuditoria() {
        return fechaAuditoria;
    }

    public void setFechaAuditoria(LocalDateTime fechaAuditoria) {
        this.fechaAuditoria = fechaAuditoria;
    }

    public String getTipoCambioAuditoria() {
        return tipoCambioAuditoria;
    }

    public void setTipoCambioAuditoria(String tipoCambioAuditoria) {
        this.tipoCambioAuditoria = tipoCambioAuditoria;
    }

    public String getComentarioAuditoria() {
        return comentarioAuditoria;
    }

    public void setComentarioAuditoria(String comentarioAuditoria) {
        this.comentarioAuditoria = comentarioAuditoria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAuditoria != null ? idAuditoria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Auditoria)) {
            return false;
        }
        Auditoria other = (Auditoria) object;
        return !((this.idAuditoria == null && other.idAuditoria != null) || (this.idAuditoria != null && !this.idAuditoria.equals(other.idAuditoria)));
    }

    @Override
    public String toString() {
        return "com.procol.auditoria.entidad.Auditoria[ "
                + "idAuditoria=" + idAuditoria
                + ", nombreEntidadAuditoria=" + nombreEntidadAuditoria
                + ", idReferenciaAuditoria=" + idReferenciaAuditoria
                + ", idUsuarioAuditoria=" + idUsuarioAuditoria
                + ", fechaAuditoria=" + fechaAuditoria
                + ", comentarioAuditoria=" + comentarioAuditoria
                + ", tipoCambioAuditoria=" + tipoCambioAuditoria
                + " ]";

    }

}
