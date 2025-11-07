package com.procol.usuario.controlador;

import com.procol.usuario.dto.ImagenDTO;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.usuario.servicio.ImagenEliminarServicio;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/imagen")
public class ImagenEliminarControlador {

    private final ImagenEliminarServicio imagenEliminarServicio;

    public ImagenEliminarControlador(ImagenEliminarServicio imagenEliminarServicio) {
        this.imagenEliminarServicio = imagenEliminarServicio;
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> borrarImagen(@PathVariable("codigo") Integer idImagen) {
        ImagenDTO dtoEliminado = imagenEliminarServicio.eliminarImagen(idImagen);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ELIMINADO, dtoEliminado);
    }

}
