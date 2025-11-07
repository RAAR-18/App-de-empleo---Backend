package com.procol.infraestructura.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;
import java.time.ZoneId;

/**
 * Clase de respuesta estándar para las APIs
 * Utilizada por el módulo de chat para mantener consistencia con el backend original
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int codigoEstado;
    private String mensaje;     // éxito o descripción breve del error
    private String error;       // nombre del error (solo en fallos)
    private T datos;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX", timezone = "America/Bogota")
    private OffsetDateTime fechaHora;

    public ApiResponse() {}

    // ---- Fábricas de éxito ----
    public static <T> ApiResponse<T> ok(T datos, String mensaje) {
        ApiResponse<T> r = new ApiResponse<>();
        r.codigoEstado = 200;
        r.mensaje = mensaje != null ? mensaje : "OK";
        r.datos = datos;
        r.fechaHora = OffsetDateTime.now(ZoneId.of("America/Bogota"));
        return r;
    }

    public static <T> ApiResponse<T> created(T datos, String mensaje) {
        ApiResponse<T> r = ok(datos, mensaje != null ? mensaje : "Creado");
        r.codigoEstado = 201;
        return r;
    }

    // ---- Fábrica de error ----
    public static <T> ApiResponse<T> error(int httpStatus, String error, String mensaje) {
        ApiResponse<T> r = new ApiResponse<>();
        r.codigoEstado = httpStatus;
        r.error = error;
        r.mensaje = mensaje;
        r.fechaHora = OffsetDateTime.now(ZoneId.of("America/Bogota"));
        return r;
    }

    // getters/setters
    public int getCodigoEstado() { return codigoEstado; }
    public void setCodigoEstado(int codigoEstado) { this.codigoEstado = codigoEstado; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public T getDatos() { return datos; }
    public void setDatos(T datos) { this.datos = datos; }
    public OffsetDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(OffsetDateTime fechaHora) { this.fechaHora = fechaHora; }
}



