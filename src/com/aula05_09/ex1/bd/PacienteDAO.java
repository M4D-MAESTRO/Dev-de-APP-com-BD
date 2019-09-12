package com.aula05_09.ex1.bd;

import com.aula05_09.ex1.domain.Paciente;
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

public class PacienteDAO implements DAO<Paciente> {

    private Connection con;

    private Integer getId() {
        con = Conexao.getConexao();
        Integer codp = -1;

        try {
            PreparedStatement ps;
            ps = con.prepareStatement("SELECT count(codp) FROM pacientes;");
            ResultSet rs = ps.executeQuery();
            rs.next();
            codp = rs.getInt(1) + 1;

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codp;
    }

    @Override
    public void insert(Paciente object) {
        con = Conexao.getConexao();

        String query = "INSERT INTO pacientes "
                + "values(?,?,?,?,?,?)";

        try {
            object.setCodp(getId());
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, object.getCodp());
            ps.setString(2, object.getNome());
            ps.setInt(3, object.getIdade());
            ps.setString(4, object.getCidade());
            ps.setString(5, object.getCpf());
            ps.setString(6, object.getDoenca());

            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente cadastrado com sucesso!");

        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "O CPF " + object.getCpf() + " já cadastrado!");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Paciente object) {
        con = Conexao.getConexao();

        String query = "DELETE FROM pacientes "
                + "WHERE codp = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, object.getCodp());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente deletado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void update(Paciente object) {
        con = Conexao.getConexao();

        String query = "UPDATE pacientes "
                + " SET nome = ?, idade = ?, doenca = ?, cpf = ?, cidade = ?"
                + " WHERE codp = ?";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, object.getNome());
            ps.setInt(2, object.getIdade());
            ps.setString(3, object.getDoenca());
            ps.setString(4, object.getCpf());
            ps.setString(5, object.getCidade());
            ps.setInt(6, object.getCodp());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Paciente atualizado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Paciente> getAll() {
        List<Paciente> pacientes = new ArrayList<>();
        con = Conexao.getConexao();
        String query = "SELECT * FROM pacientes";

        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Paciente paciente = new Paciente(rs.getInt("codp"), rs.getString("nome"), rs.getInt("idade"),
                        rs.getString("cidade"), rs.getString("cpf"), rs.getString("doenca"));
                pacientes.add(paciente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return pacientes;
    }

    public static void main(String args[]) {
        PacienteDAO dao = new PacienteDAO();
        Paciente paciente = new Paciente("Henrique", 18, "Rio de Janeiro", "19897", "NADA");
        //dao.insert(paciente);
        //dao.update(new Paciente(5, "Henrique", 22, "Rio de Janeiro", "19897", "CONTINUA SEM NADA"));
        //dao.delete(5);

        /*dao.getAll().stream().forEach(medico1 -> {
            System.out.println(medico1);
        });*/
    }

}
