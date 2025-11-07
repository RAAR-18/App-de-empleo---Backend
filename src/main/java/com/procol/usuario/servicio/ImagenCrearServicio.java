package com.procol.usuario.servicio;

import com.procol.infraestructura.constante.ConstImagenCuenta;
import com.procol.usuario.dto.ImagenDTO;
import com.procol.usuario.entidad.Imagen;
import com.procol.usuario.entidad.Usuario;
import com.procol.usuario.dto.ImagenDTOCrear;
import com.procol.usuario.repositorio.ImagenRepositorio;
import com.procol.usuario.repositorio.UsuarioRepositorio;
import com.procol.usuario.utilidad.mapeador.ImagenMapeador;

import com.procol.infraestructura.constante.ConstTipoArchivo;
import com.procol.infraestructura.excepcion.ExcepcionNegocio;
import com.procol.infraestructura.core.crud.OperacionCrudImple;
import com.procol.infraestructura.core.busqueda.BusquedaServicio;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoBuilder;
import com.procol.infraestructura.utilidad.archivo.GestorArchivoInstancia;

import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenCrearServicio extends OperacionCrudImple<Imagen, Integer> {

    // Repositorio obligatorio
    private final ImagenRepositorio imagenRepositorio;

    // Servicios o repositorios adicionales
    private final UsuarioRepositorio usuarioRepositorio;
    private final BusquedaServicio<Usuario, Integer> busquedaServicioUsuario;
    private final ImagenMapeador imagenMapeador;

    // Exclusivo para las rutas de las imágenes
    private final GestorArchivoBuilder gestorArchivoBuilder;

    public ImagenCrearServicio(
            ImagenRepositorio imagenRepositorio,
            UsuarioRepositorio usuarioRepositorio,
            ImagenMapeador imagenMapeador,
            GestorArchivoBuilder gestorArchivoBuilder,
            BusquedaServicio<Imagen, Integer> busquedaServicioImagen,
            BusquedaServicio<Usuario, Integer> busquedaServicioUsuario
    ) {
        super(busquedaServicioImagen);
        this.imagenRepositorio = imagenRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.imagenMapeador = imagenMapeador;
        this.gestorArchivoBuilder = gestorArchivoBuilder;
        this.busquedaServicioUsuario = busquedaServicioUsuario;
    }

    @Override
    protected JpaRepository<Imagen, Integer> getRepositorio() {
        return imagenRepositorio;
    }

    @Transactional
    public ImagenDTO agregarImagen(ImagenDTOCrear dto) {
        Usuario objUsuario = busquedaServicioUsuario.PorId(usuarioRepositorio, dto.getIdUsuario());

        GestorArchivoInstancia instancia = gestorArchivoBuilder.crear(
                ConstTipoArchivo.IMAGEN,
                dto.getNombrePrivadoImagen(),
                null,
                dto.getArchivo()
        );

        boolean exito = instancia.guardar();
        if (!exito) {
            throw new ExcepcionNegocio("No se pudo guardar la imagen");
        }

        imagenRepositorio.desmarcarFavoritasPorUsuario(objUsuario.getIdUsuario(), ConstImagenCuenta.NO_FAVORITA);

        Imagen objNueva = new Imagen();
        objNueva.setIdUsuario(objUsuario);
        objNueva.setNombrePublicoImagen(dto.getNombrePublicoImagen());
        objNueva.setNombrePrivadoImagen(dto.getNombrePrivadoImagen());
        objNueva.setTipoImagen(dto.getTipoImagen());
        objNueva.setTamanioImagen(dto.getTamanioImagen());
        objNueva.setFavoritaImagen(ConstImagenCuenta.FAVORITA);

        Imagen objImagenFinal = agregar(objNueva);
        return imagenMapeador.desdeEntidad(objImagenFinal);
    }

}
