package com.procol.comun.servicio;

import java.util.List;
import java.time.LocalDateTime;

import com.procol.comun.entidad.Ingreso;
import com.procol.comun.dto.IngresoDtoResumen;
import com.procol.comun.repositorio.AccesoRepositorio;
import com.procol.comun.repositorio.IngresoRepositorio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class IngresoConsultarServicio extends OperacionCrudImple<Ingreso, Integer> {

    // Repositorio obligatorio
    private final IngresoRepositorio ingresoRepositorio;

    //Otros repositorios
    // Servicios adicionales
    public IngresoConsultarServicio(
            IngresoRepositorio ingresoRepositorio,
            AccesoRepositorio accesoRepositorio,
            BusquedaServicio<Ingreso, Integer> BusquedaServicioIngreso
    ) {
        super(BusquedaServicioIngreso);
        this.ingresoRepositorio = ingresoRepositorio;
    }

    @Override
    protected JpaRepository<Ingreso, Integer> getRepositorio() {
        return ingresoRepositorio;
    }

    public IngresoDtoResumen obtenerResumenIngreso(Integer idUsuario) {
        Long cantidad = ingresoRepositorio.countByIdUsuario_IdUsuario(idUsuario);

        List<Ingreso> ingresos = ingresoRepositorio
                .findTop2ByIdUsuario_IdUsuarioOrderByFechaIngresoDesc(idUsuario);

        boolean esPrimerIngreso = cantidad <= 1;
        LocalDateTime penultimoIngreso = cantidad >= 2 && ingresos.size() > 1
                ? ingresos.get(1).getFechaIngreso()
                : null;

        return new IngresoDtoResumen(cantidad, penultimoIngreso, esPrimerIngreso);
    }

}
