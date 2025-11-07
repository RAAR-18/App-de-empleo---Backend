package com.procol.comun.servicio;

import com.procol.comun.entidad.Imagen;
import com.procol.comun.dto.ImagenDtoBinario;
import com.procol.comun.repositorio.ImagenRepositorio;

import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.constante.ConstImagenCuenta;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.constante.ConstMiniaturaAvatar;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenAvatarServicio extends OperacionCrudImple<Imagen, Integer> {

    // Repositorio obligatorio
    private final ImagenRepositorio imagenRepositorio;

    //Otros repositorios
    // Servicios adicionales
    private final GestorArchivoBuilder gestorArchivoBuilder;

    public ImagenAvatarServicio(
            ImagenRepositorio imagenRepositorio,
            GestorArchivoBuilder gestorArchivoBuilder,
            BusquedaServicio<Imagen, Integer> BusquedaServicioImagen
    ) {
        super(BusquedaServicioImagen);
        this.imagenRepositorio = imagenRepositorio;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
    }

    @Override
    protected JpaRepository<Imagen, Integer> getRepositorio() {
        return imagenRepositorio;
    }

    public ImagenDtoBinario obtenerFotoCompleta(Integer idUsuario) {
        String nombrePrivado = imagenRepositorio
                .findFirstByIdUsuario_IdUsuarioAndFavoritaImagenOrderByIdImagenDesc(
                        idUsuario, ConstImagenCuenta.FAVORITA
                )
                .map(Imagen::getNombrePrivadoImagen)
                .orElse(ConstMiniaturaAvatar.POR_DEFECTO);

        GestorArchivoInstancia instancia = gestorArchivoBuilder.crear(
                ConstTipoArchivo.IMAGEN,
                nombrePrivado,
                ConstMiniaturaAvatar.POR_DEFECTO,
                null
        );

        byte[] contenido = instancia.obtenerContenido();
        String mime = instancia.obtenerTipoMime();

        return new ImagenDtoBinario(contenido, mime);
    }

    @Transactional
    public String actualizarImagenFavorita(Integer idUsuario, Integer idImagenFavorita) {
        boolean imagenValida = imagenRepositorio.existeImagenDeUsuario(idUsuario, idImagenFavorita);
        if (!imagenValida) {
            return "Error: Verifique que la imagen exista y pertenezca al usuario. ";
        }

        int desmarcadas = imagenRepositorio.actualizarTodasNoFavoritas(idUsuario, ConstImagenCuenta.NO_FAVORITA);
        imagenRepositorio.actualizarFavoritaPorIdImagen(idImagenFavorita, ConstImagenCuenta.FAVORITA);

        String detalleDesmarcadas = switch (desmarcadas) {
            case 0 ->
                "El usuario NO tiene más imágenes";
            case 1 ->
                "El usuario tiene otra imagen adicional";
            default ->
                "El usuario tiene " + desmarcadas + " imágenes más";
        };

        return "Imagen favorita actualizada. " + detalleDesmarcadas;
    }

}
