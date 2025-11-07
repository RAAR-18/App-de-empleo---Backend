package com.procol.empresa.repositorio;

import com.procol.empresa.entidad.Empresa;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("empresa_EmpresaRepositorio")
public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {

    /**
     * Verifica si existe al menos una instancia de {@link Empresa} asociada al
     * tipo de empresa identificado por el valor {@code idTipoEmpresa}.
     *
     * @param idTipoEmpresa identificador único de {@link TipoEmpresa} que se
     * desea verificar en la entidad {@link Empresa}
     * @return {@code true} si se encuentra al menos una empresa vinculada al
     * tipo indicado, {@code false} si no existe ninguna asociación registrada
     */
    boolean existsByIdTipoEmpresa_IdTipoEmpresa(Integer idTipoEmpresa);

}
