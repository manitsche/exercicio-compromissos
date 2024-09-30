package com.manitsche.exerciciocompromissos.modelo;

import java.io.Serializable;

public class Compromisso implements Serializable {

    private String titulo;
    private String data;
    private String hora;
    private String local;
    private String diaSemana;
    private String descricao;

    public Compromisso(String titulo, String data, String hora, String local, String diaSemana, String descricao) {
        this.titulo = titulo;
        this.data = data;
        this.hora = hora;
        this.local = local;
        this.diaSemana = diaSemana;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getData() {
        return data;
    }

    public String getHora() {
        return hora;
    }

    public String getLocal() {
        return local;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return titulo + " - " + data + " " + hora;  // Exibe título, data e hora no ListView
    }
}