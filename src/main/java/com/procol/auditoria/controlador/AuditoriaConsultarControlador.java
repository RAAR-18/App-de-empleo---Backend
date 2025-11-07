package com.procol.auditoria.controlador;

import java.util.Map;

import com.procol.auditoria.dto.AuditoriaDTOPaginado;
import com.procol.auditoria.servicio.AuditoriaConsultarServicio;

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
@RequestMapping("/auditoria")
public class AuditoriaConsultarControlador {

    private final AuditoriaConsultarServicio auditoriaConsultarServicio;

    private static final Map<String, String> CAMPOS_ENTIDAD = Map.of(
            "idAuditoria", "a.id_auditoria",
            "fechaAuditoria", "a.fecha_auditoria",
            "documentoUsuario", "u.documento_usuario",
            "apellidosUsuario", "u.apellidos_usuario"
    );

    public AuditoriaConsultarControlador(AuditoriaConsultarServicio auditoriaConsultarServicio) {
        this.auditoriaConsultarServicio = auditoriaConsultarServicio;
    }

    @GetMapping("/cantidad")
    public ResponseEntity<?> obtenerCantidadRegistros() {
        int cantidad = auditoriaConsultarServicio.cantidadRegistros();
        return RespuestaHttp.ok(ConstMensajeRespuesta.CANTIDAD_REGISTROS, cantidad);
    }

    @GetMapping("/listar-paginado")
    public ResponseEntity<?> listarPaginado(
            @RequestParam(defaultValue = "idAuditoria") String campoOrden,
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

        PaginacionDto<AuditoriaDTOPaginado> resultado = auditoriaConsultarServicio.consultaPaginada(
                campoBusquedaSql, buscar, campoOrdenSql, orden, numPagina, tamanio
        );

        if (resultado.getContenido().isEmpty()) {
            return RespuestaHttp.sinContenido(ConstMensajeRespuesta.REGISTRO_NO_ENCONTRADOS);
        }
        return RespuestaHttp.ok("Paginación de la auditoría", resultado);
    }

}
