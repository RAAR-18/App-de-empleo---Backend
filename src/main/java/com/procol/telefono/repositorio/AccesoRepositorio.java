package com.procol.telefono.repositorio;

import com.procol.telefono.entidad.Acceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("telefono_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    Optional<Acceso> findByTelefonoAcceso(String correoAcceso);
}
