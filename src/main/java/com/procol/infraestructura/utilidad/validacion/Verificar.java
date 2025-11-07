package com.procol.infraestructura.utilidad.validacion;

import java.util.Map;
import java.util.Objects;

import com.procol.infraestructura.constante.ConstEstadoRegistro;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;

public class Verificar {

    private Verificar() {
    }

    public static void estadoRegistro(Short estado) {
        if (estado != null
                && !Objects.equals(estado, ConstEstadoRegistro.ACTIVO)
                && !Objects.equals(estado, ConstEstadoRegistro.INACTIVO)
                && !Objects.equals(estado, ConstEstadoRegistro.BLOQUEADO)) {
            throw new ExcepcionValidacion("Estado no válido: " + estado);
        }
    }

    public static void ordenSQL(String orden) {
        if (!orden.equalsIgnoreCase("asc") && !orden.equalsIgnoreCase("desc")) {
            throw new ExcepcionValidacion("Orden incorrecto: " + orden);
        }
    }

    public static void validarPaginacion(int numPagina, long totalRegistros, int tamanio) {
        int totalPaginas = (int) Math.ceil((double) totalRegistros / tamanio);
        if (numPagina >= totalPaginas && totalPaginas > 0) {
            throw new ExcepcionValidacion("Página solicitada incorrecta. Total de páginas: " + totalPaginas);
        }
    }

    public static void camposBusqueda(String buscar, String campoBusqueda) {
        boolean buscarPresente = buscar != null && !buscar.isBlank();
        boolean campoPresente = campoBusqueda != null && !campoBusqueda.isBlank();

        if (buscarPresente != campoPresente) {
            throw new ExcepcionValidacion("Debe proporcionar ambos parámetros: 'buscar' y 'campoBusqueda'");
        }
    }

    public static String validarCampo(String campo, Map<String, String> camposEntidad, boolean isNative, String tipoCampo) {
        String campoValido = obtenerCampoValido(campo, camposEntidad, isNative);

        if (campo != null && campoValido == null) {
            throw new ExcepcionValidacion("Campo de " + tipoCampo + " no permitido: " + campo);
        }
        return campoValido;
    }

    private static String obtenerCampoValido(String campo, Map<String, String> camposEntidad, boolean isNative) {
        if (campo == null || campo.isBlank() || camposEntidad == null) {
            return null;
        }

        String campoNormalizado = campo.toLowerCase();

        return camposEntidad.entrySet().stream()
                .filter(entry -> entry.getKey().toLowerCase().equals(campoNormalizado))
                .map(entry -> isNative ? entry.getValue() : entry.getKey())
                .findFirst()
                .orElse(null);
    }

}
