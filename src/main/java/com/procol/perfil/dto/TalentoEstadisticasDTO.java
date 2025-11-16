package com.procol.perfil.dto;

public class TalentoEstadisticasDTO {

    private Long totalTalentos;
    private Long totalCompetencias;
    private Long totalHabilidades;
    private Long nivelBasico;
    private Long nivelIntermedio;
    private Long nivelAvanzado;

    public TalentoEstadisticasDTO() {
    }

    public TalentoEstadisticasDTO(Long totalTalentos, Long totalCompetencias, Long totalHabilidades,
                                  Long nivelBasico, Long nivelIntermedio, Long nivelAvanzado) {
        this.totalTalentos = totalTalentos;
        this.totalCompetencias = totalCompetencias;
        this.totalHabilidades = totalHabilidades;
        this.nivelBasico = nivelBasico;
        this.nivelIntermedio = nivelIntermedio;
        this.nivelAvanzado = nivelAvanzado;
    }

    public Long getTotalTalentos() {
        return totalTalentos;
    }

    public void setTotalTalentos(Long totalTalentos) {
        this.totalTalentos = totalTalentos;
    }

    public Long getTotalCompetencias() {
        return totalCompetencias;
    }

    public void setTotalCompetencias(Long totalCompetencias) {
        this.totalCompetencias = totalCompetencias;
    }

    public Long getTotalHabilidades() {
        return totalHabilidades;
    }

    public void setTotalHabilidades(Long totalHabilidades) {
        this.totalHabilidades = totalHabilidades;
    }

    public Long getNivelBasico() {
        return nivelBasico;
    }

    public void setNivelBasico(Long nivelBasico) {
        this.nivelBasico = nivelBasico;
    }

    public Long getNivelIntermedio() {
        return nivelIntermedio;
    }

    public void setNivelIntermedio(Long nivelIntermedio) {
        this.nivelIntermedio = nivelIntermedio;
    }

    public Long getNivelAvanzado() {
        return nivelAvanzado;
    }

    public void setNivelAvanzado(Long nivelAvanzado) {
        this.nivelAvanzado = nivelAvanzado;
    }
}