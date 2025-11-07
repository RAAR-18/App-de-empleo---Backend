package com.procol.infraestructura.excepcion;

import com.procol.infraestructura.dto.ErrorDtoExcepcion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.BadCredentialsException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import io.jsonwebtoken.ExpiredJwtException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

@RestController
@ControllerAdvice
public class ErrorControlador {

    // Seguridad
    // *************************************************************************
    @ExceptionHandler(org.springframework.security.authentication.BadCredentialsException.class)
    public ResponseEntity<ErrorDtoExcepcion> credencialesInvalidas(BadCredentialsException ex) {
        return construirRespuesta(
                HttpStatus.UNAUTHORIZED,
                "Credenciales inválidas",
                "El correo electrónico o contraseña incorrectos"
        );
    }

    @ExceptionHandler(org.springframework.security.authentication.DisabledException.class)
    public ResponseEntity<ErrorDtoExcepcion> usuarioDeshabilitado(DisabledException ex) {
        return construirRespuesta(
                HttpStatus.UNAUTHORIZED,
                "Usuario inactivo",
                "El usuario no tiene permisos o su cuenta está inactiva"
        );
    }

    @ExceptionHandler(io.jsonwebtoken.ExpiredJwtException.class)
    public ResponseEntity<ErrorDtoExcepcion> tokenExpirado(ExpiredJwtException ex) {
        return construirRespuesta(
                HttpStatus.UNAUTHORIZED,
                "Token expirado",
                "Tu sesión ha caducado. Debe iniciar sesión nuevamente"
        );
    }

    @ExceptionHandler(ExcepcionSeguridad.class)
    public ResponseEntity<ErrorDtoExcepcion> errorSeguridad(ExcepcionSeguridad ex) {
        return construirRespuesta(
                HttpStatus.UNAUTHORIZED,
                "Acceso bloqueado",
                ex.getMessage()
        );
    }
    // *************************************************************************

    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    public ResponseEntity<ErrorDtoExcepcion> rutaNoEncontrada(org.springframework.web.servlet.NoHandlerFoundException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, "Ruta no encontrada", ex.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ErrorDtoExcepcion> tamanioNoPermitido(MaxUploadSizeExceededException ex) {
        return construirRespuesta(HttpStatus.PAYLOAD_TOO_LARGE, "Tamaño de archivo NO permitido", ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDtoExcepcion> noEncontrado(NoSuchElementException ex) {
        return construirRespuesta(HttpStatus.NOT_FOUND, "Recurso no encontrado", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorDtoExcepcion> tipoInvalido(MethodArgumentTypeMismatchException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Parámetro inválido", ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDtoExcepcion> argumentoInvalido(IllegalArgumentException ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Argumento inválido", ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDtoExcepcion> errorGeneral(Exception ex) {
        String detalle;

        if (ex.getMessage() != null && !ex.getMessage().isBlank()) {
            detalle = ex.getMessage();
        } else {
            detalle = ex.toString();
        }

        if (ex.getCause() != null) {
            detalle += " | Causa: " + ex.getCause().toString();
        }

        return construirRespuesta(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error interno del servidor",
                detalle
        );
    }

    @ExceptionHandler(ExcepcionNegocio.class)
    public ResponseEntity<ErrorDtoExcepcion> conflictoNegocio(ExcepcionNegocio ex) {
        return construirRespuesta(HttpStatus.CONFLICT, "Conflicto regla del negocio", ex.getMessage());
    }

    @ExceptionHandler(ExcepcionValidacion.class)
    public ResponseEntity<ErrorDtoExcepcion> errorValidacion(ExcepcionValidacion ex) {
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Error de validación", ex.getMessage());
    }

    @ExceptionHandler(UnrecognizedPropertyException.class)
    public ResponseEntity<ErrorDtoExcepcion> propiedadDesconocida(UnrecognizedPropertyException ex) {
        String campo = ex.getPropertyName();
        String mensaje = "El campo '" + campo + "' no es reconocido. Verifica la estructura del JSON.";
        return construirRespuesta(HttpStatus.BAD_REQUEST, "Propiedad desconocida", mensaje);
    }

    private ResponseEntity<ErrorDtoExcepcion> construirRespuesta(HttpStatus estado, String error, String detalle) {
        ErrorDtoExcepcion respuesta = new ErrorDtoExcepcion(
                estado.value(),
                LocalDateTime.now(),
                error,
                detalle
        );
        return new ResponseEntity<>(respuesta, estado);
    }
}
