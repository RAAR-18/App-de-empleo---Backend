package com.procol.seguridad.servicio;

import com.procol.seguridad.constante.TokenJwtParametro;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.WeakKeyException;

import javax.crypto.SecretKey;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TokenJwtServicio {

    private SecretKey clavePrivada;

    @PostConstruct
    public void init() {
        byte[] arregloBytes = TokenJwtParametro.CLAVE_SECRETA.getBytes();

        if (arregloBytes.length < 64) {
            throw new WeakKeyException("Se requieren 64 bytes para la clave en HS512");
        }

        this.clavePrivada = Keys.hmacShaKeyFor(arregloBytes);
    }

    public String generarToken(
            String correoUsuario, String nombres, String apellidos,
            List<String> roles, String uuidAcceso, Integer userId, Integer empresaId
    ) {
        long ahora = System.currentTimeMillis();
        long tiempoExpiracion = ahora + TokenJwtParametro.TIEMPO_EXPIRACION;

        return Jwts.builder()
                .subject(correoUsuario)
                .claim("roles", roles)
                .claim("nombres", nombres)
                .claim("apellidos", apellidos)
                .claim("uuidAcceso", uuidAcceso)
                .claim("userId", userId)
                .claim("empresaId", empresaId)
                .claim("expiraEn", tiempoExpiracion)
                .issuedAt(new Date(ahora))
                .expiration(new Date(tiempoExpiracion))
                .signWith(clavePrivada)
                .compact();
    }

    public boolean validarToken(String token) {
        try {
            extraerContenidoToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Claims extraerContenidoToken(String token) {
        return Jwts.parser()
                .verifyWith(clavePrivada)
                .build()
                .parseSignedClaims(token.replace(TokenJwtParametro.PREFIJO_TOKEN, ""))
                .getPayload();
    }

    public String obtenerCorreoDesdeToken(String token) {
        return extraerContenidoToken(token).getSubject();
    }

    public List<String> obtenerRolesDesdeToken(String token) {
        List<String> roles = extraerContenidoToken(token).get("roles", List.class);
        return roles != null ? roles : List.of();
    }

    public long obtenerExpiracionDesdeToken(String token) {
        Object valor = extraerContenidoToken(token).get("expiraEn");
        return valor != null ? Long.parseLong(valor.toString()) : -1L;
    }
}
