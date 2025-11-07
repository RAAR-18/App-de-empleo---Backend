package com.procol.infraestructura.utilidad.mapeador;

import org.springframework.data.domain.Page;
import com.procol.infraestructura.dto.PaginacionDto;

public class PaginacionMap {

    private PaginacionMap() {
    }

    public static <T> PaginacionDto<T> transformar(Page<T> pagina) {
        return new PaginacionDto<>(
                pagina.getContent(),
                pagina.getNumber(),
                pagina.getSize(),
                pagina.getTotalPages(),
                pagina.getTotalElements()
        );
    }
}
