package com.procol.comun.repositorio;


import com.procol.comun.entidad.Ingreso;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("comun_IngresoRepositorio")
public interface IngresoRepositorio extends JpaRepository<Ingreso, Integer> {

    long countByIdUsuario_IdUsuario(Integer idUsuario);

    List<Ingreso> findTop2ByIdUsuario_IdUsuarioOrderByFechaIngresoDesc(Integer idUsuario);

}
