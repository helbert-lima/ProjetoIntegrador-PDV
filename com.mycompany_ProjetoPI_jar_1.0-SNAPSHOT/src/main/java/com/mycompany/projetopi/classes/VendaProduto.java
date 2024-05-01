/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.classes;

/**
 *
 * @author Gustavo
 */
public class VendaProduto {

    private int Id;
    private int IdProduto;
    private int IdVenda;
    private int Qtd;
    private double Total;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getIdProduto() {
        return IdProduto;
    }

    public void setIdProduto(int IdProduto) {
        this.IdProduto = IdProduto;
    }

    public int getIdVenda() {
        return IdVenda;
    }

    public void setIdVenda(int IdVenda) {
        this.IdVenda = IdVenda;
    }

    public int getQtd() {
        return Qtd;
    }

    public void setQtd(int Qtd) {
        this.Qtd = Qtd;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

}
