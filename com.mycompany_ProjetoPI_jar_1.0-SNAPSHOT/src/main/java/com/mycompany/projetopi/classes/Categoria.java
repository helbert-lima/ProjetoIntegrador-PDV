package com.mycompany.projetopi.classes;

public class Categoria {

    private int Id;
    private String Nome;
    private int Ativo;

    public Categoria() {
    }

    public Categoria(String Nome) {
        this.Nome = Nome;
    }

    public Categoria(int Id, String Nome, int Ativo) {
        this.Id = Id;
        this.Nome = Nome;
        this.Ativo = Ativo;
    }

    public Categoria(int Id, String Nome) {
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
