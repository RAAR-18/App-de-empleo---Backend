package com.procol.perfil.dto;

import org.springframework.web.multipart.MultipartFile;

public class ArchivoDTOCrear {

    private Integer idArchivo;
    private Integer idUsuario;
    private String nombrePublicoArchivo;
    private String nombrePrivadoArchivo;
    private String tipoArchivo;
    private String tamanioArchivo;
    private MultipartFile archivo;

    public ArchivoDTOCrear() {}

    public ArchivoDTOCrear(Integer idArchivo, Integer idUsuario, String nombrePublicoArchivo,
                           String nombrePrivadoArchivo, String tipoArchivo, String tamanioArchivo,
                           MultipartFile archivo
    ) {
        this.idArchivo = idArchivo;
        this.idUsuario = idUsuario;
        this.nombrePublicoArchivo = nombrePublicoArchivo;
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.tamanioArchivo = tamanioArchivo;
        this.archivo = archivo;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }
    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombrePublicoArchivo() { return nombrePublicoArchivo; }

    public void setNombrePublicoArchivo(String nombrePublicoArchivo) {  this.nombrePublicoArchivo = nombrePublicoArchivo; }

    public String getNombrePrivadoArchivo() { return nombrePrivadoArchivo; }

    public void setNombrePrivadoArchivo(String nombrePrivadoArchivo) {
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTamanioArchivo() { return tamanioArchivo; }

    public void setTamanioArchivo(String tamanioArchivo) {  this.tamanioArchivo = tamanioArchivo; }

    public MultipartFile getArchivo() { return archivo; }

    public void setArchivo(MultipartFile archivo) { this.archivo = archivo; }
}
