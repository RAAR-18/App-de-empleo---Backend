package com.procol.perfil.entidad.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RelUsuarioTalentoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_talento", nullable = false)
    private Integer idTalento;

    public RelUsuarioTalentoPK() {}

    public RelUsuarioTalentoPK(Integer idUsuario, Integer idTalento) {
        this.idUsuario = idUsuario;
        this.idTalento = idTalento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdTalento() {
        return idTalento;
    }

    public void setIdTalento(Integer idTalento) {
        this.idTalento = idTalento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RelUsuarioTalentoPK)) return false;

        RelUsuarioTalentoPK that = (RelUsuarioTalentoPK) o;
        return Objects.equals(idUsuario, this.idUsuario) && Objects.equals(idTalento, this.idTalento);
    }

    @Override
    public int hashCode() { return Objects.hash(idUsuario, idTalento); }
}
