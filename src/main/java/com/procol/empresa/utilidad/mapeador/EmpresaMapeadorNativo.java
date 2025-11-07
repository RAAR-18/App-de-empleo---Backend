package com.procol.empresa.utilidad.mapeador;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.dto.TipoEmpresaDTO;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component
public class EmpresaMapeadorNativo implements MapeoNativoDTO<EmpresaDTO> {

    @Override
    public EmpresaDTO mapearDesdeTupla(Tuple tupla) {
        Integer idEmpresa = tupla.get("idEmpresa", Integer.class);
        String nombreEmpresa = tupla.get("nombreEmpresa", String.class);

        Integer idTipoEmpresa = tupla.get("idTipoEmpresa", Integer.class);
        String nombreTipoEmpresa = tupla.get("nombreTipoEmpresa", String.class);
        Short estadoTipoEmpresa = tupla.get("estadoTipoEmpresa", Short.class);

        TipoEmpresaDTO tipoEmpresaDTO = new TipoEmpresaDTO(idTipoEmpresa, nombreTipoEmpresa, estadoTipoEmpresa);
        return new EmpresaDTO(idEmpresa, nombreEmpresa, tipoEmpresaDTO);
    }


}
