/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    
    private String url="jdbc:postgresql://localhost:5432/qltv";
    private String login="postgres";
    private String pass="talavua5122001";
    
    private DatabaseConnection(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
            System.out.println(" Connection  - - - - - - - -  New DBConnection created");
        }
        try {
            return DriverManager.getConnection(instance.url, instance.login, instance.pass);
        } catch (SQLException e) {
            throw e;
        }
    }
    
     public static void close(Connection connection)
    {
        try {
            if (connection != null) {
                connection.close();
                connection=null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
