package com.procol.empresa.controlador.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.servicio.tipoempresa.TipoEmpresaEliminarServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo-empresa")
public class TipoEmpresaEliminarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final TipoEmpresaEliminarServicio tipoEmpresaEliminarServicio;

    public TipoEmpresaEliminarControlador(
            ContextoSeguridad contextoSeguridad,
            TipoEmpresaEliminarServicio tipoEmpresaEliminarServicio
    ) {
        this.tipoEmpresaEliminarServicio = tipoEmpresaEliminarServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable("codigo") Integer idTipoEmpresa) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        TipoEmpresaDTO dtoEliminado = tipoEmpresaEliminarServicio.eliminarTipoEmpresa(idEjecutor, idTipoEmpresa);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ELIMINADO, dtoEliminado);
    }

}
