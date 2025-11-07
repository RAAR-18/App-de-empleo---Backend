package com.procol.registropublico.repositorio;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.procol.registropublico.entidad.PinTemporal;

@Repository("registropublico_PinTemporalRepositorio")
public interface PinTemporalRepositorio extends JpaRepository<PinTemporal, Long> {

}
