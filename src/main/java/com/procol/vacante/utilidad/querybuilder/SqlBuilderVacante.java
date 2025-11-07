package com.procol.vacante.utilidad.querybuilder;

public class SqlBuilderVacante {

    private static final String BASE_QUERY = """
    SELECT 
        v.id_vacante,
        v.titulo_vacante,
        v.fecha_inicio_vacante,
        v.min_salario_vacante,
        v.max_salario_vacante,
        u.nombre_ubicacion,
        e.nombre_empresa,
        j.nombre_jornada,
        m.nombre_modalidad,
        tc.nombre_tipo_contrato,
        a.nombre_privado_anuncio,
        ev.nombre_estado_vacante,
        COALESCE(string_agg(pc.texto_palabra_clave, ','), '') AS palabras_clave
    FROM vacantes v
    JOIN ubicaciones u ON v.id_ubicacion = u.id_ubicacion
    JOIN empresas e ON v.id_empresa = e.id_empresa
    JOIN jornadas j ON v.id_jornada = j.id_jornada
    JOIN modalidades m ON v.id_modalidad = m.id_modalidad
    JOIN tipos_contratos tc ON v.id_tipo_contrato = tc.id_tipo_contrato
    JOIN anuncios a ON v.id_vacante = a.id_vacante
    JOIN estados_vacantes ev ON v.estado_vacante = ev.id_estado_vacante
    LEFT JOIN rel_vacante_palabraclave rvp ON v.id_vacante = rvp.id_vacante
    LEFT JOIN palabras_claves pc ON rvp.id_palabra_clave = pc.id_palabra_clave
    GROUP BY 
        v.id_vacante, v.titulo_vacante, v.fecha_inicio_vacante,
        v.min_salario_vacante, v.max_salario_vacante,
        u.nombre_ubicacion, e.nombre_empresa, j.nombre_jornada,
        m.nombre_modalidad, tc.nombre_tipo_contrato,
        a.nombre_privado_anuncio, ev.nombre_estado_vacante
    ORDER BY %s %s
    """;

    public static String consulta(String campoOrden, String orden) {
        return String.format(BASE_QUERY, campoOrden, orden);
    }
}
