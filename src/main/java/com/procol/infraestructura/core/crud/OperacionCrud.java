package com.procol.infraestructura.core.crud;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface OperacionCrud<T, ID> {

    int cantidadRegistros();

    List<T> consultarTodos(Sort sort);

    T agregar(T miEntidad);

    List<T> agregarTodos(List<T> entidades);

    T buscarPorId(ID id);

    T buscarPorId(ID id, String mensaje);

    boolean eliminar(ID codigo);

    T actualizar(T miEntidad);
}
