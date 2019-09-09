/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula22_08.ex1;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String a[]) {
        Connection con = Conexao.getConexao();
        try {
            listarAmbulatorios(con);
            inserirAmbulatorio(con);
        } catch (MySQLIntegrityConstraintViolationException ex) {
            System.out.println("Ambulatório já cadastrado");
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void listarMedicos(Connection con) throws SQLException {
        String query = "SELECT * FROM medicos";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(query);

        System.out.println("CODM - NOME - IDADE - ESPECIALIDADE - CPF - CIDADE - NROA");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " - " + rs.getString(2) + " - " + rs.getInt(3) + " - " + rs.getString(4) + " - " + rs.getString(5) + " - " + rs.getString(6) + " - " + rs.getInt(7));
        }
    }

    public static void listarAmbulatorios(Connection con) throws SQLException {
        String query = "SELECT * FROM ambulatorios";
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(query);

        System.out.println("NROA - ANDAR - CAPACIDADE ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " - " + rs.getInt(2) + " - " + rs.getInt(3));
        }
    }

    public static void inserirAmbulatorio(Connection con) throws SQLException {
        String query = "INSERT INTO ambulatorios VALUES(6,3,50)";
        Statement s = con.createStatement();

        if (s.execute(query)) {
            System.out.println("Inserido com sucesso!");
        }
    }

}
