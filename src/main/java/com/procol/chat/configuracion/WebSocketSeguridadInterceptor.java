package com.procol.chat.configuracion;

import java.io.IOException;
import java.util.List;

import com.procol.seguridad.servicio.TokenJwtServicio;
import com.procol.seguridad.servicio.AutenticacionServicio;
import com.procol.seguridad.constante.TokenJwtParametro;
import com.procol.infraestructura.excepcion.ExcepcionSeguridad;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.core.Ordered;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Interceptor de seguridad para WebSocket
 * Valida el token JWT en las conexiones WebSocket antes de permitir el acceso
 * Solo se activa cuando el perfil "security-enabled" está activo
 */
@Component
@Profile("security-enabled")
public class WebSocketSeguridadInterceptor implements ChannelInterceptor, Ordered {

    private final TokenJwtServicio tokenJwtServicio;
    private final AutenticacionServicio autenticacionServicio;

    public WebSocketSeguridadInterceptor(
            TokenJwtServicio tokenJwtServicio,
            AutenticacionServicio autenticacionServicio) {
        this.tokenJwtServicio = tokenJwtServicio;
        this.autenticacionServicio = autenticacionServicio;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            System.out.println("🔐 [WS_SEGURIDAD] Frame STOMP CONNECT recibido");
            
            // Validar token solo en la conexión inicial
            String authHeader = accessor.getFirstNativeHeader(TokenJwtParametro.HEADER_AUTORIZACION);
            System.out.println("🔐 [WS_SEGURIDAD] Header Authorization: " + (authHeader != null ? "presente" : "ausente"));
            
            if (authHeader == null || !authHeader.startsWith(TokenJwtParametro.PREFIJO_TOKEN)) {
                System.err.println("❌ [WS_SEGURIDAD] Token de autorización requerido");
                throw new ExcepcionSeguridad("Token de autorización requerido para WebSocket");
            }

            String token = authHeader.replace(TokenJwtParametro.PREFIJO_TOKEN, "").trim();
            System.out.println("🔐 [WS_SEGURIDAD] Token extraído: " + token.substring(0, Math.min(20, token.length())) + "...");

            if (!tokenJwtServicio.validarToken(token)) {
                System.err.println("❌ [WS_SEGURIDAD] Token inválido o expirado");
                throw new ExcepcionSeguridad("Token inválido o expirado");
            }

            try {
                String correo = tokenJwtServicio.obtenerCorreoDesdeToken(token);
                System.out.println("🔐 [WS_SEGURIDAD] Correo del token: " + correo);
                
                UserDetails usuarioAutenticado = autenticacionServicio.loadUserByUsername(correo);

                Authentication auth = new UsernamePasswordAuthenticationToken(
                        usuarioAutenticado, null, usuarioAutenticado.getAuthorities());
                
                accessor.setUser(auth);
                System.out.println("✅ [WS_SEGURIDAD] Usuario autenticado exitosamente");
            } catch (Exception e) {
                System.err.println("❌ [WS_SEGURIDAD] Error al autenticar: " + e.getMessage());
                throw new ExcepcionSeguridad("Error al autenticar usuario: " + e.getMessage());
            }
        }

        return message;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}

