package com.procol.perfil.dto;

public class UbicacionDTOPaginado {

    private Integer idUbicacion;
    private Integer idPadreUbicacion;
    private String nombreUbicacion;
    private String idDaneUbicacion;
    private String longitudUbicacion;
    private String latitudUbicacion;

    public UbicacionDTOPaginado() {
    }

    public UbicacionDTOPaginado(
            Integer idUbicacion, Integer idPadreUbicacion, String nombreUbicacion,
            String idDaneUbicacion, String longitudUbicacion, String latitudUbicacion
    ) {
        this.idUbicacion = idUbicacion;
        this.idPadreUbicacion = idPadreUbicacion;
        this.nombreUbicacion = nombreUbicacion;
        this.idDaneUbicacion = idDaneUbicacion;
        this.longitudUbicacion = longitudUbicacion;
        this.latitudUbicacion = latitudUbicacion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdPadreUbicacion() {
        return idPadreUbicacion;
    }

    public void setIdPadreUbicacion(Integer idPadreUbicacion) {
        this.idPadreUbicacion = idPadreUbicacion;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public String getIdDaneUbicacion() {
        return idDaneUbicacion;
    }

    public void setIdDaneUbicacion(String idDaneUbicacion) {
        this.idDaneUbicacion = idDaneUbicacion;
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

    @Override
    public String toString() {
        return "UbicacionDTO["
                + "idUbicacion=" + idUbicacion
                + ", idPadreUbicacion=" + idPadreUbicacion
                + ", nombreUbicacion=" + nombreUbicacion
                + ", idDaneUbicacion=" + idDaneUbicacion
                + ", longitudUbicacion=" + longitudUbicacion
                + ", latitudUbicacion=" + latitudUbicacion
                + "]";
    }

}
