package com.procol.empresa.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class EmpresaDTOCrear {

    @NotNull
    @Size(min = 1, max = 200)
    private String nombreEmpresa;

    @NotNull
    private Integer idTipoEmpresa;

    public EmpresaDTOCrear(String nombreEmpresa, Integer idTipoEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    @Override
    public String toString() {
        return "EmpresaCrearDTO{"
                + "nombreEmpresa=" + nombreEmpresa
                + ", idTipoEmpresa=" + idTipoEmpresa + '}';
    }

}
