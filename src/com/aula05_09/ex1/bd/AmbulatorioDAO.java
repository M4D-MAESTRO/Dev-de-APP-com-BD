/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.bd;

import com.aula05_09.ex1.domain.Ambulatorio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author 2017102465
 */
public class AmbulatorioDAO implements DAO<Ambulatorio> {

    private Connection con;

    @Override
    public void insert(Ambulatorio obj) {
        con = Conexao.getConexao();
        Integer nroa;

        try {
            PreparedStatement ps = con.prepareStatement("SELECT count(nroa) FROM ambulatorios;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            nroa = rs.getInt(1) + 1;
            String query = "INSERT INTO ambulatorios VALUES(" + nroa + ",?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, obj.getAndar());
            ps.setInt(2, obj.getCapacidade());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Integer nroa) {
        String query = "DELETE FROM ambulatorios WHERE nroa = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, nroa);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Ambulatorio ambulatorio) {
        String query = "UPDATE ambulatorios "
                + " SET andar = ?, capacidade = ? "
                + " WHERE nroa = ?; ";
        con = Conexao.getConexao();

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, ambulatorio.getAndar());
            ps.setInt(2, ambulatorio.getCapacidade());
            ps.setInt(3, ambulatorio.getNroa());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Ambulatorio> getAll() {
        List<Ambulatorio> ambulatorios = new ArrayList<>();
        PreparedStatement ps;
        String query = "SELECT * FROM ambulatorios";

        con = Conexao.getConexao();
        try {
            ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ambulatorios.add(new Ambulatorio(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AmbulatorioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ambulatorios;
    }

    public static void main(String a[]) {
        AmbulatorioDAO dao = new AmbulatorioDAO();
        dao.update(new Ambulatorio(9, 5, 5));
    }
}
