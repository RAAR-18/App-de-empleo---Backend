package com.procol.perfil.utilidad.mapeador;

import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;
import org.springframework.stereotype.Component;

@Component("perfil_RelUsuarioTalentoMapeador")
public class RelUsuarioTalentoMapeador implements MapeoEntidadDTO<RelUsuarioTalento, RelUsuarioTalentoDTO> {

    @Override
    public RelUsuarioTalentoDTO desdeEntidad(RelUsuarioTalento entidad) {
        return new RelUsuarioTalentoDTO(
                entidad.getUsuario().getIdUsuario(),
                entidad.getTalento().getIdTalento(),
                entidad.getTalento().getNombre(),
                entidad.getTalento().getTipo(),
                entidad.getNivelDominio()
        );
    }

    @Override
    public RelUsuarioTalento desdeDto(RelUsuarioTalentoDTO dto) {
        return null;
    }
}
