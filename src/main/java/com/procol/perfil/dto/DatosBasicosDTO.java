package com.procol.perfil.dto;

public class DatosBasicosDTO {

    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String documentoUsuario;
    private String profesion;
    private String ubicacion;
    private Integer idUbicacion;

    public DatosBasicosDTO() {}

    public DatosBasicosDTO(
            Integer idUsuario,
            String nombresUsuario,
            String apellidosUsuario,
            String documentoUsuario,
            String profesion,
            String ubicacion,
            Integer idUbicacion
    ) {
        this.idUsuario = idUsuario;
        this.nombresUsuario = nombresUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.documentoUsuario = documentoUsuario;
        this.profesion = profesion;
        this.ubicacion = ubicacion;
        this.idUbicacion = idUbicacion;
    }

    // Getters y Setters

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

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(Integer idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    @Override
    public String toString() {
        return "DatosBasicosDTO{" +
                "idUsuario=" + idUsuario +
                ", nombresUsuario='" + nombresUsuario + '\'' +
                ", apellidosUsuario='" + apellidosUsuario + '\'' +
                ", documentoUsuario='" + documentoUsuario + '\'' +
                ", profesion='" + profesion + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", idUbicacion=" + idUbicacion +
                '}';
    }
}