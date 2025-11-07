package com.procol.empresa.servicio.empresa;

import java.util.List;
import java.util.ArrayList;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.repositorio.EmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.EmpresaMapeador;
import com.procol.empresa.utilidad.querybuilder.SqlBuilderEmpresa;
import com.procol.empresa.utilidad.mapeador.EmpresaMapeadorNativo;

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
public class EmpresaConsultarServicio
        extends OperacionCrudPaginadoImple<Empresa, EmpresaDTO, Integer> {

    // Repositorio obligatorio
    private final EmpresaRepositorio empresaRepositorio;

    // Servicios o repositorios adicionales
    private final EmpresaMapeador empresaMapeador;
    private final EmpresaMapeadorNativo empresaNativoMapeador;

    public EmpresaConsultarServicio(
            EmpresaRepositorio empresaRepositorio,
            EmpresaMapeador empresaMapeador,
            EmpresaMapeadorNativo empresaNativoMapeador,
            BusquedaServicio<Empresa, Integer> BusquedaServicioEmpresa
    ) {
        super(BusquedaServicioEmpresa);
        this.empresaRepositorio = empresaRepositorio;
        this.empresaMapeador = empresaMapeador;
        this.empresaNativoMapeador = empresaNativoMapeador;
    }

    @Override
    protected JpaRepository<Empresa, Integer> getRepositorio() {
        return empresaRepositorio;
    }

    public List<EmpresaDTO> consultarEmpresasDTO(Sort sort) {
        List<Empresa> listaEmpresas = consultarTodos(sort);
        List<EmpresaDTO> listaEmpresasDTO = new ArrayList<>();

        for (Empresa objEmpresa : listaEmpresas) {
            EmpresaDTO dto = empresaMapeador.desdeEntidad(objEmpresa);
            listaEmpresasDTO.add(dto);
        }
        return listaEmpresasDTO;
    }

    @Override
    public PaginacionDto<EmpresaDTO> consultaPaginada(
            String campoBusqueda, String valorBusqueda,
            String campoOrden, String orden, int numPagina, int tamanio
    ) {

        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderEmpresa.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderEmpresa.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = (List<Tuple>) consulta.getResultList();

        List<EmpresaDTO> registrosDTO = resultados.stream()
                .map(empresaNativoMapeador::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);
    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderEmpresa.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderEmpresa.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************
}
