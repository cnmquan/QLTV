/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import DAO.dao.PublisherDao;
import Base.DataProvider;
import constant.PublisherStringConstant;
import DTO.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import constant.GeneralStringConstant;

/**
 *
 * @author Admin
 */
public class PublisherDaoImp implements PublisherDao {

    public PublisherDaoImp() {
    }

    // Get All List Publisher from SQL Server
    @Override
    public ArrayList<Publisher> getAll() {
        ArrayList<Publisher> list = new ArrayList<>();

        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address "
                + "FROM publishers "
                + "WHERE publisher_is_deleted = false "
                + "ORDER BY publisher_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Publisher publisher = Publisher.convertFromResultSet(rs);
                list.add(publisher);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    @Override
    public Publisher getAttribute(String attribute, String a) {
        Publisher publisher = new Publisher();
        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address "
                + "from publishers "
                + "WHERE " + attribute + " = ?";

        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
            a
        });

        try {
            while (rs.next()) {
                publisher = Publisher.convertFromResultSet(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return publisher;
    }

    @Override
    public ArrayList<Publisher> getDeleteList() {
        ArrayList<Publisher> list = new ArrayList<>();
        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number,"
                + "publisher_address "
                + "FROM publishers "
                + "WHERE publisher_is_deleted = true "
                + "ORDER BY publisher_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);

        try {
            while (rs.next()) {
                Publisher publisher = Publisher.convertFromResultSet(rs);
                list.add(publisher);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    // Insert Publisher object to SQL Database
    @Override
    public boolean insert(Publisher t) {
        boolean rowInserted;
        String sql = "INSERT INTO publishers("
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address, "
                + "publisher_is_deleted) "
                + "VALUES("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "false)";

        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherID(), 
            t.getPublisherName(), 
            t.getPublisherPhoneNumber(), 
            t.getPublisherAddress()
        });

        return rowInserted;
    }

    // Update Publisher object to SQL Database
    @Override
    public boolean update(Publisher t) {
        boolean rowUpdated;
        String sql = "UPDATE publishers "
                + "SET publisher_name = ?, "
                + "publisher_phone_number = ?, "
                + "publisher_address = ? "
                + "WHERE publisher_id = ?";

        rowUpdated = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherName(),
            t.getPublisherPhoneNumber(),
            t.getPublisherAddress(),
            t.getPublisherID()
        });

        return rowUpdated;
    }

    @Override
    public boolean delete(String id) {
        boolean rowDeleted;
        String sql = "DELETE FROM publishers "
                + "WHERE publisher_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowDeleted;
    }
    
     @Override
    public boolean delete(Publisher t) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM publishers "
                + "WHERE publisher_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherID()
        });

        return rowDeleted;
    }

    // Delete Publisher object to SQL Database
    @Override
    public boolean moveToBin(String id) {
        boolean rowMovedToBin;
        String sql = "UPDATE publishers "
                + "SET publisher_is_deleted = true "
                + "FWHERE publisher_id = ?";

        rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowMovedToBin;
    }

    @Override
    public boolean removeFromBin(String id) {
        boolean rowRemovedFromBin;
        String sql = "UPDATE publishers "
                + "SET publisher_is_deleted = false  "
                + "WHERE publisher_id = ?";

        rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowRemovedFromBin;
    }

    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(PublisherStringConstant.PUBLISHER_ID);
        vector.add(PublisherStringConstant.PUBLISHER_NAME);
        vector.add(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        vector.add(PublisherStringConstant.PUBLISHER_ADDRESS);
        return vector;
    }

    public String getIDByName(List<Publisher> t, String name) {
        for (Publisher p : t) {
            if (p.getPublisherName().contains(name)) {
                return p.getPublisherID();
            }
        }
        return GeneralStringConstant.GENERAL_EMPTY;
    }

    @Override
    public boolean isExist(ArrayList<Publisher> t, String s) {
        for (Publisher publisher : t) {
            if (publisher.getPublisherID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExistDeleteList(String s) {
        ArrayList<Publisher> deleteList = getDeleteList();
        for (Publisher publisher : deleteList) {
            if (publisher.getPublisherID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateString(String s) {
        return s.isBlank() || s.isEmpty();
    }  

}
