package com.procol.usuario.repositorio;

import com.procol.usuario.entidad.Acceso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository("usuario_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    boolean existsByCorreoAcceso(String correoAcceso);
}
