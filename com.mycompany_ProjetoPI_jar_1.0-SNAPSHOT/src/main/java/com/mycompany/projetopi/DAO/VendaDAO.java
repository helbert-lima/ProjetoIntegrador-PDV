/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

import com.mycompany.projetopi.classes.Produto;
import com.mycompany.projetopi.classes.Venda;
import com.mycompany.projetopi.classes.VendaProduto;
import java.sql.Connection;
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
public class VendaDAO {

    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    public static boolean salvar(Venda obj, ArrayList<Produto> carrinho) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Venda(Id_Cliente,Valor,Dt) VALUES(?,?,?);",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );

            instrucaoSQL.setInt(1, obj.getIdCliente());
            instrucaoSQL.setDouble(2, obj.getValor());
            instrucaoSQL.setDate(3, obj.getData());

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = instrucaoSQL.getGeneratedKeys();
                int idVenda = 0;
                if (rs.next()) {
                    idVenda = rs.getInt(1);
                }

                ArrayList<VendaProduto> lista = new ArrayList<>();
                for (Produto p : carrinho) {
                    VendaProduto item = new VendaProduto(p.getId(), idVenda, p.getQtd(), p.getQtd() * p.getPreco());
                    lista.add(item);
                }
                instrucaoSQL = conexao.prepareStatement(
                        "INSERT INTO VendaProduto(Id_Venda,Id_Produto,Qtd,Total) VALUES(?,?,?,?);"
                );
                int linhasAfetadas2 = 0;
                for (VendaProduto p : lista) {
                    instrucaoSQL.setInt(1, p.getIdVenda());
                    instrucaoSQL.setInt(2, p.getIdProduto());
                    instrucaoSQL.setInt(3, p.getQtd());
                    instrucaoSQL.setDouble(4, p.getTotal());
                    linhasAfetadas2 += instrucaoSQL.executeUpdate();
                }
                if (linhasAfetadas2 > 0) {
                    retorno = true;
                }
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
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }
}
