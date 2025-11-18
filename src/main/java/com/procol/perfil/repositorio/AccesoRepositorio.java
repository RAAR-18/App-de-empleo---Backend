package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Acceso;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("perfil_AccesoRepositorio")
public interface AccesoRepositorio extends JpaRepositoryImplementation<Acceso, Integer> {

    Optional<Acceso> findByCorreoAcceso(String correo);
}
