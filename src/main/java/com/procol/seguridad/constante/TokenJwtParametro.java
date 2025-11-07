package com.procol.seguridad.constante;

public class TokenJwtParametro {

    public static final String HEADER_AUTORIZACION = "Authorization";
    public static final String PREFIJO_TOKEN = "Bearer ";
    public static final long TIEMPO_EXPIRACION = 1000 * 60 * 60 * 8L;
    public static final String CLAVE_SECRETA
            = "F7n03!Ae@22#YkvQsLmwXnG4dJzKlMcpHpVgR5sUtBuEzQfLjMaWdXhCtNvRiSkP";

    /*
    1 minuto  = 1000 ms * 60 seg = 1000 * 60;
    1 hora    = 1000 ms * 60 seg * 60 min = 1000 * 60 * 60;
    16 horas  = 1000 ms * 60 seg * 60 min * 16L;
    8 horas   = 1000 ms * 60 seg * 60 min * 8L = 1000 * 60 * 60 * 8;
    2 días    = 1000 ms * 60 seg * 60 min * 24 horas * 2 = 1000 * 60 * 60 * 24 * 2
    1 semana  = 1000 ms * 60 seg * 60 min * 24 horas * 7 = = 1000 * 60 * 60 * 24 * 7;
     */
}
