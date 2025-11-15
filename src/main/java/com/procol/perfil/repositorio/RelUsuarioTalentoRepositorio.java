package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.RelUsuarioTalento;
import com.procol.perfil.entidad.pk.RelUsuarioTalentoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("perfil_RelUsuarioTalentoR")
public interface RelUsuarioTalentoRepositorio extends JpaRepository<RelUsuarioTalento, RelUsuarioTalentoPK> {


}
