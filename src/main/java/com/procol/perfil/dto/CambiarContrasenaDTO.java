package com.procol.perfil.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CambiarContrasenaDTO {

    @NotBlank(message = "La contraseña actual es obligatoria")
    private String contrasenaActual;

    @NotBlank(message = "La nueva contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String contrasenaNueva;

    public CambiarContrasenaDTO() {
    }

    public CambiarContrasenaDTO(String contrasenaActual, String contrasenaNueva) {
        this.contrasenaActual = contrasenaActual;
        this.contrasenaNueva = contrasenaNueva;
    }

    public String getContrasenaActual() {
        return contrasenaActual;
    }

    public void setContrasenaActual(String contrasenaActual) {
        this.contrasenaActual = contrasenaActual;
    }

    public String getContrasenaNueva() {
        return contrasenaNueva;
    }

    public void setContrasenaNueva(String contrasenaNueva) {
        this.contrasenaNueva = contrasenaNueva;
    }

    @Override
    public String toString() {
        return "CambiarContrasenaDTO{" +
                "contrasenaActual='[PROTEGIDO]'" +
                ", contrasenaNueva='[PROTEGIDO]'" +
                '}';
    }
}
