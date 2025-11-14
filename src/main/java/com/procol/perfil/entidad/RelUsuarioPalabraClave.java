package com.procol.perfil.entidad;

import com.procol.perfil.entidad.pk.RelUsuarioPalabraClavePK;
import jakarta.persistence.*;

import java.io.Serializable;

@Table(name = "rel_usuario_palabraclave")
@Entity(name = "perfil_RelUsuarioPalabraClave")
public class RelUsuarioPalabraClave implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RelUsuarioPalabraClavePK id;

    @MapsId("idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", nullable = false)
    private Usuario usuario;

    @MapsId("idPalabraClave")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_palabra_clave", referencedColumnName = "id_palabra_clave", nullable = false)
    private PalabraClave palabraClave;

    public RelUsuarioPalabraClave() {
    }

    public RelUsuarioPalabraClave(RelUsuarioPalabraClavePK id) {
        this.id = id;
    }

    public RelUsuarioPalabraClavePK getId() {
        return id;
    }

    public void setId(RelUsuarioPalabraClavePK id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PalabraClave getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(PalabraClave palabraClave) {
        this.palabraClave = palabraClave;
    }

    @Override
    public String toString() {
        return "RelUsuarioPalabraClave{" +
                "id=" + id +
                ", usuarioId=" + (usuario != null ? usuario.getIdUsuario() : null) +
                ", palabraClaveId=" + (palabraClave != null ? palabraClave.getIdPalabraClave() : null) +
                '}';
    }
}