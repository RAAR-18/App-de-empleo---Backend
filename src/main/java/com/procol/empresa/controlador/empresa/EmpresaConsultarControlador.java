package com.procol.empresa.controlador.empresa;

import java.util.Map;
import java.util.List;

import com.procol.empresa.dto.EmpresaDTO;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.utilidad.validacion.VerificarOrdenamiento;
import com.procol.empresa.servicio.empresa.EmpresaConsultarServicio;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/empresa")
public class EmpresaConsultarControlador {

    private final EmpresaConsultarServicio servicioEmpresa;

    private static final Map<String, String> CAMPOS_ENTIDAD = Map.of(
            "idEmpresa", "e.id_empresa",
            "nombreEmpresa", "e.nombre_empresa",
            "nombreTipoEmpresa", "te.nombre_tipo_empresa"
    );

    public EmpresaConsultarControlador(EmpresaConsultarServicio servi) {
        this.servicioEmpresa = servi;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadRegistros() {
        int cantidad = servicioEmpresa.cantidadRegistros();
        return RespuestaHttp.ok(ConstMensajeRespuesta.CANTIDAD_REGISTROS, cantidad);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listar(
            @RequestParam(defaultValue = "idEmpresa") String campoOrden,
            @RequestParam(defaultValue = "ASC") String orden) {

        Sort sort = VerificarOrdenamiento.obtenerSortJpa(campoOrden, orden, CAMPOS_ENTIDAD);
        List<EmpresaDTO> resultado = servicioEmpresa.consultarEmpresasDTO(sort);

        if (resultado == null || resultado.isEmpty()) {
            return RespuestaHttp.sinContenido("No empresas registradas");
        }
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resultado);
    }

    @GetMapping("/listar-paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "idEmpresa") String campoOrden,
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

        PaginacionDto<EmpresaDTO> resultado = servicioEmpresa.consultaPaginada(
                campoBusquedaSql, buscar, campoOrdenSql, orden, numPagina, tamanio
        );

        if (resultado.getContenido().isEmpty()) {
            return RespuestaHttp.sinContenido(ConstMensajeRespuesta.REGISTRO_NO_ENCONTRADOS);
        }
        return RespuestaHttp.ok("Paginación de Empresa", resultado);
    }

}
