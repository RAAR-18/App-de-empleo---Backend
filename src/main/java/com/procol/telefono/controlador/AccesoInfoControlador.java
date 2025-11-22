package com.procol.telefono.controlador;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.telefono.dto.AccesoDtoRespuesta;
import com.procol.telefono.servicio.AccesoInfoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user/acceso")
public class AccesoInfoControlador {

    private final AccesoInfoServicio accesoInfoServicio;

    public AccesoInfoControlador(AccesoInfoServicio accesoInfoServicio) {
        this.accesoInfoServicio = accesoInfoServicio;
    }

    @GetMapping("/{idUsuario}/telefono")
    public ResponseEntity<?> obtenerTelefono(@PathVariable Integer idUsuario) {
        String telefono = accesoInfoServicio.obtenerTelefonoPorUsuario(idUsuario);

        // Retornar en un objeto simple
        var respuesta = new HashMap<String, String>();
        respuesta.put("telefono", telefono);

        return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, respuesta);
    }

    @GetMapping("/{idUsuario}/informacion")
    public ResponseEntity<?> obtenerInformacionAcceso(@PathVariable Integer idUsuario) {
        AccesoDtoRespuesta respuesta = accesoInfoServicio.obtenerInformacionAcceso(idUsuario);

        return RespuestaHttp.ok(
                "Información de acceso obtenida correctamente",
                respuesta
        );
    }
}
