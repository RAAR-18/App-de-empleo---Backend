package com.procol.chat.configuracion;

import com.procol.seguridad.servicio.TokenJwtServicio;
import com.procol.seguridad.constante.TokenJwtParametro;
import com.procol.infraestructura.excepcion.ExcepcionSeguridad;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.List;

/**
 * Interceptor para validar el token JWT durante el handshake HTTP del WebSocket
 * Se ejecuta antes de que la conexión WebSocket sea establecida
 */
@Component
@Profile("security-enabled")
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {

    private final TokenJwtServicio tokenJwtServicio;

    public WebSocketHandshakeInterceptor(TokenJwtServicio tokenJwtServicio) {
        this.tokenJwtServicio = tokenJwtServicio;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        
        // Obtener el header Authorization del handshake HTTP
        List<String> authHeaders = request.getHeaders().get(TokenJwtParametro.HEADER_AUTORIZACION);
        
        if (authHeaders == null || authHeaders.isEmpty()) {
            System.out.println("❌ [WS Handshake] No se encontró header Authorization");
            return false; // Rechazar conexión
        }

        String authHeader = authHeaders.get(0);
        
        if (!authHeader.startsWith(TokenJwtParametro.PREFIJO_TOKEN)) {
            System.out.println("❌ [WS Handshake] Header Authorization no tiene el prefijo Bearer");
            return false;
        }

        String token = authHeader.replace(TokenJwtParametro.PREFIJO_TOKEN, "").trim();

        if (!tokenJwtServicio.validarToken(token)) {
            System.out.println("❌ [WS Handshake] Token inválido o expirado");
            return false;
        }

        // Token válido, guardar información en attributes para uso posterior
        String correo = tokenJwtServicio.obtenerCorreoDesdeToken(token);
        attributes.put("userEmail", correo);
        attributes.put("token", token);
        
        System.out.println("✅ [WS Handshake] Token válido para usuario: " + correo);
        return true; // Permitir conexión
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception exception) {
        // No hacer nada después del handshake
    }
}

