/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.util.ArrayList;

/**
 * Dùng để xử lý những vấn đề liên quan tới validate trong database
 */
public interface iValidation<T> {

    // Kiểm tra xem chuỗi S có tồn tại trong danh sách bảng trong databse
    public boolean isExist(ArrayList<T> t, String s);

    // Kiểm tra chuỗi s có phải là chuỗi hợp lệ để thêm vào databse
    public boolean validateString(String s);
}
