
package com.aula05_09.ex1.domain;

public class Medico {
    
    private Integer codm;
    private String nome;
    private Integer idade;
    private String especialidade;
    private String cpf;
    private String cidade;
    private Ambulatorio ambulatorio;

    public Medico() {
    }

    public Medico(Integer codm) {
        this.codm = codm;
    }
    
    

    public Medico(Integer codm, String nome, Integer idade, String especialidade, String cpf, String cidade, Ambulatorio ambulatorio) {
        this.codm = codm;
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.cpf = cpf;
        this.cidade = cidade;
        this.ambulatorio = ambulatorio;
    }
    
    public Medico(String nome, Integer idade, String especialidade, String cpf, String cidade, Ambulatorio ambulatorio) {
        this.nome = nome;
        this.idade = idade;
        this.especialidade = especialidade;
        this.cpf = cpf;
        this.cidade = cidade;
        this.ambulatorio = ambulatorio;
    }
    
    

    public Integer getCodm() {
        return codm;
    }

    public void setCodm(Integer codm) {
        this.codm = codm;
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

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Ambulatorio getAmbulatorio() {
        return ambulatorio;
    }

    public void setAmbulatorio(Ambulatorio ambulatorio) {
        this.ambulatorio = ambulatorio;
    }

    @Override
    public String toString() {
        return "CODM: " + codm 
                + "\nNome: " + nome + " Idade: " + idade + "\nEspecialidade: " + especialidade + "\tCPF: " + cpf
                +"\nCidade: " + cidade + "\n"
                + "Ambulatório: " + ambulatorio;
    }
    
    
    
}
