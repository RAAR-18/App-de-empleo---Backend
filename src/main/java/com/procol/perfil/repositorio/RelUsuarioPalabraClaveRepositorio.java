package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.PalabraClave;
import com.procol.perfil.entidad.RelUsuarioPalabraClave;
import com.procol.perfil.entidad.pk.RelUsuarioPalabraClavePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("perfil_RelUsuarioPalabraClaveRepositorio")
public interface RelUsuarioPalabraClaveRepositorio extends JpaRepository<RelUsuarioPalabraClave, RelUsuarioPalabraClavePK> {

    void deleteByUsuarioIdUsuario(Integer idUsuario);

    @Query("""
       SELECT r.palabraClave 
       FROM perfil_RelUsuarioPalabraClave r 
       WHERE r.id.idUsuario = :idUsuario
       """)
    List<PalabraClave> findPalabrasClaveByIdUsuario(@Param("idUsuario") Integer idUsuario);

}
