package com.procol.perfil.servicio;

import com.procol.perfil.dto.ImagenDTO;
import com.procol.perfil.entidad.Imagen;
import com.procol.perfil.repositorio.ImagenRepositorio;
import com.procol.perfil.utilidad.mapeador.ImagenMapeador;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("perfil_ImagenPortafolioConsultarServicio")
public class ImagenPortafolioConsultarServicio {

    private final ImagenRepositorio imagenRepositorio;
    private final ImagenMapeador imagenMapeador;

    public ImagenPortafolioConsultarServicio(
            ImagenRepositorio imagenRepositorio,
            ImagenMapeador imagenMapeador
    ) {
        this.imagenRepositorio = imagenRepositorio;
        this.imagenMapeador = imagenMapeador;
    }

    @Transactional(readOnly = true)
    public List<ImagenDTO> obtenerPortafolio(Integer idUsuario) {
        List<Imagen> imagenes = imagenRepositorio.findPortafolioByUsuario(idUsuario);

        return imagenes.stream()
                .map(imagenMapeador::desdeEntidad)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long contarImagenesPortafolio(Integer idUsuario) {
        return imagenRepositorio.contarImagenesPortafolio(idUsuario);
    }
}