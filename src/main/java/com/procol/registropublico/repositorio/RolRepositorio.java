package com.procol.registropublico.repositorio;

import com.procol.registropublico.entidad.Rol;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("registropublico_RolRepositorio")
public interface RolRepositorio extends JpaRepository<Rol, Integer> {

}
