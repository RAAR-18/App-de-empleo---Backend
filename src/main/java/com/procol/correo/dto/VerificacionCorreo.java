package com.procol.correo.dto;

public record VerificacionCorreo(
        boolean exito,
        String mensaje,
        String correoVerificado,
        Short estadoVerificacion
) {
}
