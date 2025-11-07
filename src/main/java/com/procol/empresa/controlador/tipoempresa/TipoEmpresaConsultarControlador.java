package com.procol.empresa.controlador.tipoempresa;

import java.util.Map;
import java.util.List;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.dto.TipoEmpresaDTOCantidad;
import com.procol.empresa.servicio.tipoempresa.TipoEmpresaConsultarServicio;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.VerificarOrdenamiento;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tipo-empresa")
public class TipoEmpresaConsultarControlador {

    private final TipoEmpresaConsultarServicio tipoEmpresaConsultarServicio;

    private static final Map<String, String> CAMPOS_ENTIDAD = Map.of(
            "idTipoEmpresa", "te.id_tipo_empresa",
            "nombreTipoEmpresa", "te.nombre_tipo_empresa",
            "estadoTipoEmpresa", "te.estado_tipo_empresa"
    );

    public TipoEmpresaConsultarControlador(TipoEmpresaConsultarServicio tipoEmpresaConsultarServicio) {
        this.tipoEmpresaConsultarServicio = tipoEmpresaConsultarServicio;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadRegistros() {
        int cantidad = tipoEmpresaConsultarServicio.cantidadRegistros();
        return RespuestaHttp.ok(ConstMensajeRespuesta.CANTIDAD_REGISTROS, cantidad);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "idTipoEmpresa") String campoOrden,
            @RequestParam(defaultValue = "asc") String orden
    ) {
        Sort sort = VerificarOrdenamiento.obtenerSortJpa(campoOrden, orden, CAMPOS_ENTIDAD);
        List<TipoEmpresaDTO> resultado = tipoEmpresaConsultarServicio.consultarTipoEmpresaDTO(sort);

        if (resultado == null || resultado.isEmpty()) {
            return RespuestaHttp.sinContenido("No hay tipos de empresa registrados");
        }
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resultado);
    }

    @GetMapping("/listar-paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "idTipoEmpresa") String campoOrden,
            @RequestParam(defaultValue = "asc") String orden,
            @RequestParam(defaultValue = "0") int numPagina,
            @RequestParam(defaultValue = "10") int tamanio,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String campoBusqueda
    ) {
        // Validaciones: Sintáxis - formato - presencia
        Verificar.camposBusqueda(buscar, campoBusqueda);
        String campoOrdenSql = Verificar.validarCampo(campoOrden, CAMPOS_ENTIDAD, true, "orden");
        String campoBusquedaSql = Verificar.validarCampo(campoBusqueda, CAMPOS_ENTIDAD, true, "búsqueda");
        // *********************************************************************

        PaginacionDto<TipoEmpresaDTOCantidad> resultado = tipoEmpresaConsultarServicio.consultaPaginada(
                campoBusquedaSql, buscar, campoOrdenSql, orden, numPagina, tamanio
        );

        if (resultado.getContenido().isEmpty()) {
            return RespuestaHttp.sinContenido(ConstMensajeRespuesta.REGISTRO_NO_ENCONTRADOS);
        }
        return RespuestaHttp.ok("Paginación de TipoEmpresa", resultado);
    }

}
