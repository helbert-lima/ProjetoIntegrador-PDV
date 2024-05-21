/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

import com.mycompany.projetopi.classes.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo
 */
public class ClienteDAO {

    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    public static boolean salvar(Cliente obj) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Cliente (Nome, Email, DtNasc,CPF,Telefone,Sexo,Ativo) VALUES (?,?,?,?,?,?,1);"
            );

            instrucaoSQL.setString(1, obj.getNome());
            instrucaoSQL.setString(2, obj.getEmail());
            instrucaoSQL.setDate(3, obj.getDtNasc());
            instrucaoSQL.setString(4, obj.getCpf());
            instrucaoSQL.setString(5, obj.getTel());
            instrucaoSQL.setInt(6, obj.getSexo());

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }
    
    public static boolean alterar(Cliente obj) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "UPDATE Cliente SET Nome = ?, Email = ?, DtNasc = ?, CPF = ? , Telefone = ? , Sexo = ?, Ativo = ? WHERE Id = ?;"
            );

            instrucaoSQL.setString(1, obj.getNome());
            instrucaoSQL.setString(2, obj.getEmail());
            instrucaoSQL.setDate(3, obj.getDtNasc());
            instrucaoSQL.setString(4, obj.getCpf());
            instrucaoSQL.setString(5, obj.getTel());
            instrucaoSQL.setInt(6, obj.getSexo());
            instrucaoSQL.setInt(7, obj.getAtivo());
            instrucaoSQL.setInt(8, obj.getId());

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    public static ArrayList<Cliente> listar(int index, String busca) {
        ArrayList<Cliente> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT * FROM Cliente WHERE Ativo = 1"
            );
            if (index == 1) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT * FROM Cliente WHERE Nome LIKE ? AND Ativo = 1"
                );
                if (busca.equals("")) {
                    busca = "1";
                }
                instrucaoSQL.setString(1, "%" + busca + "%");
            }
            if (index == 2) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT * FROM Cliente WHERE CPF = ? AND  Ativo = 1"
                );
                instrucaoSQL.setString(1,busca);
            }

            rs = instrucaoSQL.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    int id = rs.getInt("Id");
                    String nome = rs.getString("Nome");
                    String email = rs.getString("Email");
                    Date dtNasc = rs.getDate("DtNasc");
                    StringBuilder cpfBuilder = new StringBuilder(rs.getString("CPF"));
                    cpfBuilder.insert(3, '.').insert(7, '.').insert(11, '-');
                    String cpf = cpfBuilder.toString();
                    String tel = rs.getString("Telefone");
                    int sexo = rs.getInt("Sexo");
                    int ativo = rs.getInt("Ativo");

                    Cliente item = new Cliente(id, nome, email, dtNasc, cpf, tel, sexo, ativo);
                    listaRetorno.add(item);
                }

            }

        } catch (Exception e) {
            listaRetorno = null;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listaRetorno;
    }

}
