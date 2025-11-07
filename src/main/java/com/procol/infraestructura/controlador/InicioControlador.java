package com.procol.infraestructura.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InicioControlador {

    @GetMapping("/")
    public String inicio() {
        return "Bienvenido a la API de Propósitos Colombia!";
    }
}
