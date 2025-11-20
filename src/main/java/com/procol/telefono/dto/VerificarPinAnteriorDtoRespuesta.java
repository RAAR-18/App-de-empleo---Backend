package com.procol.telefono.dto;

public record VerificarPinAnteriorDtoRespuesta(
        Long idCambioTelefono,
        boolean verificado,
        boolean pinNuevoEnviado,
        String mensaje
) {
}
