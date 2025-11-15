package com.procol.perfil.dto;

public class ImagenDTO {

    private Integer idImagen;
    private UsuarioDTO idUsuario;
    private String nombrePublicoImagen;
    private String nombrePrivadoImagen;
    private String tipoImagen;
    private String tamanioImagen;
    private Short favoritaImagen;
    private Short categoria;

    public ImagenDTO() {
    }

    public ImagenDTO(
            Integer idImagen, UsuarioDTO idUsuario, String nombrePublicoImagen,
            String nombrePrivadoImagen, String tipoImagen, String tamanioImagen,
            Short favoritaImagen, Short categoria
    ) {
        this.idImagen = idImagen;
        this.idUsuario = idUsuario;
        this.nombrePublicoImagen = nombrePublicoImagen;
        this.nombrePrivadoImagen = nombrePrivadoImagen;
        this.tipoImagen = tipoImagen;
        this.tamanioImagen = tamanioImagen;
        this.favoritaImagen = favoritaImagen;
        this.categoria = categoria;
    }

    public Integer getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(Integer idImagen) {
        this.idImagen = idImagen;
    }

    public UsuarioDTO getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UsuarioDTO idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePublicoImagen() {
        return nombrePublicoImagen;
    }

    public void setNombrePublicoImagen(String nombrePublicoImagen) {
        this.nombrePublicoImagen = nombrePublicoImagen;
    }

    public String getNombrePrivadoImagen() {
        return nombrePrivadoImagen;
    }

    public void setNombrePrivadoImagen(String nombrePrivadoImagen) {
        this.nombrePrivadoImagen = nombrePrivadoImagen;
    }

    public String getTipoImagen() {
        return tipoImagen;
    }

    public void setTipoImagen(String tipoImagen) {
        this.tipoImagen = tipoImagen;
    }

    public String getTamanioImagen() {
        return tamanioImagen;
    }

    public void setTamanioImagen(String tamanioImagen) {
        this.tamanioImagen = tamanioImagen;
    }

    public Short getFavoritaImagen() {
        return favoritaImagen;
    }

    public void setFavoritaImagen(Short favoritaImagen) {
        this.favoritaImagen = favoritaImagen;
    }

    public Short getCategoria() {
        return categoria;
    }

    public void setCategoria(Short categoria) {
        this.categoria = categoria;
    }
}
