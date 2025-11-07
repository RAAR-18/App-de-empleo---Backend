package com.procol.seguridad.repositorio;

import com.procol.seguridad.entidad.Ingreso;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("seguridad_IngresoRepositorio")
public interface IngresoRepositorio extends JpaRepository<Ingreso, Integer> {

}
