package com.procol.perfil.repositorio;

import com.procol.perfil.entidad.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("perfil_ImagenRepositorio")
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {

    @Modifying
    @Query("UPDATE perfil_Imagen i SET i.favoritaImagen = :noFavorita " +
            "WHERE i.idUsuario.idUsuario = :idUsuario AND i.categoria = 1")
    int desmarcarFavoritasPorUsuario(@Param("idUsuario") Integer idUsuario,
                                     @Param("noFavorita") short noFavorita);

    @Query("SELECT i FROM perfil_Imagen i WHERE i.idUsuario.idUsuario = :idUsuario " +
            "AND i.categoria = 1 AND i.favoritaImagen = 1")
    Optional<Imagen> findFotoPerfilByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT i FROM perfil_Imagen i WHERE i.idUsuario.idUsuario = :idUsuario " +
            "AND i.categoria = 2 ORDER BY i.idImagen DESC")
    List<Imagen> findPortafolioByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(i) FROM perfil_Imagen i WHERE i.idUsuario.idUsuario = :idUsuario " +
            "AND i.categoria = 2")
    long contarImagenesPortafolio(@Param("idUsuario") Integer idUsuario);
}