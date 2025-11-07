package com.procol.infraestructura.utilidad.respuesta;

import java.time.LocalDateTime;

public class RespuestaHttpModelo<T> {

    private final int codigoEstado;
    private final LocalDateTime fechaHora;
    private final String mensaje;
    private final T datos;

    public RespuestaHttpModelo(int codigoEstado, String mensaje, T datos) {
        this.codigoEstado = codigoEstado;
        this.fechaHora = LocalDateTime.now();
        this.mensaje = mensaje;
        this.datos = datos;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public T getDatos() {
        return datos;
    }
}
