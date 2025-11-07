package com.procol.comun.dto;

public class ImagenDtoBinario {

    private final byte[] contenido;
    private final String mime;

    public ImagenDtoBinario(byte[] contenido, String mime) {
        this.contenido = contenido;
        this.mime = mime;
    }

    public byte[] getContenido() {
        return contenido;
    }

    public String getMime() {
        return mime;
    }
}
