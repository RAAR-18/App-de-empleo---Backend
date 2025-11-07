package com.procol.auditoria.repositorio;

import com.procol.auditoria.entidad.Auditoria;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("auditoria_AuditoriaRepositorio")
public interface AuditoriaRepositorio extends JpaRepository<Auditoria, Integer> {

}
