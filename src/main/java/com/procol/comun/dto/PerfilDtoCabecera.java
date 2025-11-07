package com.procol.comun.dto;

import java.util.List;

public class PerfilDtoCabecera {

    private Integer idUbicacion;
    private Short tipoDocumentoUsuario;
    private String documentoUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String telefonoAcceso;
    private String correoAcceso;
    private String nombreUbicacion;
    private List<RolDtoCabecera> roles;

    public PerfilDtoCabecera() {
    }

    public PerfilDtoCabecera(
            Integer idUbicacion, Short tipoDocumentoUsuario, String documentoUsuario,
            String nombresUsuario, String apellidosUsuario, String telefonoAcceso,
            String correoAcceso, String nombreUbicacion,
            List<RolDtoCabecera> roles
    ) {
        this.idUbicacion = idUbicacion;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.telefonoAcceso = telefonoAcceso;
        this.correoAcceso = correoAcceso;
        this.nombreUbicacion = nombreUbicacion;
        this.roles = roles;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public Short getTipoDocumentoUsuario() {
        return tipoDocumentoUsuario;
    }

    public void setTipoDocumentoUsuario(Short tipoDocumentoUsuario) {
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombresUsuario) {
        this.nombresUsuario = nombresUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    public String getNombreUbicacion() {
        return nombreUbicacion;
    }

    public void setNombreUbicacion(String nombreUbicacion) {
        this.nombreUbicacion = nombreUbicacion;
    }

    public List<RolDtoCabecera> getRoles() {
        return roles;
    }

    public void setRoles(List<RolDtoCabecera> roles) {
        this.roles = roles;
    }

}
