package com.procol.infraestructura.dto;

import java.time.LocalDateTime;

public class ErrorDtoExcepcion {

    private final int codigo;
    private final LocalDateTime fechaHora;
    private final String error;
    private final String mensaje;

    public ErrorDtoExcepcion(int codigo, LocalDateTime fechaHora, String error, String mensaje) {
        this.codigo = codigo;
        this.fechaHora = fechaHora;
        this.error = error;
        this.mensaje = mensaje;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getCodigoEstado() {
        return codigo;
    }

    public String getError() {
        return error;
    }

    public String getMensaje() {
        return mensaje;
    }

}
