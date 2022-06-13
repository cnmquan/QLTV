/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

/**
 * Lớp Book dùng để chứa những thông tin liên quan tới Book Có những hàm để hỗ
 * trợ convert sang các kiểu dữ liệu cần thiết
 */
public class Book {

    // ID của Sách
    private String bookID;
    
    // Tên của Sách
    private String bookName;
    
    // Tác giả của Sách
    private String bookAuthor;
    
    // Thể loại của Sách
    private String bookCategory;
    
    // Số lượng của Sách
    private int bookQuantity;
    
    // Số trang của Sách
    private int bookPageNumber;
    
    // Năm xuất bản của Sách
    private int bookPublishedYear;
    
    // Giá tiền của Sách
    private double bookPrice;
    
    // Tên nhà xuât bản của Sách
    private String publisherName;
    
    // Thời gian cập nhật thông tin của sách
    private LocalDate bookUpdatedDate;

    // Default Constructors
    public Book() {
    }

    // Constructor Đầy đủ tham số
    public Book(String bookID, String bookName, String bookAuthor, String bookCategory, int bookQuantity, 
            int bookPageNumber, int bookPublishedYear, double bookPrice, String publisherName) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookQuantity = bookQuantity;
        this.bookPageNumber = bookPageNumber;
        this.bookPublishedYear = bookPublishedYear;
        this.bookPrice = bookPrice;
        this.publisherName = publisherName;
    }
    
    /**
     * Các hàm Get/Set cho các thuộc tính của Sách
     */
    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getBookPageNumber() {
        return bookPageNumber;
    }

    public void setBookPageNumber(int bookPageNumber) {
        this.bookPageNumber = bookPageNumber;
    }

    public int getBookPublishedYear() {
        return bookPublishedYear;
    }

    public void setBookPublishedYear(int bookPublishedYear) {
        this.bookPublishedYear = bookPublishedYear;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public LocalDate getBookUpdatedDate() {
        return bookUpdatedDate;
    }

    public void setBookUpdatedDate(LocalDate bookUpdatedDate) {
        this.bookUpdatedDate = bookUpdatedDate;
    }

    // Convert to Vector
    public Vector convertToVector() {
        Vector vector = new Vector();
        vector.add(this.bookID);
        vector.add(this.bookName);
        vector.add(this.bookCategory);
        vector.add(this.bookAuthor);
        vector.add(this.bookQuantity);
        vector.add(this.bookPageNumber);
        vector.add(this.bookPrice);
        vector.add(this.publisherName);
        vector.add(this.bookPublishedYear);
        return vector;
    }

    // Convert From ResultSet
    public static Book covertFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book();
        book.setBookID(rs.getString("book_id"));
        book.setBookName(rs.getString("book_name"));
        book.setBookCategory(rs.getString("book_category"));
        book.setBookAuthor(rs.getString("book_author"));
        book.setBookQuantity(rs.getInt("book_quantity"));
        book.setBookPageNumber(rs.getInt("book_page_number"));
        book.setBookPrice(rs.getDouble("book_price"));
        book.setBookPublishedYear(rs.getInt("book_publish_year"));
        book.setPublisherName(rs.getString("publisher_name"));
        book.setBookUpdatedDate(rs.getObject("book_updated_time", LocalDate.class));
        return book;
    }
}
