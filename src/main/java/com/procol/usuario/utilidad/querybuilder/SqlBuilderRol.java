package com.procol.usuario.utilidad.querybuilder;

public class SqlBuilderRol {

    private SqlBuilderRol() {
    }

    public static String condicionBusqueda(String campoBusqueda) {
        if (campoBusqueda == null || campoBusqueda.isBlank()) {
            return "";
        }
        return "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%')) ";
    }

    public static String consulta(String campoOrden, String orden, String condicion) {
        return String.format("""
            SELECT r.id_rol as idRol, r.nombre_rol as nombreRol, r.estado_rol as estadoRol,
                COALESCE((
                    SELECT COUNT(ur.id_rol)
                    FROM usuarios_roles ur 
                    WHERE r.id_rol = ur.id_rol
                ), 0) AS cantidadUsuarios 
            FROM roles r 
            %s
            GROUP BY r.id_rol, r.nombre_rol, r.estado_rol 
            ORDER BY %s %s 
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) FROM roles r "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) FROM roles r";
    }

}
