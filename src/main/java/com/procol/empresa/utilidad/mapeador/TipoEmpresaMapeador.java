package com.procol.empresa.utilidad.mapeador;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class TipoEmpresaMapeador implements MapeoEntidadDTO<TipoEmpresa, TipoEmpresaDTO> {

    @Override
    public TipoEmpresaDTO desdeEntidad(TipoEmpresa tipoEmpresa) {
        return new TipoEmpresaDTO(
                tipoEmpresa.getIdTipoEmpresa(),
                tipoEmpresa.getNombreTipoEmpresa(),
                tipoEmpresa.getEstadoTipoEmpresa()
        );
    }

    @Override
    public TipoEmpresa desdeDto(TipoEmpresaDTO dto) {
        TipoEmpresa tipoEmpresa = new TipoEmpresa();
        tipoEmpresa.setIdTipoEmpresa(dto.getIdTipoEmpresa());
        tipoEmpresa.setNombreTipoEmpresa(dto.getNombreTipoEmpresa());
        tipoEmpresa.setEstadoTipoEmpresa(dto.getEstadoTipoEmpresa());
        return tipoEmpresa;
    }
}
