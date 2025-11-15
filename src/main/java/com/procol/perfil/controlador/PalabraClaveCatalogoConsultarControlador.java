package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.PalabraClaveDTO;
import com.procol.perfil.servicio.PalabraClaveCatalogoConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/palabra-clave")
public class PalabraClaveCatalogoConsultarControlador {

    private final PalabraClaveCatalogoConsultarServicio palabraClaveCatalogoConsultarServicio;

    public PalabraClaveCatalogoConsultarControlador (PalabraClaveCatalogoConsultarServicio palabraClaveCatalogoConsultarServicio) {
        this.palabraClaveCatalogoConsultarServicio = palabraClaveCatalogoConsultarServicio;
    }

    @GetMapping("/catalogo")
    public ResponseEntity<?> obtenerCatalogo() {
        List<PalabraClaveDTO> catalogo = palabraClaveCatalogoConsultarServicio.obtenerCatalogo();
        return RespuestaHttp.ok("Catálogo de palabras clave", catalogo);
    }
}
