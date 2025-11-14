package com.procol.perfil.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.List;

public class PalabraClaveDTOAsignar {

    private Integer idUsuario;
    private List<Integer> idsPalabrasClave;

    public PalabraClaveDTOAsignar() {}

    public PalabraClaveDTOAsignar(Integer idUsuario, List<Integer> idsPalabrasClave) {
        this.idUsuario = idUsuario;
        this.idsPalabrasClave = idsPalabrasClave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Integer> getIdsPalabrasClave() {
        return idsPalabrasClave;
    }

    public void setIdsPalabrasClave(List<Integer> idsPalabrasClave) {
        this.idsPalabrasClave = idsPalabrasClave;
    }

    @Override
    public String toString() {
        return "PalabraClaveAsignarDTO{"
                + "idUsuario=" + idUsuario
                + ", idsPalabrasClave=" + idsPalabrasClave
                + '}';
    }
}
