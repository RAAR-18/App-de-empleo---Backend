package com.procol.perfil.servicio;

import com.procol.perfil.dto.ImagenDTO;
import com.procol.perfil.entidad.Imagen;
import com.procol.perfil.repositorio.ImagenRepositorio;
import com.procol.perfil.utilidad.mapeador.ImagenMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("perfil_ImagenConsultarServicio")
public class ImagenConsultarServicio {

    private final ImagenRepositorio imagenRepositorio;
    private final ImagenMapeador imagenMapeador;

    public ImagenConsultarServicio(
            ImagenRepositorio imagenRepositorio,
            ImagenMapeador imagenMapeador
    ) {
        this.imagenRepositorio = imagenRepositorio;
        this.imagenMapeador = imagenMapeador;
    }


    @Transactional(readOnly = true)
    public ImagenDTO obtenerFotoPerfilUsuario(Integer idUsuario) {
        Optional<Imagen> imagenOpt = imagenRepositorio.findFotoPerfilByUsuario(idUsuario);

        return imagenOpt.map(imagenMapeador::desdeEntidad).orElse(null);
    }
}