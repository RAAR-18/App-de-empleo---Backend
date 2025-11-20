package com.procol.telefono.controlador;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.telefono.dto.*;
import com.procol.telefono.servicio.CambioTelefonoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/perfil/telefono")
public class CambioTelefonoControlador {

    private final CambioTelefonoServicio cambioTelefonoServicio;

    public CambioTelefonoControlador(CambioTelefonoServicio cambioTelefonoServicio) {
        this.cambioTelefonoServicio = cambioTelefonoServicio;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarCambio(
            @RequestBody IniciarCambioTelefonoDtoPeticion peticion
    ) {
        IniciarCambioTelefonoDtoRespuesta respuesta =
                cambioTelefonoServicio.iniciarCambioTelefono(peticion);

        return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, respuesta);
    }

    @PostMapping("/verificar-anterior")
    public ResponseEntity<?> verificarPinAnterior(
            @RequestBody VerificarPinAnteriorDtoPeticion peticion
    ) {
        VerificarPinAnteriorDtoRespuesta respuesta =
                cambioTelefonoServicio.verificarPinAnterior(peticion);

        return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, respuesta);
    }

    @PostMapping("/verificar-nuevo")
    public ResponseEntity<?> verificarPinNuevo(
            @RequestBody VerificarPinNuevoDtoPeticion peticion
    ) {
        VerificarPinNuevoDtoRespuesta respuesta =
                cambioTelefonoServicio.verificarPinNuevo(peticion);

        return RespuestaHttp.ok(ConstMensajeRespuesta.RESULTADO_OK, respuesta);
    }
}
