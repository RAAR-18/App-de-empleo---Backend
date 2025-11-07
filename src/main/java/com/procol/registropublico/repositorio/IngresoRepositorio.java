package com.procol.registropublico.repositorio;

import com.procol.registropublico.entidad.Ingreso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("registropublico_IngresoRepositorio")
public interface IngresoRepositorio extends JpaRepository<Ingreso, Integer> {

}
