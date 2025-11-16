package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Talento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("perfil_TalentoRepositorio")
public interface TalentoRepositorio extends JpaRepository<Talento, Integer> {
}