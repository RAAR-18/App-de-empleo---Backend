package com.procol.mensajeria.api;

import com.procol.mensajeria.dto.correo.CorreoDtoPeticion;
import com.procol.mensajeria.dto.correo.CorreoDtoRespuesta;

public interface CorreoServicio {

    CorreoDtoRespuesta enviarCorreo(CorreoDtoPeticion dto);

}
