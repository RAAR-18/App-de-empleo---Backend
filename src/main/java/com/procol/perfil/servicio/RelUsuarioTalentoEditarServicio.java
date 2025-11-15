package com.procol.perfil.servicio;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.dto.RelUsuarioTalentoDTOEditar;
import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import com.procol.perfil.repositorio.RelUsuarioTalentoRepositorio;
import com.procol.perfil.utilidad.mapeador.RelUsuarioTalentoMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_RelUsuarioTalentoEditarServicio")
public class RelUsuarioTalentoEditarServicio {

    private final RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio;
    private final BusquedaServicio<RelUsuarioTalento, RelUsuarioTalentoPK> busquedaServicio;
    private final RelUsuarioTalentoMapeador relUsuarioTalentoMapeador;

    public RelUsuarioTalentoEditarServicio(RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio,
                                            BusquedaServicio<RelUsuarioTalento, RelUsuarioTalentoPK> busquedaServicio,
                                            RelUsuarioTalentoMapeador relUsuarioTalentoMapeador) {
        this.relUsuarioTalentoRepositorio = relUsuarioTalentoRepositorio;
        this.busquedaServicio = busquedaServicio;
        this.relUsuarioTalentoMapeador = relUsuarioTalentoMapeador;
    }

    @Transactional
    public RelUsuarioTalentoDTO editarNivelDominio(RelUsuarioTalentoDTOEditar dto) {
        RelUsuarioTalentoPK pk = new RelUsuarioTalentoPK(dto.getIdUsuario(), dto.getIdTalento());

        RelUsuarioTalento relacion = busquedaServicio.PorId(
                relUsuarioTalentoRepositorio, pk, "No se encontró la relación usuario-talento especificada."
        );

        relacion.setNivelDominio(dto.getNivelDominio());
        RelUsuarioTalento actualizado = relUsuarioTalentoRepositorio.save(relacion);

        return relUsuarioTalentoMapeador.desdeEntidad(actualizado);
    }
}
