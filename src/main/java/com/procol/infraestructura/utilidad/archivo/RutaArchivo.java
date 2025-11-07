package com.procol.infraestructura.utilidad.archivo;

import org.springframework.stereotype.Component;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Component
@ConfigurationProperties(prefix = "procol.ruta")
public class RutaArchivo {

    private String imagenes;
    private String documentos;
    private String comprimidos;
    private String imagenesVacantes;

    public RutaArchivo() {
    }

    public String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public String getDocumentos() {
        return documentos;
    }

    public void setDocumentos(String documentos) {
        this.documentos = documentos;
    }

    public String getComprimidos() {
        return comprimidos;
    }

    public void setComprimidos(String comprimidos) {
        this.comprimidos = comprimidos;
    }

    public String getImagenesVacantes() {
        return imagenesVacantes;
    }

    public void setImagenesVacantes(String imagenesVacantes) {
        this.imagenesVacantes = imagenesVacantes;
    }

}
