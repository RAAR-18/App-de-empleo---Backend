package com.procol.empresa.controlador.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.dto.TipoEmpresaDTOActualizar;
import com.procol.empresa.servicio.tipoempresa.TipoEmpresaActualizarServicio;

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
@RequestMapping("/tipo-empresa")
public class TipoEmpresaActualizarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final TipoEmpresaActualizarServicio tipoEmpresaActualizarServicio;

    public TipoEmpresaActualizarControlador(
            ContextoSeguridad contextoSeguridad,
            TipoEmpresaActualizarServicio tipoEmpresaActualizarServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.tipoEmpresaActualizarServicio = tipoEmpresaActualizarServicio;
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarTE(@RequestBody TipoEmpresaDTOActualizar dto) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        TipoEmpresaDTO objActualizado = tipoEmpresaActualizarServicio.actualizarTipoEmpresa(idEjecutor, dto);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ACTUALIZADO, objActualizado);
    }

}
