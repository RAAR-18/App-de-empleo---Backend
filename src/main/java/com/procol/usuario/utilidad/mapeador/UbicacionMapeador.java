package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.UbicacionDTO;
import com.procol.usuario.entidad.Ubicacion;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class UbicacionMapeador implements MapeoEntidadDTO<Ubicacion, UbicacionDTO> {

    @Override
    public UbicacionDTO desdeEntidad(Ubicacion entidad) {
        if (entidad == null) {
            return null;
        }

        Integer idPadre = entidad.getIdPadreUbicacion() != null
                ? entidad.getIdPadreUbicacion().getIdUbicacion()
                : null;

        return new UbicacionDTO(
                entidad.getIdUbicacion(),
                idPadre,
                entidad.getNombreUbicacion(),
                entidad.getIdDaneUbicacion(),
                entidad.getLongitudUbicacion(),
                entidad.getLatitudUbicacion()
        );
    }

    @Override
    public Ubicacion desdeDto(UbicacionDTO dto) {
        if (dto == null) {
            return null;
        }

        Ubicacion entidad = new Ubicacion();
        entidad.setIdUbicacion(dto.getIdUbicacion());
        entidad.setNombreUbicacion(dto.getNombreUbicacion());
        entidad.setIdDaneUbicacion(dto.getIdDaneUbicacion());
        entidad.setLongitudUbicacion(dto.getLongitudUbicacion());
        entidad.setLatitudUbicacion(dto.getLatitudUbicacion());

        if (dto.getIdPadreUbicacion() != null) {
            Ubicacion padre = new Ubicacion();
            padre.setIdUbicacion(dto.getIdPadreUbicacion());
            entidad.setIdPadreUbicacion(padre);
        }

        return entidad;
    }
}
