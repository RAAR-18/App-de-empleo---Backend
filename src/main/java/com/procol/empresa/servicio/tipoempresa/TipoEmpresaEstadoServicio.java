package com.procol.empresa.servicio.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoEmpresaEstadoServicio extends OperacionCrudImple<TipoEmpresa, Integer> {

    // Repositorio obligatorio
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios adicionales
    private final TipoEmpresaMapeador tipoEmpresaMapeador;

    public TipoEmpresaEstadoServicio(
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            TipoEmpresaMapeador tipoEmpresaMapeador,
            BusquedaServicio<TipoEmpresa, Integer> BusquedaServicioTipoEmpresa
    ) {
        super(BusquedaServicioTipoEmpresa);
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.tipoEmpresaMapeador = tipoEmpresaMapeador;
    }

    @Override
    protected JpaRepository<TipoEmpresa, Integer> getRepositorio() {
        return tipoEmpresaRepositorio;
    }

    @Transactional
    public TipoEmpresaDTO cambiarEstadoTipoEmpresa(Integer idEjecutor, Integer idTipoEmpresa, Short nuevoEstado) {
        int actualizados = tipoEmpresaRepositorio.cambiarEstado(idTipoEmpresa, nuevoEstado);
        if (actualizados == 0) {
            throw new ExcepcionValidacion("No se actualizó el estado de TipoEmpresa: " + idTipoEmpresa);
        }

        TipoEmpresa objActualizado = buscarPorId(idTipoEmpresa);
        registroAuditoria(idEjecutor, objActualizado);
        return tipoEmpresaMapeador.desdeEntidad(objActualizado);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void registroAuditoria(Integer idEjecutor, TipoEmpresa obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdTipoEmpresa(),
                TipoCambio.ACTUALIZAR,
                obj.toString()
        );
    }
    // *************************************************************************

}
