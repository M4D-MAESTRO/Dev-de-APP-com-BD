/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aula05_09.ex1.bd;

import java.util.List;

/**
 *
 * @author 2017102465
 */
public interface DAO<T> {
    
    public void insert(T object);
    public void delete (Integer id);
    public void update(T object);
    public List<T> getAll();
    
}
