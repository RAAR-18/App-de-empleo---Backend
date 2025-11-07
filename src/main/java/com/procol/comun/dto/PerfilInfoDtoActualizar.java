package com.procol.comun.dto;

public class PerfilInfoDtoActualizar {

    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private short tipoDocumentoUsuario;
    private String documentoUsuario;
    private Integer idUbicacion;
    private String telefonoAcceso;

    public PerfilInfoDtoActualizar() {
    }

    public PerfilInfoDtoActualizar(
            Integer idUsuario, String nombresUsuario, String apellidosUsuario,
            short tipoDocumentoUsuario, String documentoUsuario,
            Integer idUbicacion, String telefonoAcceso
    ) {
        this.idUsuario = idUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.idUbicacion = idUbicacion;
        this.telefonoAcceso = telefonoAcceso;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public short getTipoDocumentoUsuario() {
        return tipoDocumentoUsuario;
    }

    public void setTipoDocumentoUsuario(short tipoDocumentoUsuario) {
        this.tipoDocumentoUsuario = tipoDocumentoUsuario;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getTelefonoAcceso() {
        return telefonoAcceso;
    }

    public void setTelefonoAcceso(String telefonoAcceso) {
        this.telefonoAcceso = telefonoAcceso;
    }

}
