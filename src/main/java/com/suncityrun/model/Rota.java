package com.suncityrun.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Rota {

    private String id;

    @NotBlank(message = "O nome da rota é obrigatório")
    private String nome;

    @Positive(message = "A distância deve ser maior que zero")
    private double distancia;

    @NotBlank(message = "A dificuldade é obrigatória")
    private String dificuldade;

    private String terreno;
    private String localizacao;

    // ======== GETTERS E SETTERS (deixa exatamente assim) ========
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getDistancia() { return distancia; }
    public void setDistancia(double distancia) { this.distancia = distancia; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }

    public String getTerreno() { return terreno; }
    public void setTerreno(String terreno) { this.terreno = terreno; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }
}