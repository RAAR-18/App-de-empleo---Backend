package com.procol.empresa.dto;

public class TipoEmpresaDTOActualizar {

    private Integer idTipoEmpresa;
    private String nombreTipoEmpresa;
    private Short estadoTipoEmpresa;

    public TipoEmpresaDTOActualizar(
            Integer idTipoEmpresa, String nombreTipoEmpresa, Short estadoTipoEmpresa
    ) {
        this.idTipoEmpresa = idTipoEmpresa;
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
    }

    public Integer getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
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
        return "TipoEmpresaActualizarDTO{"
                + "idTipoEmpresa=" + idTipoEmpresa
                + ", nombreTipoEmpresa=" + nombreTipoEmpresa
                + ", estadoTipoEmpresa=" + estadoTipoEmpresa + '}';
    }

}
