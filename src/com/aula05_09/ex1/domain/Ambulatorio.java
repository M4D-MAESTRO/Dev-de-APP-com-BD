/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.domain;

/**
 *
 * @author 2017102465
 */
public class Ambulatorio {
    private Integer nroa;
    private Integer andar;
    private Integer capacidade;

    public Ambulatorio() {
    }

    public Ambulatorio(Integer nroa, Integer andar, Integer capacidade) {
        this.nroa = nroa;
        this.andar = andar;
        this.capacidade = capacidade;
    }

    public Integer getNroa() {
        return nroa;
    }

    public void setNroa(Integer nroa) {
        this.nroa = nroa;
    }

    public Integer getAndar() {
        return andar;
    }

    public void setAndar(Integer andar) {
        this.andar = andar;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    @Override
    public String toString() {
        return "NROA: " + nroa + "\nAndar: " + andar + "\nCapacidade: " + capacidade;
    }
    
    
    
}
