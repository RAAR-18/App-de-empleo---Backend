package com.procol.perfil.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDateTime;

@Table(name = "archivos")
@Entity(name = "perfil_Archivo")
public class Archivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_Archivo")
    private Integer idArchivo;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Usuario idUsuario;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_publico_archivo", nullable = false)
    private String nombrePublicoArchivo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_privado_archivo", nullable = false)
    private String nombrePrivadoArchivo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo_archivo", nullable = false)
    private String tipoArchivo;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tamanio_archivo", nullable = false)
    private String tamanioArchivo;

    @Basic(optional = false)
    @NotNull
    @Column(name = "grupo_archivo", nullable = false)
    private Integer grupoArchivo;

    @Basic(optional = false)
    @Column(name = "fecha_subida", nullable = false, updatable = false)
    private LocalDateTime fechaSubida;

    @PrePersist
    public void prePersist() {
        this.fechaSubida = LocalDateTime.now();
    }

    public Archivo() {}

    public Archivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Archivo(Integer idArchivo, Usuario idUsuario, String nombrePublicoArchivo,
                  String nombrePrivadoArchivo, String tipoArchivo, String tamanioArchivo
    ) {
        this.idArchivo = idArchivo;
        this.idUsuario = idUsuario;
        this.nombrePublicoArchivo = nombrePublicoArchivo;
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
        this.tipoArchivo = tipoArchivo;
        this.tamanioArchivo = tamanioArchivo;
    }

    public Integer getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Integer idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePublicoArchivo() {
        return nombrePublicoArchivo;
    }

    public void setNombrePublicoArchivo(String nombrePublicoArchivo) {
        this.nombrePublicoArchivo = nombrePublicoArchivo;
    }

    public String getNombrePrivadoArchivo() {
        return nombrePrivadoArchivo;
    }

    public void setNombrePrivadoArchivo(String nombrePrivadoArchivo) {
        this.nombrePrivadoArchivo = nombrePrivadoArchivo;
    }

    public String getTipoArchivo() {
        return tipoArchivo;
    }

    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

    public String getTamanioArchivo() {
        return tamanioArchivo;
    }

    public void setTamanioArchivo(String tamanioArchivo) {
        this.tamanioArchivo = tamanioArchivo;
    }

    public Integer getGrupoArchivo() {
        return grupoArchivo;
    }

    public void setGrupoArchivo(Integer grupoArchivo) {
        this.grupoArchivo = grupoArchivo;
    }

    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this ==  object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Archivo other = (Archivo) object;
        return idArchivo != null && idArchivo.equals(other.idArchivo);
    }

    @Override
    public String toString() {
        return "Archivo{" +
                "idArchivo=" + idArchivo +
                ", idUsuario=" + idUsuario +
                ", nombrePublicoArchivo='" + nombrePublicoArchivo + '\'' +
                ", nombrePrivadoArchivo='" + nombrePrivadoArchivo + '\'' +
                ", tipoArchivo='" + tipoArchivo + '\'' +
                ", tamanioArchivo='" + tamanioArchivo + '\'' +
                ", grupoArchivo=" + grupoArchivo +
                ", fechaSubida=" + fechaSubida +
                '}';
    }
}
