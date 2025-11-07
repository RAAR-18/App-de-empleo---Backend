package com.procol.mensajeria.dto.correo;

import java.util.List;

public record CorreoDtoPeticion(
        String remitente,
        String para,
        List<String> conCopia,
        List<String> conCopiaOculta,
        String asunto,
        String cuerpoHtml,
        List<CorreoDtoAdjunto> arregloAdjuntos
        ) {

}
