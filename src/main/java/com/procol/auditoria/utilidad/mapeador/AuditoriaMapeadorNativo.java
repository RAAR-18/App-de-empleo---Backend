package com.procol.auditoria.utilidad.mapeador;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.procol.auditoria.dto.AuditoriaDTOPaginado;
import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component
public class AuditoriaMapeadorNativo implements MapeoNativoDTO<AuditoriaDTOPaginado> {

    @Override
    public AuditoriaDTOPaginado mapearDesdeTupla(Tuple tupla) {
        Timestamp fecha = tupla.get("fechaAuditoria", Timestamp.class);
        LocalDateTime fechaAuditoria = fecha.toLocalDateTime();

        return new AuditoriaDTOPaginado(
                tupla.get("idAuditoria", Integer.class),
                tupla.get("nombreEntidadAuditoria", String.class),
                tupla.get("idReferenciaAuditoria", Integer.class),
                tupla.get("idUsuarioAuditoria", Integer.class),
                fechaAuditoria,
                tupla.get("tipoCambioAuditoria", String.class),
                tupla.get("comentarioAuditoria", String.class),
                tupla.get("documentoUsuario", String.class),
                tupla.get("apellidosUsuario", String.class),
                tupla.get("nombresUsuario", String.class)
        );
    }

}
