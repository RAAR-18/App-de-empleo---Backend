package com.procol.empresa.utilidad.querybuilder;

public class SqlBuilderTipoEmpresa {

    private SqlBuilderTipoEmpresa() {
    }

    public static String condicionBusqueda(String campoBusqueda) {
        if (campoBusqueda == null || campoBusqueda.isBlank()) {
            return "";
        }
        return "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%')) ";
    }

    public static String consulta(String campoOrden, String orden, String condicion) {
        return String.format("""
            SELECT 
                te.id_tipo_empresa AS idTipoEmpresa,
                te.nombre_tipo_empresa AS nombreTipoEmpresa,
                te.estado_tipo_empresa AS estadoTipoEmpresa,
                COALESCE((
                    SELECT COUNT(e.id_empresa)
                    FROM empresas e
                    WHERE e.id_tipo_empresa = te.id_tipo_empresa
                ), 0) AS cantidadEmpresas
            FROM tipo_empresas te
            %s
            GROUP BY te.id_tipo_empresa, te.nombre_tipo_empresa, te.estado_tipo_empresa
            ORDER BY %s %s
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) FROM tipo_empresas te "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) FROM tipo_empresas";
    }
}
