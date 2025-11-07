package com.procol.empresa.utilidad.mapeador;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class EmpresaMapeador implements MapeoEntidadDTO<Empresa, EmpresaDTO> {

    @Override
    public EmpresaDTO desdeEntidad(Empresa empresa) {
        return new EmpresaDTO(
                empresa.getIdEmpresa(),
                empresa.getNombreEmpresa(),
                new TipoEmpresaDTO(
                        empresa.getIdTipoEmpresa().getIdTipoEmpresa(),
                        empresa.getIdTipoEmpresa().getNombreTipoEmpresa(),
                        empresa.getIdTipoEmpresa().getEstadoTipoEmpresa()
                )
        );
    }

    @Override
    public Empresa desdeDto(EmpresaDTO dto) {
        TipoEmpresa tipoEmpresa = new TipoEmpresa();
        tipoEmpresa.setIdTipoEmpresa(dto.getIdTipoEmpresa().getIdTipoEmpresa());
        tipoEmpresa.setNombreTipoEmpresa(dto.getIdTipoEmpresa().getNombreTipoEmpresa());
        tipoEmpresa.setEstadoTipoEmpresa(dto.getIdTipoEmpresa().getEstadoTipoEmpresa());

        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(dto.getIdEmpresa());
        empresa.setNombreEmpresa(dto.getNombreEmpresa());
        empresa.setIdTipoEmpresa(tipoEmpresa);
        return empresa;
    }
}
