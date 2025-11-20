package com.procol.telefono.dto;

public record IniciarCambioTelefonoDtoRespuesta(
        Long idCambioTelefono,
        String telefonoAnterior,
        String telefonoNuevo,
        boolean pinAnteriorEnviado,
        String mensaje
) {
}
