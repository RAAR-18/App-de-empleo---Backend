package com.procol.telefono.dto;

public class IniciarCambioTelefonoDtoPeticion {
    Integer idUsuario;
    String telefonoNuevo;

    public IniciarCambioTelefonoDtoPeticion() {}

    public IniciarCambioTelefonoDtoPeticion(Integer idUsuario, String telefonoNuevo) {
        this.idUsuario = idUsuario;
        this.telefonoNuevo = telefonoNuevo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getTelefonoNuevo() {
        return telefonoNuevo;
    }

    public void setTelefonoNuevo(String telefonoNuevo) {
        this.telefonoNuevo = telefonoNuevo;
    }
}
