package com.procol.comun.servicio;

import com.procol.comun.entidad.Ubicacion;
import com.procol.comun.dto.UbicacionDTOAutocompletado;
import com.procol.comun.repositorio.UbicacionRepositorio;
import com.procol.comun.utilidad.mapeador.UbicacionBusquedaMapeadorNativo;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import jakarta.persistence.Tuple;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class UbicacionBuscarServicio extends OperacionCrudImple<Ubicacion, Integer> {

    // Repositorio obligatorio
    private final UbicacionRepositorio ubicacionRepositorio;

    //Otros repositorios
    // Servicios adicionales
    private final UbicacionBusquedaMapeadorNativo ubicacionBusquedaMapeadorNativo;

    public UbicacionBuscarServicio(
            UbicacionRepositorio ubicacionRepositorio,
            UbicacionBusquedaMapeadorNativo ubicacionBusquedaMapeadorNativo,
            BusquedaServicio<Ubicacion, Integer> BusquedaServicioUbicacion
    ) {
        super(BusquedaServicioUbicacion);
        this.ubicacionRepositorio = ubicacionRepositorio;
        this.ubicacionBusquedaMapeadorNativo = ubicacionBusquedaMapeadorNativo;
    }

    @Override
    protected JpaRepository<Ubicacion, Integer> getRepositorio() {
        return ubicacionRepositorio;
    }

    public List<UbicacionDTOAutocompletado> obtenerUbicacionesPorTexto(String textoUbicacion) {
        List<Tuple> resultados;
        resultados = ubicacionRepositorio.buscarUbicacionesConPadre(textoUbicacion.trim());

        List<UbicacionDTOAutocompletado> registrosDTO = resultados.stream()
                .map(ubicacionBusquedaMapeadorNativo::mapearDesdeTupla).toList();
        return registrosDTO;
    }

}
