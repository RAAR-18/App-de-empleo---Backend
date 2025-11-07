package com.procol.vacante.dto;

import java.time.OffsetDateTime;
import java.util.List;

public class VacanteDTOConsulta {

    private final Integer idVacante;
    private final String tituloVacante;
    private final OffsetDateTime fechaInicioVacante;
    private final String minSalarioVacante;
    private final String maxSalarioVacante;
    private final String nombreUbicacion;
    private final String nombreEmpresa;
    private final String nombreJornada;
    private final String nombreModalidad;
    private final String nombreTipoContrato;
    private final String nombrePrivadoAnuncio;
    private final String nombreEstadoVacante;
    private final List<String> palabrasClave;
    private final String imagenUrl;

    public VacanteDTOConsulta(Integer idVacante,
            String tituloVacante,
            OffsetDateTime fechaInicioVacante,
            String minSalarioVacante,
            String maxSalarioVacante,
            String nombreUbicacion,
            String nombreEmpresa,
            String nombreJornada,
            String nombreModalidad,
            String nombreTipoContrato,
            String nombrePrivadoAnuncio,
            String nombreEstadoVacante,
            List<String> palabrasClave) {
        this(idVacante, tituloVacante, fechaInicioVacante, minSalarioVacante, maxSalarioVacante,
                nombreUbicacion, nombreEmpresa, nombreJornada, nombreModalidad, nombreTipoContrato,
                nombrePrivadoAnuncio, nombreEstadoVacante, palabrasClave, null);
    }

    public VacanteDTOConsulta(Integer idVacante,
            String tituloVacante,
            OffsetDateTime fechaInicioVacante,
            String minSalarioVacante,
            String maxSalarioVacante,
            String nombreUbicacion,
            String nombreEmpresa,
            String nombreJornada,
            String nombreModalidad,
            String nombreTipoContrato,
            String nombrePrivadoAnuncio,
            String nombreEstadoVacante,
            List<String> palabrasClave,
            String imagenUrl) {
        this.idVacante = idVacante;
        this.tituloVacante = tituloVacante;
        this.fechaInicioVacante = fechaInicioVacante;
        this.minSalarioVacante = minSalarioVacante;
        this.maxSalarioVacante = maxSalarioVacante;
        this.nombreUbicacion = nombreUbicacion;
        this.nombreEmpresa = nombreEmpresa;
        this.nombreJornada = nombreJornada;
        this.nombreModalidad = nombreModalidad;
        this.nombreTipoContrato = nombreTipoContrato;
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
        this.nombreEstadoVacante = nombreEstadoVacante;
        this.palabrasClave = palabrasClave;
        this.imagenUrl = imagenUrl;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public OffsetDateTime getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public String getMinSalarioVacante() {
        return minSalarioVacante;
    }

    public String getMaxSalarioVacante() {
        return maxSalarioVacante;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getNombreJornada() {
        return nombreJornada;
    }

    public String getNombreModalidad() {
        return nombreModalidad;
    }

    public String getNombreTipoContrato() {
        return nombreTipoContrato;
    }

    public String getNombrePrivadoAnuncio() {
        return nombrePrivadoAnuncio;
    }

    public String getNombreEstadoVacante() {
        return nombreEstadoVacante;
    }

    public List<String> getPalabrasClave() {
        return palabrasClave;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public VacanteDTOConsulta withImagenUrl(String imagenUrl) {
        return new VacanteDTOConsulta(
                idVacante, tituloVacante, fechaInicioVacante,
                minSalarioVacante, maxSalarioVacante,
                nombreUbicacion, nombreEmpresa, nombreJornada,
                nombreModalidad, nombreTipoContrato,
                nombrePrivadoAnuncio, nombreEstadoVacante,
                palabrasClave, imagenUrl
        );
    }
}
