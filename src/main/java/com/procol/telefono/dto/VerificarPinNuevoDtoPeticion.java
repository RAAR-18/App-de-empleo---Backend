package com.procol.telefono.dto;

public class VerificarPinNuevoDtoPeticion {
    Long idCambioTelefono;
    String pinNuevo;

    public VerificarPinNuevoDtoPeticion() {}

    public VerificarPinNuevoDtoPeticion(Long idCambioTelefono, String pinNuevo) {
        this.idCambioTelefono = idCambioTelefono;
        this.pinNuevo = pinNuevo;
    }

    public Long getIdCambioTelefono() {
        return idCambioTelefono;
    }

    public void setIdCambioTelefono(Long idCambioTelefono) {
        this.idCambioTelefono = idCambioTelefono;
    }

    public String getPinNuevo() {
        return pinNuevo;
    }

    public void setPinNuevo(String pinNuevo) {
        this.pinNuevo = pinNuevo;
    }
}
