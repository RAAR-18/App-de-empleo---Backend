package com.procol.chat.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Configuración de WebSocket para el módulo de chat
 * Define los endpoints y prefijos para mensajería en tiempo real
 * Requiere autenticación mediante token JWT cuando el perfil "security-enabled" está activo
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguracion implements WebSocketMessageBrokerConfigurer {

    private WebSocketSeguridadInterceptor seguridadInterceptor;
    private WebSocketHandshakeInterceptor handshakeInterceptor;

    /**
     * Inyección opcional: solo se inyecta si el perfil "security-enabled" está activo
     */
    @Autowired(required = false)
    public void setSeguridadInterceptor(WebSocketSeguridadInterceptor seguridadInterceptor) {
        this.seguridadInterceptor = seguridadInterceptor;
    }

    /**
     * Inyección opcional del interceptor de handshake HTTP
     */
    @Autowired(required = false)
    public void setHandshakeInterceptor(WebSocketHandshakeInterceptor handshakeInterceptor) {
        this.handshakeInterceptor = handshakeInterceptor;
    }

    /**
     * Registra los endpoints de WebSocket
     * 
     * NOTA: NO se valida el token en el handshake HTTP porque los navegadores web
     * no permiten headers personalizados en WebSocket. La validación se hace
     * en el frame STOMP CONNECT mediante WebSocketSeguridadInterceptor.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint principal sin interceptor de handshake (compatible con Web)
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*");   // ajusta CORS según tu frontend
        
        // Endpoint con SockJS fallback
        registry.addEndpoint("/ws-chat")
                .setAllowedOriginPatterns("*")
                .withSockJS(); // opcional para fallback
    }

    /**
     * Configura el broker de mensajes
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");      // destinos a los que el server envía
        registry.setApplicationDestinationPrefixes("/app"); // destinos a los que el cliente envía
    }

    /**
     * Configura los interceptores de canal para validar autenticación
     * Solo si el interceptor de seguridad está disponible
     */
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        if (seguridadInterceptor != null) {
            registration.interceptors(seguridadInterceptor);
        }
    }
}

