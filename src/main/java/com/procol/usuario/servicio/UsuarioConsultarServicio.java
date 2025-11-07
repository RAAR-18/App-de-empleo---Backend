package com.procol.usuario.servicio;

import java.util.List;
import java.util.ArrayList;

import com.procol.usuario.dto.UsuarioDTO;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.dto.UsuarioDTOPaginado;
import com.procol.usuario.repositorio.UsuarioRepositorio;
import com.procol.usuario.utilidad.mapeador.UsuarioMapeador;
import com.procol.usuario.utilidad.mapeador.UsuarioMapeadorNativo;
import com.procol.usuario.utilidad.querybuilder.SqlBuilderUsuario;

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
public class UsuarioConsultarServicio extends
        OperacionCrudPaginadoImple<Usuario, UsuarioDTOPaginado, Integer> {

    // Repositorio obligatorio
    private final UsuarioRepositorio usuarioRepositorio;

    //Servicios adicionales
    private final UsuarioMapeador usuarioMapeador;
    private final UsuarioMapeadorNativo usuarioMapeadorNativo;

    public UsuarioConsultarServicio(
            UsuarioRepositorio usuarioRepositorio,
            UsuarioMapeador usuarioMapeador,
            UsuarioMapeadorNativo usuarioMapeadorNativo,
            BusquedaServicio<Usuario, Integer> BusquedaServicioUsuario
    ) {
        super(BusquedaServicioUsuario);
        this.usuarioRepositorio = usuarioRepositorio;
        this.usuarioMapeador = usuarioMapeador;
        this.usuarioMapeadorNativo = usuarioMapeadorNativo;
    }

    @Override
    protected JpaRepository<Usuario, Integer> getRepositorio() {
        return usuarioRepositorio;
    }

    public List<UsuarioDTO> consultarUsuarioDTO(Sort sort) {
        List<Usuario> listaUsuarios = consultarTodos(sort);
        List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();

        for (Usuario objUsuario : listaUsuarios) {
            UsuarioDTO dto = usuarioMapeador.desdeEntidad(objUsuario);
            listaUsuarioDTO.add(dto);
        }
        return listaUsuarioDTO;
    }

    @Override
    public PaginacionDto<UsuarioDTOPaginado> consultaPaginada(
            String campoBusqueda, String valorBusqueda, String campoOrden,
            String orden, int numPagina, int tamanio
    ) {

        boolean incluirBusqueda = campoBusqueda != null;
        long total = incluirBusqueda
                ? contarRegistros(valorBusqueda, campoBusqueda)
                : contarRegistrosSinCondicion();

        Verificar.ordenSQL(orden);
        Verificar.validarPaginacion(numPagina, total, tamanio);

        String condicion = SqlBuilderUsuario.condicionBusqueda(campoBusqueda);
        String sql = SqlBuilderUsuario.consulta(campoOrden, orden, condicion);

        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        consulta.setParameter("limit", tamanio);
        consulta.setParameter("offset", numPagina * tamanio);
        if (incluirBusqueda) {
            consulta.setParameter("valorBusqueda", valorBusqueda);
        }

        List<Tuple> resultados = consulta.getResultList();
        List<UsuarioDTOPaginado> registrosDTO = resultados.stream()
                .map(usuarioMapeadorNativo::mapearDesdeTupla).toList();

        int totalPaginas = (int) Math.ceil((double) total / tamanio);
        return new PaginacionDto<>(registrosDTO, numPagina, tamanio, totalPaginas, (int) total);
    }

    // *************************************************************************
    @Override
    protected long contarRegistros(String valorBusqueda, String campoBD) {
        String sqlCantidad = SqlBuilderUsuario.construirConsultaConteo(campoBD);

        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        consultaCantidad.setParameter("valorBusqueda", valorBusqueda);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }

    @Override
    protected long contarRegistrosSinCondicion() {
        String sqlCantidad = SqlBuilderUsuario.construirConsultaConteoSinCondicion();
        Query consultaCantidad = entityManager.createNativeQuery(sqlCantidad);
        return ((Number) consultaCantidad.getSingleResult()).longValue();
    }
    // *************************************************************************
}
