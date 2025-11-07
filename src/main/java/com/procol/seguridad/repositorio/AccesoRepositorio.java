package com.procol.seguridad.repositorio;

import com.procol.seguridad.entidad.Acceso;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository("seguridad_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    boolean existsByCorreoAcceso(String correoAcceso);
}
