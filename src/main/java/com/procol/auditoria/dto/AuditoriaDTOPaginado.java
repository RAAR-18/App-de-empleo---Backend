package com.procol.auditoria.dto;

import java.time.LocalDateTime;

public class AuditoriaDTOPaginado {

    private Integer idAuditoria;
    private String nombreEntidadAuditoria;
    private Integer idReferenciaAuditoria;
    private Integer idUsuarioAuditoria;
    private LocalDateTime fechaAuditoria;
    private String tipoCambioAuditoria;
    private String comentarioAuditoria;
    private String documentoUsuario;
    private String apellidosUsuario;
    private String nombresUsuario;

    public AuditoriaDTOPaginado() {
    }

    public AuditoriaDTOPaginado(
            Integer idAuditoria, String nombreEntidadAuditoria,
            Integer idReferenciaAuditoria, Integer idUsuarioAuditoria,
            LocalDateTime fechaAuditoria, String tipoCambioAuditoria,
            String comentarioAuditoria, String documentoUsuario,
            String apellidosUsuario, String nombresUsuario
    ) {
        this.idAuditoria = idAuditoria;
        this.nombreEntidadAuditoria = nombreEntidadAuditoria;
        this.idReferenciaAuditoria = idReferenciaAuditoria;
        this.idUsuarioAuditoria = idUsuarioAuditoria;
        this.fechaAuditoria = fechaAuditoria;
        this.tipoCambioAuditoria = tipoCambioAuditoria;
        this.comentarioAuditoria = comentarioAuditoria;
        this.documentoUsuario = documentoUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.nombresUsuario = nombresUsuario;
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

    public Integer getIdReferenciaAuditoria() {
        return idReferenciaAuditoria;
    }

    public void setIdReferenciaAuditoria(Integer idReferenciaAuditoria) {
        this.idReferenciaAuditoria = idReferenciaAuditoria;
    }

    public Integer getIdUsuarioAuditoria() {
        return idUsuarioAuditoria;
    }

    public void setIdUsuarioAuditoria(Integer idUsuarioAuditoria) {
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

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    @Override
    public String toString() {
        return "AuditoriaDTO["
                + "idAuditoria=" + idAuditoria
                + ", nombreEntidadAuditoria=" + nombreEntidadAuditoria
                + ", idReferenciaAuditoria=" + idReferenciaAuditoria
                + ", idUsuarioAuditoria=" + idUsuarioAuditoria
                + ", fechaAuditoria=" + fechaAuditoria
                + ", tipoCambioAuditoria=" + tipoCambioAuditoria
                + ", comentarioAuditoria=" + comentarioAuditoria
                + ", documentoUsuario=" + documentoUsuario
                + ", apellidosUsuario=" + apellidosUsuario
                + ", nombresUsuario=" + nombresUsuario
                + "]";
    }

}
