package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.PalabraClaveDTOAsignar;
import com.procol.perfil.servicio.PalabraClaveAsignarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("palabra-clave")
public class PalabraClaveAsignarControlador {

    private final PalabraClaveAsignarServicio palabraClaveAsignarServicio;

    public PalabraClaveAsignarControlador(PalabraClaveAsignarServicio palabraClaveAsignarServicio) {
        this.palabraClaveAsignarServicio = palabraClaveAsignarServicio;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarPalabrasClave(@RequestBody PalabraClaveDTOAsignar palabraClaveDTO) {
        palabraClaveAsignarServicio.asignarPalabrasClave(palabraClaveDTO);
        return RespuestaHttp.ok("Palabras clave asignadas correctamente", null);
    }
}
