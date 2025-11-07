package com.procol.registropublico.entidad;

import java.util.Objects;
import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "accesos")
@Entity(name = "registropublico_Acceso")
public class Acceso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotBlank
    @Size(max = 150)
    @Column(name = "telefono_acceso", nullable = false, unique = true, length = 150)
    private String telefonoAcceso;

    @NotBlank
    @Size(max = 150)
    @Column(name = "correo_acceso", nullable = false, unique = true, length = 150)
    private String correoAcceso;

    @NotBlank
    @Size(max = 150)
    @Column(name = "clave_acceso", nullable = false, length = 150)
    private String claveAcceso;

    @NotBlank
    @Size(max = 150)
    @Column(name = "uuid_acceso", nullable = false, length = 150)
    private String uuidAcceso;

    @MapsId
    @OneToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;

    public Acceso() {
    }

    public Acceso(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Acceso(Integer idUsuario, String telefonoAcceso, String correoAcceso,
            String claveAcceso, String uuidAcceso, Usuario usuario
    ) {
        this.idUsuario = idUsuario;
        this.telefonoAcceso = telefonoAcceso;
        this.correoAcceso = correoAcceso;
        this.claveAcceso = claveAcceso;
        this.uuidAcceso = uuidAcceso;
        this.usuario = usuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getUuidAcceso() {
        return uuidAcceso;
    }

    public void setUuidAcceso(String uuidAcceso) {
        this.uuidAcceso = uuidAcceso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Acceso other)) {
            return false;
        }
        return Objects.equals(this.idUsuario, other.idUsuario);
    }

    @Override
    public String toString() {
        return "Acceso[ idUsuario=" + idUsuario + ", correoAcceso=" + correoAcceso + " ]";
    }

}
