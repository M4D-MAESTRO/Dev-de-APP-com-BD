/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.domain;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nroa);
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
        final Ambulatorio other = (Ambulatorio) obj;
        if (!Objects.equals(this.nroa, other.nroa)) {
            return false;
        }
        return true;
    }

}
