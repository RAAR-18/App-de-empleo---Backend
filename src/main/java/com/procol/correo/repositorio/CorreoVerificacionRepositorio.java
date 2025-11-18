package com.procol.correo.repositorio;

import com.procol.correo.entidad.CorreoVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorreoVerificacionRepositorio extends JpaRepository<CorreoVerificacion, Integer> {

    Optional<CorreoVerificacion> findByIdCorreo(String idCorreo);
}
