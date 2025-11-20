package com.procol.telefono.dto;

public class VerificarPinAnteriorDtoPeticion {

    Long idCambioTelefono;
    String pinAnterior;

    public VerificarPinAnteriorDtoPeticion(Long idCambioTelefono, String pinAnterior) {
        this.idCambioTelefono = idCambioTelefono;
        this.pinAnterior = pinAnterior;
    }

    public Long getIdCambioTelefono() {
        return idCambioTelefono;
    }

    public void setIdCambioTelefono(Long idCambioTelefono) {
        this.idCambioTelefono = idCambioTelefono;
    }

    public String getPinAnterior() {
        return pinAnterior;
    }

    public void setPinAnterior(String pinAnterior) {
        this.pinAnterior = pinAnterior;
    }
}
