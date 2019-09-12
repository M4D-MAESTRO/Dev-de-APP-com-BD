/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.domain;

import java.util.Objects;

/**
 *
 * @author ALUNO
 */
public class Paciente {

    private Integer codp;
    private String nome;
    private Integer idade;
    private String cidade;
    private String cpf;
    private String doenca;

    public Paciente() {
    }

    public Paciente(Integer codp) {
        this.codp = codp;
    }

    public Paciente(String nome, Integer idade, String cidade, String cpf, String doenca) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.cpf = cpf;
        this.doenca = doenca;
    }

    public Paciente(Integer codp, String nome, Integer idade, String cidade, String cpf, String doenca) {
        this.codp = codp;
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.cpf = cpf;
        this.doenca = doenca;
    }

    public Integer getCodp() {
        return codp;
    }

    public void setCodp(Integer codp) {
        this.codp = codp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDoenca() {
        return doenca;
    }

    public void setDoenca(String doenca) {
        this.doenca = doenca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.codp);
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
        final Paciente other = (Paciente) obj;
        if (!Objects.equals(this.codp, other.codp)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CODP: " + codp + " - " + nome + "\nIdade: " + idade
                + "\nCPF: " + cpf + "\nCidade: " + cidade + "\nDoença: " + doenca + "\n\n";
    }

}
