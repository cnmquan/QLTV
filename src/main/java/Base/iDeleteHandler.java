/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.util.ArrayList;

/**
 * Dùng để xử lý những hàm liên quan tới thùng rác
 */
public interface iDeleteHandler<T> {
    
    // Lấy danh sách các đối tượng T đã bị ẩn trong database
   public ArrayList<T> getDeleteList();
   
   // Kiểm tra xem chuỗi s này có trùng với id trong danh sách ẩn 
   public boolean isExistDeleteList(String s);
   
   // Chuyển một đối tượng vào thùng rác
   public boolean moveToBin(String id);
   
   // Chuyển một đối tượng ra khỏi thùng rác
   public boolean removeFromBin(String id);
}
