package com.procol.usuario.repositorio;

import com.procol.usuario.entidad.Imagen;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("usuario_ImagenRepositorio")
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {

    @Modifying
    @Query("UPDATE usuario_Imagen i SET i.favoritaImagen = :noFavorita WHERE i.idUsuario.idUsuario = :idUsuario")
    int desmarcarFavoritasPorUsuario(@Param("idUsuario") Integer idUsuario, @Param("noFavorita") short noFavorita);

}
