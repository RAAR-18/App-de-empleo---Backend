package com.procol.vacante.repositorio;

import com.procol.vacante.entidad.Vacante;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("vacante_VacanteRepositorio")
public interface VacanteRepositorio extends JpaRepository<Vacante, Integer> {

}
