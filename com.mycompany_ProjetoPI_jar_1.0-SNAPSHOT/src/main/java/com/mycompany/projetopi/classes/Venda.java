/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.classes;

import java.sql.Date;

/**
 *
 * @author Gustavo
 */
public class Venda {

    private int Id;
    private Cliente cliente;
    private double Valor;
    private Date Data;

    public Venda(Cliente cliente, double Valor, Date Data) {
        this.cliente = cliente;
        this.Valor = Valor;
        this.Data = Data;
    }

    public Venda(int Id, Cliente cliente, double Valor, Date Data) {
        this.Id = Id;
        this.cliente = cliente;
        this.Valor = Valor;
        this.Data = Data;
    }
    



    public Venda() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }



    public double getValor() {
        return Valor;
    }

    public void setValor(double Valor) {
        this.Valor = Valor;
    }

    public Date getData() {
        return Data;
    }

    public void setData(Date Data) {
        this.Data = Data;
    }

}
