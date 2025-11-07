package com.procol.auditoria.servicio;

import java.time.LocalDateTime;

import com.procol.auditoria.entidad.Auditoria;
import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.repositorio.AuditoriaRepositorio;

import org.springframework.stereotype.Component;

@Component
public class AuditoriaServicioImple implements AuditoriaServicio {

    private final AuditoriaRepositorio auditoriaRepositorio;

    public AuditoriaServicioImple(AuditoriaRepositorio auditoriaRepositorio) {
        this.auditoriaRepositorio = auditoriaRepositorio;
    }

    @Override
    public void registrar(
            int idUsuario, String entidad,
            int idReferencia, TipoCambio tipo, String comentario
    ) {
        Auditoria auditoria = new Auditoria();
        auditoria.setNombreEntidadAuditoria(entidad);
        auditoria.setIdReferenciaAuditoria(idReferencia);
        auditoria.setIdUsuarioAuditoria(idUsuario);
        auditoria.setFechaAuditoria(LocalDateTime.now());
        auditoria.setTipoCambioAuditoria(tipo.name());
        auditoria.setComentarioAuditoria(comentario);
        auditoriaRepositorio.save(auditoria);
    }
}
