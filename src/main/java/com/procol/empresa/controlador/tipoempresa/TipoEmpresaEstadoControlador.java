package com.procol.empresa.controlador.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.servicio.tipoempresa.TipoEmpresaEstadoServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.Verificar;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo-empresa")
public class TipoEmpresaEstadoControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final TipoEmpresaEstadoServicio tipoEmpresaEstadoServicio;

    public TipoEmpresaEstadoControlador(
            ContextoSeguridad contextoSeguridad,
            TipoEmpresaEstadoServicio tipoEmpresaEstadoServicio
    ) {
        this.contextoSeguridad = contextoSeguridad;
        this.tipoEmpresaEstadoServicio = tipoEmpresaEstadoServicio;
    }

    @PatchMapping("/estado/{codTipoEmpresa}/{nuevoEstado}")
    public ResponseEntity<?> cambiarEstado(
            @PathVariable("codTipoEmpresa") Integer idTipoEmpresa,
            @PathVariable("nuevoEstado") Short nuevoEstado
    ) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        Verificar.estadoRegistro(nuevoEstado);
        TipoEmpresaDTO objActualizado = tipoEmpresaEstadoServicio.cambiarEstadoTipoEmpresa(idEjecutor, idTipoEmpresa, nuevoEstado);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ACTUALIZADO, objActualizado);
    }

}
