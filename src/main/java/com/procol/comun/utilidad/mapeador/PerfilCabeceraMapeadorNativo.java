package com.procol.comun.utilidad.mapeador;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.procol.comun.dto.PerfilDtoCabecera;
import com.procol.comun.dto.RolDtoCabecera;

import org.postgresql.util.PGobject;

import com.procol.infraestructura.utilidad.mapeador.MapeoNativoDTO;

import org.springframework.stereotype.Component;

import jakarta.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;

@Component
public class PerfilCabeceraMapeadorNativo implements MapeoNativoDTO<PerfilDtoCabecera> {

    @Override
    public PerfilDtoCabecera mapearDesdeTupla(Tuple tupla) {
        Object rolesRaw = tupla.get("roles");
        List<RolDtoCabecera> roles = deserializarRoles(rolesRaw);

        return new PerfilDtoCabecera(
                (Integer) tupla.get("idUbicacion"),
                (Short) tupla.get("tipoDocumentoUsuario"),
                (String) tupla.get("documentoUsuario"),
                (String) tupla.get("nombresUsuario"),
                (String) tupla.get("apellidosUsuario"),
                (String) tupla.get("telefonoAcceso"),
                (String) tupla.get("correoAcceso"),
                (String) tupla.get("nombreUbicacion"),
                roles
        );
    }

    private List<RolDtoCabecera> deserializarRoles(Object rolesRaw) {
        ObjectMapper mapper = new ObjectMapper();
        List<RolDtoCabecera> roles = new ArrayList<>();

        try {
            String json;
            switch (rolesRaw) {
                case PGobject pGobject ->
                    json = pGobject.getValue();
                case String string ->
                    json = string;
                default ->
                    throw new IllegalArgumentException("Tipo inesperado para roles: " + rolesRaw.getClass());
            }

            roles = mapper.readValue(json, new TypeReference<List<RolDtoCabecera>>() {
            });
        } catch (JsonProcessingException | IllegalArgumentException e) {
            System.out.println("Error al deserializar roles: " + e.getMessage());
        }

        return roles;
    }

}
