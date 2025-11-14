package com.procol.perfil.utilidad.mapeador;

import com.procol.perfil.dto.UsuarioDTO;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.dto.UbicacionDTO;
import com.procol.perfil.entidad.Ubicacion;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component("perfil_UsuarioMapeador")
public class UsuarioMapeador implements MapeoEntidadDTO<Usuario, UsuarioDTO> {

    @Override
    public UsuarioDTO desdeEntidad(Usuario usuario) {
        Ubicacion ubicacion = usuario.getIdUbicacion();

        UbicacionDTO ubicacionDTO = new UbicacionDTO(
                ubicacion.getIdUbicacion(),
                ubicacion.getIdPadreUbicacion() != null
                        ? ubicacion.getIdPadreUbicacion().getIdUbicacion()
                        : null,
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
                usuario.getEstadoUsuario(),
                usuario.getProfesion()
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
        objUsuario.setProfesion(dto.getProfesion());

        Ubicacion ubicacion = new Ubicacion();
        ubicacion.setIdUbicacion(dto.getIdUbicacion().getIdUbicacion());

        objUsuario.setIdUbicacion(ubicacion);

        return objUsuario;
    }
}
