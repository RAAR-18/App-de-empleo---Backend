package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Table(
        name = "requisitos",
        indexes = @Index(name = "idx_requisito_vacante", columnList = "id_vacante")
)
@Entity(name = "vacante_Requisito")
public class Requisito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito")
    private Integer idRequisito;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "titulo_requisito", nullable = false, length = 200)
    private String tituloRequisito;

    @NotNull
    @Lob
    @Column(name = "detalle_requisito", nullable = false)
    private String detalleRequisito;

    @NotNull
    @Column(name = "orden_requisito", nullable = false)
    private short ordenRequisito;

    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Vacante idVacante;

    public Requisito() {
    }

    public Requisito(Integer idRequisito) {
        this.idRequisito = idRequisito;
    }

    public Requisito(
            Integer idRequisito, String tituloRequisito,
            String detalleRequisito, short ordenRequisito
    ) {
        this.idRequisito = idRequisito;
        this.tituloRequisito = tituloRequisito;
        this.detalleRequisito = detalleRequisito;
        this.ordenRequisito = ordenRequisito;
    }

    public Integer getIdRequisito() {
        return idRequisito;
    }

    public void setIdRequisito(Integer idRequisito) {
        this.idRequisito = idRequisito;
    }

    public String getTituloRequisito() {
        return tituloRequisito;
    }

    public void setTituloRequisito(String tituloRequisito) {
        this.tituloRequisito = tituloRequisito;
    }

    public String getDetalleRequisito() {
        return detalleRequisito;
    }

    public void setDetalleRequisito(String detalleRequisito) {
        this.detalleRequisito = detalleRequisito;
    }

    public short getOrdenRequisito() {
        return ordenRequisito;
    }

    public void setOrdenRequisito(short ordenRequisito) {
        this.ordenRequisito = ordenRequisito;
    }

    public Vacante getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Vacante idVacante) {
        this.idVacante = idVacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRequisito != null ? idRequisito.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Requisito)) {
            return false;
        }
        Requisito other = (Requisito) object;
        return !((this.idRequisito == null && other.idRequisito != null) || (this.idRequisito != null && !this.idRequisito.equals(other.idRequisito)));
    }

    @Override
    public String toString() {
        return "idRequisito=" + idRequisito
                + ", tituloRequisito='" + tituloRequisito + '\''
                + ", ordenRequisito=" + ordenRequisito
                + ", vacanteId=" + (idVacante != null ? idVacante.getIdVacante() : null);
    }

}
