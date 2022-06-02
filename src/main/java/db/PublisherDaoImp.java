/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import Adapter.DatabaseConnection;
import constant.PublisherStringConstant;
import model.Publisher;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import model.Book;

/**
 *
 * @author Admin
 */
public class PublisherDaoImp implements PublisherDao {

    public PublisherDaoImp() {
    }

    // Create singleton class for publisherDAO
    private static class SingletonHelper {

        private static final PublisherDaoImp INSTANCE = new PublisherDaoImp();
    }

    // Create PublisherDAO Singleton
    public static PublisherDaoImp getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    private BookDaoImp bookDaoImp = BookDaoImp.getInstance();

    // Get All List Publisher from SQL Server
    @Override
    public ArrayList<Publisher> getAll() {
        ArrayList<Publisher> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT * FROM publishers "
                + " WHERE publisher_is_deleted = false"
                + " ORDER BY publisher_id";
        try {
            c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherID(rs.getString("publisher_id"));
                publisher.setPublisherName(rs.getString("publisher_name"));
                publisher.setPublisherPhoneNumber(rs.getString("publisher_phone_number"));
                publisher.setPublisherAddress(rs.getString("publisher_address"));
                list.add(publisher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(c);
        }
        return list;
    }

    // Insert Publisher object to SQL Database
    @Override
    public boolean insert(Publisher t) {
        String sql = "INSERT INTO publishers(publisher_id,publisher_name,publisher_phone_number,publisher_address,publisher_is_deleted) VALUES(?,?,?,?,false)";
        boolean rowInserted = false;
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, t.getPublisherID());
            statement.setString(2, t.getPublisherName());
            statement.setString(3, t.getPublisherPhoneNumber());
            statement.setString(4, t.getPublisherAddress());

            rowInserted = statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DatabaseConnection.close(con);
        }
        return rowInserted;
    }

    // Update Publisher object to SQL Database
    @Override
    public boolean update(Publisher t) {
        String sql = "UPDATE publishers SET publisher_name = ?, publisher_phone_number = ?, publisher_address = ? ";
        sql += " WHERE publisher_id = ?";

        boolean rowUpdated = false;
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, t.getPublisherName());
            statement.setString(2, t.getPublisherPhoneNumber());
            statement.setString(3, t.getPublisherAddress());
            statement.setString(4, t.getPublisherID());

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }

        return rowUpdated;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM publishers   "
                + " WHERE publisher_id = ?";

        boolean rowUpdated = false;
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
        return rowUpdated;
    }

    // Delete Publisher object to SQL Database
    @Override
    public boolean moveToBin(String id) {
        ArrayList<Book> bookList = bookDaoImp.getListByPublisher(id);
        for(Book book : bookList){
            bookDaoImp.moveToBin(book.getBookID());
        }
        String sql = "UPDATE publishers SET publisher_is_deleted = true ";
        sql += " WHERE publisher_id = ?";
        boolean rowDeleted = false;
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }

        return rowDeleted;
    }

    @Override
    public boolean removeFromBin(String id) {
        String sql = "UPDATE publishers SET publisher_is_deleted = false  "
                + " WHERE publisher_id = ?";

        boolean rowUpdated = false;
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, id);

            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }
        return rowUpdated;
    }

    @Override
    public Publisher getAttribute(String atribute, String a) {
        String sql = "SELECT * from publishers WHERE ? = ?";
        Publisher publisher = new Publisher();
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, atribute);
            statement.setString(2, a);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                publisher.setPublisherID(resultSet.getString("publisher_id"));
                publisher.setPublisherName(resultSet.getString("publisher_name"));
                publisher.setPublisherPhoneNumber(resultSet.getString("publisher_phone_number"));
                publisher.setPublisherAddress(resultSet.getString("publisher_address"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }

        return publisher;
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
        return "";
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
    public ArrayList<Publisher> getDeleteList() {
        ArrayList<Publisher> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT * FROM publishers "
                + " WHERE publisher_is_deleted = true"
                + " ORDER BY publisher_id";
        try {
            c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setPublisherID(rs.getString("publisher_id"));
                publisher.setPublisherName(rs.getString("publisher_name"));
                publisher.setPublisherPhoneNumber(rs.getString("publisher_phone_number"));
                publisher.setPublisherAddress(rs.getString("publisher_address"));
                list.add(publisher);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(c);
        }
        return list;
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
