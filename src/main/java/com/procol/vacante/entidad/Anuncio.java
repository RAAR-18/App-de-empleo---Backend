package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "anuncios")
@Entity(name = "vacante_Anuncio")
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_vacante", nullable = false)
    private Integer idVacante;

    @MapsId
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vacante", referencedColumnName = "id_vacante")
    private Vacante vacante;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_publico_anuncio", nullable = false, length = 200)
    private String nombrePublicoAnuncio;

    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nombre_privado_anuncio", nullable = false, length = 200)
    private String nombrePrivadoAnuncio;

    @Size(min = 1, max = 50)
    @Column(name = "tipo_anuncio", nullable = false, length = 50)
    private String tipoAnuncio;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tamanio_anuncio", nullable = false, length = 50)
    private String tamanioAnuncio;

    public Anuncio() {
    }

    public Anuncio(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Anuncio(
            Integer idVacante, String nombrePublicoAnuncio,
            String nombrePrivadoAnuncio, String tipoAnuncio,
            String tamanioAnuncio
    ) {
        this.idVacante = idVacante;
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
        this.tipoAnuncio = tipoAnuncio;
        this.tamanioAnuncio = tamanioAnuncio;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getNombrePublicoAnuncio() {
        return nombrePublicoAnuncio;
    }

    public void setNombrePublicoAnuncio(String nombrePublicoAnuncio) {
        this.nombrePublicoAnuncio = nombrePublicoAnuncio;
    }

    public String getNombrePrivadoAnuncio() {
        return nombrePrivadoAnuncio;
    }

    public void setNombrePrivadoAnuncio(String nombrePrivadoAnuncio) {
        this.nombrePrivadoAnuncio = nombrePrivadoAnuncio;
    }

    public String getTipoAnuncio() {
        return tipoAnuncio;
    }

    public void setTipoAnuncio(String tipoAnuncio) {
        this.tipoAnuncio = tipoAnuncio;
    }

    public String getTamanioAnuncio() {
        return tamanioAnuncio;
    }

    public void setTamanioAnuncio(String tamanioAnuncio) {
        this.tamanioAnuncio = tamanioAnuncio;
    }

    public Vacante getVacante() {
        return vacante;
    }

    public void setVacante(Vacante vacante) {
        this.vacante = vacante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idVacante != null ? idVacante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anuncio other = (Anuncio) obj;
        return Objects.equals(this.idVacante, other.idVacante);
    }

    @Override
    public String toString() {
        return "idVacante=" + idVacante
                + ", nombrePublicoAnuncio='" + nombrePublicoAnuncio + '\''
                + ", nombrePrivadoAnuncio='" + nombrePrivadoAnuncio + '\''
                + ", tipoAnuncio='" + tipoAnuncio + '\''
                + ", tamanioAnuncio='" + tamanioAnuncio + '\''
                + ", vacanteId=" + (vacante != null ? vacante.getIdVacante() : null);
    }

}
