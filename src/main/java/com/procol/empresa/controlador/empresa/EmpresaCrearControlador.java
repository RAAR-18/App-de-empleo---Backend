package com.procol.empresa.controlador.empresa;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.dto.EmpresaDTOCrear;
import com.procol.empresa.servicio.empresa.EmpresaCrearServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empresa")
public class EmpresaCrearControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final EmpresaCrearServicio empresaCrearServicio;

    public EmpresaCrearControlador(
            EmpresaCrearServicio empresaCrearServicio,
            ContextoSeguridad contextoSeguridad
    ) {
        this.empresaCrearServicio = empresaCrearServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> nuevaEmpresa(@RequestBody EmpresaDTOCrear dto) {
        Integer idEjecutor = contextoSeguridad.getIdUsuario();

        EmpresaDTO empresaNueva = empresaCrearServicio.crearEmpresa(idEjecutor, dto);
        return RespuestaHttp.creado(ConstMensajeRespuesta.REGISTRO_CREADO, empresaNueva);
    }

}
