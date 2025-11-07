package com.procol.usuario.servicio;

import java.util.List;
import java.util.ArrayList;

import com.procol.usuario.entidad.Rol;
import com.procol.usuario.dto.RolDTO;
import com.procol.usuario.dto.RolDTOCantidadUsuario;
import com.procol.usuario.repositorio.RolRepositorio;
import com.procol.usuario.utilidad.mapeador.RolMapeador;
import com.procol.usuario.utilidad.querybuilder.SqlBuilderRol;
import com.procol.usuario.utilidad.mapeador.RolMapeadorNativo;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudPaginadoImple;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.Tuple;
import jakarta.persistence.Query;

@Service
public class RolConsultarServicio extends
        OperacionCrudPaginadoImple<Rol, RolDTOCantidadUsuario, Integer> {

    // Repositorio obligatorio
    private final RolRepositorio rolRepositorio;
    
    // Servicios o repositorios adicionales
    private final RolMapeador rolMapeador;
    private final RolMapeadorNativo rolNativoMapeador;

    public RolConsultarServicio(
            RolRepositorio rolRepositorio,
            RolMapeador rolMapeador,
            RolMapeadorNativo rolNativoMapeador,
            BusquedaServicio<Rol, Integer> BusquedaServicioRol) {

        super(BusquedaServicioRol);
        this.rolRepositorio = rolRepositorio;
        this.rolMapeador = rolMapeador;
        this.rolNativoMapeador = rolNativoMapeador;
    }

    @Override
    protected JpaRepository<Rol, Integer> getRepositorio() {
        return rolRepositorio;
    }

    public List<RolDTO> consultarRolesDTO(Sort sort) {
        // Forma compacta:
        // return consultarTodos(sort).stream().map(rolMapeador::desdeEntidad).toList();
        List<Rol> listaRoles = consultarTodos(sort);
        List<RolDTO> listaRolesDTO = new ArrayList<>();

        for (Rol objRol : listaRoles) {
            RolDTO dto = rolMapeador.desdeEntidad(objRol);
            listaRolesDTO.add(dto);
        }
        return listaRolesDTO;
    }

    @Override
    public PaginacionDto<RolDTOCantidadUsuario> consultaPaginada(
            String campoBusqueda, String valorBusqueda, String campoOrden,
            String orden, int numPagina, int tamanio
    ) {
        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderRol.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderRol.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = consulta.getResultList();
        List<RolDTOCantidadUsuario> registrosDTO = resultados.stream()
                .map(rolNativoMapeador::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);

    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderRol.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderRol.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************

}
