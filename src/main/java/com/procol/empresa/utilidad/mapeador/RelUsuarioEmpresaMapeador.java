package com.procol.empresa.utilidad.mapeador;

import org.springframework.stereotype.Component;

import com.procol.empresa.dto.UsuarioEmpresaDTO;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.entidad.Usuario;
import com.procol.empresa.entidad.RelUsuarioEmpresa;
import com.procol.empresa.entidad.pk.RelUsuarioEmpresaPK;

@Component
public class RelUsuarioEmpresaMapeador implements MapeoEntidadDTO<RelUsuarioEmpresa, UsuarioEmpresaDTO> {

    @Override
    public UsuarioEmpresaDTO desdeEntidad(RelUsuarioEmpresa entidad) {
        UsuarioEmpresaDTO dto = new UsuarioEmpresaDTO();
        dto.setIdUsuario(entidad.getRelUsuarioEmpresaPK().getIdUsuario());
        dto.setIdEmpresa(entidad.getRelUsuarioEmpresaPK().getIdEmpresa());
        dto.setPermisoRelUsuarioEmpresa(entidad.getPermisoRelUsuarioEmpresa());
        return dto;
    }

    @Override
    public RelUsuarioEmpresa desdeDto(UsuarioEmpresaDTO dto) {
        RelUsuarioEmpresa entidad = new RelUsuarioEmpresa();
        entidad.setRelUsuarioEmpresaPK(new RelUsuarioEmpresaPK(dto.getIdUsuario(), dto.getIdEmpresa()));
        entidad.setPermisoRelUsuarioEmpresa(dto.getPermisoRelUsuarioEmpresa());
        return entidad;
    }

    @Override
    public RelUsuarioEmpresa desdeDto(UsuarioEmpresaDTO dto, Object... objetosExternos) {
        Usuario objUsuario = (Usuario) objetosExternos[0];
        Empresa objEmpresa = (Empresa) objetosExternos[1];

        RelUsuarioEmpresa entidad = new RelUsuarioEmpresa();
        entidad.setRelUsuarioEmpresaPK(new RelUsuarioEmpresaPK(dto.getIdUsuario(), dto.getIdEmpresa()));
        entidad.setPermisoRelUsuarioEmpresa(dto.getPermisoRelUsuarioEmpresa());
        entidad.setUsuario(objUsuario);
        entidad.setEmpresa(objEmpresa);

        return entidad;
    }

}
