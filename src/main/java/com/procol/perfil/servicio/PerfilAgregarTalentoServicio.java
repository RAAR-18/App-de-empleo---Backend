package com.procol.perfil.servicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.dto.RelUsuarioTalentoDTOAgregar;
import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.perfil.entidad.Talento;
import com.procol.perfil.entidad.Usuario;
import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import com.procol.perfil.repositorio.RelUsuarioTalentoRepositorio;
import com.procol.perfil.repositorio.TalentoRepositorio;
import com.procol.perfil.repositorio.UsuarioRepositorio;
import com.procol.perfil.utilidad.mapeador.RelUsuarioTalentoMapeador;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_AgregarTalentoServicio")
public class PerfilAgregarTalentoServicio {

    private final RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final TalentoRepositorio talentoRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final BusquedaServicio<Talento, Integer> busquedaServicioTalento;
    private final RelUsuarioTalentoMapeador relUsuarioTalentoMapeador;
    private EntityManager entityManager;

    public PerfilAgregarTalentoServicio(
            RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            TalentoRepositorio talentoRepositorio,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario,
            BusquedaServicio<Talento, Integer> busquedaServicioTalento,
            RelUsuarioTalentoMapeador relUsuarioTalentoMapeador
    ) {
        this.relUsuarioTalentoRepositorio = relUsuarioTalentoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.talentoRepositorio = talentoRepositorio;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
        this.busquedaServicioTalento = busquedaServicioTalento;
        this.relUsuarioTalentoMapeador = relUsuarioTalentoMapeador;
    }


    @Transactional
    public RelUsuarioTalentoDTO asignarTalento(RelUsuarioTalentoDTOAgregar dto) {
        Usuario usuario = busquedaServicioUsuario.PorId(usuarioRepositorio, dto.getIdUsuario());
        Talento talento = busquedaServicioTalento.PorId(talentoRepositorio, dto.getIdTalento());

        RelUsuarioTalentoPK pk = new RelUsuarioTalentoPK(dto.getIdUsuario(), dto.getIdTalento());
        boolean existe = relUsuarioTalentoRepositorio.existsById(pk);

        if (existe) {
            throw new ExcepcionNegocio("El usuario ya tiene asignado este talento.");
        }

        RelUsuarioTalento relacion = new RelUsuarioTalento();
        relacion.setId(pk);
        relacion.setUsuario(usuario);
        relacion.setTalento(talento);
        relacion.setNivelDominio(dto.getNivelDominio());

        RelUsuarioTalento guardado = relUsuarioTalentoRepositorio.save(relacion);
        return relUsuarioTalentoMapeador.desdeEntidad(guardado);
    }
}
