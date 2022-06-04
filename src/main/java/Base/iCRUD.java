/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 * @param <T>
 */
public interface iCRUD<T> {

    ArrayList<T> getAll() throws SQLException;

    boolean insert(T t) throws SQLException;

    boolean update(T t) throws SQLException;

    boolean delete(String id) throws SQLException;
    
    public boolean delete(T t) throws SQLException;
    
    T getAttribute(String atribute, String s);
}
