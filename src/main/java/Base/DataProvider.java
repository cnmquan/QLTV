/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class give method to connect to the database
 *
 * @author Asus
 */
public class DataProvider {

    static String url = "jdbc:postgresql://localhost:5432/QLTV";
    static String unameDB = "postgres";
    static String passDB = "admin";

    private static DataProvider instance = null;
    private Connection connection = null;

    private DataProvider() {
        try {
            connection = DriverManager.getConnection(url, unameDB, passDB);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return DataProvider.instance;
    }

    public static void setInstance(DataProvider instance) {
        DataProvider.instance = instance;
    }

    public void  getConnection() throws SQLException{
        connection = DriverManager.getConnection(url, unameDB, passDB);
    }
    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
            connection = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get list of row. Often use for (select) query
     *
     * @param query String query
     * @param parameter list parameter
     * @return list of row
     */
    public ResultSet ExecuteQuery(String query, Object[] parameter) {
        ResultSet result = null;

        if(connection == null)
            return null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            int index = 1;
            // Insert parameter
            if (null != parameter && parameter.length != 0) {
                for (Object para : parameter) {
                    pstmt.setObject(index++, para);
                }
            }
            result = pstmt.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
        return result;
    }

    /**
     * Get the number of row. Often use for (insert,update,delete) query
     *
     * @param query String query
     * @param parameter list parameter
     * @return Number of row
     */
    public int ExecuteNonQuery(String query, Object[] parameter) {
        int result = -1;
        if(connection == null)
            return result;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            int index = 1;
            // Insert parameter
            if (null != parameter && parameter.length != 0) {
                for (Object para : parameter) {
                    pstmt.setObject(index++, para);
                }
            }
            result = pstmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
        return result;
    }

    /**
     * Get the first data. Often use for (count, sum, average...) query
     *
     * @param query String query
     * @param parameter list parameter
     * @return first data
     */
    public String ExecuteScalar(String query, Object[] parameter) {
        String result = null;
        if(connection == null)
            return result;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            int index = 1;
            // Insert parameter
            if (null != parameter && parameter.length != 0) {
                for (Object para : parameter) {
                    pstmt.setObject(index++, para);
                }
            }
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        } 
        return result;
    }
}
