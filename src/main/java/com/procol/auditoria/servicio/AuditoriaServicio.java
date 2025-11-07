package com.procol.auditoria.servicio;

import com.procol.auditoria.constante.TipoCambio;

public interface AuditoriaServicio {

    void registrar(
            int idUsuario, String entidad, int idReferencia,
            TipoCambio tipo, String detalleCambio
    );
}
