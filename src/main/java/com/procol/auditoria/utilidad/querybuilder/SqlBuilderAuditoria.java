package com.procol.auditoria.utilidad.querybuilder;

public class SqlBuilderAuditoria {

    private SqlBuilderAuditoria() {
    }

    public static String condicionBusqueda(String campoBusqueda) {
        if (campoBusqueda == null || campoBusqueda.isBlank()) {
            return "";
        }
        return "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%')) ";
    }

    public static String consulta(String campoOrden, String orden, String condicion) {
        return String.format("""
            SELECT a.id_auditoria as idAuditoria,
                   a.nombre_entidad_auditoria as nombreEntidadAuditoria, 
                   a.id_referencia_auditoria as idReferenciaAuditoria,
                   a.id_usuario_auditoria as idUsuarioAuditoria,
                   a.fecha_auditoria as fechaAuditoria,
                   a.tipo_cambio_auditoria as tipoCambioAuditoria,
                   a.comentario_auditoria as comentarioAuditoria,
                   u.documento_usuario as documentoUsuario,
                   u.apellidos_usuario as apellidosUsuario,
                   u.nombres_usuario as nombresUsuario
            FROM auditorias a INNER JOIN usuarios u on a.id_usuario_auditoria = u.id_usuario 
            %s
            GROUP BY a.id_auditoria, a.nombre_entidad_auditoria, a.id_referencia_auditoria, 
                     a.id_usuario_auditoria, a.fecha_auditoria, a.tipo_cambio_auditoria, 
                     a.comentario_auditoria, u.documento_usuario, u.apellidos_usuario,
                     u.nombres_usuario 
            ORDER BY %s %s 
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) "
                + "FROM auditorias a INNER JOIN usuarios u on a.id_usuario_auditoria = u.id_usuario "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) FROM auditorias a INNER JOIN usuarios u on a.id_usuario_auditoria = u.id_usuario";
    }

}
