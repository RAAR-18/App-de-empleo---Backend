package com.procol.perfil.utilidad.mapeador;

import com.procol.perfil.dto.UbicacionDTO;
import com.procol.perfil.dto.UsuarioDTOPaginado;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component("perfil_UsuarioMapeadorNativo")
public class UsuarioMapeadorNativo implements MapeoNativoDTO<UsuarioDTOPaginado> {

    @Override
    public UsuarioDTOPaginado mapearDesdeTupla(Tuple tupla) {
        UbicacionDTO ubicacion = new UbicacionDTO(
                tupla.get("idUbicacion", Integer.class),
                tupla.get("idPadreUbicacion", Integer.class),
                tupla.get("nombreUbicacion", String.class),
                tupla.get("idDaneUbicacion", String.class),
                tupla.get("longitudUbicacion", String.class),
                tupla.get("latitudUbicacion", String.class)
        );

        return new UsuarioDTOPaginado(
                tupla.get("idUsuario", Integer.class),
                ubicacion,
                tupla.get("documentoUsuario", String.class),
                tupla.get("nombresUsuario", String.class),
                tupla.get("apellidosUsuario", String.class),
                tupla.get("estadoUsuario", Short.class),
                tupla.get("cantidadRoles", Long.class),
                tupla.get("correoAcceso", String.class)
        );
    }

}
