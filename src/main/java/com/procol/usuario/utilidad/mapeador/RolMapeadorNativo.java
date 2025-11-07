package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.RolDTOCantidadUsuario;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;

@Component
public class RolMapeadorNativo implements MapeoNativoDTO<RolDTOCantidadUsuario> {

    @Override
    public RolDTOCantidadUsuario mapearDesdeTupla(Tuple tupla) {
        Integer idRol = tupla.get("idRol", Integer.class);
        String nombreRol = tupla.get("nombreRol", String.class);
        Short estadoRol = tupla.get("estadoRol", Short.class);
        Long cantidadUsuarios = tupla.get("cantidadUsuarios", Long.class);

        return new RolDTOCantidadUsuario(idRol, nombreRol, estadoRol, cantidadUsuarios);
    }

}
