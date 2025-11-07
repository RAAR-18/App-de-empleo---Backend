package com.procol.empresa.controlador.empresa;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.dto.EmpresaDTOActualizar;
import com.procol.empresa.servicio.empresa.EmpresaActualizarServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empresa")
public class EmpresaActualizarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final EmpresaActualizarServicio empresaActualizarServicio;

    public EmpresaActualizarControlador(
            EmpresaActualizarServicio empresaActualizarServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.empresaActualizarServicio = empresaActualizarServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTE(@RequestBody EmpresaDTOActualizar dto) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        EmpresaDTO objActualizado = empresaActualizarServicio.actualizarEmpresa(idEjecutor, dto);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ACTUALIZADO, objActualizado);
    }

}
