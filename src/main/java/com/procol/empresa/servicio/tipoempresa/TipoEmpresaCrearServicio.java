package com.procol.empresa.servicio.tipoempresa;

import java.util.Optional;

import com.procol.empresa.dto.TipoEmpresaDTO;
import com.procol.empresa.entidad.TipoEmpresa;
import com.procol.empresa.dto.TipoEmpresaDTOCrear;
import com.procol.empresa.repositorio.TipoEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.TipoEmpresaMapeador;
import com.procol.empresa.utilidad.validacion.TipoEmpresaValidar;
import com.procol.empresa.servicio.busqueda.TipoEmpresaPorNombreServicio;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.utilidad.validacion.Verificar;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class TipoEmpresaCrearServicio extends OperacionCrudImple<TipoEmpresa, Integer> {

    // Repositorio obligatorio
    private final TipoEmpresaRepositorio tipoEmpresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    //Servicios adicionales
    private final TipoEmpresaMapeador tipoEmpresaMapeador;
    private final TipoEmpresaPorNombreServicio tipoEmpresaPorNombreServicio;

    public TipoEmpresaCrearServicio(
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

    public TipoEmpresaDTO crearTipoEmpresa(TipoEmpresaDTOCrear dto, int idUsuario) {
        String nombreEmpresa = dto.getNombreTipoEmpresa().trim();
        String nombreMinusculas = nombreEmpresa.toLowerCase();
        Short estado = TipoEmpresaValidar.asignarEstadoInicial(dto.getEstadoTipoEmpresa());

        TipoEmpresaValidar.validarNombre(nombreEmpresa);
        Verificar.estadoRegistro(estado);

        verificarNombreTipoEmpresa(nombreEmpresa, nombreMinusculas);

        TipoEmpresa objNueva = new TipoEmpresa();
        objNueva.setNombreTipoEmpresa(nombreEmpresa);
        objNueva.setEstadoTipoEmpresa(estado);

        TipoEmpresa objRegistrada = agregar(objNueva);
        registroAuditoria(idUsuario, objRegistrada);

        return tipoEmpresaMapeador.desdeEntidad(objRegistrada);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void verificarNombreTipoEmpresa(String nombreEmpresa, String nombreMinusculas) {
        Optional<TipoEmpresa> existente = tipoEmpresaPorNombreServicio.buscar(nombreMinusculas);
        if (existente.isPresent()) {
            throw new ExcepcionNegocio("TipoEmpresa ya existe: " + nombreEmpresa);
        }
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
