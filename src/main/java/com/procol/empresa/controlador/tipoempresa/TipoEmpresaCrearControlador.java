package com.procol.empresa.controlador.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.dto.TipoEmpresaDTOCrear;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;
import com.procol.empresa.servicio.tipoempresa.TipoEmpresaCrearServicio;

import com.procol.seguridad.utilidad.ContextoSeguridad;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo-empresa")
public class TipoEmpresaCrearControlador {

    private final ContextoSeguridad contextoSeguridad;
    private final TipoEmpresaCrearServicio tipoEmpresaCrearServicio;

    public TipoEmpresaCrearControlador(
            TipoEmpresaCrearServicio tipoEmpresaCrearServicio,
            ContextoSeguridad contextoSeguridad,
            TipoEmpresaMapeador tipoEmpresaMapeador
    ) {
        this.tipoEmpresaCrearServicio = tipoEmpresaCrearServicio;
        this.contextoSeguridad = contextoSeguridad;
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearTE(@RequestBody TipoEmpresaDTOCrear dto) {
        int idUsuario = contextoSeguridad.getIdUsuario();

        TipoEmpresaDTO objNuevo = tipoEmpresaCrearServicio.crearTipoEmpresa(dto, idUsuario);
        return RespuestaHttp.creado(ConstMensajeRespuesta.REGISTRO_CREADO, objNuevo);
    }

}
