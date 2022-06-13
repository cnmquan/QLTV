/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 * Mục đích của lớp [BookStringConstant] là để tạo những static final gán
 * cho những String text của Component UI cũng như các thông báo của UI
 */
public class BookStringConstant {

     // Tên các label và hint text trong Publisher UI
    public static final String BOOK_ID = "ID sách";
    public static final String BOOK_TITLE = "Tiêu đề";
    public static final String BOOK_CATEGORY = "Thể loại";
    public static final String BOOK_AUTHOR = "Tác giả";
    public static final String BOOK_PUBLISHER = "Nhà xuất bản";
    public static final String BOOK_PUBLISH_YEAR = "Năm xuất bản";
    public static final String BOOK_PAGE_NUMBER = "Số trang";
    public static final String BOOK_QUANTITY = "Số lượng";
    public static final String BOOK_PRICE = "Giá tiền";

    // Thông báo lỗi khi kiểm tra
    public static final String BOOK_ID_ERROR = "ID không hợp lệ";
    public static final String BOOK_ID_INSERT_ERROR = "ID đã tồn tại";
    public static final String BOOK_ID_UPDATE_ERROR = "ID không tồn tại";
    public static final String BOOK_ID_DELETED_ERROR = "ID đã tồn tại trong danh sách xoá. Vui lòng kiểm tra lại";
    public static final String BOOK_TITLE_ERROR = "Tiêu đề không hợp lệ";
    public static final String BOOK_CATEGORY_ERROR = "Thể loại không hợp lệ";
    public static final String BOOK_AUTHOR_ERROR = "Tác giả không hợp lệ";
    public static final String BOOK_PUBLISHER_ERROR = "Nhà xuất bản không hợp lệ";
    public static final String BOOK_PUBLISH_YEAR_EMPTY_ERROR = "Năm xuất bản không hợp lệ";
    public static final String BOOK_PUBLISH_YEAR_ERROR = "Năm xuất bản đã nhập lớn hơn năm hiện tại";
    public static final String BOOK_PAGE_NUMBER_ERROR = "Số trang không hợp lệ";
    public static final String BOOK_QUANTITY_ERROR = "Số lượng không hợp lệ";
    public static final String BOOK_PRICE_ERROR = "Giá tiền không hợp lệ";

    // Thông báo sau khi thực hiện chức năng thêm
    public static final String BOOK_INSERT_SUCCESS = "Thêm thông tin sách thành công!";
    public static final String BOOK_INSERT_ERROR = "Thêm thông tin sách thất bại! Vui lòng thử lại!";

    // Thông báo sau khi thực hiện chức năng cập nhật
    public static final String BOOK_UPDATE_SUCCESS = "Cập nhật thông tin sách thành công!";
    public static final String BOOK_UPDATE_ERROR = "Cập nhật thông tin sách thất bại! Vui lòng thử lại!";

    // Tiêu đề của Dialog Xoá
    public static final String BOOK_DELETE_TITLE = "Bạn có muốn chuyển thông tin sách này vào thùng rác không?";
    // Thông báo sau khi thực hiện chức năng xoá
    public static final String BOOK_DELETE_SUCCESS = "Chuyển thông tin sách vào thùng rác thành công!";
    public static final String BOOK_DELETE_ERROR = "Chuyển thông tin sách vào thùng rác thất bại! Vui lòng thử lại!";
}
