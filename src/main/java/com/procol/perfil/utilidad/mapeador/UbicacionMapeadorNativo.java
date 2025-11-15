package com.procol.perfil.utilidad.mapeador;

import com.procol.perfil.dto.UbicacionDTOPaginado;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component("perfil_UbicacionMapeadorNativo")
public class UbicacionMapeadorNativo implements MapeoNativoDTO<UbicacionDTOPaginado> {

    @Override
    public UbicacionDTOPaginado mapearDesdeTupla(Tuple tupla) {
        if (tupla == null) {
            return null;
        }

        return new UbicacionDTOPaginado(
                tupla.get("idUbicacion", Integer.class),
                tupla.get("idPadreUbicacion", Integer.class),
                tupla.get("nombreUbicacion", String.class),
                tupla.get("idDaneUbicacion", String.class),
                tupla.get("longitudUbicacion", String.class),
                tupla.get("latitudUbicacion", String.class)
        );
    }

}
