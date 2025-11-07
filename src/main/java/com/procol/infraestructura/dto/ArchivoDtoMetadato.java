package com.procol.infraestructura.dto;

public class ArchivoDtoMetadato {

    private final String nombrePublico;
    private final String nombrePrivado;
    private final String tipoMime;
    private final String tamanio;
    private final String extension;

    public ArchivoDtoMetadato(
            String nomPublico, String nomPrivado,
            String tipo, String tamanio, String extension
    ) {
        this.nombrePublico = nomPublico;
        this.nombrePrivado = nomPrivado;
        this.tipoMime = tipo;
        this.tamanio = tamanio;
        this.extension = extension;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public String getNombrePrivado() {
        return nombrePrivado;
    }

    public String getTipoMime() {
        return tipoMime;
    }

    public String getTamanio() {
        return tamanio;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "ArchivoMetadatoDTO["
                + "nombrePublico=" + nombrePublico
                + ", nombrePrivado=" + nombrePrivado
                + ", tipoMime=" + tipoMime
                + ", tamanio=" + tamanio
                + ", extension=" + extension
                + "]";
    }

}
