package com.procol.vacante.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Table(name = "vacantes")
@Entity(name = "vacante_Vacante")
public class Vacante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vacante")
    private Integer idVacante;

    @JoinColumn(name = "id_ubicacion", referencedColumnName = "id_ubicacion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Ubicacion idUbicacion;

    @JoinColumns({
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"),
        @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa")})
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private RelUsuarioEmpresa relUsuarioEmpresa;

    @JoinColumn(name = "id_jornada", referencedColumnName = "id_jornada")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Jornada idJornada;

    @JoinColumn(name = "id_modalidad", referencedColumnName = "id_modalidad")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Modalidad idModalidad;

    @JoinColumn(name = "id_tipo_contrato", referencedColumnName = "id_tipo_contrato")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoContrato idTipoContrato;

    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "titulo_vacante", nullable = false, length = 300)
    private String tituloVacante;

    @NotNull
    @Lob
    @Column(name = "detalle_vacante", nullable = false)
    private String detalleVacante;

    @NotNull
    @Column(name = "fecha_inicio_vacante", nullable = false)
    private OffsetDateTime fechaInicioVacante;

    @NotNull
    @Column(name = "fecha_fin_vacante", nullable = false)
    private OffsetDateTime fechaFinVacante;

    @NotNull
    @Column(name = "estado_vacante", nullable = false)
    private short estadoVacante;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "min_salario_vacante", nullable = false, length = 100)
    private String minSalarioVacante;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "max_salario_vacante", nullable = false, length = 100)
    private String maxSalarioVacante;

    public Vacante() {
    }

    public Vacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public Vacante(
            Integer idVacante, String tituloVacante, String detalleVacante,
            OffsetDateTime fechaInicioVacante, OffsetDateTime fechaFinVacante,
            short estadoVacante, String minSalarioVacante, String maxSalarioVacante
    ) {
        this.idVacante = idVacante;
        this.tituloVacante = tituloVacante;
        this.detalleVacante = detalleVacante;
        this.fechaInicioVacante = fechaInicioVacante;
        this.fechaFinVacante = fechaFinVacante;
        this.estadoVacante = estadoVacante;
        this.minSalarioVacante = minSalarioVacante;
        this.maxSalarioVacante = maxSalarioVacante;
    }

    public Integer getIdVacante() {
        return idVacante;
    }

    public void setIdVacante(Integer idVacante) {
        this.idVacante = idVacante;
    }

    public String getTituloVacante() {
        return tituloVacante;
    }

    public void setTituloVacante(String tituloVacante) {
        this.tituloVacante = tituloVacante;
    }

    public String getDetalleVacante() {
        return detalleVacante;
    }

    public void setDetalleVacante(String detalleVacante) {
        this.detalleVacante = detalleVacante;
    }

    public OffsetDateTime getFechaInicioVacante() {
        return fechaInicioVacante;
    }

    public void setFechaInicioVacante(OffsetDateTime fechaInicioVacante) {
        this.fechaInicioVacante = fechaInicioVacante;
    }

    public OffsetDateTime getFechaFinVacante() {
        return fechaFinVacante;
    }

    public void setFechaFinVacante(OffsetDateTime fechaFinVacante) {
        this.fechaFinVacante = fechaFinVacante;
    }

    public short getEstadoVacante() {
        return estadoVacante;
    }

    public void setEstadoVacante(short estadoVacante) {
        this.estadoVacante = estadoVacante;
    }

    public String getMinSalarioVacante() {
        return minSalarioVacante;
    }

    public void setMinSalarioVacante(String minSalarioVacante) {
        this.minSalarioVacante = minSalarioVacante;
    }

    public String getMaxSalarioVacante() {
        return maxSalarioVacante;
    }

    public void setMaxSalarioVacante(String maxSalarioVacante) {
        this.maxSalarioVacante = maxSalarioVacante;
    }

    public Jornada getIdJornada() {
        return idJornada;
    }

    public void setIdJornada(Jornada idJornada) {
        this.idJornada = idJornada;
    }

    public Modalidad getIdModalidad() {
        return idModalidad;
    }

    public void setIdModalidad(Modalidad idModalidad) {
        this.idModalidad = idModalidad;
    }

    public RelUsuarioEmpresa getRelUsuariosEmpresas() {
        return relUsuarioEmpresa;
    }

    public void setRelUsuariosEmpresas(RelUsuarioEmpresa relUsuariosEmpresas) {
        this.relUsuarioEmpresa = relUsuariosEmpresas;
    }

    public TipoContrato getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(TipoContrato idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
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
        hash += (idVacante != null ? idVacante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vacante)) {
            return false;
        }
        Vacante other = (Vacante) object;
        return !((this.idVacante == null && other.idVacante != null) || (this.idVacante != null && !this.idVacante.equals(other.idVacante)));
    }

    @Override
    public String toString() {
        return "Vacante{"
                + "idVacante=" + idVacante
                + ", ubicacionId=" + (idUbicacion != null ? idUbicacion.getIdUbicacion() : null)
                + ", usuarioEmpresa=" + (relUsuarioEmpresa != null ? relUsuarioEmpresa.getRelUsuarioEmpresaPK() : null)
                + ", tituloVacante='" + tituloVacante + '\''
                + ", estadoVacante=" + estadoVacante
                + '}';
    }

}
