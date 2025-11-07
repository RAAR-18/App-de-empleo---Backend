package com.procol.empresa.servicio.tipoempresa;

import java.util.List;
import java.util.ArrayList;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.dto.TipoEmpresaDTOCantidad;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;
import com.procol.empresa.utilidad.querybuilder.SqlBuilderTipoEmpresa;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeadorNativo;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.core.crud.OperacionCrudPaginadoImple;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Tuple;
import jakarta.persistence.Query;

@Service
public class TipoEmpresaConsultarServicio extends
        OperacionCrudPaginadoImple<TipoEmpresa, TipoEmpresaDTOCantidad, Integer> {

    // Repositorio obligatorio
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;

    //Servicios adicionales
    private final TipoEmpresaMapeador tipoEmpresaMapeador;
    private final TipoEmpresaMapeadorNativo tipoEmpresaNativoMapeador;

    public TipoEmpresaConsultarServicio(
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            TipoEmpresaMapeador tipoEmpresaMapeador,
            TipoEmpresaMapeadorNativo tipoEmpresaNativoMapeador,
            BusquedaServicio<TipoEmpresa, Integer> BusquedaServicioTipoEmpresa
    ) {
        super(BusquedaServicioTipoEmpresa);
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.tipoEmpresaMapeador = tipoEmpresaMapeador;
        this.tipoEmpresaNativoMapeador = tipoEmpresaNativoMapeador;
    }

    @Override
    protected JpaRepository<TipoEmpresa, Integer> getRepositorio() {
        return tipoEmpresaRepositorio;
    }

    public List<TipoEmpresaDTO> consultarTipoEmpresaDTO(Sort sort) {
        // Forma compacta:
        // return consultarTodos(sort).stream().map(tipoEmpresaMapeador::desdeEntidad).toList();
        List<TipoEmpresa> listaTipoEmpresas = consultarTodos(sort);
        List<TipoEmpresaDTO> listaTipoEmpresaDTO = new ArrayList<>();

        for (TipoEmpresa objTipoEmpresa : listaTipoEmpresas) {
            TipoEmpresaDTO dto = tipoEmpresaMapeador.desdeEntidad(objTipoEmpresa);
            listaTipoEmpresaDTO.add(dto);
        }
        return listaTipoEmpresaDTO;
    }

    @Override
    public PaginacionDto<TipoEmpresaDTOCantidad> consultaPaginada(
            String campoBusqueda, String valorBusqueda, String campoOrden,
            String orden, int numPagina, int tamanio) {

        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderTipoEmpresa.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderTipoEmpresa.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = consulta.getResultList();
        List<TipoEmpresaDTOCantidad> registrosDTO = resultados.stream()
                .map(tipoEmpresaNativoMapeador::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);
    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderTipoEmpresa.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderTipoEmpresa.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************
}
