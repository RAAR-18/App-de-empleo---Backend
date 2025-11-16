package com.procol.perfil.servicio;

import com.procol.perfil.dto.RelUsuarioTalentoDTO;
import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.perfil.repositorio.RelUsuarioTalentoRepositorio;
import com.procol.perfil.utilidad.mapeador.RelUsuarioTalentoMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("perfil_RelUsuarioTalentoListarServicio")
public class RelUsuarioTalentoListarServicio {

    private final RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio;
    private final RelUsuarioTalentoMapeador relUsuarioTalentoMapeador;

    public RelUsuarioTalentoListarServicio(
            RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio,
            RelUsuarioTalentoMapeador relUsuarioTalentoMapeador) {
        this.relUsuarioTalentoRepositorio = relUsuarioTalentoRepositorio;
        this.relUsuarioTalentoMapeador = relUsuarioTalentoMapeador;
    }

    @Transactional(readOnly = true)
    public List<RelUsuarioTalentoDTO> listarTalentosPorUsuario(Integer idUsuario) {
        List<RelUsuarioTalento> talentos = relUsuarioTalentoRepositorio.findByIdIdUsuario(idUsuario);
        return talentos.stream()
                .map(relUsuarioTalentoMapeador::desdeEntidad)
                .collect(Collectors.toList());
    }
}