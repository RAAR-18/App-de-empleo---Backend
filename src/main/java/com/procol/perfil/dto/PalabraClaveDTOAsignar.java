package com.procol.perfil.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class PalabraClaveDTOAsignar {

    private Integer idUsuario;
    private String idsPalabraClaveTexto;
    private List<Integer> idsPalabrasClave;

    public PalabraClaveDTOAsignar() {}

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdsPalabraClaveTexto() {
        return idsPalabraClaveTexto;
    }

    public void setIdsPalabraClaveTexto(String idsPalabraClaveTexto) {
        this.idsPalabraClaveTexto = idsPalabraClaveTexto;
        this.idsPalabrasClave = parsearIds(idsPalabraClaveTexto);
    }

    public List<Integer> getIdsPalabrasClave() {
        return idsPalabrasClave;
    }

    private List<Integer> parsearIds(String raw) {
        if (raw == null || raw.isBlank()) {
            return Collections.emptyList();
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(raw, new TypeReference<List<Integer>>() {});
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Formato inválido para idsPalabrasClaveTexto: " + raw, e);
        }
    }
}
