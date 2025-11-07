package com.procol.mensajeria.api;

import com.procol.mensajeria.dto.mensaje.MensajeDtoPeticion;
import com.procol.mensajeria.dto.mensaje.MensajeDtoRespuesta;

public interface MensajeServicio {

    MensajeDtoRespuesta enviarSms(MensajeDtoPeticion peticion);
}
