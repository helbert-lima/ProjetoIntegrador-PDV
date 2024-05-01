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
    private String Produto;
    private double Preco;
    private int qtd;

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
