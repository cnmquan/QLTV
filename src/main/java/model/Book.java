/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class Book {
    private String bookID;
    private String bookName;
    private String bookAuthor;
    private String bookCategory;
    private int bookQuantity;
    private int bookPageNumber;
    private int bookPublishedYear;
    private double bookPrice;
    private String publisherID;
    private String publisherName;

    public Book() {
    }

    public Book(String bookID, String bookName, String bookAuthor, String bookCategory, int bookQuantity, int bookPageNumber, int bookPublishedYear, double bookPrice, String publisherID, String publisherName) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookCategory = bookCategory;
        this.bookQuantity = bookQuantity;
        this.bookPageNumber = bookPageNumber;
        this.bookPublishedYear = bookPublishedYear;
        this.bookPrice = bookPrice;
        this.publisherID = publisherID;
        this.publisherName = publisherName;
    }

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

    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
    
    
    
    public Vector convertToVector(){
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
}
