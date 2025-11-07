package com.procol.comun.utilidad.mapeador;

import com.procol.comun.dto.UbicacionDTOAutocompletado;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component
public class UbicacionBusquedaMapeadorNativo implements MapeoNativoDTO<UbicacionDTOAutocompletado> {

    @Override
    public UbicacionDTOAutocompletado mapearDesdeTupla(Tuple tupla) {

        return new UbicacionDTOAutocompletado(
                (Integer) tupla.get("idUbicacion"),
                (String) tupla.get("nombreUbicacion"),
                (String) tupla.get("longitudUbicacion"),
                (String) tupla.get("latitudUbicacion")
        );
    }

}
