package com.procol.infraestructura.excepcion;

public class ExcepcionSeguridad extends RuntimeException {

    public ExcepcionSeguridad(String mensaje) {
        super(mensaje);
    }
}
