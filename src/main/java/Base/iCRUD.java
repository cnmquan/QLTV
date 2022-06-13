/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Dùng để xử lý các chức năng liên quan tới database
 *
 * @param <T>
 */
public interface iCRUD<T> {

    // Lấy toàn bộ danh sách từ database của bảng T
    public ArrayList<T> getAll() throws SQLException;

    // Chèn phần từ T vào bảng trong database
    public boolean insert(T t) throws SQLException;

    // Cập nhật phần tử T của bảng trong database
    public boolean update(T t) throws SQLException;

    // Xoá phần tử với ID tương ứng của bảng T trong database
    public boolean delete(String id) throws SQLException;

    // Xoá phần tử T ra khỏi bảng trong database
    public boolean delete(T t) throws SQLException;

    // Lấy T từ giá trị đã chọn
    public T getAttribute(String atribute, String s);
}
