package com.procol.usuario.servicio;

import java.util.UUID;
import java.util.Optional;

import com.procol.auditoria.constante.TipoCambio;
import com.procol.auditoria.servicio.AuditoriaServicio;

import com.procol.usuario.dto.AccesoDTO;
import com.procol.usuario.entidad.Acceso;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.dto.AccesoDTOCrear;
import com.procol.usuario.repositorio.AccesoRepositorio;
import com.procol.usuario.repositorio.UsuarioRepositorio;
import com.procol.usuario.utilidad.mapeador.AccesoMapeador;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AccesoRegistrarServicio extends OperacionCrudImple<Acceso, Integer> {

    // Repositorio obligatorio
    private final AccesoRepositorio accesoRepositorio;

    // Servicio de auditoria opcional
    private final AuditoriaServicio auditoriaServicio;

    // Servicios o repositorios adicionales
    private final UsuarioRepositorio usuarioRepositorio;
    private final AccesoMapeador accesoMapeador;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccesoRegistrarServicio(
            AccesoRepositorio accesoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            AuditoriaServicio auditoriaServicio,
            AccesoMapeador accesoMapeador,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            BusquedaServicio<Acceso, Integer> busquedaServicioAcceso
    ) {
        super(busquedaServicioAcceso);
        this.accesoRepositorio = accesoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.auditoriaServicio = auditoriaServicio;
        this.accesoMapeador = accesoMapeador;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected JpaRepository<Acceso, Integer> getRepositorio() {
        return accesoRepositorio;
    }

    @Transactional
    public AccesoDTO agregarCuentaAcceso(Integer idEjecutor, AccesoDTOCrear dto) {
        // *********************************************************************        
        // NO se usa BusquedaServicio<T, ID> porque es OneToOne y queda detached
        // *********************************************************************
        Usuario objUsuario = usuarioRepositorio.findById(dto.getIdUsuario())
                .orElseThrow(() -> new ExcepcionNegocio("Usuario NO encontrado: " + dto.getIdUsuario()));

        Acceso accesoExistente = accesoRepositorio.findById(objUsuario.getIdUsuario()).orElse(null);
        Optional<Acceso> accesoPorCorreo = accesoRepositorio.findByCorreoAcceso(dto.getCorreoAcceso());

        if (accesoPorCorreo.isPresent()) {
            Acceso encontrado = accesoPorCorreo.get();
            if (accesoExistente == null || !encontrado.getIdUsuario().equals(accesoExistente.getIdUsuario())) {
                throw new ExcepcionNegocio("Correo ya registrado: " + dto.getCorreoAcceso());
            }
        }

        Acceso accesoFinal = (accesoExistente == null)
                ? construirAcceso(objUsuario, dto)
                : actualizarAcceso(accesoExistente, dto);

        Acceso accesoPersistido = agregar(accesoFinal);
        registroAuditoria(idEjecutor, accesoPersistido);

        return accesoMapeador.desdeEntidad(accesoPersistido);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private Acceso construirAcceso(Usuario objUsuario, AccesoDTOCrear dto) {
        Acceso accesoNuevo = new Acceso();

        accesoNuevo.setUsuario(objUsuario);
        accesoNuevo.setCorreoAcceso(dto.getCorreoAcceso());
        accesoNuevo.setClaveAcceso(bCryptPasswordEncoder.encode(dto.getClaveAcceso()));
        accesoNuevo.setUuidAcceso(UUID.randomUUID().toString());
        return accesoNuevo;
    }

    private Acceso actualizarAcceso(Acceso accesoExistente, AccesoDTOCrear dto) {
        accesoExistente.setCorreoAcceso(dto.getCorreoAcceso());
        accesoExistente.setClaveAcceso(bCryptPasswordEncoder.encode(dto.getClaveAcceso()));
        accesoExistente.setUuidAcceso(UUID.randomUUID().toString());
        return accesoExistente;
    }

    private void registroAuditoria(Integer idEjecutor, Acceso obj) {
        auditoriaServicio.registrar(
                idEjecutor,
                obj.getClass().getName(),
                obj.getIdUsuario(),
                TipoCambio.ACTUALIZAR,
                obj.toString()
        );
    }
    // *************************************************************************
}
