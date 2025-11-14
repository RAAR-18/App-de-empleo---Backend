package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("perfil_AreaRepositorio")
public interface AreaRepositorio extends JpaRepository<Area, Integer> {
}
