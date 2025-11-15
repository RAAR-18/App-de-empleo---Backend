package com.procol.seguridad.constante;

public class RutaPublica {

    public static final String[] RUTAS_PUBLICAS = {
        "/auth/login",
        "/user/register",
        "/user/pin",
        "/user/pinprueba",
        "/user/send-welcome-email",
        "/ws-chat/**",  // 🔌 WebSocket endpoint (autenticación en STOMP CONNECT)
        "/",
        "/*.jpg",
        "/*.jpeg",
        "/*.png",
            "/imagenes/**"
    };

    private RutaPublica() {
    }
}
