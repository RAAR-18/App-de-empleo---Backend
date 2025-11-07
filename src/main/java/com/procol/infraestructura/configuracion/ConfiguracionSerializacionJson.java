package com.procol.infraestructura.configuracion;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configura componentes relacionados con la serialización JSON.
 *
 * <p>
 * Esta clase registra el módulo {@code JavaTimeModule} para que Jackson pueda
 * serializar correctamente tipos de fecha-hora modernos como {@link java.time.LocalDateTime},
 * {@link java.time.ZonedDateTime} y {@link java.time.Instant}.
 *
 * <p>
 * Spring Boot detecta automáticamente los {@code Module} registrados como
 * {@code @Bean} y los incorpora al {@code ObjectMapper} global, evitando la
 * necesidad de sobreescribir la configuración estándar de mapeo JSON.
 */
@Configuration
public class ConfiguracionSerializacionJson {

    /**
     * Módulo de Jackson para habilitar la compatibilidad con la API de
     * fecha-hora de Java 8.
     *
     * <p>
     * Este bean permite que Jackson serialice y deserialice correctamente
     * objetos como {@link java.time.LocalDateTime} en respuestas JSON. Se
     * recomienda en sistemas que utilicen Java moderno para trazabilidad
     * temporal.
     *
     * @return instancia configurada de {@link JavaTimeModule}
     */
    @Bean
    public Module javaTimeModule() {
        return new JavaTimeModule();
    }
}
