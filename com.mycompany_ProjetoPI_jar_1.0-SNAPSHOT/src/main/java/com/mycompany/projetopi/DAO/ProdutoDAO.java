/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projetopi.DAO;

import com.mycompany.projetopi.classes.Categoria;
import com.mycompany.projetopi.classes.Marca;
import com.mycompany.projetopi.classes.Produto;
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
public class ProdutoDAO {

    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    /**
     * Metodo utilizado para cadastrar um produto no banco de dados
     *
     * @param obj Recebe um objeto do tipo Produto
     * @return boolean indicando true: sucesso , false: falha
     */
    public static boolean salvar(Produto obj) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Produto (id_Marca, Id_Categoria, Produto,Preco,Qtd,Ativo) VALUES (?,?,?,?,?,1);"
            );

            instrucaoSQL.setInt(1, obj.getMarca().getId());
            instrucaoSQL.setInt(2, obj.getCategoria().getId());
            instrucaoSQL.setString(3, obj.getProduto());
            instrucaoSQL.setDouble(4, obj.getPreco());
            instrucaoSQL.setInt(5, obj.getQtd());

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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    /**
     * Metodo utilizado para atualizar a quantidade dos produtos após realizar
     * uma venda
     *
     * @param listo Recebe um ArrayList do tipo Produto
     * @return boolean indicando true: sucesso , false: falha
     */
    public static boolean atualizarQtd(ArrayList<Produto> lista) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "UPDATE Produto SET Qtd =  Qtd - ? WHERE Id = ?"
            );
            int linhasAfetadas = 0;
            for (Produto p : lista) {
                instrucaoSQL.setInt(1, p.getQtd());
                instrucaoSQL.setInt(2, p.getId());
                linhasAfetadas = instrucaoSQL.executeUpdate();
            }
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }

    /**
     * Metodo utilizado para alterar um produto no banco de dados
     *
     * @param obj Recebe um objeto do tipo Produto
     * @return boolean indicando true: sucesso , false: falha
     */
    public static boolean alterar(Produto obj) {
        boolean retorno = false;
        Connection conexao = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "UPDATE Produto SET Id_Marca = ?, Id_Categoria = ?, Produto = ?, Preco = ?, Qtd = ?,Ativo = ? WHERE Id = ?"
            );

            instrucaoSQL.setInt(1, obj.getMarca().getId());
            instrucaoSQL.setInt(2, obj.getCategoria().getId());
            instrucaoSQL.setString(3, obj.getProduto());
            instrucaoSQL.setDouble(4, obj.getPreco());
            instrucaoSQL.setInt(5, obj.getQtd());
            instrucaoSQL.setInt(6, obj.getAtivo());
            instrucaoSQL.setInt(7, obj.getId());

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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return retorno;
    }
        /**
     * Metodo utilizado para listar os produtos
     *
     * @param index Recebe o index do ComboBox como parametro de pesquisa
     * @param busca Recebe uma string para realizar a pesquisa
     * @return retorna um Array com o resultado da busca
     */
    public static ArrayList<Produto> listar(int index, String busca) {
        ArrayList<Produto> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT p.*, c.Nome AS Categoria, m.Nome AS Marca FROM Produto p JOIN Categorias c ON p.Id_Categoria = c.Id JOIN Marca m ON p.Id_Marca = m.Id WHERE p.Ativo = 1"
            );
            if (index == 1) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT p.*, c.Nome AS Categoria, m.Nome AS Marca FROM Produto p JOIN Categorias c ON p.Id_Categoria = c.Id JOIN Marca m ON p.Id_Marca = m.Id WHERE p.Ativo = 1 AND p.Id = ?"
                );
                instrucaoSQL.setString(1, busca);
            }
            if (index == 2) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT p.*, c.Nome AS Categoria, m.Nome AS Marca FROM Produto p JOIN Categorias c ON p.Id_Categoria = c.Id JOIN Marca m ON p.Id_Marca = m.Id WHERE p.Ativo = 1 AND p.Produto LIKE ?"
                );
                if (busca.equals("")) {
                    busca = ";;;";
                }
                instrucaoSQL.setString(1, "%" + busca + "%");
            }
            if (index == 3) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT p.*, c.Nome AS Categoria, m.Nome AS Marca FROM Produto p JOIN Categorias c ON p.Id_Categoria = c.Id JOIN Marca m ON p.Id_Marca = m.Id WHERE p.Ativo = 1 AND m.Nome LIKE ?"
                );
                if (busca.equals("")) {
                    busca = ";;;";
                }
                instrucaoSQL.setString(1, "%" + busca + "%");
            }
            if (index == 4) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT p.*, c.Nome AS Categoria, m.Nome AS Marca FROM Produto p JOIN Categorias c ON p.Id_Categoria = c.Id JOIN Marca m ON p.Id_Marca = m.Id WHERE p.Ativo = 1 AND c.Nome LIKE ?"
                );
                if (busca.equals("")) {
                    busca = ";;;";
                }
                instrucaoSQL.setString(1, "%" + busca + "%");
            }

            rs = instrucaoSQL.executeQuery();
            if (rs != null) {
                while (rs.next()) {

                    int id = rs.getInt("Id");
                    String produto = rs.getString("Produto");
                    double preco = rs.getInt("Preco");
                    int qtd = rs.getInt("Qtd");
                    Marca marca = new Marca(rs.getInt("Id_Marca"), rs.getString("Marca"));
                    Categoria categoria = new Categoria(rs.getInt("Id_Categoria"), rs.getString("Categoria"));
                    int ativo = rs.getInt("Ativo");
                    Produto item = new Produto(id, marca, categoria, produto, preco, qtd, ativo);
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listaRetorno;
    }
}
