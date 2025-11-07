package com.procol.empresa.servicio.empresa;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.dto.EmpresaDTOActualizar;
import com.procol.empresa.repositorio.EmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.EmpresaMapeador;
import com.procol.empresa.utilidad.validacion.EmpresaValidar;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpresaActualizarServicio extends OperacionCrudImple<Empresa, Integer> {

    // Repositorio obligatorio
    private final EmpresaRepositorio empresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final EmpresaMapeador empresaMapeador;
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;
    private final BusquedaServicio<TipoEmpresa, Integer> busquedaServicioTipoEmpresa;

    public EmpresaActualizarServicio(
            EmpresaRepositorio empresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            EmpresaMapeador empresaMapeador,
            BusquedaServicio<Empresa, Integer> BusquedaServicioEmpresa,
            BusquedaServicio<TipoEmpresa, Integer> busquedaServicioTipoEmpresa
    ) {
        super(BusquedaServicioEmpresa);
        this.empresaRepositorio = empresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.empresaMapeador = empresaMapeador;
        this.busquedaServicioTipoEmpresa = busquedaServicioTipoEmpresa;
    }

    @Override
    protected JpaRepository<Empresa, Integer> getRepositorio() {
        return empresaRepositorio;
    }

    @Transactional
    public EmpresaDTO actualizarEmpresa(Integer idEjecutor, EmpresaDTOActualizar dto) {
        String nombreEmpresa = dto.getNombreEmpresa().trim();
        EmpresaValidar.validarNombre(nombreEmpresa);

        Empresa objEmpresa = buscarPorId(dto.getIdEmpresa());
        TipoEmpresa objTipoEmpresa = busquedaServicioTipoEmpresa.PorId(
                tipoEmpresaRepositorio, dto.getIdTipoEmpresa()
        );

        objEmpresa.setNombreEmpresa(nombreEmpresa);
        objEmpresa.setIdTipoEmpresa(objTipoEmpresa);

        Empresa objActualizado = actualizarRegistro(objEmpresa);
        registroAuditoria(idEjecutor, objActualizado);
        return empresaMapeador.desdeEntidad(objActualizado);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private Empresa actualizarRegistro(Empresa obj) {
        Empresa objActualizado = actualizar(obj);
        if (objActualizado == null) {
            String msg = ConstMensajeRespuesta.REGISTRO_ACTUALIZADO_ERROR + obj.getIdEmpresa();
            throw new ExcepcionNegocio(msg);
        }
        return objActualizado;
    }

    private void registroAuditoria(Integer idEjecutor, Empresa obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdEmpresa(),
                TipoCambio.ACTUALIZAR,
                obj.toString()
        );
    }
    // *************************************************************************

}
