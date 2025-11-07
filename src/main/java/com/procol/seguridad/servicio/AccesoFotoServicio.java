package com.procol.seguridad.servicio;

import java.util.Optional;

import com.procol.seguridad.entidad.Imagen;
import com.procol.seguridad.repositorio.ImagenRepositorio;

import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.constante.ConstImagenCuenta;
import com.procol.infraestructura.constante.ConstMiniaturaAvatar;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;

import org.springframework.stereotype.Service;

@Service
public class AccesoFotoServicio {

    private final ImagenRepositorio imagenRepositorio;
    private final GestorArchivoBuilder gestorArchivoBuilder;

    public AccesoFotoServicio(
            ImagenRepositorio imagenRepositorio,
            GestorArchivoBuilder gestorArchivoBuilder
    ) {
        this.imagenRepositorio = imagenRepositorio;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
    }

    public String obtenerFoto(Integer idUsuario) {
        Optional<Imagen> imagenOpt = imagenRepositorio
                .findFirstByIdUsuario_IdUsuarioAndFavoritaImagenOrderByIdImagenDesc(idUsuario, ConstImagenCuenta.FAVORITA);

        Integer tamanioRedimensionar = ConstMiniaturaAvatar.TAMANIO;
        String imgPorDefecto = ConstMiniaturaAvatar.POR_DEFECTO;

        String nombrePrivado = imagenOpt.map(Imagen::getNombrePrivadoImagen).orElse(imgPorDefecto);

        GestorArchivoInstancia instancia = gestorArchivoBuilder.crear(ConstTipoArchivo.IMAGEN,
                nombrePrivado,
                imgPorDefecto,
                null);
        return instancia.imagenBase64Miniatura(tamanioRedimensionar);
    }

}
