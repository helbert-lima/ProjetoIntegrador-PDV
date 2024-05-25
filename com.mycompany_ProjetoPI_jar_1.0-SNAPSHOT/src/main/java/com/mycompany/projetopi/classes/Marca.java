package com.mycompany.projetopi.classes;

public class Marca {

    private int Id;
    private String Nome;
    private int Ativo;

    public Marca() {
    }

    public Marca(String Nome) {
        this.Nome = Nome;
    }

    public Marca(int id, String nome, int ativo) {
        this.Id = id;
        this.Nome = nome;
        this.Ativo = ativo;
    }

    public Marca(int Id, String Nome) {
        this.Id = Id;
        this.Nome = Nome;
    }

    public int getAtivo() {
        return Ativo;
    }

    public void setAtivo(int Ativo) {
        this.Ativo = Ativo;
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

    @Override
    public String toString() {
        return Nome;
    }
}
