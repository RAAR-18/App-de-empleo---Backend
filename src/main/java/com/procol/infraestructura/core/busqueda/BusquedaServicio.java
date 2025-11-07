package com.procol.infraestructura.core.busqueda;

import org.springframework.stereotype.Service;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Servicio auxiliar para realizar operaciones de búsqueda segura sobre
 * entidades persistentes.
 * <p>
 * Esta clase encapsula la lógica de recuperación por identificador único
 * ({@code ID}), permitiendo reutilizar validación semántica en múltiples
 * servicios sin repetir condiciones de existencia ni manejo de errores.
 *
 * <p>
 * Está diseñada para integrarse con cualquier repositorio que extienda
 * {@link JpaRepository}, manteniendo independencia de tipo y contexto de
 * persistencia.
 *
 * @param <T> el tipo de entidad persistente sobre la que se realiza la
 * búsqueda.
 * @param <ID> el tipo del identificador único de la entidad, típicamente
 * {@code Long}, {@code UUID}, etc.
 */
@Service
public class BusquedaServicio<T, ID> {

    /**
     * Busca una entidad por su identificador, lanzando una excepción con un
     * mensaje de error genérico si no se encuentra.
     *
     * @param repositorio una instancia de {@link JpaRepository} para consultar
     * la entidad.
     * @param id el identificador único ({@code ID}) de la entidad buscada.
     * @return instancia recuperada de {@link T} correspondiente al {@code id}
     * proporcionado
     * @throws ExcepcionNegocio si no se encuentra ningún registro asociado al
     * identificador
     * @throws ExcepcionNegocio si la entidad no existe en el repositorio.
     */
    public T PorId(JpaRepository<T, ID> repositorio, ID id) {
        String mensaje = "No existe el registro con ID: " + id;
        return PorId(repositorio, id, mensaje);
    }

    /**
     * Busca una entidad por su identificador, lanzando una excepción
     * personalizada si no se encuentra.
     *
     * @param repositorio instancia de {@link JpaRepository} para acceder a los
     * datos.
     * @param id el identificador único ({@code ID}) de la entidad buscada.
     * @param mensajeError mensaje personalizado a incluir en la
     * {@link ExcepcionNegocio} si no se encuentra la entidad.
     * @return la instancia recuperada de {@code T}; garantiza existencia o
     * lanza excepción.
     * @throws ExcepcionNegocio si la entidad no se encuentra en el repositorio.
     */
    public T PorId(JpaRepository<T, ID> repositorio, ID id, String mensajeError) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ExcepcionNegocio(mensajeError));
    }
}
