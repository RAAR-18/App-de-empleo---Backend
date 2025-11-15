package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.dto.RelUsuarioTalentoDTOAgregar;
import com.procol.perfil.servicio.PerfilAgregarTalentoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoAgregarControlador {

    private final PerfilAgregarTalentoServicio perfilAgregarTalentoServicio;

    public TalentoAgregarControlador(PerfilAgregarTalentoServicio perfilAgregarTalentoServicio) {
        this.perfilAgregarTalentoServicio = perfilAgregarTalentoServicio;
    }

    @PostMapping("/agregar")
    public ResponseEntity<?> agregarTalento(@RequestBody RelUsuarioTalentoDTOAgregar dto) {
        RelUsuarioTalentoDTO respuesta = perfilAgregarTalentoServicio.asignarTalento(dto);
        return RespuestaHttp.ok("Talento agregado correctamente al usuario", respuesta);
    }
}
