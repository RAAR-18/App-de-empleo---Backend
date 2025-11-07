package com.procol.comun.dto;

import java.time.LocalDateTime;

public class IngresoDtoResumen {

    private Long cantidad;
    private LocalDateTime ultimoIngreso;
    private Boolean esPrimerIngreso;

    public IngresoDtoResumen(
            Long cantidad, LocalDateTime ultimoIngreso, Boolean esPrimerIngreso
    ) {
        this.cantidad = cantidad;
        this.ultimoIngreso = ultimoIngreso;
        this.esPrimerIngreso = esPrimerIngreso;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public LocalDateTime getUltimoIngreso() {
        return ultimoIngreso;
    }

    public void setUltimoIngreso(LocalDateTime ultimoIngreso) {
        this.ultimoIngreso = ultimoIngreso;
    }

    public Boolean getEsPrimerIngreso() {
        return esPrimerIngreso;
    }

    public void setEsPrimerIngreso(Boolean esPrimerIngreso) {
        this.esPrimerIngreso = esPrimerIngreso;
    }

}
