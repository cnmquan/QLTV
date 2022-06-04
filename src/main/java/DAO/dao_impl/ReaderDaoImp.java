package DAO.dao_impl;

import Adapter.DatabaseConnection;
import DAO.dao.ReaderDao;
import constant.ReaderStringConstant;
import model.Publisher;
import model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ReaderDaoImp implements ReaderDao {
    public ReaderDaoImp() {

    }

    @Override
    public ArrayList<Reader> getAll() {
        ArrayList<Reader> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT * FROM readers "
                + " WHERE reader_is_deleted = false"
                + " ORDER BY reader_id";
//        try {
//            c = DatabaseConnection.getConnection();
//            Statement s = c.createStatement();
//            ResultSet rs = s.executeQuery(sql);
//
//            while (rs.next()) {
//                Reader reader = new Reader();
//                reader.setId(rs.getString("reader_id"));
//                reader.setName(rs.getString("reader_name"));
//                reader.setPhoneNumber(rs.getString("reader_phone_number"));
//                list.add(reader);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            DatabaseConnection.close(c);
//        }
        return list;
    }

    @Override
    public boolean insert(Reader reader) {
        String sql = "INSERT INTO readers(reader_id,reader_name,reader_phone_number,reader_is_deleted) VALUES(?,?,?,false)";
        boolean rowInserted = false;
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, reader.getId());
            statement.setString(2, reader.getName());
            statement.setString(3, reader.getPhoneNumber());

            rowInserted = statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DatabaseConnection.close(con);
        }
        return rowInserted;
    }

    @Override
    public boolean update(Reader reader) {
        String sql = "UPDATE readers SET reader_name = ?, reader_phone_number = ?";
        sql += " WHERE reader_id = ?";

        boolean rowUpdated = false;
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, reader.getName());
            statement.setString(2, reader.getPhoneNumber());
            statement.setString(3, reader.getId());

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
        String sql = "DELETE FROM readers   "
                + " WHERE reader_id = ?";

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
    public boolean moveToBin(String id) {
        String sql = "UPDATE readers SET reader_is_deleted = true ";
        sql += " WHERE reader_id = ?";
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
        String sql = "UPDATE readers SET reader_is_deleted = false  "
                + " WHERE reader_id = ?";

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
    public Reader getAttribute(String atribute, String s) {
        String sql = "SELECT * from readers WHERE ? = ?";
        Reader reader = new Reader();
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, atribute);
            statement.setString(2, s);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                reader.setId(resultSet.getString("reader_id"));
                reader.setName(resultSet.getString("reader_name"));
                reader.setPhoneNumber(resultSet.getString("reader_phone_number"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }

        return reader;
    }

    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(ReaderStringConstant.READER_ID);
        vector.add(ReaderStringConstant.READER_NAME);
        vector.add(ReaderStringConstant.READER_PHONE_NUMBER);
        return vector;
    }
    public String getIDByName(List<Reader> t, String name) {
        for (Reader p : t) {
            if (p.getName().contains(name)) {
                return p.getId();
            }
        }
        return "";
    }

    @Override
    public ArrayList<Reader> getDeleteList() {
        ArrayList<Reader> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT * FROM readers "
                + " WHERE reader_is_deleted = true"
                + " ORDER BY reader_id";
        try {
            c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Reader reader = new Reader();
                reader.setId(rs.getString("reader_id"));
                reader.setName(rs.getString("reader_name"));
                reader.setPhoneNumber(rs.getString("reader_phone_number"));

                list.add(reader);
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
        ArrayList<Reader> deleteList = getDeleteList();
        for (Reader reader : deleteList) {
            if (reader.getId().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean isExist(ArrayList<Reader> t, String s) {
        for (Reader reader : t) {
            if (reader.getId().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateString(String s) {
        return  s.isBlank()||s.isEmpty();
    }

    //Create Singleton class for ReaderDAO
    private static class SingletonHelper {
        private static final ReaderDaoImp INSTANCE = new ReaderDaoImp();
    }

    //Create ReaderDAO Singleton
    public static ReaderDaoImp getInstance() {
        return SingletonHelper.INSTANCE;
    }

}
