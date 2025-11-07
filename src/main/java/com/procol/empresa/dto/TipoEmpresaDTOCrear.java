package com.procol.empresa.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class TipoEmpresaDTOCrear {

    @NotNull
    @Size(min = 1, max = 150)
    private String nombreTipoEmpresa;

    private Short estadoTipoEmpresa;

    public TipoEmpresaDTOCrear(String nombreTipoEmpresa, Short estadoTipoEmpresa) {
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
    }

    public String getNombreTipoEmpresa() {
        return nombreTipoEmpresa;
    }

    public void setNombreTipoEmpresa(String nombreTipoEmpresa) {
        this.nombreTipoEmpresa = nombreTipoEmpresa;
    }

    public Short getEstadoTipoEmpresa() {
        return estadoTipoEmpresa;
    }

    public void setEstadoTipoEmpresa(Short estadoTipoEmpresa) {
        this.estadoTipoEmpresa = estadoTipoEmpresa;
    }

    @Override
    public String toString() {
        return "TipoEmpresaCrearDTO{"
                + "nombreTipoEmpresa=" + nombreTipoEmpresa
                + ", estadoTipoEmpresa=" + estadoTipoEmpresa + '}';
    }

}
