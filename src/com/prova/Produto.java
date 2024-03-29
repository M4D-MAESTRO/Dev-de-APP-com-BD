package com.prova;

public class Produto {

    private Integer id;
    private String descricao;
    private Double valor;
    private Integer quantidade;

    public Produto() {
    }

    public Produto(String descricao, Double valor, Integer quantidade) {
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Produto(Integer id, String descricao, Double valor, Integer quantidade) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\n"
                + "Descrição: " + descricao + "\n"
                + "Valor: R$" + valor + " - Quantidade: " + quantidade + "\n"
                + "Total: R$" + (valor * quantidade) + "\n\n";
    }
}
