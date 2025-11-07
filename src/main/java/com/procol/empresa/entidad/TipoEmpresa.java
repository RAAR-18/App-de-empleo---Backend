package com.procol.empresa.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(
        name = "tipo_empresas",
        indexes = @Index(name = "idx_nombre_tipo_empresa", columnList = "nombre_tipo_empresa")
)
@Entity(name = "empresa_TipoEmpresa")
public class TipoEmpresa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_empresa")
    private Integer idTipoEmpresa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_tipo_empresa", nullable = false, unique = true)
    private String nombreTipoEmpresa;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_tipo_empresa", nullable = false)
    private short estadoTipoEmpresa;

    public TipoEmpresa() {
    }

    public TipoEmpresa(Integer idTipoEmpresa) {
        this.idTipoEmpresa = idTipoEmpresa;
    }

    public TipoEmpresa(Integer idTipoEmpresa, String nombreTipoEmpresa, short estadoTipoEmpresa) {
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

    public short getEstadoTipoEmpresa() {
        return estadoTipoEmpresa;
    }

    public void setEstadoTipoEmpresa(short estadoTipoEmpresa) {
        this.estadoTipoEmpresa = estadoTipoEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoEmpresa != null ? idTipoEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TipoEmpresa other = (TipoEmpresa) obj;
        return idTipoEmpresa != null && idTipoEmpresa.equals(other.idTipoEmpresa);
    }

    @Override
    public String toString() {
        return "["
                + "idTipoEmpresa=" + idTipoEmpresa
                + ", nombreTipoEmpresa=" + nombreTipoEmpresa
                + ", estadoTipoEmpresa" + estadoTipoEmpresa
                + "]";
    }

}
