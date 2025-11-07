package com.procol.empresa.controlador.empresa;

import com.procol.empresa.dto.UsuarioEmpresaDTO;
import com.procol.empresa.servicio.empresa.RelacionUsuarioEmpresaServicio;

import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class UsuarioEmpresaControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final RelacionUsuarioEmpresaServicio relacionServicio;

    public UsuarioEmpresaControlador(
            RelacionUsuarioEmpresaServicio relacionServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.relacionServicio = relacionServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PostMapping("/asignarusuempre")
    public ResponseEntity<?> asignarUsuarioAEmpresa(@RequestBody UsuarioEmpresaDTO dto) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        UsuarioEmpresaDTO respuesta = relacionServicio.asignarUsuarioAEmpresa(idEjecutor, dto);
        return RespuestaHttp.creado("Usuario - empresa asignado correctamente", respuesta);
    }
}
