package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.RolDTO;
import com.procol.usuario.entidad.Rol;
import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class RolMapeador implements MapeoEntidadDTO<Rol, RolDTO> {

    @Override
    public RolDTO desdeEntidad(Rol rol) {
        return new RolDTO(
                rol.getIdRol(),
                rol.getNombreRol(),
                rol.getEstadoRol()
        );
    }

    @Override
    public Rol desdeDto(RolDTO dto) {
        Rol rol = new Rol();
        rol.setIdRol(dto.getIdRol());
        rol.setNombreRol(dto.getNombreRol());
        rol.setEstadoRol(dto.getEstadoRol());
        return rol;
    }
}
