package com.procol.comun.servicio;

import com.procol.comun.entidad.Ingreso;
import com.procol.comun.dto.PerfilDtoCabecera;
import com.procol.comun.entidad.Usuario;
import com.procol.comun.repositorio.AccesoRepositorio;
import com.procol.comun.repositorio.IngresoRepositorio;
import com.procol.comun.repositorio.UsuarioRepositorio;
import com.procol.comun.utilidad.mapeador.PerfilCabeceraMapeadorNativo;
import com.procol.comun.utilidad.querybuilder.SqlBuilderPerfilCabecera;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import jakarta.persistence.Query;
import jakarta.persistence.Tuple;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class PerfilCabeceraServicio extends OperacionCrudImple<Ingreso, Integer> {

    // Repositorio obligatorio
    private final IngresoRepositorio ingresoRepositorio;

    //Otros repositorios
    private final UsuarioRepositorio usuarioRepositorio;

    // Servicios adicionales
    private final PerfilCabeceraMapeadorNativo perfilCabeceraMapeadorNativo;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;

    public PerfilCabeceraServicio(
            IngresoRepositorio ingresoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            AccesoRepositorio accesoRepositorio,
            PerfilCabeceraMapeadorNativo perfilCabeceraMapeadorNativo,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            BusquedaServicio<Ingreso, Integer> BusquedaServicioIngreso
    ) {
        super(BusquedaServicioIngreso);
        this.ingresoRepositorio = ingresoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.perfilCabeceraMapeadorNativo = perfilCabeceraMapeadorNativo;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
    }

    @Override
    protected JpaRepository<Ingreso, Integer> getRepositorio() {
        return ingresoRepositorio;
    }

    public PerfilDtoCabecera obtenerCabecera(Integer idUsuario) {
        busquedaServicioUsuario.PorId(usuarioRepositorio, idUsuario, "No existe el usuario");
        String sql = SqlBuilderPerfilCabecera.obtener(idUsuario);
        Query consulta = entityManager.createNativeQuery(sql, Tuple.class);
        Tuple resultado = (Tuple) consulta.getSingleResult();

        return perfilCabeceraMapeadorNativo.mapearDesdeTupla(resultado);
    }

}
