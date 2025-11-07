package com.procol.vacante.repositorio;

import com.procol.vacante.entidad.EstadoVacante;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("vacante_EstadoVacanteRepositorio")
public interface EstadoVacanteRepositorio extends JpaRepository<EstadoVacante, Integer> {

}
