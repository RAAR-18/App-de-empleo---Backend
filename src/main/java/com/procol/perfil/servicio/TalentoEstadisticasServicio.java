package com.procol.perfil.servicio;

import com.procol.perfil.dto.TalentoEstadisticasDTO;
import com.procol.perfil.repositorio.RelUsuarioTalentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_TalentoEstadisticasServicio")
public class TalentoEstadisticasServicio {

    private final RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio;

    public TalentoEstadisticasServicio(RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio) {
        this.relUsuarioTalentoRepositorio = relUsuarioTalentoRepositorio;
    }

    @Transactional(readOnly = true)
    public TalentoEstadisticasDTO obtenerEstadisticas(Integer idUsuario) {
        Long total = relUsuarioTalentoRepositorio.countByIdIdUsuario(idUsuario);
        Long basico = relUsuarioTalentoRepositorio.countByIdIdUsuarioAndNivelDominio(idUsuario, (short) 1);
        Long intermedio = relUsuarioTalentoRepositorio.countByIdIdUsuarioAndNivelDominio(idUsuario, (short) 2);
        Long avanzado = relUsuarioTalentoRepositorio.countByIdIdUsuarioAndNivelDominio(idUsuario, (short) 3);

        return new TalentoEstadisticasDTO(total, total, 0L, basico, intermedio, avanzado);
    }
}