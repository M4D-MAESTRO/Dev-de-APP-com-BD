package com.prova;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class app {

    private static final String INSTANCE = "prova";
    private static final String URL = "jdbc:mysql://localhost:3306/" + INSTANCE;
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static Connection con = null;

    public static Connection getConexao() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Classe JDBC para conexão com o Banco de Dados não encontrado: \n" + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: \n" + ex.getMessage());
        }
        return con;
    }

    public static void insert(Produto p) {
        con = getConexao();
        String query = "INSERT INTO produtos(descricao, valor, quantidade) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getDescricao());
            ps.setDouble(2, p.getValor());
            ps.setInt(3, p.getQuantidade());
            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.first();
                JOptionPane.showMessageDialog(null, p.getDescricao() + " inserido com sucesso!\n"
                        + "ID do Produto: " + rs.getInt(1));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir o produto: \n" + ex.getMessage());
        }
    }

    public static List<Produto> getAll() {
        List<Produto> produtos = new ArrayList<>();
        con = getConexao();
        String query = "SELECT * FROM produtos";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                produtos.add(new Produto(rs.getInt("id"), rs.getString("descricao"),
                        rs.getDouble("valor"), rs.getInt("quantidade")));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao listar os produtos: \n" + ex.getMessage());
        }

        return produtos;
    }

    /*APP*/
    public static void main(String a[]) {
        /* Produto p1 = new Produto("Teste 1", 120.0, 5);
         Produto p2 = new Produto("Teste 2", 30.0, 2);
        Produto p3 = new Produto("Teste 3", 50.0, 10);
        Produto p4 = new Produto("Teste 4", 1000.0, 1);

        insert(p1);
        insert(p2);
        insert(p3);
        insert(p4);*/

        getAll().forEach(produto -> {
            System.out.println(produto + "-----------------------------------");
        });
    }
}
