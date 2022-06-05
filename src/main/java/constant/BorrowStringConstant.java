package constant;

public class BorrowStringConstant {
    public static final String BORROW_ID = "ID mượn";
    public static final String BORROW_QUANTITY = "Số lượng";
    public static final String BORROW_DATE = "Ngày mượn";
    public static final String RETURN_DATE = "Ngày trả";

    // Thông báo lỗi khi kiểm tra
    public static final String BORROW_ID_ERROR = "ID không hợp lệ";
    public static final String BORROW_ID_INSERT_ERROR = "ID đã tồn tại";
    public static final String BORROW_ID_UPDATE_ERROR = "ID không tồn tại";
    public static final String BORROW_ID_DELETED_ERROR = "ID đã tồn tại trong danh sách xoá. Vui lòng kiểm tra lại";
    public static final String BORROW_NAME_ERROR ="Tên không hợp lệ";
    public static final String BORROW_READER_ERROR = "Không có tên độc giả nào được tìm thấy";
    public static final String BORROW_BOOK_ERROR = "Không có tiêu đề sách nào được tìm thấy";

    public static final String BORROW_QUANTITY_ERROR = "Số lượng sách mượn vượt quá số lượng sách còn lại của thư viện";
    public static final String BORROW_DATE_ERROR = "Ngày trả sách không được nhỏ hơn ngày mượn sách";
    // Thông báo sau khi thực hiện chức năng thêm
    public static final String BORROW_INSERT_SUCCESS = "Thêm thông tin mượn sách thành công!";
    public static final String BORROW_INSERT_ERROR ="Thêm thông tin mượn sách thất bại! Vui lòng thử lại!";

    // Thông báo sau khi thực hiện chức năng cập nhật
    public static final String BORROW_UPDATE_SUCCESS = "Cập nhật thông tin mượn sách thành công!";
    public static final String BORROW_UPDATE_ERROR ="Cập nhật thông tin mượn sách thất bại! Vui lòng thử lại!";

    // Tiêu đề của Dialog Xoá
    public static final String BORROW_DELETE_TITLE = "Bạn có muốn chuyển thông tin mượn sách này vào thùng rác không?";
    // Thông báo sau khi thực hiện chức năng xoá
    public static final String BORROW_DELETE_SUCCESS = "Chuyển thông tin mượn sách vào thùng rác thành công!";
    public static final String BORROW_DELETE_ERROR ="Chuyển thông tin mượn sách vào thùng rác thất bại! Vui lòng thử lại!";

}
