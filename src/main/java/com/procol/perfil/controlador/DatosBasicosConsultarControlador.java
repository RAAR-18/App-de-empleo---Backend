package com.procol.perfil.controlador;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.DatosBasicosDTO;
import com.procol.perfil.servicio.DatosBasicosConsultarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/datos-basicos/consultar")
public class DatosBasicosConsultarControlador {

    private final DatosBasicosConsultarServicio  datosBasicosConsultarServicio;

    public DatosBasicosConsultarControlador(DatosBasicosConsultarServicio datosBasicosConsultarServicio) {
        this.datosBasicosConsultarServicio =  datosBasicosConsultarServicio;
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> obtenerDatosBasicos(@PathVariable Integer idUsuario) {
        DatosBasicosDTO respuesta = datosBasicosConsultarServicio.obtenerDatosBasicos(idUsuario);
        return RespuestaHttp.ok("Datos basicos cargados", respuesta);
    }
}
