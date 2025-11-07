package com.procol.usuario.controlador;

import java.util.Map;
import java.util.List;

import com.procol.usuario.dto.RolDTO;
import com.procol.usuario.servicio.RolConsultarServicio;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;
import com.procol.infraestructura.utilidad.validacion.VerificarOrdenamiento;
import com.procol.usuario.dto.RolDTOCantidadUsuario;

import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/rol")
public class RolConsultarControlador {

    private final RolConsultarServicio rolConsultarServicio;

    private static final Map<String, String> CAMPOS_ENTIDAD = Map.of(
            "idRol", "r.id_rol",
            "nombreRol", "r.nombre_rol",
            "estadoRol", "r.estado_rol"
    );

    public RolConsultarControlador(RolConsultarServicio rolConsultarServicio) {
        this.rolConsultarServicio = rolConsultarServicio;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadRegistros() {
        int cantidad = rolConsultarServicio.cantidadRegistros();
        return RespuestaHttp.ok(ConstMensajeRespuesta.CANTIDAD_REGISTROS, cantidad);
    }

    @GetMapping("/listar")
    public ResponseEntity<?> consultarRoles(
            @RequestParam(defaultValue = "idRol") String campoOrden,
            @RequestParam(defaultValue = "asc") String orden
    ) {
        Sort sort = VerificarOrdenamiento.obtenerSortJpa(campoOrden, orden, CAMPOS_ENTIDAD);
        List<RolDTO> resultado = rolConsultarServicio.consultarRolesDTO(sort);

        if (resultado.isEmpty()) {
            return RespuestaHttp.sinContenido("No hay roles registrados");
        }
        return RespuestaHttp.ok(ConstMensajeRespuesta.CONSULTA_OK, resultado);
    }

    @GetMapping("/listar-paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "idRol") String campoOrden,
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

        PaginacionDto<RolDTOCantidadUsuario> resultado = rolConsultarServicio.consultaPaginada(
                campoBusquedaSql, buscar, campoOrdenSql, orden, numPagina, tamanio
        );

        if (resultado.getContenido().isEmpty()) {
            return RespuestaHttp.sinContenido(ConstMensajeRespuesta.REGISTRO_NO_ENCONTRADOS);
        }

        return RespuestaHttp.ok("Paginación de Roles", resultado);
    }

}
