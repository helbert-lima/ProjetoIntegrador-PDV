package com.mycompany.projetopi.classes;

public class Categoria {

    private int Id;
    private String Nome;

    public Categoria() {
    }

    public Categoria(String nome) {
        this.Nome = nome;
    }

    public Categoria(int id, String nome) {
        this.Id = id;
        this.Nome = nome;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }
}
