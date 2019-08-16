package com.aula15_08.ex1;

import java.sql.*;
public class Basico {

    public static void main(String a[]) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            final String URL = "jdbc:mysql://localhost:3306/CLINICA";
            final String USER = "root";
            final String PASSWORD = "root";
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado");
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
