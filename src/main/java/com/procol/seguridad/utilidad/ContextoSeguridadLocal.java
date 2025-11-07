package com.procol.seguridad.utilidad;

import java.util.List;
import java.util.Optional;

import com.procol.seguridad.entidad.Usuario;
import com.procol.seguridad.dto.AccesoDetalleDTO;

import com.procol.infraestructura.excepcion.ExcepcionSeguridad;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public class ContextoSeguridadLocal implements ContextoSeguridad {

    public Optional<AccesoDetalleDTO> obtenerUsuarioActual() {
        Authentication autenticacion = SecurityContextHolder.getContext().getAuthentication();

        if (autenticacion == null || !(autenticacion.getPrincipal() instanceof AccesoDetalleDTO)) {
            return Optional.empty();
        }
        return Optional.of((AccesoDetalleDTO) autenticacion.getPrincipal());
    }

    @Override
    public Integer getIdUsuario() {
        return obtenerUsuarioActual()
                .map(detalle -> detalle.getAcceso().getIdUsuario())
                .orElseThrow(() -> new ExcepcionSeguridad("No hay usuario autenticado"));
    }

    @Override
    public String getCorreo() {
        return obtenerUsuarioActual()
                .map(detalle -> detalle.getAcceso().getCorreoAcceso())
                .orElseThrow(() -> new ExcepcionSeguridad("No hay usuario autenticado"));
    }

    @Override
    public String getNombreCompleto() {
        return obtenerUsuarioActual()
                .map(detalle -> {
                    Usuario usuario = detalle.getAcceso().getUsuario();
                    return usuario.getNombresUsuario() + " " + usuario.getApellidosUsuario();
                })
                .orElse("Desconocido");
    }

    @Override
    public String getUuidAcceso() {
        return obtenerUsuarioActual()
                .map(detalle -> detalle.getAcceso().getUuidAcceso())
                .orElse("UUID no disponible");
    }

    @Override
    public List<String> getRoles() {
        return obtenerUsuarioActual()
                .map(AccesoDetalleDTO::getNombresRoles)
                .orElse(List.of());
    }
}
