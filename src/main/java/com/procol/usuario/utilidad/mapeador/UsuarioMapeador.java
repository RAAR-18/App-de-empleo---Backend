package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.UsuarioDTO;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.dto.UbicacionDTO;
import com.procol.usuario.entidad.Ubicacion;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class UsuarioMapeador implements MapeoEntidadDTO<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO desdeEntidad(Usuario usuario) {
        Ubicacion ubicacion = usuario.getIdUbicacion();

        UbicacionDTO ubicacionDTO = new UbicacionDTO(
                ubicacion.getIdUbicacion(),
                ubicacion.getIdPadreUbicacion().getIdUbicacion(),
                ubicacion.getNombreUbicacion(),
                ubicacion.getIdDaneUbicacion(),
                ubicacion.getLongitudUbicacion(),
                ubicacion.getLatitudUbicacion()
        );

        return new UsuarioDTO(
                usuario.getIdUsuario(),
                ubicacionDTO,
                usuario.getDocumentoUsuario(),
                usuario.getNombresUsuario(),
                usuario.getApellidosUsuario(),
                usuario.getEstadoUsuario()
        );
    }

    @Override
    public Usuario desdeDto(UsuarioDTO dto) {
        Usuario objUsuario = new Usuario();

        objUsuario.setIdUsuario(dto.getIdUsuario());
        objUsuario.setDocumentoUsuario(dto.getDocumentoUsuario());
        objUsuario.setNombresUsuario(dto.getNombresUsuario());
        objUsuario.setApellidosUsuario(dto.getApellidosUsuario());
        objUsuario.setEstadoUsuario(dto.getEstadoUsuario());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdUbicacion(dto.getIdUbicacion().getIdUbicacion());

        objUsuario.setIdUbicacion(ubicacion);

        return objUsuario;
    }
}
