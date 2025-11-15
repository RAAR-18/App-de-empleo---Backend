package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.PalabraClaveDTO;
import com.procol.perfil.servicio.PerfilPalabraClaveConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/palabra-clave")
public class PalabraClaveConsultarControlador {

    private final PerfilPalabraClaveConsultarServicio perfilPalabraClaveConsultarServicio;

    public PalabraClaveConsultarControlador(PerfilPalabraClaveConsultarServicio perfilPalabraClaveConsultarServicio) {
        this.perfilPalabraClaveConsultarServicio =  perfilPalabraClaveConsultarServicio;
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<?> obtenerPalabrasPorUsuario(@PathVariable Integer idUsuario) {
        List<PalabraClaveDTO> palabras = perfilPalabraClaveConsultarServicio.obtenerPalabrasClavesPorUsuario(idUsuario);
        return RespuestaHttp.ok("Lista de palabras", palabras);
    }
}
