package com.procol.empresa.utilidad.validacion;

import com.procol.infraestructura.excepcion.ExcepcionValidacion;

public class EmpresaValidar {

    private static final int LONGITUD_MAXIMA = 200;

    private EmpresaValidar() {
    }

    public static void validarNombre(String nombre) {
        validarNombreNoNuloNiVacio(nombre);
        validarLongitudMaxima(nombre, LONGITUD_MAXIMA);
    }

    public static void validarNombreNoNuloNiVacio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ExcepcionValidacion("El nombre de la empresa no puede ser nulo ni vacío");
        }
    }

    public static void validarLongitudMaxima(String nombre, int maximo) {
        if (nombre.trim().length() > maximo) {
            throw new ExcepcionValidacion("El nombre de la empresa supera los " + maximo + " caracteres");
        }
    }

}
