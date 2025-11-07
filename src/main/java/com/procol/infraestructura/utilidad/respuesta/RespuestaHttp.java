package com.procol.infraestructura.utilidad.respuesta;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RespuestaHttp {

    public static <T> ResponseEntity<RespuestaHttpModelo<T>> ok(String mensaje, T datos) {
        return ResponseEntity.ok(new RespuestaHttpModelo<>(HttpStatus.OK.value(), mensaje, datos));
    }

    public static <T> ResponseEntity<RespuestaHttpModelo<T>> creado(String mensaje, T datos) {
        return new ResponseEntity<>(new RespuestaHttpModelo<>(HttpStatus.CREATED.value(), mensaje, datos), HttpStatus.CREATED);
    }

    public static <T> ResponseEntity<RespuestaHttpModelo<T>> personalizado(HttpStatus estado, String mensaje, T datos) {
        return new ResponseEntity<>(new RespuestaHttpModelo<>(estado.value(), mensaje, datos), estado);
    }

    public static <T> ResponseEntity<RespuestaHttpModelo<T>> sinContenido(String mensaje) {
        return new ResponseEntity<>(new RespuestaHttpModelo<>(HttpStatus.OK.value(), mensaje, null), HttpStatus.OK);
    }

    public static ResponseEntity<?> error(String mensaje, int codigoEstado) {
        Map<String, Object> cuerpo = new LinkedHashMap<>();
        cuerpo.put("codigoEstado", codigoEstado);
        cuerpo.put("mensaje", mensaje);
        cuerpo.put("fechaHora", LocalDateTime.now());
        cuerpo.put("datos", null);
        return ResponseEntity.status(codigoEstado).body(cuerpo);
    }

}
