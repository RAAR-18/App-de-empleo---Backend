package com.procol.perfil.entidad;

import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Table(name = "rel_usuarios_talentos")
@Entity(name = "perfil_RelUsuarioTalento")
public class RelUsuarioTalento implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelUsuarioTalentoPK id;

    @MapsId("idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @MapsId("idTalento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_talento", referencedColumnName = "id_talento", nullable = false)
    private Talento talento;

    @NotNull
    @Min(1)
    @Max(3)
    @Column(name = "nivel_dominio", nullable = false)
    private Short nivelDominio; // 1 = Básico, 2 = Intermedio, 3 = Avanzado

    public RelUsuarioTalento() {}

    public RelUsuarioTalento(RelUsuarioTalentoPK id, Short nivelDominio) {
        this.id =  id;
        this.nivelDominio = nivelDominio;
    }

    public RelUsuarioTalentoPK getId() {
        return id;
    }

    public void setId(RelUsuarioTalentoPK id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Talento getTalento() {
        return talento;
    }

    public void setTalento(Talento talento) {
        this.talento = talento;
    }

    public Short getNivelDominio() {
        return nivelDominio;
    }

    public void setNivelDominio(Short nivelDominio) {
        this.nivelDominio = nivelDominio;
    }

    @Override
    public String toString() {
        return "RelUsuarioTalento{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", talento=" + talento +
                ", nivelDominio=" + nivelDominio +
                '}';
    }
}
