/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.classes;

import java.sql.Date;

/**
 *
 * @author Pichau
 */
public class Cliente {

    
    private int Id;
    private String Nome;
    private String Email;
    private Date DtNasc;
    private String Cpf;
    private String Tel;
    private int Sexo;
    private int Ativo;

    public int getAtivo() {
        return Ativo;
    }

    public void setAtivo(int Ativo) {
        this.Ativo = Ativo;
    }

    public Cliente() {
    }

    public Cliente(int Id, String Nome, String Email, Date DtNasc, String Cpf, String Tel, int Sexo, int Ativo) {
        this.Id = Id;
        this.Nome = Nome;
        this.Email = Email;
        this.DtNasc = DtNasc;
        this.Cpf = Cpf;
        this.Tel = Tel;
        this.Sexo = Sexo;
        this.Ativo = Ativo;
    }

    public Cliente(String Nome, String Email, Date DtNasc, String Cpf, String Tel, int Sexo, int Ativo) {
        this.Nome = Nome;
        this.Email = Email;
        this.DtNasc = DtNasc;
        this.Cpf = Cpf;
        this.Tel = Tel;
        this.Sexo = Sexo;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getDtNasc() {
        return DtNasc;
    }

    public void setDtNasc(Date DtNasc) {
        this.DtNasc = DtNasc;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String Cpf) {
        this.Cpf = Cpf;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String Tel) {
        this.Tel = Tel;
    }

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int Sexo) {
        this.Sexo = Sexo;
    }
}
