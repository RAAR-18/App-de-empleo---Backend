package com.procol.comun.controlador;

import com.procol.comun.dto.PerfilDtoCabecera;
import com.procol.comun.servicio.PerfilCabeceraServicio;

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
@RequestMapping("/comun/perfil")
public class PerfilInfoCabeceraControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final PerfilCabeceraServicio perfilCabeceraServicio;

    public PerfilInfoCabeceraControlador(
            ContextoSeguridad contextoSeguridad,
            PerfilCabeceraServicio perfilCabeceraServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.perfilCabeceraServicio = perfilCabeceraServicio;
    }

    @GetMapping("/cabecera")
    public ResponseEntity<?> obtenerResumenIngreso() {
        Integer idUsuarioSesion = contextoSeguridad.getIdUsuario();
        
        PerfilDtoCabecera resumen = perfilCabeceraServicio.obtenerCabecera(idUsuarioSesion);
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resumen);
    }

}
