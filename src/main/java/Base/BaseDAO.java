/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This is the base DAO interface. In includes all common method to interact
 * with database
 * @author Asus
 * @param <T> is entity
 */
public interface BaseDAO<T> {
    
    public int create(T t)throws SQLException;
    public ArrayList<T> findAll() throws SQLException;
    public T findByID(String id) throws SQLException;
    public int update(T t) throws SQLException;
    public int delete(T t) throws SQLException;
    public int delete(String id) throws SQLException;
        
//    public final Connection connection=DataProvider.getInstance().getConnection();
//    default void closeConnection() throws SQLException{
//        DataProvider.getInstance().closeConnection();
//    }
}
