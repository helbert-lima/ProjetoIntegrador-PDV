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
    private int IdCliente;
    private double Valor;
    private Date Data;

    public Venda(int Id, int IdCliente, double Valor, Date Data) {
        this.Id = Id;
        this.IdCliente = IdCliente;
        this.Valor = Valor;
        this.Data = Data;
    }

    public Venda(int IdCliente, double Valor, Date Data) {
        this.IdCliente = IdCliente;
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

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
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
