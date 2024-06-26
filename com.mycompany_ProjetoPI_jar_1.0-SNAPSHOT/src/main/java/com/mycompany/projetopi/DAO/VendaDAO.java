/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

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
public class VendaDAO {

    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    /**
     * Metodo utilizado para cadastrar uma Venda no banco de dados
     *
     * @param obj Recebe um objeto do tipo Venda
     * @param carrinho Recebe um Array do tipo Produto
     * @return boolean indicando true: sucesso , false: falha
     */
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

            instrucaoSQL.setInt(1, obj.getCliente().getId());
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
                    VendaProduto item = new VendaProduto(p, idVenda, p.getQtd(), p.getQtd() * p.getPreco());
                    lista.add(item);
                }
                instrucaoSQL = conexao.prepareStatement(
                        "INSERT INTO VendaProduto(Id_Venda,Id_Produto,Qtd,Total) VALUES(?,?,?,?);"
                );
                int linhasAfetadas2 = 0;
                for (VendaProduto p : lista) {
                    instrucaoSQL.setInt(1, p.getIdVenda());
                    instrucaoSQL.setInt(2, p.getProduto().getId());
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
    /**
     * Metodo utilizado para listar as Vendas
     *@param inicio Recebe um Date como parametro de busca
     *@param fim Recebe um Date como parametro de busca
     * @return Retorna um Array com todas as venda do periodo
     */
    public static ArrayList<Venda> listar(Date inicio, Date fim) {
        ArrayList<Venda> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT v.*, c.Nome FROM Venda v JOIN Cliente c ON(v.Id_Cliente = c.Id) WHERE Dt BETWEEN ? AND ?;"
            );
            instrucaoSQL.setDate(1, inicio);
            instrucaoSQL.setDate(2, fim);
            rs = instrucaoSQL.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    int idCliente = rs.getInt("Id_Cliente");
                    String nome = rs.getString("Nome");
                    Cliente cliente = new Cliente(idCliente, nome);
                    double total = rs.getDouble("Valor");
                    Date dt = rs.getDate("Dt");
                    Venda item = new Venda(id, cliente, total, dt);
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
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaRetorno;
    }
}
