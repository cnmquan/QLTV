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

    /**
     * Get all readers from database Print exception if there is an error
     * getting readers
     *
     * @return All readers in the database
     */
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

    /**
     * Insert reader to database
     *
     * @param reader reader that you want to insert
     * @return true if the reader is successfully inserted, false if the reader
     * is failed to be inserted
     */
    @Override
    public boolean insert(Reader reader) {
        String sql = "INSERT INTO readers(reader_id,reader_name,reader_phone_number,reader_is_deleted) VALUES(?,?,?,false)";
        boolean rowInserted;
        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            reader.getId(),
            reader.getName(),
            reader.getPhoneNumber(),});

        return rowInserted;
    }

    /**
     * Update reader's information
     *
     * @param reader reader that you want to update
     * @return true if the reader is successfully updated, false if the reader
     * is failed to be updated
     */
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

    /**
     * Delete reader's information
     *
     * @param id reader id that you want to delete
     * @return true if the reader is successfully deleted, false if the reader
     * is failed to be deleted
     */
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

    /**
     * Delete reader's information
     *
     * @param reader reader that you want to delete
     * @return true if the reader is successfully deleted, false if the reader
     * is failed to be deleted
     */
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

    /**
     * Move reader to Bin
     *
     * @param id reader id that you want to move to bin
     * @return true if the reader is successfully moved to bin, false if the
     * reader is failed to be moved to bin
     */
    @Override
    public boolean moveToBin(String id) {
        String sql = "UPDATE readers SET reader_is_deleted = true ";
        sql += " WHERE reader_id = ?";
        boolean rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowMovedToBin;
    }

    /**
     * Delete reader from Bin
     *
     * @param id reader id that you want to remove from bin
     * @return true if the reader is successfully removed from bin, false if the
     * reader is failed to be removed to bin
     */
    @Override
    public boolean removeFromBin(String id) {
        String sql = "UPDATE readers SET reader_is_deleted = false  "
                + " WHERE reader_id = ?";

        boolean rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });
        return rowRemovedFromBin;
    }

    /**
     * Get Reader by attribute
     *
     * @param attribute attribute of the reader you want to get,
     * <p>
     * For the list of attribute, visit data table of Reader
     * @param value value of the attribute you want to get
     * <p>
     * Throw exception if the attribute provided is not included in Reader table
     * @return Reader which has the @attribute match the value @s
     */
    @Override
    public Reader getAttribute(String attribute, String value) {
        Reader reader = new Reader();
        String sql = "SELECT "
                + "reader_id, "
                + "reader_name, "
                + "reader_phone_number "
                + "FROM readers "
                + "WHERE " + attribute + " = ?";
        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
            value
        });
        try {
            while (rs.next()) {

                rs = DataProvider.ExecuteQuery(sql, new Object[]{
                    value
                });

                try {
                    while (rs.next()) {
                        reader = Reader.covertFromResultSet(rs);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reader;
    }

    /**
     * Get Title column of reader
     *
     * @return Vector which represents for the title of the column
     */
    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(ReaderStringConstant.READER_ID);
        vector.add(ReaderStringConstant.READER_NAME);
        vector.add(ReaderStringConstant.READER_PHONE_NUMBER);
        return vector;
    }

    /**
     * Get ID of reader by reader's name
     *
     * @param readers list of readers
     * @param name name of the reader you want to get
     * @return id of the reader
     */
    public String getIDByName(List<Reader> readers, String name) {
        for (Reader p : readers) {
            if (p.getName().contains(name)) {
                return p.getId();
            }
        }
        return "";
    }

    /**
     * Get list of readers which has been deleted
     *
     * @return list of deleted readers
     */
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

    /**
     * Check if the reader is deleted
     *
     * @param id id of the reader you want to check
     * @return true if the reader is deleted, false if the reader has not been
     * deleted
     */
    @Override
    public boolean isExistDeleteList(String id) {
        ArrayList<Reader> deleteList = getDeleteList();
        for (Reader reader : deleteList) {
            if (reader.getId().compareTo(id) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the reader is existed in the provided list
     *
     * @param readers list of readers to be checked
     * @param idToBeChecked id of the reader to be checked
     * @return true if the reader is existed in the provided list, false if the
     * reader is not existed in the provided list
     */
    @Override
    public boolean isExist(ArrayList<Reader> readers, String idToBeChecked) {
        for (Reader reader : readers) {
            if (reader.getId().compareTo(idToBeChecked) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate String
     *
     * @param s value to that need to be validated
     * @return true if the value meet the requirement, false if the value do not
     * meet the requirement
     */
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
