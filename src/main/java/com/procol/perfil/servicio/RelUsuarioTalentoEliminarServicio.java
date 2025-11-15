package com.procol.perfil.servicio;

import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import com.procol.perfil.repositorio.RelUsuarioTalentoRepositorio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("perfil_RelUsuarioTalentoEliminarServicio")
public class RelUsuarioTalentoEliminarServicio {

    private final RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio;

    public RelUsuarioTalentoEliminarServicio(RelUsuarioTalentoRepositorio relUsuarioTalentoRepositorio) {
        this.relUsuarioTalentoRepositorio = relUsuarioTalentoRepositorio;
    }

    @Transactional
    public void eliminarTalento(Integer idUsuario, Integer idTalento) {
        RelUsuarioTalentoPK pk = new RelUsuarioTalentoPK(idUsuario, idTalento);
        if (relUsuarioTalentoRepositorio.existsById(pk)) {
            relUsuarioTalentoRepositorio.deleteById(pk);
        } else {
            throw new ExcepcionNegocio("No se encontró la relación usuario-talento a eliminar.");
        }
    }
}
