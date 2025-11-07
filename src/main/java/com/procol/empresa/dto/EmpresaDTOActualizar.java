package com.procol.empresa.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class EmpresaDTOActualizar {

    @NotNull
    private Integer idEmpresa;

    @NotNull
    @Size(min = 1, max = 200)
    private String nombreEmpresa;

    @NotNull
    private Integer idTipoEmpresa;

    public EmpresaDTOActualizar(Integer idEmpresa, String nombreEmpresa, Integer idTipoEmpresa) {
        this.idEmpresa = idEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
        return "EmpresaActualizarDTO{"
                + "idEmpresa=" + idEmpresa
                + ", nombreEmpresa=" + nombreEmpresa
                + ", idTipoEmpresa=" + idTipoEmpresa + '}';
    }

}
