package com.procol.empresa.dto;

public class EmpresaDTO {

    private Integer idEmpresa;
    private String nombreEmpresa;
    private TipoEmpresaDTO idTipoEmpresa;

    public EmpresaDTO(Integer idEmpresa, String nombreEmpresa, TipoEmpresaDTO idTipoEmpresa) {
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

    public TipoEmpresaDTO getIdTipoEmpresa() {
        return idTipoEmpresa;
    }

    public void setIdTipoEmpresa(TipoEmpresaDTO idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    @Override
    public String toString() {
        return "EmpresaDTO{"
                + "idEmpresa=" + idEmpresa
                + ", nombreEmpresa=" + nombreEmpresa
                + ", idTipoEmpresa=" + idTipoEmpresa + '}';
    }

}
