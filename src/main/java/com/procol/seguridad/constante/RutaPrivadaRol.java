package com.procol.seguridad.constante;

import java.util.Map;
import java.util.List;

public class RutaPrivadaRol {

    public static final Map<String, List<String>> MAPA_RUTA_ROL = Map.of(
            "/comun/ingreso/**", List.of(
                    "ROLE_Administrador",
                    "ROLE_Tecnologia",
                    "ROLE_Empresa",
                    "ROLE_Psicologo",
                    "ROLE_Entrevistador",
                    "ROLE_Aspirante"),
            "/empresa/**", List.of(
                    "ROLE_Empresa",
                    "ROLE_Tecnologia",
                    "ROLE_Administrador"),
            "/tipo-empresa/**", List.of(
                    "ROLE_Tecnologia",
                    "ROLE_Administrador"),
            "/usuario/**", List.of(
                    "ROLE_Administrador",
                    "ROLE_Tecnologia",
                    "ROLE_Empresa",
                    "ROLE_Psicologo",
                    "ROLE_Entrevistador",
                    "ROLE_Aspirante"),
            "/vacante/**", List.of(
                    "ROLE_Psicologo",
                    "ROLE_Entrevistador",
                    "ROLE_Administrador"),
            "/postulacion/**", List.of(
                    "ROLE_Aspirante",
                    "ROLE_Administrador"),
            "/api/chats/**", List.of(
                    "ROLE_Aspirante",
                    "ROLE_Empresa",
                    "ROLE_Administrador",
                    "ROLE_Psicologo",
                    "ROLE_Entrevistador",
                    "ROLE_Tecnologia"),
            "/api/messages", List.of(
                    "ROLE_Aspirante",
                    "ROLE_Empresa",
                    "ROLE_Administrador",
                    "ROLE_Psicologo",
                    "ROLE_Entrevistador",
                    "ROLE_Tecnologia")
    );

    private RutaPrivadaRol() {
    }
}
