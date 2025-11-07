package com.procol.empresa.servicio.busqueda;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;

@Service
public class TipoEmpresaPorNombreServicio {

    private final TipoEmpresaRepositorio repositorio;

    public TipoEmpresaPorNombreServicio(TipoEmpresaRepositorio repo) {
        this.repositorio = repo;
    }

    public Optional<TipoEmpresa> buscar(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return null;
        }
        return repositorio.findByNombreTipoEmpresaIgnoreCase(nombre.trim());
    }
}
