package com.procol.perfil.dto;

public class DatosBasicosDTOActualizar {

    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String documentoUsuario;
    private String profesion;
    private Integer idUbicacion;

    public DatosBasicosDTOActualizar() {
    }

    public DatosBasicosDTOActualizar(Integer idUsuario, String nombresUsuario, String apellidosUsuario,
                                     String documentoUsuario, String profesion, Integer idUbicacion
    ) {
        this.idUsuario = idUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.documentoUsuario = documentoUsuario;
        this.profesion = profesion;
        this.idUbicacion = idUbicacion;
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

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documentoUsuario) {
        this.documentoUsuario = documentoUsuario;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }
}
