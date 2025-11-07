package com.procol.comun.controlador;

import com.procol.comun.dto.ImagenDtoBinario;
import com.procol.comun.servicio.ImagenAvatarServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comun/ingreso")
public class IngresoAvatarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final ImagenAvatarServicio imagenAvatarServicio;

    public IngresoAvatarControlador(
            ContextoSeguridad contextoSeguridad,
            ImagenAvatarServicio imagenAvatarServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.imagenAvatarServicio = imagenAvatarServicio;
    }

    @GetMapping("/avatar")
    public ResponseEntity<byte[]> obtenerImagenIngreso() {
        Integer idUsuarioSesion = contextoSeguridad.getIdUsuario();

        ImagenDtoBinario imagen = imagenAvatarServicio.obtenerFotoCompleta(idUsuarioSesion);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(imagen.getMime()));
        headers.setContentLength(imagen.getContenido().length);

        return new ResponseEntity<>(imagen.getContenido(), headers, HttpStatus.OK);
    }

}
