package com.procol.vacante.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VacanteDTOCrear {

    private String tituloVacante;
    private String detalleVacante;
    private OffsetDateTime fechaInicioVacante;
    private OffsetDateTime fechaFinVacante;
    private short estadoVacante;
    private String minSalarioVacante;
    private String maxSalarioVacante;

    private Integer idUbicacion;
    private Integer idJornada;
    private Integer idModalidad;
    private Integer idTipoContrato;
    private Integer idUsuario;
    private Integer idEmpresa;

    private String idsPalabrasClaveTexto;
    private List<Integer> idsPalabrasClave;

    private MultipartFile archivo;

    public VacanteDTOCrear() {
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public void setDetalleVacante(String detalleVacante) {
        this.detalleVacante = detalleVacante;
    }

    public OffsetDateTime getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public void setFechaInicioVacante(OffsetDateTime fechaInicioVacante) {
        this.fechaInicioVacante = fechaInicioVacante;
    }

    public OffsetDateTime getFechaFinVacante() {
        return fechaFinVacante;
    }

    public void setFechaFinVacante(OffsetDateTime fechaFinVacante) {
        this.fechaFinVacante = fechaFinVacante;
    }

    public short getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(short estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public String getMinSalarioVacante() {
        return minSalarioVacante;
    }

    public void setMinSalarioVacante(String minSalarioVacante) {
        this.minSalarioVacante = minSalarioVacante;
    }

    public String getMaxSalarioVacante() {
        return maxSalarioVacante;
    }

    public void setMaxSalarioVacante(String maxSalarioVacante) {
        this.maxSalarioVacante = maxSalarioVacante;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Integer idJornada) {
        this.idJornada = idJornada;
    }

    public Integer getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Integer idModalidad) {
        this.idModalidad = idModalidad;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }

    public String getIdsPalabrasClaveTexto() {
        return idsPalabrasClaveTexto;
    }

    public void setIdsPalabrasClaveTexto(String idsPalabrasClaveTexto) {
        this.idsPalabrasClaveTexto = idsPalabrasClaveTexto;
        this.idsPalabrasClave = parsearIds(idsPalabrasClaveTexto);
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
            return mapper.readValue(raw, new TypeReference<List<Integer>>() {
            });
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Formato inválido para idsPalabrasClaveTexto: " + raw, e);
        }
    }
}
