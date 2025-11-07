package com.procol.registropublico.dto;

public class PinTemporalDtoPeticion {

    private String telefonoAcceso;

    public PinTemporalDtoPeticion() {
    }

    public PinTemporalDtoPeticion(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

}
