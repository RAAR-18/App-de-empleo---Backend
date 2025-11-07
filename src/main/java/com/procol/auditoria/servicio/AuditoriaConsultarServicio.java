package com.procol.auditoria.servicio;

import java.util.List;

import com.procol.auditoria.entidad.Auditoria;
import com.procol.auditoria.dto.AuditoriaDTOPaginado;
import com.procol.auditoria.repositorio.AuditoriaRepositorio;
import com.procol.auditoria.utilidad.mapeador.AuditoriaMapeadorNativo;
import com.procol.auditoria.utilidad.querybuilder.SqlBuilderAuditoria;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudPaginadoImple;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

@Service
public class AuditoriaConsultarServicio extends
        OperacionCrudPaginadoImple<Auditoria, AuditoriaDTOPaginado, Integer> {

    // Repositorio obligatorio
    private final AuditoriaRepositorio auditoriaRepositorio;

    //Servicios adicionales
    private final AuditoriaMapeadorNativo auditoriaMapeadorNativo;

    public AuditoriaConsultarServicio(
            AuditoriaRepositorio auditoriaRepositorio,
            AuditoriaMapeadorNativo auditoriaMapeadorNativo,
            BusquedaServicio<Auditoria, Integer> BusquedaServicioAuditoria
    ) {
        super(BusquedaServicioAuditoria);
        this.auditoriaRepositorio = auditoriaRepositorio;
        this.auditoriaMapeadorNativo = auditoriaMapeadorNativo;
    }

    @Override
    protected JpaRepository<Auditoria, Integer> getRepositorio() {
        return auditoriaRepositorio;
    }

    @Override
    public PaginacionDto<AuditoriaDTOPaginado> consultaPaginada(
            String campoBusqueda, String valorBusqueda, String campoOrden,
            String orden, int numPagina, int tamanio
    ) {

        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderAuditoria.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderAuditoria.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = consulta.getResultList();
        List<AuditoriaDTOPaginado> registrosDTO = resultados.stream()
                .map(auditoriaMapeadorNativo::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);
    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderAuditoria.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderAuditoria.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************
}
