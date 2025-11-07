package com.procol.seguridad.configuracion;

import java.io.IOException;
import java.time.LocalDateTime;

import io.jsonwebtoken.ExpiredJwtException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.procol.seguridad.servicio.TokenJwtServicio;
import com.procol.seguridad.constante.TokenJwtParametro;
import com.procol.seguridad.servicio.AutenticacionServicio;

import com.procol.infraestructura.dto.ErrorDtoExcepcion;
import com.procol.infraestructura.excepcion.ExcepcionSeguridad;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Profile;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Component
@Profile("security-enabled")
public class SeguridadFiltroToken extends OncePerRequestFilter {

    private final ObjectMapper mapeadorJson;
    private final TokenJwtServicio tokenJwtServicio;
    private final AutenticacionServicio autenticacionServicio;

    public SeguridadFiltroToken(
            TokenJwtServicio tokenJwtServicio,
            AutenticacionServicio autenticacionServicio,
            ObjectMapper mapeadorJson
    ) {
        this.tokenJwtServicio = tokenJwtServicio;
        this.autenticacionServicio = autenticacionServicio;
        this.mapeadorJson = mapeadorJson;
    }

    private void errorRespuestaSeguridad(HttpServletResponse response, HttpStatus estado,
            String error, String mensaje) throws IOException {
        response.setStatus(estado.value());
        response.setContentType("application/json");

        ErrorDtoExcepcion errorRespuesta = new ErrorDtoExcepcion(
                estado.value(),
                LocalDateTime.now(),
                error,
                mensaje
        );

        mapeadorJson.writeValue(response.getWriter(), errorRespuesta);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(TokenJwtParametro.HEADER_AUTORIZACION);

        try {
            if (header != null && header.startsWith(TokenJwtParametro.PREFIJO_TOKEN)) {
                String token = header.replace(TokenJwtParametro.PREFIJO_TOKEN, "");

                if (!tokenJwtServicio.validarToken(token)) {
                    throw new ExcepcionSeguridad("El token no es válido");
                }

                String correo = tokenJwtServicio.obtenerCorreoDesdeToken(token);
                UserDetails usuarioAutenticado = autenticacionServicio.loadUserByUsername(correo);

//                System.out.println("Authorities de usuarioAutenticado:" + usuarioAutenticado.getAuthorities());
                
                UsernamePasswordAuthenticationToken tokenInterno
                        = new UsernamePasswordAuthenticationToken(
                                usuarioAutenticado, null, usuarioAutenticado.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(tokenInterno);
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException e) {
            errorRespuestaSeguridad(response, HttpStatus.UNAUTHORIZED,
                    "Token expirado", "Tu sesión ha caducado");
        } catch (ExcepcionSeguridad e) {
            errorRespuestaSeguridad(response, HttpStatus.UNAUTHORIZED,
                    "Acceso bloqueado", e.getMessage());
        } catch (ServletException | IOException | UsernameNotFoundException e) {
            errorRespuestaSeguridad(response, HttpStatus.UNAUTHORIZED,
                    "Token inválido", e.getMessage());
        }
    }
}
