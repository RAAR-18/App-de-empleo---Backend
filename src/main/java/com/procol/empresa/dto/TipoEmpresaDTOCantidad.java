package com.procol.empresa.dto;

public class TipoEmpresaDTOCantidad {

    private Integer idTipoEmpresa;
    private String nombreTipoEmpresa;
    private Short estadoTipoEmpresa;
    private Long cantidadEmpresas;

    public TipoEmpresaDTOCantidad() {
    }

    public TipoEmpresaDTOCantidad(Integer idTipoEmpresa, String nombreTipoEmpresa, Short estadoTipoEmpresa, Long cantidadEmpresas) {
        this.idTipoEmpresa = idTipoEmpresa;
        this.nombreTipoEmpresa = nombreTipoEmpresa;
        this.estadoTipoEmpresa = estadoTipoEmpresa;
        this.cantidadEmpresas = cantidadEmpresas;
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

    public Long getCantidadEmpresas() {
        return cantidadEmpresas;
    }

    public void setCantidadEmpresas(Long cantidadEmpresas) {
        this.cantidadEmpresas = cantidadEmpresas;
    }

    @Override
    public String toString() {
        return "TipoEmpresaDTO{"
                + "idTipoEmpresa=" + idTipoEmpresa
                + ", nombreTipoEmpresa='" + nombreTipoEmpresa + '\''
                + ", estadoTipoEmpresa=" + estadoTipoEmpresa
                + ", cantidadEmpresas=" + cantidadEmpresas
                + '}';
    }

}
