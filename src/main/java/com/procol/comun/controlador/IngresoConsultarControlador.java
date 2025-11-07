package com.procol.comun.controlador;

import com.procol.comun.dto.IngresoDtoResumen;
import com.procol.comun.servicio.IngresoConsultarServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comun/ingreso")
public class IngresoConsultarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final IngresoConsultarServicio ingresoConsultarServicio;

    public IngresoConsultarControlador(
            ContextoSeguridad contextoSeguridad,
            IngresoConsultarServicio ingresoConsultarServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.ingresoConsultarServicio = ingresoConsultarServicio;
    }

    @GetMapping("/resumen")
    public ResponseEntity<?> obtenerResumenIngreso() {
        Integer idUsuarioSesion = contextoSeguridad.getIdUsuario();
        IngresoDtoResumen resumen = ingresoConsultarServicio.obtenerResumenIngreso(idUsuarioSesion);
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resumen);
    }

}
