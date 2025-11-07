package com.procol.seguridad.configuracion;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.procol.infraestructura.dto.ErrorDtoExcepcion;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SeguridadInterceptor implements AuthenticationEntryPoint {

    private final ObjectMapper mapeadorJson;

    public SeguridadInterceptor(ObjectMapper mapeadorJson) {
        this.mapeadorJson = mapeadorJson;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");

        ErrorDtoExcepcion error = new ErrorDtoExcepcion(
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                "Interceptor: Usuario no autorizado",
                "Se requiere autenticación"
        );

        mapeadorJson.writeValue(response.getWriter(), error);
    }
}
