package com.aula05_09.ex1.bd;

import com.aula05_09.ex1.domain.Consulta;
import com.aula05_09.ex1.domain.Medico;
import com.aula05_09.ex1.domain.Paciente;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConsultaDAO implements DAO<Consulta> {
    
    private Connection con;
    
    @Override
    public void insert(Consulta object) {
        con = Conexao.getConexao();
        
        String query = "INSERT INTO consultas "
                + "values(?,?,?,?)";
        try {
            PreparedStatement ps;
            ps = con.prepareStatement(query);
            ps.setInt(1, object.getMedico().getCodm());
            ps.setInt(2, object.getPaciente().getCodp());
            ps.setDate(3, Date.valueOf(object.getDataC()));
            ps.setTime(4, Time.valueOf(object.getHora()));
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta cadastrada com sucesso!");
            
        } catch (MySQLIntegrityConstraintViolationException ex) {
            JOptionPane.showMessageDialog(null, "A consulta não pôde ser cadastrada, pois, já há uma agendada do " + object.toString());
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void delete(Consulta object) {
        con = Conexao.getConexao();
        
        String query = "DELETE FROM consultas "
                + "WHERE codm = ? AND codp = ? AND dataC = ? AND hora = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, object.getMedico().getCodm());
            ps.setInt(2, object.getPaciente().getCodp());
            ps.setDate(3, Date.valueOf(object.getDataC()));
            ps.setTime(4, Time.valueOf(object.getHora()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta cancelada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void update(Consulta object) {
        con = Conexao.getConexao();
        
        String query = "UPDATE consultas "
                + " SET codm = ?, codp = ?, dataC = ?, hora = ?"
                + " WHERE codm = ?, codp = ?, dataC = ?, hora = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, object.getMedico().getCodm());
            ps.setInt(2, object.getPaciente().getCodp());
            ps.setDate(3, Date.valueOf(object.getDataC()));
            ps.setTime(4, Time.valueOf(object.getHora()));
            
            ps.setInt(5, object.getMedico().getCodm());
            ps.setInt(6, object.getPaciente().getCodp());
            ps.setDate(7, Date.valueOf(object.getDataC()));
            ps.setTime(8, Time.valueOf(object.getHora()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Consulta atualizada com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public List<Consulta> getAll() {
        List<Consulta> consultas = new ArrayList<>();
        con = Conexao.getConexao();
        String query = "SELECT * FROM consultas";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Consulta consulta = new Consulta(new Medico(rs.getInt("codm")), new Paciente(rs.getInt("codp")),
                        rs.getDate("dataC").toLocalDate(), rs.getTime("hora").toLocalTime());
                consultas.add(consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return consultas;
    }
    
    public List<Consulta> getConsultasMedico(Integer codm) {
        List<Consulta> consultas = new ArrayList<>();
        con = Conexao.getConexao();
        String query = "SELECT consultas.codm, consultas.dataC, consultas.hora FROM consultas \n"
                + "JOIN medicos\n"
                + "WHERE medicos.codm = consultas.codm AND consultas.codm = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codm);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Consulta consulta = new Consulta(new Medico(rs.getInt("codm")), null,
                        rs.getDate("dataC").toLocalDate(), rs.getTime("hora").toLocalTime());
                consultas.add(consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return consultas;
    }
    
    public List<Consulta> getConsultasPaciente(Integer codp) {
        List<Consulta> consultas = new ArrayList<>();
        con = Conexao.getConexao();
        String query = "SELECT consultas.codm, consultas.dataC, consultas.hora FROM consultas \n"
                + "JOIN pacientes\n"
                + "WHERE pacientes.codp = consultas.codm AND consultas.codp = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, codp);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Consulta consulta = new Consulta(null, new Paciente(codp),
                        rs.getDate("dataC").toLocalDate(), rs.getTime("hora").toLocalTime());
                consultas.add(consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return consultas;
    }
    
    public static void main(String args[]) {
        ConsultaDAO dao = new ConsultaDAO();
       /* Consulta consulta = new Consulta(new Medico(1), new Paciente(1),
                LocalDate.of(2006, Month.JUNE, 12), LocalTime.of(14, 0));*/
        //dao.insert(consulta);
        //dao.update(new Paciente(5, "Henrique", 22, "Rio de Janeiro", "19897", "CONTINUA SEM NADA"));
        //dao.delete(consulta);
        
        dao.getConsultasMedico(1).stream().forEach(consul -> {
            System.out.println(consul);
        });
    }
    
    @Override
    public List<Consulta> getByNome(String nome) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
