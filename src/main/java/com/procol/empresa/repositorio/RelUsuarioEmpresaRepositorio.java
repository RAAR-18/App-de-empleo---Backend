package com.procol.empresa.repositorio;

import java.util.Optional;

import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.entidad.RelUsuarioEmpresa;
import com.procol.empresa.entidad.pk.RelUsuarioEmpresaPK;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("empresa_RelUsuarioEmpresaRepositorio")
public interface RelUsuarioEmpresaRepositorio extends JpaRepository<RelUsuarioEmpresa, RelUsuarioEmpresaPK> {

    /**
     * Verifica si la instancia de {@link Empresa} suministrada tiene al menos
     * un registro asociado en la entidad {@link RelUsuarioEmpresa}.
     *
     * @param empresa instancia de {@link Empresa} con identificador válido
     * @return {@code true} si la empresa tiene al menos una relación registrada
     * con un usuario, {@code false} si no existe ninguna asociación
     */
    boolean existsByEmpresa(Empresa empresa);

    /**
     * Verifica si la empresa identificada por el valor {@code idEmpresa} tiene
     * al menos un registro asociado en la entidad {@link RelUsuarioEmpresa}.
     *
     * @param idEmpresa identificador único de {@link Empresa} que se desea
     * verificar
     * @return {@code true} si la empresa tiene al menos una relación registrada
     * con un usuario, {@code false} si no existe ninguna asociación
     */
    boolean existsByRelUsuarioEmpresaPK_IdEmpresa(Integer idEmpresa);

    Optional<RelUsuarioEmpresa> findByRelUsuarioEmpresaPK_IdUsuarioAndRelUsuarioEmpresaPK_IdEmpresa(
            Integer idUsuario, Integer idEmpresa
    );

    boolean existsByRelUsuarioEmpresaPK_IdUsuarioAndRelUsuarioEmpresaPK_IdEmpresa(
            Integer idUsuario, Integer idEmpresa
    );

    /**
     * Busca la primera relación de un usuario con una empresa.
     * Útil para obtener la empresa principal del usuario al hacer login.
     *
     * @param idUsuario identificador único del usuario
     * @return Optional con la relación si existe, vacío si el usuario no tiene empresa
     */
    Optional<RelUsuarioEmpresa> findFirstByRelUsuarioEmpresaPK_IdUsuario(Integer idUsuario);

}
