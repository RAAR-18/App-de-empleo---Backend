package com.procol.perfil.controlador;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.servicio.ArchivoEliminarServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/perfil/archivo")
public class ArchivoEliminarControlador {

    private final ArchivoEliminarServicio archivoEliminarServicio;

    public ArchivoEliminarControlador(ArchivoEliminarServicio archivoEliminarServicio) {
        this.archivoEliminarServicio = archivoEliminarServicio;
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> eliminarArchivo(@PathVariable("codigo") Integer idArchivo) {
        ArchivoDTO dtoEliminado = archivoEliminarServicio.eliminarArchivo(idArchivo);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ELIMINADO, dtoEliminado);
    }
}
