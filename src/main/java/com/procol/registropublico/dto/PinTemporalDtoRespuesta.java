package com.procol.registropublico.dto;

import java.time.OffsetDateTime;

public record PinTemporalDtoRespuesta(
        Long idTelefonoPinTemporal,
        String valorPinTemporal,
        OffsetDateTime fechaCreacionPinTemporal,
        Short intentoPinTemporal,
        Short totalIntentosPinTemporal,
        Short minutosSuspensionPinTemporal,
        boolean smsEnviado
        ) {

}
