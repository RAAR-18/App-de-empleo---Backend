package com.procol.empresa.repositorio;

import java.util.Optional;

import com.procol.empresa.entidad.TipoEmpresa;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository("empresa_TipoEmpresaRepositorio")
public interface TipoEmpresaRepositorio extends JpaRepository<TipoEmpresa, Integer> {

    /**
     * Devuelve una instancia de {@link TipoEmpresa} cuyo nombre coincide con el
     * {@code nombreTipoEmpresa}, ignorando mayúsculas y minúsculas.
     *
     * @param nombreTipoEmpresa nombre del {@link TipoEmpresa} a buscar sin
     * sensibilidad a mayúsculas
     * @return un {@link Optional} con la instancia encontrada, o vacío si no
     * existe ninguna coincidencia
     */
    Optional<TipoEmpresa> findByNombreTipoEmpresaIgnoreCase(String nombreTipoEmpresa);

    /**
     * Actualiza el estado de una instancia de {@link TipoEmpresa} identificada
     * por su identificador único {@code idTipoEmpresa}.
     *
     * <p>
     * Ejecuta una sentencia JPQL de actualización directa sobre la base de
     * datos. Requiere estar anotado con {@code @Modifying} para indicar que se
     * trata de una operación que modifica datos, y con {@code @Transactional}
     * para asegurar que se ejecute dentro de una transacción activa.
     *
     * @param idTipoEmpresa identificador único de la instancia de
     * {@link TipoEmpresa} a modificar
     * @param codEstado nuevo valor de estado que se desea asignar
     * @return número de registros afectados por la actualización; debería ser 1
     * si la operación es exitosa
     */
    @Modifying
    @Transactional
    @Query("update empresa_TipoEmpresa te set te.estadoTipoEmpresa = :nuevoEstado "
            + "where te.idTipoEmpresa = :codTE")
    int cambiarEstado(@Param("codTE") Integer idTipoEmpresa, @Param("nuevoEstado") Short codEstado);

}
