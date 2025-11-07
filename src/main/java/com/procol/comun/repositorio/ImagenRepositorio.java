package com.procol.comun.repositorio;

import java.util.Optional;

import com.procol.comun.entidad.Imagen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

@Repository("comun_ImagenRepositorio")
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {

    Optional<Imagen> findFirstByIdUsuario_IdUsuarioAndFavoritaImagenOrderByIdImagenDesc(
            Integer idUsuario, short favoritaImagen
    );

    @Query("SELECT COUNT(i) > 0 FROM comun_Imagen i WHERE i.idImagen = :idImagen AND i.idUsuario.idUsuario = :idUsuario")
    boolean existeImagenDeUsuario(@Param("idUsuario") Integer idUsuario, @Param("idImagen") Integer idImagen);

    @Modifying
    @Transactional
    @Query("UPDATE comun_Imagen i SET i.favoritaImagen = :nuevoValor WHERE i.idImagen = :idImagen")
    int actualizarFavoritaPorIdImagen(Integer idImagen, short nuevoValor);

    @Modifying
    @Transactional
    @Query("UPDATE comun_Imagen i SET i.favoritaImagen = :nuevoValor WHERE i.idUsuario.idUsuario = :idUsuario")
    int actualizarTodasNoFavoritas(Integer idUsuario, short nuevoValor);

}
