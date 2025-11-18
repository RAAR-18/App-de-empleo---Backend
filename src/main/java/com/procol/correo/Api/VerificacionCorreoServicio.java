package com.procol.correo.Api;

import com.procol.correo.dto.EnviarCodigoVerficiacion;
import com.procol.correo.dto.VerificarCodigo;
import com.procol.correo.dto.VerificacionCorreo;

public interface VerificacionCorreoServicio {

    VerificacionCorreo enviarCodigoVerificacion(EnviarCodigoVerficiacion request);

    VerificacionCorreo verificarCodigo(VerificarCodigo request);

    VerificacionCorreo obtenerEstadoVerificacion(String correoAcceso);
}
