package com.procol.empresa.servicio.busqueda;

import org.springframework.stereotype.Service;
import com.procol.empresa.repositorio.EmpresaRepositorio;

@Service
public class TipoEmpresaTieneEmpresaServicio {

    private final EmpresaRepositorio empresaRepositorio;

    public TipoEmpresaTieneEmpresaServicio(EmpresaRepositorio repo) {
        this.empresaRepositorio = repo;
    }

    public boolean verificar(Integer idTipoEmpresa) {
        return empresaRepositorio.existsByIdTipoEmpresa_IdTipoEmpresa(idTipoEmpresa);
    }
}
