package com.procol.empresa.servicio.empresa;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.dto.EmpresaDTOCrear;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.repositorio.EmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.EmpresaMapeador;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaCrearServicio extends OperacionCrudImple<Empresa, Integer> {

    // Repositorio obligatorio
    private final EmpresaRepositorio repositorioEmpresa;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final EmpresaMapeador empresaMapeador;
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;
    private final BusquedaServicio<TipoEmpresa, Integer> busquedaServicioTipoEmpresa;

    public EmpresaCrearServicio(
            EmpresaRepositorio repositorioEmpresa,
            AuditoriaServicio auditoriaServicio,
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            EmpresaMapeador empresaMapeador,
            BusquedaServicio<Empresa, Integer> servicioBuscarEmpresa,
            BusquedaServicio<TipoEmpresa, Integer> busquedaServicioTipoEmpresa
    ) {
        super(servicioBuscarEmpresa);
        this.repositorioEmpresa = repositorioEmpresa;
        this.auditoriaServicio = auditoriaServicio;
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.empresaMapeador = empresaMapeador;
        this.busquedaServicioTipoEmpresa = busquedaServicioTipoEmpresa;
    }

    @Override
    protected JpaRepository<Empresa, Integer> getRepositorio() {
        return repositorioEmpresa;
    }

    @Transactional
    public EmpresaDTO crearEmpresa(Integer idEjecutor, EmpresaDTOCrear dto) {
        TipoEmpresa objTipoEmpresa = busquedaServicioTipoEmpresa.PorId(tipoEmpresaRepositorio, dto.getIdTipoEmpresa());

        Empresa empresa = new Empresa();
        empresa.setNombreEmpresa(dto.getNombreEmpresa());
        empresa.setIdTipoEmpresa(objTipoEmpresa);

        Empresa objNuevaEmpresa = agregar(empresa);
        registroAuditoria(idEjecutor, objNuevaEmpresa);
        return empresaMapeador.desdeEntidad(objNuevaEmpresa);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void registroAuditoria(Integer idEjecutor, Empresa obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdEmpresa(),
                TipoCambio.CREAR,
                obj.toString()
        );
    }
    // *************************************************************************

}
