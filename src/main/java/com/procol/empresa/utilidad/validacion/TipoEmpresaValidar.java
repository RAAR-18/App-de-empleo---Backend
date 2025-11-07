package com.procol.empresa.utilidad.validacion;

import com.procol.infraestructura.constante.ConstEstadoRegistro;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;

public class TipoEmpresaValidar {

    private static final int LONGITUD_MAXIMA = 150;

    private TipoEmpresaValidar() {
    }

    public static void validarNombre(String nombre) {
        validarNombreNoNuloNiVacio(nombre);
        validarLongitudMaxima(nombre, LONGITUD_MAXIMA);
    }

    public static void validarNombreNoNuloNiVacio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ExcepcionValidacion("El nombre del TipoEmpresa no puede ser nulo ni vacío");
        }
    }

    public static void validarLongitudMaxima(String nombre, int maximo) {
        if (nombre.trim().length() > maximo) {
            throw new ExcepcionValidacion("El nombre del TipoEmpresa supera la longitud máxima de " + maximo + " caracteres");
        }
    }

    public static Short asignarEstadoInicial(Short valorEstado) {
        return (valorEstado == null || valorEstado == 0) ? ConstEstadoRegistro.ACTIVO : valorEstado;
    }

}
