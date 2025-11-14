package com.procol.perfil.dto;

public class PalabraClaveDTO {
    private Integer idPalabraClave;
    private String textoPalabraClave;

    public PalabraClaveDTO() {
    }

    public PalabraClaveDTO(Integer idPalabraClave, String textoPalabraClave) {
        this.idPalabraClave = idPalabraClave;
        this.textoPalabraClave = textoPalabraClave;
    }

    public Integer getIdPalabraClave() { return idPalabraClave; }

    public void setIdPalabraClave(Integer idPalabraClave) { this.idPalabraClave = idPalabraClave; }

    public String getTextoPalabraClave() { return textoPalabraClave; }

    public void setTextoPalabraClave(String textoPalabraClave) { this.textoPalabraClave = textoPalabraClave; }

    @Override
    public String toString() {
        return "PalabraClaveDTO{" +
                "idPalabraClave=" + idPalabraClave +
                ", nombre='" + textoPalabraClave + '\'' +
                '}';
    }
}
