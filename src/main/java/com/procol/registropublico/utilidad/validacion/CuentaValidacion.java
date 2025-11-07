package com.procol.registropublico.utilidad.validacion;

import java.util.regex.Pattern;

import com.procol.registropublico.dto.CuentaDTOCrear;

import com.procol.infraestructura.excepcion.ExcepcionValidacion;

public class CuentaValidacion {

    private static final int LONGITUD_MINIMA_CLAVE = 4;
    private static final int LONGITUD_MAXIMA_TEXTO = 50;

    private static final Pattern CORREO_REGEX = Pattern.compile(
            "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE
    );

    private CuentaValidacion() {
    }

    public static void verificarDTO(CuentaDTOCrear dto) {
        validarTexto(dto.getNombresUsuario(), "nombres");
        validarTexto(dto.getApellidosUsuario(), "apellidos");
        validarCorreo(dto.getCorreoAcceso());
        validarClave(dto.getClaveAcceso());
    }

    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new ExcepcionValidacion("El campo '" + campo + "' es obligatorio");
        }

        if (valor.length() > LONGITUD_MAXIMA_TEXTO) {
            throw new ExcepcionValidacion("El campo '" + campo + "' no debe superar los " + LONGITUD_MAXIMA_TEXTO + " caracteres");
        }
    }

    private static void validarCorreo(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new ExcepcionValidacion("El correo es obligatorio");
        }

        if (correo.length() > LONGITUD_MAXIMA_TEXTO) {
            throw new ExcepcionValidacion("El correo no debe superar los " + LONGITUD_MAXIMA_TEXTO + " caracteres");
        }

        if (!CORREO_REGEX.matcher(correo).matches()) {
            throw new ExcepcionValidacion("El formato del correo no es válido");
        }
    }

    private static void validarClave(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new ExcepcionValidacion("La contraseña es obligatoria");
        }

        if (clave.length() < LONGITUD_MINIMA_CLAVE) {
            throw new ExcepcionValidacion("La contraseña debe tener al menos " + LONGITUD_MINIMA_CLAVE + " caracteres");
        }
    }
}
