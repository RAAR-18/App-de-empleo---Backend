package com.procol.perfil.utilidad.mapeador;

import com.procol.perfil.dto.PerfilDTO;
import com.procol.perfil.entidad.Ubicacion;
import com.procol.perfil.entidad.Usuario;
import org.springframework.stereotype.Component;

@Component("perfil_PerfilMapeador")
public class PerfilMapeador {

    public PerfilDTO desdeEntidad(Usuario usuario) {
        String nombreCompleto = usuario.getNombresUsuario() + " " + usuario.getApellidosUsuario();
        String ubicacion = construirUbicacionCompleta(usuario.getIdUbicacion());

        return new PerfilDTO(
                usuario.getIdUsuario(),
                nombreCompleto,
                usuario.getProfesion(),
                ubicacion
        );
    }

    private String construirUbicacionCompleta(Ubicacion ubicacion) {
        if (ubicacion == null) {
            return "";
        }

        String nombreUbicacion = ubicacion.getNombreUbicacion();

        if (ubicacion.getIdPadreUbicacion() != null) {
            String nombrePadre = ubicacion.getIdPadreUbicacion().getNombreUbicacion();
            return nombreUbicacion + ", " + nombrePadre;
        }

        return nombreUbicacion;
    }
}