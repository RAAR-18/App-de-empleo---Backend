package com.procol.usuario.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "usuarios")
@Entity(name = "usuario_Usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "tipo_documento_usuario", nullable = false)
    private short tipoDocumentoUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "documento_usuario", nullable = false, unique = true)
    private String documentoUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombres_usuario", nullable = false)
    private String nombresUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "apellidos_usuario", nullable = false)
    private String apellidosUsuario;

    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_usuario", nullable = false)
    private short estadoUsuario;

    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ubicacion idUbicacion;

    public Usuario() {
    }

    public Usuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(Integer idUsuario, short tipoDocumentoUsuario, String documentoUsuario,
            String nombresUsuario, String apellidosUsuario, short estadoUsuario, Ubicacion idUbicacion
    ) {
        this.idUsuario = idUsuario;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.estadoUsuario = estadoUsuario;
        this.idUbicacion = idUbicacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public short getTipoDocumentoUsuario() {
        return tipoDocumentoUsuario;
    }

    public void setTipoDocumentoUsuario(short tipoDocumentoUsuario) {
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public short getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(short estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    public Ubicacion getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Ubicacion idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        return !((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario)));
    }

    @Override
    public String toString() {
        return "Usuarios["
                + "idUsuario=" + idUsuario
                + ", idUbicacion=" + idUbicacion
                + ", documentoUsuario=" + documentoUsuario
                + ", nombresUsuario=" + nombresUsuario
                + ", apellidosUsuario=" + apellidosUsuario
                + ", estadoUsuario=" + estadoUsuario
                + " ]";
    }

}
