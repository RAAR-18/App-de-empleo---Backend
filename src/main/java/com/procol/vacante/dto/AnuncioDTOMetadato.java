package com.procol.vacante.dto;

public class AnuncioDTOMetadato {

    private String nombrePublicoAnuncio;
    private String nombrePrivadoAnuncio;
    private String tipoAnuncio;
    private String tamanioAnuncio;

    public AnuncioDTOMetadato() {
    }

    public AnuncioDTOMetadato(
            String nombrePublicoAnuncio, String nombrePrivadoAnuncio,
            String tipoAnuncio, String tamanioAnuncio
    ) {
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.tamanioAnuncio = tamanioAnuncio;
    }

    public String getNombrePublicoAnuncio() {
        return nombrePublicoAnuncio;
    }

    public void setNombrePublicoAnuncio(String nombrePublicoAnuncio) {
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
    }

    public String getNombrePrivadoAnuncio() {
        return nombrePrivadoAnuncio;
    }

    public void setNombrePrivadoAnuncio(String nombrePrivadoAnuncio) {
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getTamanioAnuncio() {
        return tamanioAnuncio;
    }

    public void setTamanioAnuncio(String tamanioAnuncio) {
        this.tamanioAnuncio = tamanioAnuncio;
    }
}
