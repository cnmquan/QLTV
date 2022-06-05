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
//        try {
//            while (resultSet.next()) {
//                Borrow borrow = Borrow.convertFromResultSet(resultSet);
//                borrows.add(borrow);
//            }
//        } catch (SQLException ex) {
//            System.out.println("Error: " + ex.getMessage());
//        } catch (ParseException e) {
//            throw new RuntimeException(e);
//        }

        return borrows;
    }

    @Override
    public Borrow getAttribute(String attribute, String a) {
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
                a
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

    ///Use this update function when you want to update the quantity of the book
    ///In short, this function is used when the reader want to return the book to the library
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

    @Override
    public boolean isExist(ArrayList<Borrow> t, String s) {
        for (Borrow borrow : t) {
            if (borrow.getBorrowId().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isExistDeleteList(String s) {
        ArrayList<Borrow> deleteList = getDeleteList();
        for (Borrow borrow : deleteList) {
            if (borrow.getBorrowId().compareTo(s) == 0) {
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
