package com.procol.correo.repositorio;

import com.procol.correo.entidad.Acceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("correo_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    Optional<Acceso> findByTelefonoAcceso(String telefonoAcceso);
}
