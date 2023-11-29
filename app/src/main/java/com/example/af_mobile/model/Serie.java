package com.example.af_mobile.model;

public class Serie {

    private String titulo, plataforma,diasemana,epAssistido,assistir,temporada;

    public Serie(String titulo, String plataforma, String diasemana, String epAssistido, String assistir, String temporada) {
        this.titulo = titulo;
        this.plataforma = plataforma;
        this.diasemana = diasemana;
        this.epAssistido = epAssistido;
        this.assistir = assistir;
        this.temporada = temporada;
    }

    public Serie() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public String getEpAssistido() {
        return epAssistido;
    }

    public void setEpAssistido(String epAssistido) {
        this.epAssistido = epAssistido;
    }

    public String getAssistir() {
        return assistir;
    }

    public void setAssistir(String assistir) {
        this.assistir = assistir;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
}
