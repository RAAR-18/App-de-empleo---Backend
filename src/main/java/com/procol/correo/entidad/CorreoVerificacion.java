package com.procol.correo.entidad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Table(name = "correo_verificacion")
@Entity(name = "correo_CorreoVerificacion")
public class CorreoVerificacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_correo_verificado", nullable = false, length = 150)
    private String idCorreo;

    @Column(name = "pin_correo_verificado", length = 20)
    private String pinCorreo;

    @Column(name = "estado_correo_verificado", nullable = false)
    private Short estadoCorreoVerificado; // 1=sin verificar, 2=pendiente, 3=verificado

    public CorreoVerificacion() {
    }

    public CorreoVerificacion(String idCorreo, Short estadoCorreoVerificado) {
        this.idCorreo = idCorreo;
        this.estadoCorreoVerificado = estadoCorreoVerificado;
    }

    public String getIdCorreo() {
        return idCorreo;
    }

    public void setIdCorreo(String idCorreo) {
        this.idCorreo = idCorreo;
    }

    public String getPinCorreo() {
        return pinCorreo;
    }

    public void setPinCorreo(String pinCorreo) {
        this.pinCorreo = pinCorreo;
    }

    public Short getEstadoCorreoVerificado() {
        return estadoCorreoVerificado;
    }

    public void setEstadoCorreoVerificado(Short estadoCorreoVerificado) {
        this.estadoCorreoVerificado = estadoCorreoVerificado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CorreoVerificacion that)) return false;
        return Objects.equals(idCorreo, that.idCorreo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCorreo);
    }

    @Override
    public String toString() {
        return "CorreoVerificacion{" +
                "idCorreo='" + idCorreo + '\'' +
                ", estadoCorreoVerificado=" + estadoCorreoVerificado +
                '}';
    }
}
