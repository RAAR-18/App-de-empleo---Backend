package com.procol.seguridad.dto;

public class RespuestaJwtDTO {

    private final String tokenApp;
    private final String fotoApp;
    private final long expiraEn;

    public RespuestaJwtDTO(String tokenApp, String fotoApp, long expiraEn) {
        this.tokenApp = tokenApp;
        this.fotoApp = fotoApp;
        this.expiraEn = expiraEn;
    }

    public String getTokenApp() {
        return tokenApp;
    }

    public String getFotoApp() {
        return fotoApp;
    }

    public long getExpiraEn() {
        return expiraEn;
    }

}
