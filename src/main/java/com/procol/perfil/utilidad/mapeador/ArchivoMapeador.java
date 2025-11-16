package com.procol.perfil.utilidad.mapeador;

import com.procol.infraestructura.utilidad.mapeador.MapeoEntidadDTO;
import com.procol.perfil.dto.ArchivoDTO;
import com.procol.perfil.entidad.Archivo;
import org.springframework.stereotype.Component;

@Component("perfil_ArchivoMapeador")
public class ArchivoMapeador implements MapeoEntidadDTO<Archivo, ArchivoDTO> {

    private final UsuarioMapeador usuarioMapeador;

    public ArchivoMapeador(UsuarioMapeador usuarioMapeador) {
        this.usuarioMapeador = usuarioMapeador;
    }

    @Override
    public ArchivoDTO desdeEntidad(Archivo objArchivo) {
        if (objArchivo == null) {
            return null;
        }

        return new ArchivoDTO(
                objArchivo.getIdArchivo(),
                usuarioMapeador.desdeEntidad(objArchivo.getIdUsuario()),
                objArchivo.getNombrePublicoArchivo(),
                objArchivo.getNombrePrivadoArchivo(),
                objArchivo.getTipoArchivo(),
                objArchivo.getTamanioArchivo(),
                objArchivo.getGrupoArchivo(),
                objArchivo.getFechaSubida()
        );

    }

    @Override
    public Archivo desdeDto(ArchivoDTO dto) {
        if (dto == null) {
            return null;
        }

        Archivo entidad = new Archivo();
        entidad.setIdArchivo(dto.getIdArchivo());
        entidad.setIdUsuario(usuarioMapeador.desdeDto(dto.getIdUsuario()));
        entidad.setNombrePublicoArchivo(dto.getNombrePublicoArchivo());
        entidad.setNombrePrivadoArchivo(dto.getNombrePrivadoArchivo());
        entidad.setTipoArchivo(dto.getTipoArchivo());
        entidad.setTamanioArchivo(dto.getTamanioArchivo());
        entidad.setGrupoArchivo(dto.getGrupoArchivo());
        entidad.setFechaSubida(dto.getFechaSubida());

        return entidad;
    }
}
