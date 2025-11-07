package com.procol.empresa.servicio.empresa;

import com.procol.empresa.entidad.Usuario;
import com.procol.empresa.entidad.Empresa;
import com.procol.empresa.dto.UsuarioEmpresaDTO;
import com.procol.empresa.entidad.RelUsuarioEmpresa;
import com.procol.empresa.entidad.pk.RelUsuarioEmpresaPK;
import com.procol.empresa.repositorio.EmpresaRepositorio;
import com.procol.empresa.repositorio.UsuarioRepositorio;
import com.procol.empresa.repositorio.RelUsuarioEmpresaRepositorio;
import com.procol.empresa.utilidad.mapeador.RelUsuarioEmpresaMapeador;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RelacionUsuarioEmpresaServicio extends OperacionCrudImple<RelUsuarioEmpresa, RelUsuarioEmpresaPK> {

    // Repositorio obligatorio
    private final RelUsuarioEmpresaRepositorio relUsuarioEmpresaRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;

    private final EmpresaRepositorio empresaRepositorio;
    private final BusquedaServicio<Empresa, Integer> busquedaServicioEmpresa;

    private final RelUsuarioEmpresaMapeador relUsuarioEmpresaMapeador;

    public RelacionUsuarioEmpresaServicio(
            RelUsuarioEmpresaRepositorio relUsuarioEmpresaRepositorio,
            AuditoriaServicio auditoriaServicio,
            UsuarioRepositorio usuarioRepositorio,
            EmpresaRepositorio empresaRepositorio,
            RelUsuarioEmpresaMapeador relUsuarioEmpresaMapeador,
            BusquedaServicio<RelUsuarioEmpresa, RelUsuarioEmpresaPK> busquedaServicioUsuarioRol,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            BusquedaServicio<Empresa, Integer> busquedaServicioEmpresa
    ) {
        super(busquedaServicioUsuarioRol);
        this.relUsuarioEmpresaRepositorio = relUsuarioEmpresaRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.empresaRepositorio = empresaRepositorio;
        this.relUsuarioEmpresaMapeador = relUsuarioEmpresaMapeador;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.busquedaServicioEmpresa = busquedaServicioEmpresa;
    }

    @Override
    protected JpaRepository<RelUsuarioEmpresa, RelUsuarioEmpresaPK> getRepositorio() {
        return relUsuarioEmpresaRepositorio;
    }

    @Transactional
    public UsuarioEmpresaDTO asignarUsuarioAEmpresa(Integer idEjecutor, UsuarioEmpresaDTO dto) {
        Integer idUsuario = dto.getIdUsuario();
        Integer idEmpresa = dto.getIdEmpresa();

        verificaUsuarioNoAsociado(idUsuario, idEmpresa);
        Usuario objUsuario = busquedaServicioUsuario.PorId(usuarioRepositorio, idUsuario, "Usuario no encontrado");
        Empresa objEmpresa = busquedaServicioEmpresa.PorId(empresaRepositorio, idEmpresa, "Empresa no encontrada");

        RelUsuarioEmpresa relUsuarioEmpresa = relUsuarioEmpresaMapeador.desdeDto(dto, objUsuario, objEmpresa);

        RelUsuarioEmpresa objRegistrado = relUsuarioEmpresaRepositorio.save(relUsuarioEmpresa);

        registroAuditoria(idEjecutor, objRegistrado);
        return relUsuarioEmpresaMapeador.desdeEntidad(objRegistrado);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void verificaUsuarioNoAsociado(Integer idUsuario, Integer idEmpresa) {
        if (relUsuarioEmpresaRepositorio.existsByRelUsuarioEmpresaPK_IdUsuarioAndRelUsuarioEmpresaPK_IdEmpresa(idUsuario, idEmpresa)) {
            throw new ExcepcionNegocio("El usuario ya está asociado a la empresa");
        }
    }

    private void registroAuditoria(Integer idEjecutor, RelUsuarioEmpresa obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getRelUsuarioEmpresaPK().getIdEmpresa(),
                TipoCambio.ASIGNAR_USUARIO,
                obj.toString()
        );
    }
    // *************************************************************************

}
