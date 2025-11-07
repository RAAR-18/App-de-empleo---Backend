package com.procol.usuario.repositorio;

import com.procol.usuario.entidad.Ingreso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("usuario_IngresoRepositorio")
public interface IngresoRepositorio extends JpaRepository<Ingreso, Integer> {

}
