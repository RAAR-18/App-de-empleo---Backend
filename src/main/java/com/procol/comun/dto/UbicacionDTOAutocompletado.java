package com.procol.comun.dto;

public class UbicacionDTOAutocompletado {

    private Integer idUbicacion;
    private String nombreUbicacion;
    private String longitudUbicacion;
    private String latitudUbicacion;

    public UbicacionDTOAutocompletado() {
    }

    public UbicacionDTOAutocompletado(
            Integer idUbicacion, String nombreUbicacion,
            String longitudUbicacion, String latitudUbicacion
    ) {
        this.idUbicacion = idUbicacion;
        this.nombreUbicacion = nombreUbicacion;
        this.longitudUbicacion = longitudUbicacion;
        this.latitudUbicacion = latitudUbicacion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getLongitudUbicacion() {
        return longitudUbicacion;
    }

    public void setLongitudUbicacion(String longitudUbicacion) {
        this.longitudUbicacion = longitudUbicacion;
    }

    public String getLatitudUbicacion() {
        return latitudUbicacion;
    }

    public void setLatitudUbicacion(String latitudUbicacion) {
        this.latitudUbicacion = latitudUbicacion;
    }

}
