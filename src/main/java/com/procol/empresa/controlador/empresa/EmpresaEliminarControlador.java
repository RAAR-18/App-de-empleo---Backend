package com.procol.empresa.controlador.empresa;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.servicio.empresa.EmpresaEliminarServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empresa")
public class EmpresaEliminarControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final EmpresaEliminarServicio empresaEliminarServicio;

    public EmpresaEliminarControlador(
            EmpresaEliminarServicio empresaEliminarServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.empresaEliminarServicio = empresaEliminarServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<?> eliminar(@PathVariable("codigo") Integer idEmpresa) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        EmpresaDTO dtoEliminado = empresaEliminarServicio.eliminarEmpresa(idEjecutor, idEmpresa);
        return RespuestaHttp.ok(ConstMensajeRespuesta.REGISTRO_ELIMINADO, dtoEliminado);
    }

}
