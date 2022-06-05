/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import DAO.dao.BookDao;
import Base.DataProvider;
import constant.BookStringConstant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;

import DTO.Book;

/**
 * @author Admin
 */
public class BookDaoImp implements BookDao {

    public BookDaoImp() {
    }

    @Override
    public ArrayList<Book> getAll() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT "
                + "book_id, "
                + "book_name, "
                + "book_category, "
                + "book_author, "
                + "book_quantity, "
                + "book_page_number, "
                + "book_price, "
                + "book_publish_year, "
                + "publisher_name, "
                + "book_updated_time "
                + "from books "
                + "WHERE book_is_deleted = false "
                + "ORDER BY book_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);

        try {
            while (rs.next()) {
                Book book = Book.covertFromResultSet(rs);
                list.add(book);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    @Override
    public Book getAttribute(String attribute, String a) {
        Book book = new Book();
        String sql = "SELECT "
                + "book_id, "
                + "book_name, "
                + "book_category, "
                + "book_author, "
                + "book_quantity, "
                + "book_page_number, "
                + "book_price, "
                + "book_publish_year,"
                + "publisher_name,"
                + "book_updated_time "
                + "from books "
                + "WHERE " + attribute + " = ?";

        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
                a
        });

        try {
            while (rs.next()) {
                book = Book.covertFromResultSet(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return book;
    }

    @Override
    public ArrayList<Book> getDeleteList() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT "
                + "book_id, "
                + "book_name, "
                + "book_category, "
                + "book_author, "
                + "book_quantity, "
                + "book_page_number, "
                + "book_price, "
                + "book_publish_year, "
                + "publisher_name,"
                + "book_updated_time "
                + "from books "
                + "WHERE book_is_deleted = true "
                + "ORDER BY book_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Book book = Book.covertFromResultSet(rs);
                list.add(book);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    @Override
    public ArrayList<Book> getNewestFiveBook() {
        ArrayList<Book> list = new ArrayList<>();
        String sql = "SELECT "
                + "book_id, "
                + "book_name, "
                + "book_category, "
                + "book_author, "
                + "book_quantity, "
                + "book_page_number, "
                + "book_price, "
                + "book_publish_year, "
                + "publisher_name,"
                + "book_updated_time "
                + "from books "
                + "WHERE book_is_deleted = false "
                + "ORDER BY book_updated_time "
                + "LIMIT 5";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Book book = Book.covertFromResultSet(rs);
                list.add(book);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    @Override
    public int getSumBook() {
        int quantity = 0;
        String sql = "SELECT SUM(book_quantity) "
                + "FROM books "
                + "WHERE book_is_deleted = false ";

        String rs = DataProvider.ExecuteScalar(sql, null);
        quantity = Integer.valueOf(rs);

        return quantity;
    }

    @Override
    public String getLongestString(String attribute) {
        String longestString = "";
        String sql = "SELECT " + attribute + " "
                + "FROM ("
                + "SELECT * "
                + "FROM books "
                + "WHERE book_is_deleted = false "
                + "ORDER BY book_updated_time DESC "
                + "LIMIT 5 "
                + ") AS booklists "
                + "ORDER BY LENGTH(" + attribute + ") DESC "
                + "LIMIT 1";

        longestString = DataProvider.ExecuteScalar(sql, null);
        System.out.println(longestString);

        return longestString;
    }

    @Override
    public boolean insert(Book t) {
        LocalDate localDate = LocalDate.now();
        boolean rowInserted;
        String sql = "INSERT INTO books("
                + "book_id,"
                + "book_name, "
                + "book_category, "
                + "book_author, "
                + "book_quantity, "
                + "book_page_number, "
                + "book_price, "
                + "publisher_name, "
                + "book_publish_year, "
                + "book_updated_time, "
                + "book_is_deleted) "
                + "VALUES("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "false)";

        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                t.getBookID(),
                t.getBookName(),
                t.getBookCategory(),
                t.getBookAuthor(),
                t.getBookQuantity(),
                t.getBookPageNumber(),
                t.getBookPrice(),
                t.getPublisherName(),
                t.getBookPublishedYear(),
                localDate
        });

        return rowInserted;
    }

    @Override
    public boolean update(Book t) {
        LocalDate localDate = LocalDate.now();
        boolean rowUpdated;
        String sql = "UPDATE books SET "
                + "book_name = ?, "
                + "book_category = ?, "
                + "book_author = ?, "
                + "book_quantity = ?, "
                + "book_page_number = ?, "
                + "book_price = ?, "
                + "publisher_name = ?, "
                + "book_publish_year = ?,"
                + "book_updated_time = ?  "
                + "WHERE book_id = ?";

        rowUpdated = DataProvider.ExecuteNonQuery(sql, new Object[]{
                t.getBookName(),
                t.getBookCategory(),
                t.getBookAuthor(),
                t.getBookQuantity(),
                t.getBookPageNumber(),
                t.getBookPrice(),
                t.getPublisherName(),
                t.getBookPublishedYear(),
                localDate,
                t.getBookID()
        });

        return rowUpdated;
    }

    @Override
    public boolean delete(String id) {
        boolean rowDeleted;
        String sql = "DELETE FROM books "
                + "WHERE book_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });

        return rowDeleted;
    }

    @Override
    public boolean delete(Book t) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM books "
                + "WHERE book_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
                t.getBookID()
        });

        return rowDeleted;
    }

    @Override
    public boolean moveToBin(String id) {
        boolean rowMovedToBin;
        String sql = "UPDATE books SET "
                + "book_is_deleted = true "
                + "WHERE book_id = ?";

        rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                id
        });

        return rowMovedToBin;
    }

    @Override
    public boolean removeFromBin(String id) {
        LocalDate localDate = LocalDate.now();
        boolean rowRemovedFromBin;
        String sql = "UPDATE books SET "
                + "book_updated_time = ?, "
                + "book_is_deleted = false  "
                + "WHERE book_id = ?";

        rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
                localDate,
                id
        });

        return rowRemovedFromBin;
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
