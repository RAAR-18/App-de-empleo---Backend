package com.procol.usuario.dto;

public class UsuarioDTO {

    private Integer idUsuario;
    private UbicacionDTO idUbicacion;
    private String documentoUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private Short estadoUsuario;

    public UsuarioDTO(Integer idUsuario, UbicacionDTO idUbicacion, String documentoUsuario, String nombresUsuario, String apellidosUsuario, Short estadoUsuario) {
        this.idUsuario = idUsuario;
        this.idUbicacion = idUbicacion;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.estadoUsuario = estadoUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UbicacionDTO getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(UbicacionDTO idUbicacion) {
        this.idUbicacion = idUbicacion;
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

    public Short getEstadoUsuario() {
        return estadoUsuario;
    }

    public void setEstadoUsuario(Short estadoUsuario) {
        this.estadoUsuario = estadoUsuario;
    }

    @Override
    public String toString() {
        return "UsuarioDTO["
                + "idUsuario=" + idUsuario
                + ", idUbicacion=" + idUbicacion
                + ", documentoUsuario=" + documentoUsuario
                + ", nombresUsuario=" + nombresUsuario
                + ", apellidosUsuario=" + apellidosUsuario
                + ", estadoUsuario=" + estadoUsuario
                + "]";
    }

}
