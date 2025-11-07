package com.procol.usuario.servicio;

import com.procol.usuario.dto.RolDTO;
import com.procol.usuario.entidad.Rol;
import com.procol.usuario.repositorio.RolRepositorio;
import com.procol.usuario.utilidad.mapeador.RolMapeador;

import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.excepcion.ExcepcionValidacion;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.auditoria.constante.TipoCambio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RolEstadoServicio extends OperacionCrudImple<Rol, Integer> {

    // Repositorio obligatorio
    private final RolRepositorio rolRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final RolMapeador rolMapeador;

    public RolEstadoServicio(
            RolRepositorio rolRepositorio,
            RolMapeador rolMapeador,
            AuditoriaServicio auditoriaServicio,
            BusquedaServicio<Rol, Integer> BusquedaServicioRol) {
        super(BusquedaServicioRol);
        this.rolRepositorio = rolRepositorio;
        this.rolMapeador = rolMapeador;
        this.auditoriaServicio = auditoriaServicio;
    }

    @Override
    protected JpaRepository<Rol, Integer> getRepositorio() {
        return rolRepositorio;
    }

    @Transactional
    public RolDTO cambiarEstadoRol(Integer idEjecutor, Integer idRol, Short nuevoEstado) {
        int actualizados = rolRepositorio.cambiarEstado(idRol, nuevoEstado);

        verificarActualizacion(actualizados, idRol);
        Rol rolActualizado = buscarPorId(idRol);
        registroAuditoria(idEjecutor, rolActualizado);

        return rolMapeador.desdeEntidad(rolActualizado);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void verificarActualizacion(Integer actualizados, Integer idRol) {
        if (actualizados == 0) {
            throw new ExcepcionValidacion("Error actualizando estado del rol: " + idRol);
        }
    }

    private void registroAuditoria(Integer idEjecutor, Rol obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdRol(),
                TipoCambio.ACTUALIZAR,
                obj.toString()
        );
    }
    // *************************************************************************
}
