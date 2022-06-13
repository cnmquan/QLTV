package DAO.dao_impl;

import Base.DataProvider;
import DAO.dao.BorrowDao;
import DTO.Book;
import DTO.Borrow;
import constant.BookStringConstant;
import constant.BorrowStringConstant;
import constant.ReaderStringConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class BorrowDaoImp implements BorrowDao {
    private BookDaoImp bookDaoImp = new BookDaoImp();

    /**
     * Get all borrows from database
     * Print exception if there is an error getting borrows
     *
     * @return All borrows in the database
     */
    @Override
    public ArrayList<Borrow> getAll() throws SQLException {
        ArrayList<Borrow> borrows = new ArrayList<>();
        String sql = "SELECT "
                + "borrow_id, "
                + "reader_id, "
                + "book_id, "
                + "quantity, "
                + "borrow_date, "
                + "return_date "
                + "FROM borrows "
                + "WHERE borrow_is_deleted = false "
                + "ORDER BY borrow_id";
        ResultSet resultSet = DataProvider.ExecuteQuery(sql, null);
        try {
            while (resultSet.next()) {
                Borrow borrow = Borrow.convertFromResultSet(resultSet);
                borrows.add(borrow);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return borrows;
    }

    /**
     * Get Borrow by attribute
     *
     * @param attribute attribute of the borrow you want to get,
     *                  <p>
     *                  For the list of attribute, visit data table of Borrow
     * @param value     value of the attribute you want to get
     *                  <p>
     *                  Throw exception if the attribute provided is not included in Borrow table
     * @return Borrow which has the @attribute match the value @value
     */
    @Override
    public Borrow getAttribute(String attribute, String value) {
        Borrow borrow = new Borrow();
        String sql = "SELECT "
                + "borrow_id, "
                + "reader_id, "
                + "book_id, "
                + "quantity, "
                + "borrow_date, "
                + "return_date "
                + "from borrows "
                + "WHERE " + attribute + " = ?";

        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
                value
        });

        try {
            while (rs.next()) {
                borrow = Borrow.convertFromResultSet(rs);
            }
        } catch (SQLException | ParseException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return borrow;
    }

    /**
     * Get list of borrows which has been deleted
     *
     * @return list of deleted borrows
     */
    @Override
    public ArrayList<Borrow> getDeleteList() {
        ArrayList<Borrow> list = new ArrayList<>();
        String sql = "SELECT "
                + "borrow_id, "
                + "reader_id, "
                + "book_id, "
                + "quantity, "
                + "borrow_date, "
                + "return_date "
                + "FROM borrows "
                + "WHERE borrow_is_deleted = true "
                + "ORDER BY borrow_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);

        try {
            while (rs.next()) {
                Borrow borrow = Borrow.convertFromResultSet(rs);
                list.add(borrow);
            }
        } catch (SQLException | ParseException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    /**
     * Insert borrow to database
     *
     * @param borrow borrow that you want to insert
     * @return true if the borrow is successfully inserted, false if the borrow is failed to be inserted
     */
    @Override
    public boolean insert(Borrow borrow) throws SQLException {
        boolean rowInserted;
        String sql = "INSERT INTO borrows("
                + "borrow_id, "
                + "reader_id, "
                + "book_id, "
                + "quantity, "
                + "borrow_date, "
                + "return_date, "
                + "borrow_is_deleted) "
                + "VALUES("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "false)";
        Timestamp borrowDate = new Timestamp(borrow.getBorrowDate().getTime());
        Timestamp returnDate;
        if (borrow.getReturnDate() != null) {
            returnDate = new Timestamp(borrow.getReturnDate().getTime());
        } else {
            returnDate = null;
        }
        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                borrow.getBorrowId(),
                borrow.getReaderId(),
                borrow.getBookId(),
                borrow.getQuantity(),
                borrowDate,
                returnDate
        });
        Book book = bookDaoImp.getAttribute("book_id", borrow.getBookId());
        book.setBookQuantity(book.getBookQuantity() - borrow.getQuantity());
        System.out.println(book.getBookQuantity());
        bookDaoImp.update(book);
        return rowInserted;
    }

    /**
     * Update borrow's information
     *
     * @param borrow borrow that you want to update
     *               <p>
     *               Calling this function will call BookDao to update the quantity of borrowed book
     * @return true if the borrow is successfully updated, false if the borrow is failed to be updated
     */
    @Override
    public boolean update(Borrow borrow) throws SQLException {
        boolean rowInserted;
        String sql = "UPDATE borrows "
                + "SET reader_id = ?, "
                + "book_id = ?, "
                + "quantity = ?, "
                + "borrow_date = ?, "
                + "return_date = ? "
                + "WHERE borrow_id = ?";
        Timestamp borrowDate = new Timestamp(borrow.getBorrowDate().getTime());
        Timestamp returnDate;
        if (borrow.getReturnDate() != null) {
            returnDate = new Timestamp(borrow.getReturnDate().getTime());
        } else {
            returnDate = null;
        }
        Book book = bookDaoImp.getAttribute("book_id", borrow.getBookId());
        book.setBookQuantity(book.getBookQuantity() + borrow.getQuantity());
        System.out.println(book.getBookQuantity());
        bookDaoImp.update(book);

        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                borrow.getReaderId(),
                borrow.getBookId(),
                borrow.getQuantity(),
                borrowDate,
                returnDate,
                borrow.getBorrowId(),
        });

        return rowInserted;
    }

    /**
     * Update borrow's information
     * Use this update function when you want to update the quantity of the book
     * In short, this function is used when there was an error inserting new Borrow
     *
     * @param borrow borrow that you want to update
     *               <p>
     *               Calling this function will call BookDao to update the quantity of borrowed book
     * @return true if the borrow is successfully updated, false if the borrow is failed to be updated
     */
    ///Use this update function when you want to update the quantity of the book
    ///In short, this function is used when there was an error inserting new Borrow
    public boolean updateNoIncreaseBookQuantity(Borrow borrow) {
        boolean rowInserted;
        String sql = "UPDATE borrows "
                + "SET reader_id = ?, "
                + "book_id = ?, "
                + "quantity = ?, "
                + "borrow_date = ?, "
                + "return_date = ? "
                + "WHERE borrow_id = ?";
        Timestamp borrowDate = new Timestamp(borrow.getBorrowDate().getTime());
        Timestamp returnDate;
        if (borrow.getReturnDate() != null) {
            returnDate = new Timestamp(borrow.getReturnDate().getTime());
        } else {
            returnDate = null;
        }
        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                borrow.getReaderId(),
                borrow.getBookId(),
                borrow.getQuantity(),
                borrowDate,
                returnDate,
                borrow.getBorrowId(),
        });
        return rowInserted;
    }

    /**
     * Delete borrows information
     * <p>
     * Call this function will also call BookDao.setBookQuantity() to update the quantity of book
     *
     * @param id borrow id that you want to delete
     * @return true if the borrow is successfully deleted, false if the borrow is failed to be deleted
     */
    @Override
    public boolean delete(String id) throws SQLException {
        Borrow borrow = getAttribute("borrow_id", id);
        Book book = bookDaoImp.getAttribute("book_id", borrow.getBookId());
        boolean rowDeleted;
        String sql = "DELETE FROM borrows "
                + "WHERE borrow_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });
        if (rowDeleted) {
            book.setBookQuantity(book.getBookQuantity() + borrow.getQuantity());
            bookDaoImp.update(book);
        }
        return rowDeleted;
    }

    /**
     * Delete borrows information
     * <p>
     * Call this function will also call BookDao.setBookQuantity() to update the quantity of book
     *
     * @param borrow borrow  that you want to delete
     * @return true if the borrow is successfully deleted, false if the borrow is failed to be deleted
     */
    @Override
    public boolean delete(Borrow borrow) throws SQLException {
        boolean rowDeleted;
        Book book = bookDaoImp.getAttribute("book_id", borrow.getBookId());
        String sql = "DELETE FROM borrows "
                + "WHERE borrow_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                borrow.getBorrowId()
        });
        if (rowDeleted) {
            book.setBookQuantity(book.getBookQuantity() + borrow.getQuantity());
            bookDaoImp.update(book);
        }
        return rowDeleted;
    }

    /**
     * Move borrow to Bin
     *
     * @param id borrow id that you want to move to bin
     * @return true if the borrow is successfully moved to bin, false if the borrow is failed to be moved to bin
     */
    @Override
    public boolean moveToBin(String id) {
        boolean rowMovedToBin;
        String sql = "UPDATE borrows "
                + "SET borrow_is_deleted = true "
                + "WHERE borrow_id = ?";

        rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });

        return rowMovedToBin;
    }

    /**
     * Remove borrow from Bin
     *
     * @param id borrow id that you want to remove from bin
     * @return true if the borrow is successfully removed from bin, false if the borrow is failed to be removed from bin
     */
    @Override
    public boolean removeFromBin(String id) {
        boolean rowRemovedFromBin;
        String sql = "UPDATE borrows "
                + "SET borrow_is_deleted = false  "
                + "WHERE borrow_id = ?";

        rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });

        return rowRemovedFromBin;
    }

    /**
     * Get Title column of borrow
     *
     * @return Vector which represents for the title of the column
     */
    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(BorrowStringConstant.BORROW_ID);
        vector.add(ReaderStringConstant.READER_ID);
        vector.add(BookStringConstant.BOOK_ID);
        vector.add(BorrowStringConstant.BORROW_QUANTITY);
        vector.add(BorrowStringConstant.BORROW_DATE);
        vector.add(BorrowStringConstant.RETURN_DATE);
        return vector;
    }

    /**
     * Check if the borrow is existed in the provided list
     *
     * @param borrows       list of borrows to be checked
     * @param idToBeChecked id of the borrow to be checked
     * @return true if the borrow is existed in the provided list, false if the borrow is not existed in the provided list
     */
    @Override
    public boolean isExist(ArrayList<Borrow> borrows, String idToBeChecked) {
        for (Borrow borrow : borrows) {
            if (borrow.getBorrowId().compareTo(idToBeChecked) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the borrow is deleted
     *
     * @param id id of the borrow you want to check
     * @return true if the borrow is deleted, false if the borrow has not been deleted
     */
    @Override
    public boolean isExistDeleteList(String id) {
        ArrayList<Borrow> deleteList = getDeleteList();
        for (Borrow borrow : deleteList) {
            if (borrow.getBorrowId().compareTo(id) == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Validate String
     *
     * @param s value to that need to be validated
     * @return true if the value meet the requirement, false if the value do not meet the requirement
     */
    @Override
    public boolean validateString(String s) {
        return s.isBlank() || s.isEmpty();
    }
}
