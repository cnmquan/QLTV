/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import Adapter.DatabaseConnection;
import constant.BookStringConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import model.Book;

/**
 *
 * @author Admin
 */
public class BookDaoImp implements BookDao {

    public BookDaoImp() {
    }

    // Create singleton class for bookDAO
    private static class SingletonHelper {

        private static final BookDaoImp INSTANCE = new BookDaoImp();
    }

    // Create BookDAO Singleton
    public static BookDaoImp getInstance() {
        return SingletonHelper.INSTANCE;
    }

    @Override
    public ArrayList<Book> getAll() {
        ArrayList<Book> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT b.book_id, book_name,book_category, book_author, book_quantity, book_page_number, book_price, book_publish_year, b.publisher_id, p.publisher_name ";
        sql += " from books b, publishers p WHERE b.publisher_id = p.publisher_id and b.book_is_deleted = false ";
        sql += " ORDER BY book_id";
        try {
            c = DatabaseConnection.getConnection();
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookCategory(rs.getString("book_category"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookQuantity(rs.getInt("book_quantity"));
                book.setBookPageNumber(rs.getInt("book_page_number"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublishedYear(rs.getInt("book_publish_year"));
                book.setPublisherID(rs.getString("publisher_id"));
                book.setPublisherName(rs.getString("publisher_name"));
                list.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(c);
        }
        return list;
    }

    @Override
    public ArrayList<Book> getListByPublisher(String publisherID) {
        ArrayList<Book> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT b.book_id, book_name,book_category, book_author, book_quantity, book_page_number, book_price, book_publish_year, b.publisher_id, p.publisher_name ";
        sql += " from books b, publishers p WHERE b.publisher_id = p.publisher_id and b.publisher_id = ? ";
        sql += " ORDER BY book_id";
        try {
            c = DatabaseConnection.getConnection();
            PreparedStatement statement = c.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
            statement.setString(1, publisherID);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookCategory(rs.getString("book_category"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookQuantity(rs.getInt("book_quantity"));
                book.setBookPageNumber(rs.getInt("book_page_number"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublishedYear(rs.getInt("book_publish_year"));
                book.setPublisherID(rs.getString("publisher_id"));
                book.setPublisherName(rs.getString("publisher_name"));
                list.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DatabaseConnection.close(c);
        }
        return list;
    }

    @Override
    public boolean insert(Book t) {
        String sql = "INSERT INTO books(book_id,book_name,book_category,book_author, book_quantity, book_page_number, book_price, publisher_id, book_publish_year, book_is_deleted) VALUES(?,?,?,?,?,?,?,?,?, false)";
        boolean rowInserted = false;
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, t.getBookID());
            statement.setString(2, t.getBookName());
            statement.setString(3, t.getBookCategory());
            statement.setString(4, t.getBookAuthor());
            statement.setInt(5, t.getBookQuantity());
            statement.setInt(6, t.getBookPageNumber());
            statement.setDouble(7, t.getBookPrice());
            statement.setString(8, t.getPublisherID());
            statement.setInt(9, t.getBookPublishedYear());

            rowInserted = statement.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            DatabaseConnection.close(con);
        }
        return rowInserted;
    }

    @Override
    public boolean update(Book t) {
        String sql = "UPDATE books SET book_name = ?, book_category = ?, book_author = ?, book_quantity = ?, book_page_number = ?, book_price = ?, publisher_id = ?, book_publish_year = ?  ";
        sql += " WHERE book_id = ?";

        boolean rowUpdated = false;
        Connection con = null;

        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, t.getBookName());
            statement.setString(2, t.getBookCategory());
            statement.setString(3, t.getBookAuthor());
            statement.setInt(4, t.getBookQuantity());
            statement.setInt(5, t.getBookPageNumber());
            statement.setDouble(6, t.getBookPrice());
            statement.setString(7, t.getPublisherID());
            statement.setInt(8, t.getBookPublishedYear());
            statement.setString(9, t.getBookID());

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
        String sql = "DELETE FROM books   "
                + " WHERE book_id = ?";

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
        String sql = "UPDATE books SET book_is_deleted = true ";
        sql += " WHERE book_id = ?";
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
        String sql = "UPDATE books SET book_is_deleted = false  "
                + " WHERE book_id = ?";

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
    public Book getAttribute(String atribute, String a) {
        String sql = "SELECT book_id, book_name,book_category, book_author, book_quantity, book_page_number, book_price, book_publish_year, b.publisher_id, p.publisher_name ";
        sql += "from books b, publishers p WHERE b.publisher_id = p.publisher_id and ? = ?";
        Book book = new Book();
        Connection con = null;
        try {
            con = DatabaseConnection.getConnection();

            PreparedStatement statement = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE);
            statement.setString(1, atribute);
            statement.setString(2, a);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book.setBookID(resultSet.getString("book_id"));
                book.setBookName(resultSet.getString("book_name"));
                book.setBookCategory(resultSet.getString("book_category"));
                book.setBookAuthor(resultSet.getString("book_author"));
                book.setBookQuantity(resultSet.getInt("book_quantity"));
                book.setBookPageNumber(resultSet.getInt("book_page_number"));
                book.setBookPrice(resultSet.getDouble("book_price"));
                book.setBookPublishedYear(resultSet.getInt("book_publish_year"));
                book.setPublisherID(resultSet.getString("publisher_id"));
                book.setPublisherName(resultSet.getString("publisher_name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DatabaseConnection.close(con);
        }

        return book;
    }

    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(BookStringConstant.BOOK_ID);
        vector.add(BookStringConstant.BOOK_TITLE);
        vector.add(BookStringConstant.BOOK_CATEGORY);
        vector.add(BookStringConstant.BOOK_AUTHOR);
        vector.add(BookStringConstant.BOOK_QUANTITY);
        vector.add(BookStringConstant.BOOK_PAGE_NUMBER);
        vector.add(BookStringConstant.BOOK_PRICE);
        vector.add(BookStringConstant.BOOK_PUBLISHER);
        vector.add(BookStringConstant.BOOK_PUBLISH_YEAR);
        return vector;
    }

    @Override
    public boolean isExist(ArrayList<Book> t, String s) {
        for (Book book : t) {
            if (book.getBookID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateString(String s) {
        return s.isBlank() || s.isEmpty();
    }

    @Override
    public ArrayList<Book> getDeleteList() {
        ArrayList<Book> list = new ArrayList<>();
        Connection c = null;
        String sql = "SELECT b.book_id, book_name,book_category, book_author, book_quantity, book_page_number, book_price, book_publish_year, b.publisher_id, p.publisher_name ";
        sql += " from books b, publishers p WHERE b.publisher_id = p.publisher_id and b.book_is_deleted = true ";
        sql += " ORDER BY book_id";
        try {
            c = DatabaseConnection.getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                Book book = new Book();
                book.setBookID(rs.getString("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookCategory(rs.getString("book_category"));
                book.setBookAuthor(rs.getString("book_author"));
                book.setBookQuantity(rs.getInt("book_quantity"));
                book.setBookPageNumber(rs.getInt("book_page_number"));
                book.setBookPrice(rs.getDouble("book_price"));
                book.setBookPublishedYear(rs.getInt("book_publish_year"));
                book.setPublisherID(rs.getString("publisher_id"));
                book.setPublisherName(rs.getString("publisher_name"));
                list.add(book);
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
        ArrayList<Book> deleteList = getDeleteList();
        for (Book book : deleteList) {
            if (book.getBookID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }
}
