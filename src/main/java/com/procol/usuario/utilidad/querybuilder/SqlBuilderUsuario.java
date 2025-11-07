package com.procol.usuario.utilidad.querybuilder;

public class SqlBuilderUsuario {

    private SqlBuilderUsuario() {
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
                u.id_usuario AS idUsuario,
                u.id_ubicacion AS idUbicacion,
                u.documento_usuario AS documentoUsuario,
                u.nombres_usuario AS nombresUsuario,
                u.apellidos_usuario AS apellidosUsuario,
                u.estado_usuario AS estadoUsuario,
                ubi.id_padre_ubicacion as idPadreUbicacion,
                ubi.nombre_ubicacion as nombreUbicacion,
                ubi.id_dane_ubicacion as idDaneUbicacion,
                ubi.longitud_ubicacion as longitudUbicacion,
                ubi.latitud_ubicacion as latitudUbicacion,
                a.correo_acceso as correoAcceso,
                COALESCE((
                    SELECT COUNT(ur.id_rol)
                    FROM usuarios_roles ur
                    WHERE ur.id_usuario = u.id_usuario
                ), 0) AS cantidadRoles
            FROM (usuarios u INNER JOIN ubicaciones ubi ON u.id_ubicacion = ubi.id_ubicacion) 
                INNER JOIN accesos a ON u.id_usuario = a.id_usuario 
            %s
            GROUP BY u.id_usuario, u.id_ubicacion, u.documento_usuario, 
                    u.nombres_usuario, u.apellidos_usuario, u.estado_usuario, 
                    id_padre_ubicacion, nombre_ubicacion, id_dane_ubicacion, 
                    longitud_ubicacion, latitud_ubicacion, correo_acceso 
            ORDER BY %s %s
            LIMIT :limit OFFSET :offset
            """, condicion, campoOrden, orden.toUpperCase());
    }

    public static String construirConsultaConteo(String campoBusqueda) {
        return "SELECT COUNT(*) "
                + "FROM (usuarios u INNER JOIN ubicaciones ubi ON u.id_ubicacion = ubi.id_ubicacion) "
                + "INNER JOIN accesos a ON u.id_usuario = a.id_usuario "
                + "WHERE LOWER(CAST(" + campoBusqueda + " AS TEXT)) LIKE LOWER(CONCAT('%', :valorBusqueda, '%'))";
    }

    public static String construirConsultaConteoSinCondicion() {
        return "SELECT COUNT(*) "
                + "FROM (usuarios u INNER JOIN ubicaciones ubi ON u.id_ubicacion = ubi.id_ubicacion) "
                + "INNER JOIN accesos a ON u.id_usuario = a.id_usuario ";
    }
}
