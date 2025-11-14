package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("perfil_UbicacionRepositorio")
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Integer> {
}
