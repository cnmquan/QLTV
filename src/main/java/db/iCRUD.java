/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package db;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface iCRUD<T> {

    ArrayList<T> getAll();

    boolean insert(T t);

    boolean update(T t);

    boolean delete(String id);
    
    T getAttribute(String atribute, String s);
}
