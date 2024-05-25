/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

import static com.mycompany.projetopi.DAO.VendaDAO.URL;
import static com.mycompany.projetopi.DAO.VendaDAO.login;
import static com.mycompany.projetopi.DAO.VendaDAO.senha;
import com.mycompany.projetopi.classes.Cliente;
import com.mycompany.projetopi.classes.Produto;
import com.mycompany.projetopi.classes.Venda;
import com.mycompany.projetopi.classes.VendaProduto;
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
public class VendaProdutoDAO {

    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    public static ArrayList<VendaProduto> listar(ArrayList<Integer> ids) {
        ArrayList<VendaProduto> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        PreparedStatement instrucaoSQL = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            instrucaoSQL = conexao.prepareStatement(
                    "SELECT v.*, p.Produto FROM VendaProduto v JOIN Produto p ON(v.Id_Produto = p.Id) WHERE Id_Venda = ?;"
            );
            for (int i : ids) {
                instrucaoSQL.setInt(1, i);
                rs = instrucaoSQL.executeQuery();
                while (rs.next()) {
                    int idProduto = rs.getInt("Id_Produto");
                    String nomeProduto = rs.getString("Produto");
                    Produto produto = new Produto(idProduto, nomeProduto);
                    int qtd = rs.getInt("Qtd");
                    double total = rs.getDouble("Total");
                    VendaProduto item = new VendaProduto(idProduto, produto, qtd, total);
                    boolean existe = false;
                    for (VendaProduto v : listaRetorno) {
                        if (v.getProduto().getId() == idProduto) {
                            existe = true;
                            v.setQtd(v.getQtd() + qtd);
                            v.setTotal(v.getTotal() + total);
                            break;
                        }
                    }
                    if (!existe) {
                        listaRetorno.add(item);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (instrucaoSQL != null) {
                try {
                    instrucaoSQL.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return listaRetorno;
    }
}
