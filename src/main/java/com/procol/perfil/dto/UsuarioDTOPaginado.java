package com.procol.perfil.dto;

public class UsuarioDTOPaginado {

    private Integer idUsuario;
    private UbicacionDTO idUbicacion;
    private String documentoUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private Short estadoUsuario;
    private Long cantidadRoles;
    private String correoAcceso;

    public UsuarioDTOPaginado() {
    }

    public UsuarioDTOPaginado(
            Integer idUsuario, UbicacionDTO idUbicacion, String documentoUsuario,
            String nombresUsuario, String apellidosUsuario, Short estadoUsuario,
            Long cantidadRoles, String correoAcceso
    ) {
        this.idUsuario = idUsuario;
        this.idUbicacion = idUbicacion;
        this.documentoUsuario = documentoUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.estadoUsuario = estadoUsuario;
        this.cantidadRoles = cantidadRoles;
        this.correoAcceso = correoAcceso;
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

    public Long getCantidadRoles() {
        return cantidadRoles;
    }

    public void setCantidadRoles(Long cantidadRoles) {
        this.cantidadRoles = cantidadRoles;
    }

    public String getCorreoAcceso() {
        return correoAcceso;
    }

    public void setCorreoAcceso(String correoAcceso) {
        this.correoAcceso = correoAcceso;
    }

    @Override
    public String toString() {
        return "UsuarioDTOPaginar["
                + "idUsuario=" + idUsuario
                + ", idUbicacion=" + idUbicacion
                + ", documentoUsuario=" + documentoUsuario
                + ", nombresUsuario=" + nombresUsuario
                + ", apellidosUsuario=" + apellidosUsuario
                + ", estadoUsuario=" + estadoUsuario
                + ", cantidadRoles=" + cantidadRoles
                + ", correoAcceso=" + correoAcceso
                + "]";
    }

}

