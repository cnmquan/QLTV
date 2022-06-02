/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 * Mục đích của lớp [PublisherStringConstant] là để tạo những static final 
 * gán cho những String text của Component UI cũng như các thông báo của UI
 * @author Admin
 */
public class PublisherStringConstant {

    // Tên các labe và hint text trong Publisher UI
    public static final String PUBLISHER_ID = "ID nhà xuất bản";
    public static final String PUBLISHER_NAME = "Tên nhà xuất bản";
    public static final String PUBLISHER_PHONE_NUMBER = "Số điện thoại";
    public static final String PUBLISHER_ADDRESS = "Địa chỉ";       
        
    // Thông báo lỗi khi kiểm tra
    public static final String PUBLISHER_ID_ERROR = "ID không hợp lệ";
    public static final String PUBLISHER_ID_INSERT_ERROR = "ID đã tồn tại";
    public static final String PUBLISHER_ID_UPDATE_ERROR = "ID không tồn tại";
    public static final String PUBLISHER_ID_DELETED_ERROR = "ID đã tồn tại trong danh sách xoá. Vui lòng kiểm tra lại";
    public static final String PUBLISHER_NAME_ERROR ="Tên không hợp lệ";
    public static final String PUBLISHER_PHONE_NUMBER_ERROR = "Số điện thoai không hợp lệ";
    public static final String PUBLISHER_ADDRESS_ERROR = "Địa chỉ không hợp lệ";
    
    // Thông báo sau khi thực hiện chức năng thêm
    public static final String PUBLISHER_INSERT_SUCCESS = "Thêm thông tin nhà xuất bản thành công!";
    public static final String PUBLISHER_INSERT_ERROR ="Thêm thông tin nhà xuất bản thất bại! Vui lòng thử lại!";

    // Thông báo sau khi thực hiện chức năng cập nhật
    public static final String PUBLISHER_UPDATE_SUCCESS = "Cập nhật thông tin nhà xuất bản thành công!";
    public static final String PUBLISHER_UPDATE_ERROR ="Cập nhật thông tin nhà xuất bản thất bại! Vui lòng thử lại!";
    
    // Tiêu đề của Dialog Xoá
    public static final String PUBLISHER_DELETE_TITLE = "Bạn có muốn xoá thông tin nhà xuất bản này không?";
     // Thông báo sau khi thực hiện chức năng xoá
    public static final String PUBLISHER_DELETE_SUCCESS = "Xoá thông tin nhà xuất bản thành công!";
    public static final String PUBLISHER_DELETE_ERROR ="Cập nhật thông tin nhà xuất bản thất bại! Vui lòng thử lại!";
    
}
