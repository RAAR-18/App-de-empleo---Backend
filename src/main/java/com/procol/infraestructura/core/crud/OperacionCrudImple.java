package com.procol.infraestructura.core.crud;

import java.util.List;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public abstract class OperacionCrudImple<T, ID> implements OperacionCrud<T, ID> {

    @PersistenceContext
    protected EntityManager entityManager;

    private final BusquedaServicio<T, ID> servicioBuscar;

    protected OperacionCrudImple(BusquedaServicio<T, ID> servicioBuscar) {
        this.servicioBuscar = servicioBuscar;
    }

    protected abstract JpaRepository<T, ID> getRepositorio();

    @Override
    public int cantidadRegistros() {
        return (int) getRepositorio().count();
    }

    @Override
    public List<T> consultarTodos(Sort sort) {
        return getRepositorio().findAll(sort);
    }

    @Override
    public T agregar(T miEntidad) {
        return getRepositorio().save(miEntidad);
    }

    @Override
    public List<T> agregarTodos(List<T> entidades) {
        return getRepositorio().saveAll(entidades);
    }

    @Override
    public boolean eliminar(ID codigo) {
        if (getRepositorio().existsById(codigo)) {
            getRepositorio().deleteById(codigo);
            return true;
        }
        return false;
    }

    @Override
    public T actualizar(T miEntidad) {
        return getRepositorio().save(miEntidad);
    }

    @Override
    public T buscarPorId(ID id) {
        return servicioBuscar.PorId(getRepositorio(), id);
    }

    @Override
    public T buscarPorId(ID id, String mensajeErrorPersonalizado) {
        return servicioBuscar.PorId(getRepositorio(), id, mensajeErrorPersonalizado);
    }
}
