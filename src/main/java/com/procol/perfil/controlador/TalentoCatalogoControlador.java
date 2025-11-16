package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.entidad.Talento;
import com.procol.perfil.servicio.TalentoCatalogoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/talento")
public class TalentoCatalogoControlador {

    private final TalentoCatalogoServicio talentoCatalogoServicio;

    public TalentoCatalogoControlador(TalentoCatalogoServicio talentoCatalogoServicio) {
        this.talentoCatalogoServicio = talentoCatalogoServicio;
    }

    @GetMapping("/catalogo")
    public ResponseEntity<?> listarTodosCatalogo() {
        List<Talento> talentos = talentoCatalogoServicio.listarTodos();
        return RespuestaHttp.ok("Catálogo de competencias obtenido correctamente", talentos);
    }
}