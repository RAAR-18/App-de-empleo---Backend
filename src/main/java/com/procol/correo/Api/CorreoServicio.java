package com.procol.correo.Api;

import com.procol.correo.dto.CorreoDtoRespuesta;
import com.procol.correo.dto.CorreoDtoPeticion;
public interface CorreoServicio {

    CorreoDtoRespuesta enviarCorreo(CorreoDtoPeticion dto);

}
