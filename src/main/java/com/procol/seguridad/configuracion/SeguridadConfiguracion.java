package com.procol.seguridad.configuracion;

import java.util.List;

import com.procol.seguridad.constante.RutaPublica;
import com.procol.seguridad.constante.RutaPrivadaRol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
@Profile("security-enabled")
public class SeguridadConfiguracion {

    private final SeguridadFiltroToken seguridadFiltroToken;
    private final SeguridadInterceptor interceptorAccesoNoAutenticado;

    public SeguridadConfiguracion(SeguridadFiltroToken seguridadFiltroToken,
            SeguridadInterceptor interceptorAccesoNoAutenticado) {
        this.seguridadFiltroToken = seguridadFiltroToken;
        this.interceptorAccesoNoAutenticado = interceptorAccesoNoAutenticado;
    }

    @Bean
    public BCryptPasswordEncoder codificadorDeClaves() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager gestorDeAutenticacion(
            AuthenticationConfiguration configuracionAutenticacion) throws Exception {
        return configuracionAutenticacion.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        config.setExposedHeaders(List.of("Authorization"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filtrosDeSeguridad(HttpSecurity http) throws Exception {
        return http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(autorizacion -> {
                    autorizacion.requestMatchers(RutaPublica.RUTAS_PUBLICAS).permitAll();
                    RutaPrivadaRol.MAPA_RUTA_ROL.forEach((ruta, roles)
                            -> autorizacion.requestMatchers(ruta).hasAnyAuthority(roles.toArray(String[]::new))
                    );
                    autorizacion.anyRequest().authenticated();
                })
                .exceptionHandling(ex -> ex.authenticationEntryPoint(interceptorAccesoNoAutenticado))
                .addFilterBefore(seguridadFiltroToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
