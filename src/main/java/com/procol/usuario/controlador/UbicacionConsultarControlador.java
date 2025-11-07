package com.procol.usuario.controlador;

import java.util.Map;

import com.procol.usuario.dto.UbicacionDTOPaginado;
import com.procol.usuario.servicio.UbicacionConsultarServicio;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.utilidad.respuesta.RespuestaHttp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario/ubicacion")
public class UbicacionConsultarControlador {

    private final UbicacionConsultarServicio ubicacionConsultarServicio;

    private static final Map<String, String> CAMPOS_ENTIDAD = Map.of(
            "idUbicacion", "u.id_ubicacion",
            "nombreUbicacion", "u.nombre_ubicacion",
            "idDaneUbicacion", "u.id_dane_ubicacion"
    );

    public UbicacionConsultarControlador(UbicacionConsultarServicio ubicacionConsultarServicio) {
        this.ubicacionConsultarServicio = ubicacionConsultarServicio;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadRegistros() {
        int cantidad = ubicacionConsultarServicio.cantidadRegistros();
        return RespuestaHttp.ok(ConstMensajeRespuesta.CANTIDAD_REGISTROS, cantidad);
    }

    // Dado que son muchos registros, por seguridad se inhabilita
    // *************************************************************************
//    @GetMapping("/listar")
//    public ResponseEntity<?> listar(
//            @RequestParam(defaultValue = "idUbicacion") String campoOrden,
//            @RequestParam(defaultValue = "asc") String orden
//    ) {
//        Sort sort = VerificarOrdenamiento.obtenerSortJpa(campoOrden, orden, CAMPOS_ENTIDAD);
//        List<UbicacionDTO> resultado = ubicacionConsultarServicio.consultarUbicacionDTO(sort);
//
//        if (resultado == null || resultado.isEmpty()) {
//            return RespuestaHttp.sinContenido("No hay Ubicaciones registradas");
//        }
//        return RespuestaHttp.ok(MensajeRespuesta.CONSULTA_OK, resultado);
//    }
    // *************************************************************************
    @GetMapping("/listar-paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "idUbicacion") String campoOrden,
            @RequestParam(defaultValue = "ASC") String orden,
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

        PaginacionDto<UbicacionDTOPaginado> resultado = ubicacionConsultarServicio.consultaPaginada(
                campoBusquedaSql, buscar, campoOrdenSql, orden, numPagina, tamanio
        );

        if (resultado.getContenido().isEmpty()) {
            return RespuestaHttp.sinContenido(ConstMensajeRespuesta.REGISTRO_NO_ENCONTRADOS);
        }
        return RespuestaHttp.ok("Paginación de ubicaciones", resultado);
    }

}
