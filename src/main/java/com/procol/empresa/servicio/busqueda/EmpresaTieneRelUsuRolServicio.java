package com.procol.empresa.servicio.busqueda;

import org.springframework.stereotype.Service;
import com.procol.empresa.repositorio.RelUsuarioEmpresaRepositorio;

@Service
public class EmpresaTieneRelUsuRolServicio {

    private final RelUsuarioEmpresaRepositorio relUsuEmpreRepositorio;

    public EmpresaTieneRelUsuRolServicio(RelUsuarioEmpresaRepositorio repo) {
        this.relUsuEmpreRepositorio = repo;
    }

    public boolean verificar(Integer idEmpresa) {
        return relUsuEmpreRepositorio.existsByRelUsuarioEmpresaPK_IdEmpresa(idEmpresa);
    }
}
