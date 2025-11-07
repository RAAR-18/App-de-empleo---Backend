package com.procol.usuario.repositorio;

import com.procol.usuario.entidad.Ubicacion;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("usuario_UbicacionRepositorio")
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Integer> {

}
