package com.procol.empresa.servicio.tipoempresa;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;
import com.procol.empresa.servicio.busqueda.TipoEmpresaTieneEmpresaServicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class TipoEmpresaEliminarServicio extends OperacionCrudImple<TipoEmpresa, Integer> {

    // Repositorio obligatorio
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios adicionales
    private final TipoEmpresaMapeador tipoEmpresaMapeador;
    private final TipoEmpresaTieneEmpresaServicio tipoEmpresaTieneEmpresaServicio;
    
    public TipoEmpresaEliminarServicio(
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            TipoEmpresaMapeador tipoEmpresaMapeador,
            TipoEmpresaTieneEmpresaServicio tipoEmpresaTieneEmpresaServicio,
            BusquedaServicio<TipoEmpresa, Integer> busquedaServicioTipoEmpresa
    ) {
        super(busquedaServicioTipoEmpresa);
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.tipoEmpresaTieneEmpresaServicio = tipoEmpresaTieneEmpresaServicio;
        this.tipoEmpresaMapeador = tipoEmpresaMapeador;
    }
    
    @Override
    protected JpaRepository<TipoEmpresa, Integer> getRepositorio() {
        return tipoEmpresaRepositorio;
    }
    
    public TipoEmpresaDTO eliminarTipoEmpresa(Integer idEjecutor, Integer idTipoEmpresa) {
        TipoEmpresa objTipoEmpresa = buscarPorId(idTipoEmpresa);
        
        existeTipoEmpresa(objTipoEmpresa, idTipoEmpresa);
        noTieneEmpresasAsociadas(idTipoEmpresa, objTipoEmpresa.getNombreTipoEmpresa());
        eliminarRegistro(idTipoEmpresa, objTipoEmpresa.getNombreTipoEmpresa());
        
        registroAuditoria(idEjecutor, objTipoEmpresa);
        return tipoEmpresaMapeador.desdeEntidad(objTipoEmpresa);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void existeTipoEmpresa(TipoEmpresa objTipoEmpresa, Integer idTipoEmpresa) {
        if (objTipoEmpresa == null) {
            throw new ExcepcionNegocio("No existe TipoEmpresa: " + idTipoEmpresa);
        }
    }
    
    private void noTieneEmpresasAsociadas(Integer idTipoEmpresa, String nombreTipoEmpresa) {
        if (tipoEmpresaTieneEmpresaServicio.verificar(idTipoEmpresa)) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR + nombreTipoEmpresa
                    + ". Tiene empresas asociadas";
            throw new ExcepcionNegocio(msg);
        }
    }
    
    private void eliminarRegistro(Integer idTipoEmpresa, String nombreTipoEmpresa) {
        boolean resultado = eliminar(idTipoEmpresa);
        if (!resultado) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR + nombreTipoEmpresa;
            throw new ExcepcionNegocio(msg);
        }
    }
    
    private void registroAuditoria(Integer idEjecutor, TipoEmpresa objTipoEmpresa) {
        auditoriaServicio.registrar(
                idEjecutor,
                objTipoEmpresa.getClass().getName(),
                objTipoEmpresa.getIdTipoEmpresa(),
                TipoCambio.ELIMINAR,
                objTipoEmpresa.toString()
        );
    }
    // *************************************************************************
}
