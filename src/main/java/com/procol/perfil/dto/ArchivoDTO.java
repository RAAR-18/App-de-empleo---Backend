package com.procol.perfil.dto;

import java.time.LocalDateTime;

public class ArchivoDTO {

    private Integer idArchivo;
    private UsuarioDTO idUsuario;
    private String nombrePublicoArchivo;
    private String nombrePrivadoArchivo;
    private String tipoArchivo;
    private String tamanioArchivo;
    private LocalDateTime  fechaSubida;

    public ArchivoDTO() {}

    public ArchivoDTO(Integer idArchivo, UsuarioDTO idUsuario,
                      String nombrePublicoArchivo, String nombrePrivadoArchivo,
                      String tipoArchivo, String tamanioArchivo,
                      LocalDateTime fechaSubida) {
        this.idArchivo = idArchivo;
        this.idUsuario = idUsuario;
        this.nombrePublicoArchivo = nombrePublicoArchivo;
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.tamanioArchivo = tamanioArchivo;
        this.fechaSubida = fechaSubida;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePublicoArchivo() {
        return nombrePublicoArchivo;
    }

    public void setNombrePublicoArchivo(String nombrePublicoArchivo) {
        this.nombrePublicoArchivo = nombrePublicoArchivo;
    }

    public String getNombrePrivadoArchivo() {
        return nombrePrivadoArchivo;
    }

    public void setNombrePrivadoArchivo(String nombrePrivadoArchivo) {
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(String tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
