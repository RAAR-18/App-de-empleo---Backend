package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.AccesoDTO;
import com.procol.usuario.entidad.Acceso;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class AccesoMapeador implements MapeoEntidadDTO<Acceso, AccesoDTO> {

    private final UsuarioMapeador usuarioMapeador;

    public AccesoMapeador(UsuarioMapeador usuarioMapeador) {
        this.usuarioMapeador = usuarioMapeador;
    }

    @Override
    public AccesoDTO desdeEntidad(Acceso entidad) {
        if (entidad == null) {
            return null;
        }

        return new AccesoDTO(
                usuarioMapeador.desdeEntidad(entidad.getUsuario()),
                entidad.getCorreoAcceso(),
                entidad.getClaveAcceso(),
                entidad.getUuidAcceso()
        );
    }

    @Override
    public Acceso desdeDto(AccesoDTO dto) {
        if (dto == null) {
            return null;
        }
        Acceso entidad = new Acceso();
        entidad.setIdUsuario(dto.getIdUsuario().getIdUsuario());
        entidad.setCorreoAcceso(dto.getCorreoAcceso());
        entidad.setClaveAcceso(dto.getClaveAcceso());
        entidad.setUuidAcceso(dto.getUuidAcceso());
        entidad.setIdUsuario(dto.getIdUsuario().getIdUsuario());
        return entidad;
    }
}
