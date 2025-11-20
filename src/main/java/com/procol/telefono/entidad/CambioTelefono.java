package com.procol.telefono.entidad;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;

@Table(name = "cambios_telefono")
@Entity(name = "telefono_CambiosTelefono")
public class CambioTelefono implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cambio_telefono")
    private Long idCambioTelefono;

    @NotNull
    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_anterior", nullable = false, length = 50)
    private String telefonoAnterior;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "telefono_nuevo", nullable = false, length = 50)
    private String telefonoNuevo;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pin_anterior", nullable = false, length = 20)
    private String pinAnterior;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "pin_nuevo", nullable = false, length = 20)
    private String pinNuevo;

    @NotNull
    @Column(name = "verificado_anterior", nullable = false)
    private Boolean verificadoAnterior = false;

    @NotNull
    @Column(name = "verificado_nuevo", nullable = false)
    private Boolean verificadoNuevo = false;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private OffsetDateTime fechaCreacion;

    @NotNull
    @Column(name = "fecha_expiracion", nullable = false)
    private OffsetDateTime fechaExpiracion;

    @NotNull
    @Column(name = "intentos_anterior", nullable = false)
    private Short intentosAnterior = 0;

    @NotNull
    @Column(name = "intentos_nuevo", nullable = false)
    private Short intentosNuevo = 0;

    @NotNull
    @Column(name = "max_intentos", nullable = false)
    private Short maxIntentos = 3;

    @NotNull
    @Column(name = "completado", nullable = false)
    private Boolean completado = false;

    public CambioTelefono() {
    }

    public CambioTelefono(
            Integer idUsuario,
            String telefonoAnterior,
            String telefonoNuevo,
            String pinAnterior,
            String pinNuevo,
            OffsetDateTime fechaCreacion,
            OffsetDateTime fechaExpiracion
    ) {
        this.idUsuario = idUsuario;
        this.telefonoAnterior = telefonoAnterior;
        this.telefonoNuevo = telefonoNuevo;
        this.pinAnterior = pinAnterior;
        this.pinNuevo = pinNuevo;
        this.fechaCreacion = fechaCreacion;
        this.fechaExpiracion = fechaExpiracion;
        this.verificadoAnterior = false;
        this.verificadoNuevo = false;
        this.intentosAnterior = 0;
        this.intentosNuevo = 0;
        this.maxIntentos = 3;
        this.completado = false;
    }

    public Long getIdCambioTelefono() {
        return idCambioTelefono;
    }

    public void setIdCambioTelefono(Long idCambioTelefono) {
        this.idCambioTelefono = idCambioTelefono;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefonoAnterior() {
        return telefonoAnterior;
    }

    public void setTelefonoAnterior(String telefonoAnterior) {
        this.telefonoAnterior = telefonoAnterior;
    }

    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }

    public String getPinAnterior() {
        return pinAnterior;
    }

    public void setPinAnterior(String pinAnterior) {
        this.pinAnterior = pinAnterior;
    }

    public String getPinNuevo() {
        return pinNuevo;
    }

    public void setPinNuevo(String pinNuevo) {
        this.pinNuevo = pinNuevo;
    }

    public Boolean getVerificadoAnterior() {
        return verificadoAnterior;
    }

    public void setVerificadoAnterior(Boolean verificadoAnterior) {
        this.verificadoAnterior = verificadoAnterior;
    }

    public Boolean getVerificadoNuevo() {
        return verificadoNuevo;
    }

    public void setVerificadoNuevo(Boolean verificadoNuevo) {
        this.verificadoNuevo = verificadoNuevo;
    }

    public OffsetDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(OffsetDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public OffsetDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(OffsetDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public Short getIntentosAnterior() {
        return intentosAnterior;
    }

    public void setIntentosAnterior(Short intentosAnterior) {
        this.intentosAnterior = intentosAnterior;
    }

    public Short getIntentosNuevo() {
        return intentosNuevo;
    }

    public void setIntentosNuevo(Short intentosNuevo) {
        this.intentosNuevo = intentosNuevo;
    }

    public Short getMaxIntentos() {
        return maxIntentos;
    }

    public void setMaxIntentos(Short maxIntentos) {
        this.maxIntentos = maxIntentos;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    @Override
    public String toString() {
        return String.format(
                "CambioTelefono{id=%d, idUsuario=%d, telefonoAnterior='%s', " +
                        "telefonoNuevo='%s', verificadoAnterior=%b, verificadoNuevo=%b, completado=%b}",
                idCambioTelefono, idUsuario, telefonoAnterior, telefonoNuevo,
                verificadoAnterior, verificadoNuevo, completado
        );
    }
}
