package com.procol.vacante.entidad;

import com.procol.vacante.entidad.pk.RelVacantePalabraClavePK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.io.Serializable;

@Table(name = "rel_vacante_palabraclave")
@Entity(name = "vacante_RelVacantePalabraClave")
public class RelVacantePalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelVacantePalabraClavePK id;

    @MapsId("idVacante")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante", nullable = false)
    private Vacante vacante;

    @MapsId("idPalabraClave")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_palabra_clave", referencedColumnName = "id_palabra_clave", nullable = false)
    private PalabraClave palabraClave;

    public RelVacantePalabraClave() {
    }

    public RelVacantePalabraClave(RelVacantePalabraClavePK id) {
        this.id = id;
    }

    public RelVacantePalabraClavePK getId() {
        return id;
    }

    public void setId(RelVacantePalabraClavePK id) {
        this.id = id;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    public PalabraClave getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(PalabraClave palabraClave) {
        this.palabraClave = palabraClave;
    }

    @Override
    public String toString() {
        return "id=" + id
                + ", vacanteId=" + (vacante != null ? vacante.getIdVacante() : null)
                + ", palabraClaveId=" + (palabraClave != null ? palabraClave.getIdPalabraClave() : null);
    }
}
