package com.procol.usuario.controlador;

import com.procol.usuario.dto.AccesoDTO;
import com.procol.usuario.dto.AccesoDTOCrear;
import com.procol.usuario.servicio.AccesoRegistrarServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/acceso")
public class AccesoRegistrarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final AccesoRegistrarServicio accesoRegistrarServicio;

    public AccesoRegistrarControlador(
            ContextoSeguridad contextoSeguridad,
            AccesoRegistrarServicio accesoRegistrarServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.accesoRegistrarServicio = accesoRegistrarServicio;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> nuevoUsuario(@RequestBody AccesoDTOCrear dto) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        AccesoDTO respuesta = accesoRegistrarServicio.agregarCuentaAcceso(idEjecutor, dto);
        return RespuestaHttp.ok("Cuenta de usuario registrada", respuesta);
    }

}
