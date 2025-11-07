package com.procol.comun.repositorio;

import java.util.List;

import com.procol.comun.entidad.Ubicacion;
import jakarta.persistence.Tuple;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("comun_UbicacionRepositorio")
public interface UbicacionRepositorio extends JpaRepository<Ubicacion, Integer> {

    @Query(value = """
        SELECT 
            u.id_ubicacion AS idUbicacion,
            CONCAT(u.nombre_ubicacion, ' (', 
                   (SELECT nombre_ubicacion FROM ubicaciones 
                    WHERE id_ubicacion = u.id_padre_ubicacion), ')') AS nombreUbicacion,
            u.longitud_ubicacion AS longitudUbicacion,
            u.latitud_ubicacion AS latitudUbicacion
        FROM ubicaciones u
        WHERE unaccent(LOWER(u.nombre_ubicacion)) LIKE unaccent(LOWER(CONCAT('%', :texto, '%')))
        """, nativeQuery = true)
    List<Tuple> buscarUbicacionesConPadre(@Param("texto") String texto);

}
