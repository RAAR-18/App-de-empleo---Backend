package com.procol.registropublico.controlador;

import com.procol.registropublico.dto.CuentaDTOCrear;
import com.procol.registropublico.servicio.CuentaCrearServicio;

import com.procol.seguridad.dto.RespuestaJwtDTO;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.registropublico.utilidad.validacion.CuentaValidacion;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class CuentaCrearControlador {

    private final CuentaCrearServicio cuentaCrearServicio;

    public CuentaCrearControlador(
            CuentaCrearServicio cuentaCrearServicio
    ) {
        this.cuentaCrearServicio = cuentaCrearServicio;
    }

    @PostMapping("/register")
    public ResponseEntity<?> nuevaCuenta(@RequestBody CuentaDTOCrear dto) {
        CuentaValidacion.verificarDTO(dto);

        RespuestaJwtDTO respuesta = cuentaCrearServicio.crearCuenta(dto);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_CREADO, respuesta);
    }

}
