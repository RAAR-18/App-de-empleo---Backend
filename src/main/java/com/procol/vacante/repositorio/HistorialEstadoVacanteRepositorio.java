package com.procol.vacante.repositorio;

import com.procol.vacante.entidad.HistorialEstadoVacante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacante_HistorialEstadoVacanteRepositorio")
public interface HistorialEstadoVacanteRepositorio extends JpaRepository<HistorialEstadoVacante, Integer> {
}
