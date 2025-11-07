package com.procol.usuario.servicio;

import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.procol.usuario.dto.ImagenDTO;
import com.procol.usuario.entidad.Imagen;
import com.procol.usuario.repositorio.ImagenRepositorio;
import com.procol.usuario.utilidad.mapeador.ImagenMapeador;

import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.constante.ConstMensajeRespuesta;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.utilidad.archivo.RutaArchivo;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;

@Service
public class ImagenEliminarServicio extends OperacionCrudImple<Imagen, Integer> {

    // Repositorio obligatorio
    private final ImagenRepositorio imagenRepositorio;

    // Servicios adicionales
    private final ImagenMapeador imagenMapeador;

    // Exclusivo para las rutas de las imágenes
    private final RutaArchivo rutaArchivo;

    public ImagenEliminarServicio(
            ImagenRepositorio imagenRepositorio,
            ImagenMapeador imagenMapeador,
            RutaArchivo rutaArchivo,
            BusquedaServicio<Imagen, Integer> busquedaServicioImagen
    ) {
        super(busquedaServicioImagen);
        this.imagenRepositorio = imagenRepositorio;
        this.imagenMapeador = imagenMapeador;
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    protected JpaRepository<Imagen, Integer> getRepositorio() {
        return imagenRepositorio;
    }

    public ImagenDTO eliminarImagen(Integer idImagen) {
        Imagen objImagen = buscarPorId(idImagen);
        eliminarRegistroBD(idImagen);
        eliminarImagenDisco(objImagen.getNombrePrivadoImagen());
        return imagenMapeador.desdeEntidad(objImagen);
    }

    // *************************************************************************
    // Métodos privados
    // *************************************************************************
    private void eliminarRegistroBD(Integer idImagen) {
        boolean resultado = eliminar(idImagen);
        if (!resultado) {
            String msg = ConstMensajeRespuesta.REGISTRO_ELIMINADO_ERROR + idImagen;
            throw new ExcepcionNegocio(msg);
        }
    }

    private void eliminarImagenDisco(String nombrePrivado) {
        String rutaImagenes = rutaArchivo.getImagenes();

        if (rutaImagenes == null || rutaImagenes.isBlank()) {
            throw new ExcepcionNegocio("Ruta de imágenes no configurada");
        }

        Path rutaCompleta = Paths.get(rutaImagenes).resolve(nombrePrivado);

        try {
            Files.deleteIfExists(rutaCompleta);
        } catch (IOException e) {
            throw new ExcepcionNegocio("No se pudo eliminar la imagen: " + nombrePrivado);
        }
    }

    // *************************************************************************
}
