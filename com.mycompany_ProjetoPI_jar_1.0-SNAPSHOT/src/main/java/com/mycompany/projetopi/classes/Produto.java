/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.classes;

/**
 *
 * @author Pichau
 */
public class Produto {

    private int Id;
    private Marca marca;
    private Categoria categoria;
    private String Produto;
    private double Preco;
    private int qtd;
    private int Ativo;

    public Produto(int Id) {
        this.Id = Id;
    }

    public Produto(int qtd, int Ativo) {
        this.qtd = qtd;
        this.Ativo = Ativo;
    }
    
    @Override
    public String toString() {
        return Produto;
    }
    
    public Produto(int Id, String Produto) {
        this.Id = Id;
        this.Produto = Produto;
    }

    public Produto(int Id, Marca marca, String Produto, double Preco, int qtd) {
        this.Id = Id;
        this.marca = marca;
        this.Produto = Produto;
        this.Preco = Preco;
        this.qtd = qtd;
    }



    public Produto(Marca marca, Categoria categoria, String Produto, double Preco, int qtd) {
        this.marca = marca;
        this.categoria = categoria;
        this.Produto = Produto;
        this.Preco = Preco;
        this.qtd = qtd;
    }

    public Produto(int Id, Marca marca, Categoria categoria, String Produto, double Preco, int qtd, int Ativo) {
        this.Id = Id;
        this.marca = marca;
        this.categoria = categoria;
        this.Produto = Produto;
        this.Preco = Preco;
        this.qtd = qtd;
        this.Ativo = Ativo;
    }

    public Produto() {
    }

    public int getAtivo() {
        return Ativo;
    }

    public void setAtivo(int Ativo) {
        this.Ativo = Ativo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getProduto() {
        return Produto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public void setProduto(String Produto) {
        this.Produto = Produto;
    }

    public double getPreco() {
        return Preco;
    }

    public void setPreco(double Preco) {
        this.Preco = Preco;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}
