package com.procol.registropublico.entidad;

import jakarta.persistence.Id;
import jakarta.persistence.Basic;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.Objects;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Table(name = "pines_temporales")
@Entity(name = "registropublico_PinTemporal")
public class PinTemporal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_telefono_pin_temporal", nullable = false)
    private Long idTelefonoPinTemporal;

    @Basic(optional = false)
    @Column(name = "valor_pin_temporal", length = 20, nullable = false)
    private String valorPinTemporal;

    @Column(name = "fecha_creacion_pin_temporal", nullable = false)
    private OffsetDateTime fechaCreacionPinTemporal;

    @Column(name = "intento_pin_temporal", nullable = false)
    private Short intentoPinTemporal;

    @Column(name = "total_intentos_pin_temporal", nullable = false)
    private Short totalIntentosPinTemporal;

    @Column(name = "minutos_suspension_pin_temporal", nullable = false)
    private Short minutosSuspensionPinTemporal;

    protected PinTemporal() {
        // Exclusivo para JPA
    }

    public PinTemporal(
            Long idTelefonoPinTemporal, String valorPinTemporal,
            OffsetDateTime fechaCreacionPinTemporal, Short intentoPinTemporal,
            Short totalIntentosPinTemporal, Short minutosSuspensionPinTemporal
    ) {
        this.idTelefonoPinTemporal = idTelefonoPinTemporal;
        this.valorPinTemporal = valorPinTemporal;
        this.fechaCreacionPinTemporal = fechaCreacionPinTemporal;
        this.intentoPinTemporal = intentoPinTemporal;
        this.totalIntentosPinTemporal = totalIntentosPinTemporal;
        this.minutosSuspensionPinTemporal = minutosSuspensionPinTemporal;
    }

    public Long getIdTelefonoPinTemporal() {
        return idTelefonoPinTemporal;
    }

    public void setIdTelefonoPinTemporal(Long idTelefonoPinTemporal) {
        this.idTelefonoPinTemporal = idTelefonoPinTemporal;
    }

    public String getValorPinTemporal() {
        return valorPinTemporal;
    }

    public void setValorPinTemporal(String valorPinTemporal) {
        this.valorPinTemporal = valorPinTemporal;
    }

    public OffsetDateTime getFechaCreacionPinTemporal() {
        return fechaCreacionPinTemporal;
    }

    public void setFechaCreacionPinTemporal(OffsetDateTime fechaCreacionPinTemporal) {
        this.fechaCreacionPinTemporal = fechaCreacionPinTemporal;
    }

    public Short getIntentoPinTemporal() {
        return intentoPinTemporal;
    }

    public void setIntentoPinTemporal(Short intentoPinTemporal) {
        this.intentoPinTemporal = intentoPinTemporal;
    }

    public Short getTotalIntentosPinTemporal() {
        return totalIntentosPinTemporal;
    }

    public void setTotalIntentosPinTemporal(Short totalIntentosPinTemporal) {
        this.totalIntentosPinTemporal = totalIntentosPinTemporal;
    }

    public Short getMinutosSuspensionPinTemporal() {
        return minutosSuspensionPinTemporal;
    }

    public void setMinutosSuspensionPinTemporal(Short minutosSuspensionPinTemporal) {
        this.minutosSuspensionPinTemporal = minutosSuspensionPinTemporal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PinTemporal that)) {
            return false;
        }
        return Objects.equals(idTelefonoPinTemporal, that.idTelefonoPinTemporal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTelefonoPinTemporal);
    }

    @Override
    public String toString() {
        return String.format(
                "PinTemporal{idTelefonoPinTemporal=%d, valorPinTemporal='%s', "
                + "fechaCreacionPinTemporal=%s, intentoPinTemporal=%d, "
                + "totalIntentosPinTemporal=%d, minutosSuspensionPinTemporal=%d}",
                idTelefonoPinTemporal, valorPinTemporal, fechaCreacionPinTemporal,
                intentoPinTemporal, totalIntentosPinTemporal, minutosSuspensionPinTemporal
        );
    }

}
