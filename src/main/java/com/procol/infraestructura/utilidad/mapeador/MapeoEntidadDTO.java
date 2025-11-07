package com.procol.infraestructura.utilidad.mapeador;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;

public interface MapeoEntidadDTO<E, D> {

    D desdeEntidad(E miEntidad);

    E desdeDto(D miDto);

    default E desdeDto(D dto, Object... objetosExternos) {
        throw new ExcepcionNegocio("El mapeador no ha sido implementado");

    }
}
