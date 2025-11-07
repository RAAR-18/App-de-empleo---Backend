package com.procol.mensajeria.servicio;

import com.procol.mensajeria.dto.mensaje.MensajeDtoPeticion;
import com.procol.mensajeria.api.MensajeServicio;
import com.procol.mensajeria.dto.mensaje.MensajeDtoRespuesta;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MensajeServicioImpl implements MensajeServicio  {

    private final WebClient webClient;

    public MensajeServicioImpl(
            WebClient.Builder builder,
            ObjectMapper mapper,
            @Value("${hablame.api.url}") String apiUrl,
            @Value("${hablame.api.key}") String apiKey) {

        this.webClient = builder
                .baseUrl(apiUrl)
                .defaultHeader("accept", "application/json")
                .defaultHeader("content-type", "application/json")
                .defaultHeader("X-Hablame-Key", apiKey)
                .build();
    }

    @Override
    public MensajeDtoRespuesta enviarSms(MensajeDtoPeticion peticion) {
        try {
            String respuesta = webClient.post()
                    .bodyValue(peticion)
                    .retrieve()
                    .onStatus(status -> !status.is2xxSuccessful(),
                            resp -> resp.bodyToMono(String.class)
                                    .map(msg -> new RuntimeException("Error al enviar: " + msg)))
                    .bodyToMono(String.class)
                    .block();

            return new MensajeDtoRespuesta(true, respuesta);
        } catch (Exception e) {
            return new MensajeDtoRespuesta(false, "Error de comunicación: " + e.getMessage());
        }
    }
}
