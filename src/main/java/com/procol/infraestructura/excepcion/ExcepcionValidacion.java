package com.procol.infraestructura.excepcion;

public class ExcepcionValidacion extends RuntimeException {

    public ExcepcionValidacion(String mensaje) {
        super(mensaje);
    }
}
