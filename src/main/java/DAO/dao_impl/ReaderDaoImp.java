package DAO.dao_impl;

import Base.DataProvider;
import DAO.dao.ReaderDao;
import DTO.Book;
import DTO.Publisher;
import constant.ReaderStringConstant;
import DTO.Reader;

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
        String sql = "SELECT * FROM readers "
                + " WHERE reader_is_deleted = false"
                + " ORDER BY reader_id";
        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Reader reader = Reader.covertFromResultSet(rs);
                list.add(reader);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }


        return list;
    }

    @Override
    public boolean insert(Reader reader) {
        String sql = "INSERT INTO readers(reader_id,reader_name,reader_phone_number,reader_is_deleted) VALUES(?,?,?,false)";
        boolean rowInserted;
        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                reader.getId(),
                reader.getName(),
                reader.getPhoneNumber(),
        });

        return rowInserted;
    }

    @Override
    public boolean update(Reader reader) {
        String sql = "UPDATE readers SET reader_name = ?, reader_phone_number = ?";
        sql += " WHERE reader_id = ?";

        boolean rowUpdated = false;
        rowUpdated = DataProvider.ExecuteNonQuery(sql, new Object[]{
                reader.getName(),
                reader.getPhoneNumber(),

                reader.getId()
        });

        return rowUpdated;
    }

    @Override
    public boolean delete(String id) {
        String sql = "DELETE FROM readers   "
                + " WHERE reader_id = ?";
        boolean rowDeleted;
        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });
        return rowDeleted;
    }

    @Override
    public boolean delete(Reader reader) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM readers "
                + "WHERE reader_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                reader.getId()
        });

        return rowDeleted;
    }

    @Override
    public boolean moveToBin(String id) {
        String sql = "UPDATE readers SET reader_is_deleted = true ";
        sql += " WHERE reader_id = ?";
        boolean rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });

        return rowMovedToBin;
    }

    @Override
    public boolean removeFromBin(String id) {
        String sql = "UPDATE readers SET reader_is_deleted = false  "
                + " WHERE reader_id = ?";

        boolean rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });
        return rowRemovedFromBin;
    }

    @Override
    public Reader getAttribute(String attribute, String s){
        Reader reader = new Reader();
        String sql = "SELECT "
                + "reader_id, "
                + "reader_name, "
                + "reader_phone_number "
                + "FROM readers "
                + "WHERE " + attribute + " = ?";
        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
                s
        });
        try {
            while (rs.next()){

        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
                s
        });

        try {
            while (rs.next()) {
                reader = Reader.covertFromResultSet(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

            reader = Reader.covertFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        String sql = "SELECT * FROM readers "
                + " WHERE reader_is_deleted = true"
                + " ORDER BY reader_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Reader reader = new Reader();
                reader.setId(rs.getString("reader_id"));
                reader.setName(rs.getString("reader_name"));
                reader.setPhoneNumber(rs.getString("reader_phone_number"));

                list.add(reader);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
        return s.trim().isEmpty() || s.isEmpty();
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
