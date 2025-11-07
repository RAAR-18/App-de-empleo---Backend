package com.procol.usuario.utilidad.mapeador;

import com.procol.usuario.dto.ImagenDTO;
import com.procol.usuario.entidad.Imagen;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;

import org.springframework.stereotype.Component;

@Component
public class ImagenMapeador implements MapeoEntidadDTO<Imagen, ImagenDTO> {

    private final UsuarioMapeador usuarioMapeador;

    public ImagenMapeador(UsuarioMapeador usuarioMapeador) {
        this.usuarioMapeador = usuarioMapeador;
    }

    @Override
    public ImagenDTO desdeEntidad(Imagen objImagen) {
        if (objImagen == null) {
            return null;
        }

        return new ImagenDTO(
                objImagen.getIdImagen(),
                usuarioMapeador.desdeEntidad(objImagen.getIdUsuario()),
                objImagen.getNombrePublicoImagen(),
                objImagen.getNombrePrivadoImagen(),
                objImagen.getTipoImagen(),
                objImagen.getTamanioImagen(),
                objImagen.getFavoritaImagen()
        );
    }

    @Override
    public Imagen desdeDto(ImagenDTO dto) {
        if (dto == null) {
            return null;
        }

        Imagen entidad = new Imagen();
        entidad.setIdImagen(dto.getIdImagen());
        entidad.setIdUsuario(usuarioMapeador.desdeDto(dto.getIdUsuario()));
        entidad.setNombrePublicoImagen(dto.getNombrePublicoImagen());
        entidad.setNombrePrivadoImagen(dto.getNombrePrivadoImagen());
        entidad.setTipoImagen(dto.getTipoImagen());
        entidad.setTamanioImagen(dto.getTamanioImagen());
        entidad.setFavoritaImagen(dto.getFavoritaImagen());

        return entidad;
    }
}
