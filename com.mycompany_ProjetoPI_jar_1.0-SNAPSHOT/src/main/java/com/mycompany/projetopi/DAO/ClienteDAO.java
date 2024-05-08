/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

import com.mycompany.projetopi.classes.Cliente;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
            conexao = DriverManager.getConnection(URL,login,senha);
            
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Cliente (Nome, Email, DtNasc,CPF,Telefone,Sexo) VALUES (?,?,?,?,?,?);"
            );
            
            instrucaoSQL.setString(1, obj.getNome());
            instrucaoSQL.setString(2,obj.getEmail());
            instrucaoSQL.setDate(3, obj.getDtNasc());
            instrucaoSQL.setString(4,obj.getCpf());
            instrucaoSQL.setString(5,obj.getTel());
            instrucaoSQL.setInt(6,obj.getSexo());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas>0) {
                retorno = true;
            }
            
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conexao!=null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }
}
