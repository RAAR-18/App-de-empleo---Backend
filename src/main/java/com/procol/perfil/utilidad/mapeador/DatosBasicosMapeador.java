package com.procol.perfil.utilidad.mapeador;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;
import com.procol.perfil.dto.DatosBasicosDTO;
import com.procol.perfil.entidad.Ubicacion;
import com.procol.perfil.entidad.Usuario;
import org.springframework.stereotype.Component;

@Component("perfil_DatosBasicosMapeador")
public class DatosBasicosMapeador implements MapeoEntidadDTO<Usuario, DatosBasicosDTO> {

    @Override
    public DatosBasicosDTO desdeEntidad(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        String profesion = usuario.getProfesion() != null
                ? usuario.getProfesion()
                : "Sin especificar";

        String ubicacion = construirUbicacionCompleta(usuario.getIdUbicacion());

        Integer idUbicacion = usuario.getIdUbicacion() != null
                ? usuario.getIdUbicacion().getIdUbicacion()
                : null;

        return new DatosBasicosDTO(
                usuario.getIdUsuario(),
                usuario.getNombresUsuario(),
                usuario.getApellidosUsuario(),
                usuario.getDocumentoUsuario(),
                profesion,
                ubicacion,
                idUbicacion
        );
    }

    private String construirUbicacionCompleta(Ubicacion ubicacion) {
        if (ubicacion == null) {
            return "Ubicación no registrada";
        }

        String nombreUbicacion = ubicacion.getNombreUbicacion();

        if (ubicacion.getIdPadreUbicacion() != null) {
            String nombrePadre = ubicacion.getIdPadreUbicacion().getNombreUbicacion();
            return nombreUbicacion + ", " + nombrePadre;
        }

        return nombreUbicacion;
    }

    @Override
    public Usuario desdeDto(DatosBasicosDTO dto) {
        throw new UnsupportedOperationException("DatosBasicosDTO es solo de lectura");
    }
}