package com.procol.empresa.utilidad.querybuilder;

public class SqlBuilderEmpresa {

    public static String condicionBusqueda(String campoBusqueda) {
        if (campoBusqueda == null || campoBusqueda.isBlank()) {
            return "";
        }
        return "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%')) ";
    }

    public static String consulta(String campoOrden, String orden, String condicion) {
        return String.format("""
            SELECT 
                e.id_empresa AS idEmpresa,
                e.nombre_empresa AS nombreEmpresa,
                te.id_tipo_empresa AS idTipoEmpresa,
                te.nombre_tipo_empresa AS nombreTipoEmpresa,
                te.estado_tipo_empresa AS estadoTipoEmpresa
            FROM empresas e 
            INNER JOIN tipo_empresas te ON e.id_tipo_empresa = te.id_tipo_empresa
            %s
            ORDER BY %s %s
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) FROM empresas e INNER JOIN tipo_empresas te "
                + "ON e.id_tipo_empresa = te.id_tipo_empresa "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) FROM empresas e INNER JOIN tipo_empresas te ON e.id_tipo_empresa = te.id_tipo_empresa";
    }
}
