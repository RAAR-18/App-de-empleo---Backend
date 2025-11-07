package com.procol.infraestructura.utilidad.validacion;

import java.util.Map;
import java.util.List;

import com.procol.infraestructura.excepcion.ExcepcionValidacion;

import org.springframework.data.domain.Sort;

public class VerificarOrdenamiento {

    public static Sort obtenerSortJpa(String campo, String orden, Map<String, String> camposEntidad) {
        String campoOrdenable = obtenerCampoParaJpa(campo, camposEntidad);
        Sort.Direction direccion = obtenerDireccionValidada(orden);
        return Sort.by(direccion, campoOrdenable);
    }

    public static String obtenerCampoParaNative(String campo, Map<String, String> camposEntidad) {
        if (campo == null || camposEntidad == null) {
            throw new ExcepcionValidacion("Campo o configuración inválida para ordenamiento.");
        }

        return camposEntidad.entrySet().stream()
                .filter(entry -> entry.getKey().equalsIgnoreCase(campo))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new ExcepcionValidacion("Campo no permitido para ordenamiento: " + campo));
    }

    private static String obtenerCampoParaJpa(String campo, Map<String, String> camposEntidad) {
        return camposEntidad.keySet().stream()
                .filter(k -> k.equalsIgnoreCase(campo))
                .findFirst()
                .orElseThrow(() -> new ExcepcionValidacion("Campo no permitido para ordenamiento: " + campo));
    }

    private static Sort.Direction obtenerDireccionValidada(String orden) {
        if (!List.of("asc", "desc").contains(orden.toLowerCase())) {
            throw new ExcepcionValidacion("Orden " + orden + " no permitido. Use asc o desc");
        }
        return Sort.Direction.fromOptionalString(orden).orElse(Sort.Direction.ASC);
    }
}
