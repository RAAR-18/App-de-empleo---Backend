package com.procol.infraestructura.core.crud;

import com.procol.infraestructura.dto.PaginacionDto;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

/**
 * Clase abstracta que extiende {@link OperacionCrudImple} para incluir soporte
 * de paginación especializada.
 * <p>
 * Esta clase sirve como plantilla base para servicios que requieren
 * funcionalidad de consulta paginada y conteo flexible de registros.
 *
 * @param <T> Tipo de la entidad persistente.
 * @param <D> Tipo del DTO utilizado para representar la entidad paginada.
 * @param <ID> Tipo del identificador único de la entidad.
 *
 */
public abstract class OperacionCrudPaginadoImple<T, D, ID> extends OperacionCrudImple<T, ID> {

    /**
     * Constructor protegido para permitir inyección de dependencias desde
     * subclases concretas.
     *
     * @param servicioBuscar Componente de ayuda para búsqueda segura por ID.
     */
    protected OperacionCrudPaginadoImple(BusquedaServicio<T, ID> servicioBuscar) {
        super(servicioBuscar);
    }

    /**
     * Consulta paginada con soporte de ordenamiento y filtrado por campo/valor.
     *
     * @param campoBusqueda Campo sobre el cual aplicar el filtro.
     * @param valorBusqueda Valor del filtro de búsqueda.
     * @param campoOrden Campo por el cual ordenar los resultados.
     * @param orden Dirección del ordenamiento ("asc" o "desc").
     * @param numPagina Número de página solicitada (0-indexado).
     * @param tamanio Tamaño de cada página de resultados.
     * @return Objeto {@link PaginacionDto} con la página solicitada y metadatos
     * del resultado.
     */
    public abstract PaginacionDto<D> consultaPaginada(
            String campoBusqueda,
            String valorBusqueda,
            String campoOrden,
            String orden,
            int numPagina,
            int tamanio
    );

    /**
     * Cuenta la cantidad de registros que cumplen con un criterio de búsqueda
     * específico.
     *
     * @param valorBusqueda Valor con el que filtrar.
     * @param campoBD Campo de la base de datos que se usará para aplicar el
     * filtro.
     * @return Número total de registros que coinciden con el filtro.
     */
    protected abstract long contarRegistros(String valorBusqueda, String campoBD);

    /**
     * Cuenta todos los registros disponibles sin aplicar ningún filtro.
     *
     * @return Número total de registros existentes en la entidad.
     */
    protected abstract long contarRegistrosSinCondicion();
}
