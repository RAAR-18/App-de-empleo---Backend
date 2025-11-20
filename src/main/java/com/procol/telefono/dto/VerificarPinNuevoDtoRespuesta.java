package com.procol.telefono.dto;

public record VerificarPinNuevoDtoRespuesta(
        Long idCambioTelefono,
        boolean verificado,
        boolean cambioCompletado,
        String nuevoTelefono,
        String mensaje
) {
}
