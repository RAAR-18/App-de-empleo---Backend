package com.procol.perfil.entidad.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelUsuarioPalabraClavePK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_palabra_clave", nullable = false)
    private Integer idPalabraClave;

    public RelUsuarioPalabraClavePK() {
    }

    public RelUsuarioPalabraClavePK(Integer idUsuario, Integer idPalabraClave) {
        this.idUsuario = idUsuario;
        this.idPalabraClave = idPalabraClave;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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
        if (!(o instanceof RelUsuarioPalabraClavePK)) {
            return false;
        }

        RelUsuarioPalabraClavePK that = (RelUsuarioPalabraClavePK) o;
        return Objects.equals(idUsuario, that.idUsuario)
                && Objects.equals(idPalabraClave, that.idPalabraClave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPalabraClave);
    }
}