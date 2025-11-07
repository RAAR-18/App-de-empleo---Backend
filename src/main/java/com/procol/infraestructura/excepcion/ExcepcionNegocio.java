package com.procol.infraestructura.excepcion;

public class ExcepcionNegocio extends RuntimeException {

    public ExcepcionNegocio(String mensaje) {
        super(mensaje);
    }
}
