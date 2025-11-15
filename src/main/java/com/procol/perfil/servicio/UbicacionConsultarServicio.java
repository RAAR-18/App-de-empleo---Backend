package com.procol.perfil.servicio;

import java.util.List;
import java.util.ArrayList;

import com.procol.perfil.dto.UbicacionDTO;
import com.procol.perfil.entidad.Ubicacion;
import com.procol.perfil.dto.UbicacionDTOPaginado;
import com.procol.perfil.repositorio.UbicacionRepositorio;
import com.procol.perfil.utilidad.mapeador.UbicacionMapeador;
import com.procol.perfil.utilidad.mapeador.UbicacionMapeadorNativo;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudPaginadoImple;
import com.procol.perfil.utilidad.querybuilder.SqlBuilderUbicacion;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

@Service("perfil_UbicacionConsultarServicio")
public class UbicacionConsultarServicio extends
        OperacionCrudPaginadoImple<Ubicacion, UbicacionDTOPaginado, Integer> {

    // Repositorio obligatorio
    private final UbicacionRepositorio ubicacionRepositorio;

    //Servicios adicionales
    private final UbicacionMapeador ubicacionMapeador;
    private final UbicacionMapeadorNativo ubicacionMapeadorNativo;

    public UbicacionConsultarServicio(
            UbicacionRepositorio ubicacionRepositorio,
            UbicacionMapeador ubicacionMapeador,
            UbicacionMapeadorNativo ubicacionMapeadorNativo,
            BusquedaServicio<Ubicacion, Integer> BusquedaServicioUbicacion
    ) {
        super(BusquedaServicioUbicacion);
        this.ubicacionRepositorio = ubicacionRepositorio;
        this.ubicacionMapeador = ubicacionMapeador;
        this.ubicacionMapeadorNativo = ubicacionMapeadorNativo;
    }

    @Override
    protected JpaRepository<Ubicacion, Integer> getRepositorio() {
        return ubicacionRepositorio;
    }

    public List<UbicacionDTO> consultarUbicacionDTO(Sort sort) {
        List<Ubicacion> listaUbicaciones = consultarTodos(sort);
        List<UbicacionDTO> listaUbicacionDTO = new ArrayList<>();

        for (Ubicacion objUbicacion : listaUbicaciones) {
            UbicacionDTO dto = ubicacionMapeador.desdeEntidad(objUbicacion);
            listaUbicacionDTO.add(dto);
        }
        return listaUbicacionDTO;
    }

    @Override
    public PaginacionDto<UbicacionDTOPaginado> consultaPaginada(
            String campoBusqueda, String valorBusqueda, String campoOrden,
            String orden, int numPagina, int tamanio
    ) {

        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderUbicacion.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderUbicacion.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = consulta.getResultList();
        List<UbicacionDTOPaginado> registrosDTO = resultados.stream()
                .map(ubicacionMapeadorNativo::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);
    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderUbicacion.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderUbicacion.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************
}
