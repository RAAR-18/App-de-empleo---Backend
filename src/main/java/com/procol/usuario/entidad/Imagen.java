package com.procol.usuario.entidad;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

@Table(name = "imagenes")
@Entity(name = "usuario_Imagen")
public class Imagen implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_imagen")
    private Integer idImagen;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario idUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_publico_imagen", nullable = false)
    private String nombrePublicoImagen;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_privado_imagen", nullable = false)
    private String nombrePrivadoImagen;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_imagen", nullable = false)
    private String tipoImagen;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tamanio_imagen", nullable = false)
    private String tamanioImagen;

    @Basic(optional = false)
    @NotNull
    @Column(name = "favorita_imagen", nullable = false)
    private short favoritaImagen;

    public Imagen() {
    }

    public Imagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Imagen(Integer idImagen, Usuario idUsuario, String nombrePublicoImagen,
            String nombrePrivadoImagen, String tipoImagen, String tamanioImagen, short favoritaImagen
    ) {
        this.idImagen = idImagen;
        this.idUsuario = idUsuario;
        this.nombrePublicoImagen = nombrePublicoImagen;
        this.nombrePrivadoImagen = nombrePrivadoImagen;
        this.tipoImagen = tipoImagen;
        this.tamanioImagen = tamanioImagen;
        this.favoritaImagen = favoritaImagen;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePublicoImagen() {
        return nombrePublicoImagen;
    }

    public void setNombrePublicoImagen(String nombrePublicoImagen) {
        this.nombrePublicoImagen = nombrePublicoImagen;
    }

    public String getNombrePrivadoImagen() {
        return nombrePrivadoImagen;
    }

    public void setNombrePrivadoImagen(String nombrePrivadoImagen) {
        this.nombrePrivadoImagen = nombrePrivadoImagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getTamanioImagen() {
        return tamanioImagen;
    }

    public void setTamanioImagen(String tamanioImagen) {
        this.tamanioImagen = tamanioImagen;
    }

    public short getFavoritaImagen() {
        return favoritaImagen;
    }

    public void setFavoritaImagen(short favoritaImagen) {
        this.favoritaImagen = favoritaImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idImagen != null ? idImagen.hashCode() : 0);
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
        Imagen other = (Imagen) obj;
        return idImagen != null && idImagen.equals(other.idImagen);
    }

    @Override
    public String toString() {
        return "Imagen[ "
                + "idImagen=" + idImagen
                + ", idUsuario=" + idUsuario
                + ", nombrePublicoImagen=" + nombrePublicoImagen
                + ", nombrePrivadoImagen=" + nombrePrivadoImagen
                + ", tipoImagen=" + tipoImagen
                + ", tamanioImagen=" + tamanioImagen
                + ", favoritaImagen" + favoritaImagen
                + " ]";
    }

}
