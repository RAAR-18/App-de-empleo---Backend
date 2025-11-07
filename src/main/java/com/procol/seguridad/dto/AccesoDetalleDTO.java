package com.procol.seguridad.dto;

import java.util.List;
import java.util.Collection;

import com.procol.infraestructura.constante.ConstEstadoRegistro;
import com.procol.seguridad.entidad.Acceso;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AccesoDetalleDTO implements UserDetails {

    private final Acceso acceso;
    private final List<? extends GrantedAuthority> autoridades;

    public AccesoDetalleDTO(Acceso acceso, List<? extends GrantedAuthority> autoridades) {
        this.acceso = acceso;
        this.autoridades = autoridades;
    }

    public Acceso getAcceso() {
        return acceso;
    }

    public List<String> getNombresRoles() {
        return autoridades.stream().map(GrantedAuthority::getAuthority)
                .map(rol -> rol.replace("ROLE_", "")).toList();
    }

    @Override
    public String getUsername() {
        return acceso.getCorreoAcceso();
    }

    @Override
    public String getPassword() {
        return acceso.getClaveAcceso();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return autoridades;
    }

    @Override
    public boolean isEnabled() {
        return acceso.getUsuario().getEstadoUsuario() == ConstEstadoRegistro.ACTIVO;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return acceso.getUsuario().getEstadoUsuario() != ConstEstadoRegistro.BLOQUEADO;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
