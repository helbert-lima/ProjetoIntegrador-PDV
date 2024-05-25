
package com.mycompany.projetopi.DAO;

import com.mycompany.projetopi.classes.Categoria;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoriaDAO {
    
    static String URL = "jdbc:mysql://localhost:3306/lojainformatica";
    static String login = "root";
    static String senha = "p4$$w0rd";

    public static boolean salvar(Categoria obj) {
        boolean retorno = false;
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "INSERT INTO Categorias (Nome,Ativo) VALUES (?,1);"
            );
            instrucaoSQL.setString(1, obj.getNome());
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
        } catch (Exception e) {
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
    public static boolean alterar(Categoria obj) {
        boolean retorno = false;
        Connection conexao = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);

            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "UPDATE Categorias SET Nome = ?,Ativo = ? WHERE Id = ?"
            );
            instrucaoSQL.setString(1, obj.getNome());
            instrucaoSQL.setInt(2, obj.getAtivo());
            instrucaoSQL.setInt(3, obj.getId());
            
            int linhasAfetadas = instrucaoSQL.executeUpdate();
            if (linhasAfetadas > 0) {
                retorno = true;
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado");
        } catch (Exception e) {
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return retorno;
    }
   
    public static ArrayList<Categoria> listar(int index, String busca) {
        ArrayList<Categoria> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                    "SELECT * FROM Categorias WHERE Ativo = 1"
            );
            if (index == 1) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT * FROM Categorias WHERE NOME LIKE ? AND Ativo = 1"
                );
                instrucaoSQL.setString(1, "%" + busca + "%");
            }
            rs = instrucaoSQL.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("Id");
                    String nome = rs.getString("Nome");

                    Categoria item = new Categoria(id, nome);

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
                    Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        return listaRetorno;
    }
    public static ArrayList<Categoria> listarN(int index, String busca, int idM) {
        ArrayList<Categoria> listaRetorno = new ArrayList<>();
        Connection conexao = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, login, senha);
            PreparedStatement instrucaoSQL;
            if (index == 1) {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT * FROM Categorias WHERE NOME LIKE ? AND Ativo = 1"
                );
                instrucaoSQL.setString(1, "%" + busca + "%");
            } else {
                instrucaoSQL = conexao.prepareStatement(
                        "SELECT * FROM Categorias WHERE Ativo = 1 OR Id = ?"
                );
                instrucaoSQL.setInt(1, idM);
            }
            rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String nome = rs.getString("Nome");
                Categoria item = new Categoria(id, nome);
                listaRetorno.add(item);
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
        }
        return listaRetorno;
    }
}
