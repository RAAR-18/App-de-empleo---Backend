package com.procol.empresa.utilidad.mapeador;

import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;
import com.procol.empresa.dto.TipoEmpresaDTOCantidad;
import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

@Component
public class TipoEmpresaMapeadorNativo implements MapeoNativoDTO<TipoEmpresaDTOCantidad> {

    @Override
    public TipoEmpresaDTOCantidad mapearDesdeTupla(Tuple tupla) {
        Integer idTipoEmpresa = tupla.get("idTipoEmpresa", Integer.class);
        String nombreTipoEmpresa = tupla.get("nombreTipoEmpresa", String.class);
        Short estadoTipoEmpresa = tupla.get("estadoTipoEmpresa", Short.class);
        Long cantidadEmpresas = tupla.get("cantidadEmpresas", Long.class);

        return new TipoEmpresaDTOCantidad(
                idTipoEmpresa, nombreTipoEmpresa, estadoTipoEmpresa, cantidadEmpresas
        );
    }

}
