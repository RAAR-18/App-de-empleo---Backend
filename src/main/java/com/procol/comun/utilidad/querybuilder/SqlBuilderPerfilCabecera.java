package com.procol.comun.utilidad.querybuilder;

public class SqlBuilderPerfilCabecera {

    private SqlBuilderPerfilCabecera() {
    }

    public static String obtener(Integer idUsuario) {
        return String.format("""
            SELECT 
                u.id_ubicacion as idUbicacion, tipo_documento_usuario as tipoDocumentoUsuario, 
                u.documento_usuario as documentoUsuario, u.nombres_usuario as nombresUsuario,
                u.apellidos_usuario as apellidosUsuario, a.telefono_acceso as telefonoAcceso,
                a. correo_acceso as correoAcceso, ubi.nombre_ubicacion as nombreUbicacion, 
                (
                    SELECT json_agg(json_build_object('idRol', r.id_rol, 'nombreRol', r.nombre_rol))
                    FROM roles r
                    INNER JOIN usuarios_roles ur ON r.id_rol = ur.id_rol
                    WHERE ur.id_usuario = u.id_usuario
                ) AS roles
            FROM usuarios u
            INNER JOIN accesos a ON u.id_usuario = a.id_usuario
            INNER JOIN ubicaciones ubi ON u.id_ubicacion = ubi.id_ubicacion
            WHERE u.id_usuario = %s;
            """, idUsuario);
    }

}
