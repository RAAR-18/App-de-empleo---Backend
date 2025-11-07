package com.procol.registropublico.repositorio;

import java.util.Optional;

import com.procol.registropublico.entidad.Acceso;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("registropublico_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepository<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correoAcceso);

    Optional<Acceso> findByTelefonoAcceso(String telefonoAcceso);
}
