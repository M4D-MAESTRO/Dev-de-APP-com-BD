/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author ALUNO
 */
public class Consulta {

    private Medico medico;
    private Paciente paciente;
    private LocalDate dataC;
    private LocalTime hora;

    DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter horaFormat = DateTimeFormatter.ofPattern("HH:mm");

    public Consulta() {
    }

    public Consulta(Medico medico, Paciente paciente, LocalDate dataC, LocalTime hora) {
        this.medico = medico;
        this.paciente = paciente;
        this.dataC = dataC;
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDate getDataC() {
        return dataC;
    }

    public void setDataC(LocalDate dataC) {
        this.dataC = dataC;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.medico);
        hash = 89 * hash + Objects.hashCode(this.paciente);
        hash = 89 * hash + Objects.hashCode(this.dataC);
        hash = 89 * hash + Objects.hashCode(this.hora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        if (!Objects.equals(this.dataC, other.dataC)) {
            return false;
        }
        if (!Objects.equals(this.hora, other.hora)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Médico: " + medico + "\nNo paciente " + paciente
                + "\nNo dia: " + dataC.format(dataFormat) + " às " + hora.format(horaFormat) + "\n\n";
    }

    public static void main(String[] args) {

        Consulta cons = new Consulta(null, null, LocalDate.of(2018, Month.MARCH, 4), LocalTime.of(17, 20));
        System.out.println(cons.toString());
    }

}
