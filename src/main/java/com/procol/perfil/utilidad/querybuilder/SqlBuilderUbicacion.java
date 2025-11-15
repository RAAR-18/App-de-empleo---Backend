package com.procol.perfil.utilidad.querybuilder;

public class SqlBuilderUbicacion {

    private SqlBuilderUbicacion() {
    }

    public static String condicionBusqueda(String campoBusqueda) {
        if (campoBusqueda == null || campoBusqueda.isBlank()) {
            return "";
        }
        return "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%')) ";
    }

    public static String consulta(String campoOrden, String orden, String condicion) {
        return String.format("""
            SELECT u.id_ubicacion as idUbicacion, 
                   u.id_padre_ubicacion as idPadreUbicacion, 
                   u.nombre_ubicacion as nombreUbicacion, 
                   u.id_dane_ubicacion as idDaneUbicacion,
                   u.longitud_ubicacion as longitudUbicacion, 
                   u.latitud_ubicacion as latitudUbicacion 
            FROM ubicaciones u  
            %s
            GROUP BY u.id_ubicacion, u.nombre_ubicacion, u.id_dane_ubicacion, u.longitud_ubicacion, u.latitud_ubicacion 
            ORDER BY %s %s 
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) "
                + "FROM ubicaciones u "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) FROM ubicaciones u";
    }

}
