package com.procol.infraestructura.utilidad.mapeador;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;

import jakarta.persistence.Tuple;

public interface MapeoNativoDTO<D> {

    /**
     * Mapea una instancia de Tuple a un DTO específico.
     *
     * @param tupla el Tuple resultado de una consulta nativa
     * @return una instancia del DTO mapeada desde el Tuple
     */
    D mapearDesdeTupla(Tuple tupla);

    /**
     * Método por defecto para extensibilidad. Permite pasar parámetros extra si
     * la operación de mapeo necesita datos adicionales.
     *
     * @param tupla el Tuple base
     * @param objetosExternos parámetros adicionales
     * @return una instancia del DTO mapeada desde el Tuple
     */
    default D mapearDesdeTupla(Tuple tupla, Object... objetosExternos) {
        throw new ExcepcionNegocio("El mapeo nativo con parámetros externos no ha sido implementado");
    }
}
