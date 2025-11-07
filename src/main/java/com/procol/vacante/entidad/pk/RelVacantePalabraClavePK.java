package com.procol.vacante.entidad.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelVacantePalabraClavePK implements Serializable {

    @Column(name = "id_vacante", nullable = false)
    private Integer idVacante;

    @Column(name = "id_palabra_clave", nullable = false)
    private Integer idPalabraClave;

    public RelVacantePalabraClavePK() {
    }

    public RelVacantePalabraClavePK(Integer idVacante, Integer idPalabraClave) {
        this.idVacante = idVacante;
        this.idPalabraClave = idPalabraClave;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Integer getIdPalabraClave() {
        return idPalabraClave;
    }

    public void setIdPalabraClave(Integer idPalabraClave) {
        this.idPalabraClave = idPalabraClave;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RelVacantePalabraClavePK)) {
            return false;
        }
        RelVacantePalabraClavePK that = (RelVacantePalabraClavePK) o;
        return Objects.equals(idVacante, that.idVacante)
                && Objects.equals(idPalabraClave, that.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVacante, idPalabraClave);
    }
}
