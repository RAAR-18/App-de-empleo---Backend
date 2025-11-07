package com.procol.empresa.servicio.tipoempresa;

import java.util.Optional;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.dto.TipoEmpresaDTOActualizar;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;
import com.procol.empresa.utilidad.validacion.TipoEmpresaValidar;
import com.procol.empresa.servicio.busqueda.TipoEmpresaPorNombreServicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.utilidad.validacion.Verificar;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TipoEmpresaActualizarServicio extends OperacionCrudImple<TipoEmpresa, Integer> {

    // Repositorio obligatorio
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    //Servicios adicionales
    private final TipoEmpresaMapeador tipoEmpresaMapeador;
    private final TipoEmpresaPorNombreServicio tipoEmpresaPorNombreServicio;

    public TipoEmpresaActualizarServicio(
            TipoEmpresaRepositorio tipoEmpresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            TipoEmpresaMapeador tipoEmpresaMapeador,
            TipoEmpresaPorNombreServicio tipoEmpresaPorNombreServicio,
            BusquedaServicio<TipoEmpresa, Integer> BusquedaServicioTipoEmpresa
    ) {
        super(BusquedaServicioTipoEmpresa);
        this.tipoEmpresaRepositorio = tipoEmpresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.tipoEmpresaMapeador = tipoEmpresaMapeador;
        this.tipoEmpresaPorNombreServicio = tipoEmpresaPorNombreServicio;
    }

    @Override
    protected JpaRepository<TipoEmpresa, Integer> getRepositorio() {
        return tipoEmpresaRepositorio;
    }

    @Transactional
    public TipoEmpresaDTO actualizarTipoEmpresa(Integer idEjecutor, TipoEmpresaDTOActualizar dto) {
        String nombreEmpresa = dto.getNombreTipoEmpresa().trim();

        TipoEmpresaValidar.validarNombre(nombreEmpresa);
        TipoEmpresa objTipoEmpresa = buscarPorId(dto.getIdTipoEmpresa());

        nombreNoRepetido(dto.getIdTipoEmpresa(), dto.getNombreTipoEmpresa().trim());

        objTipoEmpresa.setNombreTipoEmpresa(nombreEmpresa);
        if (dto.getEstadoTipoEmpresa() != null) {
            Verificar.estadoRegistro(dto.getEstadoTipoEmpresa());
            objTipoEmpresa.setEstadoTipoEmpresa(dto.getEstadoTipoEmpresa());
        }

        TipoEmpresa objActualizado = actualizarRegistro(objTipoEmpresa);
        registroAuditoria(idEjecutor, objActualizado);
        return tipoEmpresaMapeador.desdeEntidad(objActualizado);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void nombreNoRepetido(Integer idTipoEmpresa, String nombreOriginalTipoEmpresa) {
        String nombreMinusculas = nombreOriginalTipoEmpresa.toLowerCase();

        Optional<TipoEmpresa> nombreIgual = tipoEmpresaPorNombreServicio.buscar(nombreMinusculas);
        if (nombreIgual.isPresent() && !nombreIgual.get().getIdTipoEmpresa().equals(idTipoEmpresa)) {
            throw new ExcepcionNegocio("Ya existe el TipoEmpresa: " + nombreOriginalTipoEmpresa);
        }
    }

    private TipoEmpresa actualizarRegistro(TipoEmpresa obj) {
        TipoEmpresa objActualizado = actualizar(obj);
        if (objActualizado == null) {
            String msg = ConstMensajeRespuesta.REGISTRO_ACTUALIZADO_ERROR + obj.getIdTipoEmpresa();
            throw new ExcepcionNegocio(msg);
        }
        return objActualizado;
    }

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
