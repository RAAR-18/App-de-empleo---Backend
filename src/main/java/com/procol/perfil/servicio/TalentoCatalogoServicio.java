package com.procol.perfil.servicio;

import com.procol.perfil.entidad.Talento;
import com.procol.perfil.repositorio.TalentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("perfil_TalentoCatalogoServicio")
public class TalentoCatalogoServicio {

    private final TalentoRepositorio talentoRepositorio;

    public TalentoCatalogoServicio(TalentoRepositorio talentoRepositorio) {
        this.talentoRepositorio = talentoRepositorio;
    }

    @Transactional(readOnly = true)
    public List<Talento> listarTodos() {
        return talentoRepositorio.findAll();
    }
}