package com.procol.vacante.repositorio;

import com.procol.vacante.entidad.RelVacantePalabraClave;
import com.procol.vacante.entidad.pk.RelVacantePalabraClavePK;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacante_RelVacantePalabraClaveRepositorio")
public interface RelVacantePalabraClaveRepositorio extends JpaRepository<RelVacantePalabraClave, RelVacantePalabraClavePK> {
}