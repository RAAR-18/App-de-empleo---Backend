package com.procol.seguridad.repositorio;

import java.util.Optional;

import com.procol.seguridad.entidad.Imagen;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository("seguridad_ImagenRepositorio")
public interface ImagenRepositorio extends JpaRepository<Imagen, Integer> {

    Optional<Imagen> findFirstByIdUsuario_IdUsuarioAndFavoritaImagenOrderByIdImagenDesc(
            Integer idUsuario, short favoritaImagen
    );

}
