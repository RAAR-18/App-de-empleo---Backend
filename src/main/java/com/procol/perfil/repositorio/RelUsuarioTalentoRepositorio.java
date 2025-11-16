package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("perfil_RelUsuarioTalentoR")
public interface RelUsuarioTalentoRepositorio extends JpaRepository<RelUsuarioTalento, RelUsuarioTalentoPK> {

    // Listar todos los talentos de un usuario
    List<RelUsuarioTalento> findByIdIdUsuario(Integer idUsuario);

    // Contar talentos de un usuario
    Long countByIdIdUsuario(Integer idUsuario);

    // Contar talentos de un usuario por nivel de dominio
    Long countByIdIdUsuarioAndNivelDominio(Integer idUsuario, Short nivelDominio);
}