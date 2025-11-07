package com.procol.vacante.repositorio;

import com.procol.vacante.entidad.Anuncio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("vacante_AnuncioRepositorio")
public interface AnuncioRepositorio extends JpaRepository<Anuncio, Integer> {
}
