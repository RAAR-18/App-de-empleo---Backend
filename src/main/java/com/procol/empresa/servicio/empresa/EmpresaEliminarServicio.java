package com.procol.empresa.servicio.empresa;

import com.procol.empresa.dto.EmpresaDTO;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.repositorio.EmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.EmpresaMapeador;
import com.procol.empresa.servicio.busqueda.EmpresaTieneRelUsuRolServicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class EmpresaEliminarServicio extends OperacionCrudImple<Empresa, Integer> {

    // Repositorio obligatorio
    private final EmpresaRepositorio empresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final EmpresaMapeador empresaMapeador;
    private final EmpresaTieneRelUsuRolServicio empresaTieneRelUsuRolServicio;

    public EmpresaEliminarServicio(
            EmpresaRepositorio empresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            EmpresaMapeador empresaMapeador,
            BusquedaServicio<Empresa, Integer> busquedaServicioEmpresa,
            EmpresaTieneRelUsuRolServicio empresaTieneRelUsuRolServicio
    ) {
        super(busquedaServicioEmpresa);
        this.empresaRepositorio = empresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.empresaMapeador = empresaMapeador;
        this.empresaTieneRelUsuRolServicio = empresaTieneRelUsuRolServicio;
    }

    @Override
    protected JpaRepository<Empresa, Integer> getRepositorio() {
        return empresaRepositorio;
    }

    public EmpresaDTO eliminarEmpresa(Integer idEjecutor, Integer idEmpresa) {
        Empresa objEmpresa = buscarPorId(idEmpresa);

        noTieneUsuariosAsociados(idEmpresa, objEmpresa.getNombreEmpresa());
        eliminarRegistro(idEmpresa, objEmpresa.getNombreEmpresa());

        registroAuditoria(idEjecutor, objEmpresa);
        return empresaMapeador.desdeEntidad(objEmpresa);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void noTieneUsuariosAsociados(Integer idEmpresa, String nombreEmpresa) {
        if (empresaTieneRelUsuRolServicio.verificar(idEmpresa)) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR
                    + nombreEmpresa + ". Tiene usuarios asociados";
            throw new ExcepcionNegocio(msg);
        }
    }

    private void eliminarRegistro(Integer idEmpresa, String nombreTipoEmpresa) {
        boolean resultado = eliminar(idEmpresa);
        if (!resultado) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR + nombreTipoEmpresa;
            throw new ExcepcionNegocio(msg);
        }
    }

    private void registroAuditoria(Integer idEjecutor, Empresa obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdEmpresa(),
                TipoCambio.ELIMINAR,
                obj.toString()
        );
    }
    // *************************************************************************

}
