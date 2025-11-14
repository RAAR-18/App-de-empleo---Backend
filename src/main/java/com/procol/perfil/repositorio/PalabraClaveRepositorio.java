package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.PalabraClave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("perfil_PalabraRepositorio")
public interface PalabraClaveRepositorio extends JpaRepository<PalabraClave, Integer> {

    @Query("SELECT p FROM perfil_PalabraClave p WHERE p.idArea.idArea = :idArea")
    List<PalabraClave> findByArea(@Param("idArea") Integer idArea);

    @Query("SELECT p FROM perfil_PalabraClave p WHERE LOWER(p.textoPalabraClave) LIKE LOWER(CONCAT('%', :texto, '%'))")
    List<PalabraClave> buscarPorTexto(@Param("texto") String texto);
}
