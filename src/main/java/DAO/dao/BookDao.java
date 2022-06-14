/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao;

import Base.iCRUD;
import Base.iDeleteHandler;
import Base.iGetTitle;
import Base.iValidation;
import java.util.ArrayList;
import DTO.Book;

/**
 * BookDao Dùng để xử lí các chức năng liên quan tới Book
 */
public interface BookDao extends iCRUD<Book>, iGetTitle, iValidation<Book>, iDeleteHandler<Book> {

    // Dùng để lấy 5 quyển sách mới được thêm và cập nhật
    ArrayList<Book> getNewestFiveBook();

    // Dùng để lấy tổng số sách
    int getSumBook();

    // Dùng để xác nhận giá trị có số lượng chữ cái nhiều nhất trong 1 thuộc tính
    String getLongestString(String attribute, int number);
}
