package com.aula05_09.ex1.bd;

import com.aula05_09.ex1.domain.Ambulatorio;
import com.aula05_09.ex1.domain.Medico;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MedicoDAO implements DAO<Medico> {

    private Connection con;

    private Integer getId() {
        con = Conexao.getConexao();
        Integer codm = -1;

        try {
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT count(codm) FROM medicos;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            codm = rs.getInt(1) + 1;

        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codm;
    }

    @Override
    public void insert(Medico object) {
        con = Conexao.getConexao();

        String query = "INSERT INTO medicos "
                + "values(?,?,?,?,?,?,?)";

        try {
            object.setCodm(getId());
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, object.getCodm());
            ps.setString(2, object.getNome());
            ps.setInt(3, object.getIdade());
            ps.setString(4, object.getEspecialidade());
            ps.setString(5, object.getCpf());
            ps.setString(6, object.getCidade());

            if (object.getAmbulatorio() != null) {
                ps.setInt(7, object.getAmbulatorio().getNroa());
            }else{
                ps.setString(7, null);
            }

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Médico cadastrado com sucesso!");

        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "O CPF " + object.getCpf() +" já cadastrado!");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Medico object) {
        con = Conexao.getConexao();

        String query = "DELETE FROM medicos "
                + "WHERE codm = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, object.getCodm());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Médico deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Medico object) {
        con = Conexao.getConexao();

        String query = "UPDATE medicos "
                + " SET nome = ?, idade = ?, especialidade = ?, cpf = ?, cidade = ?, nroa = ?"
                + " WHERE codm = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, object.getNome());
            ps.setInt(2, object.getIdade());
            ps.setString(3, object.getEspecialidade());
            ps.setString(4, object.getCpf());
            ps.setString(5, object.getCidade());
            ps.setInt(6, object.getAmbulatorio().getNroa());
            ps.setInt(7, object.getCodm());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Médico atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Medico> getAll() {
        List<Medico> medicos = new ArrayList<>();
        con = Conexao.getConexao();
        String query = "SELECT * FROM medicos";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Ambulatorio ambulatorio = new Ambulatorio(rs.getInt("nroa"), null, null);
                Medico medico = new Medico(rs.getInt("codm"), rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("especialidade"), rs.getString("cpf"), rs.getString("cidade"), ambulatorio);
                medicos.add(medico);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return medicos;
    }

    public static void main(String args[]) {
        // MedicoDAO dao = new MedicoDAO();
        // Medico medico = new Medico("Henrique", 22, "DEV", "158745", "Rio de Janeiro", new Ambulatorio(1, null, null));
        //dao.insert(medico);
        // dao.update(new Medico(6,"Henrique de Castro", 25, "Senior Developer", "158745", "Rio de Janeiro", new Ambulatorio(1, null, null)));
        /*dao.getAll().stream().forEach(medico1 -> {
            System.out.println(medico1);
        });*/

 /*dao.update(new Medico(5, "Henrique", 18, "", "789789", "Rio de Janeiro", new Ambulatorio(1, null, null)));*/
        // dao.delete(6);
    }

}
