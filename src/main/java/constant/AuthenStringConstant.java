/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package constant;

/**
 *
 * @author Asus
 */
public class AuthenStringConstant {

    //Xác thực người dùng
    public static final String SIGN_IN = "ĐĂNG NHẬP";
    public static final String SIGN_UP = "ĐĂNG KÝ";
    public static final String FORGOT_PWD = "Quên mật khẩu";
    public static final String CHANGE_PWD = "Cập nhật mật khẩu";

    //Kết quả Xác thực người dùng
    public static final String SIGN_IN_SUCCESS = "Đăng nhập thành công";
    public static final String SIGN_UP_SUCCESS = "Đăng ký thành công";
    public static final String SIGN_IN_FAIL = "Sai tài khoản hoặc mật khẩu";
    public static final String SIGN_UP_FAIL = "Đăng ký thất bại";
    public static final String ANSWER_NOT_MATCH = "Đáp án không khớp";
    public static final String ACCOUNT_NOT_FOUND = "Không tìm thấy tài khoản này";
    //Bộ câu hỏi
    public static final String[] QUESTIONS = {
        "Biệt danh hồi bé của bạn là gì?",
        "Bạn đã gặp vợ / chồng / người yêu của mình ở thành phố nào?",
        "Tên của người bạn thời thơ ấu yêu thích của bạn là gì?",
        "Bạn đã sống trên đường nào ở lớp ba?",
        "Tháng và năm sinh nhật của anh chị em lớn nhất của bạn là gì?",
        "Tên đệm của đứa con út của bạn là gì?",
        "Tên đệm của anh chị cả của bạn là gì?",
        "Bạn đã học trường nào cho lớp sáu?",
        "Họ và tên của người anh họ lớn nhất của bạn là gì?",
        "Mẹ và bố của bạn đã gặp nhau ở thành phố hay thị trấn nào?",
        "Bạn đã ở đâu khi bạn có nụ hôn đầu tiên?",
        "Tên đầu tiên của chàng trai hoặc cô gái mà bạn hôn lần đầu tiên là gì?",
        "Anh chị em gần nhất của bạn sống ở thành phố nào?",
        "Sinh nhật của em trai út vào tháng và năm nào? (ví dụ: tháng 1 năm 1900)",
        "Công việc đầu tiên của bạn ở thành phố hay thị trấn nào?",
        "Tên nơi tổ chức tiệc cưới của bạn là gì?"
    };

    //danh sách chức năng
    public static final String[] ROLES = {
        "ADMIN",
        "LIBRARIAN"
    };    
    public static final String[] ROLES_VS={
        "Quản trị viên",
        "Thủ thư"
    };

    //Mật khẩu mặc định
    public static final String DEFAULT_PWD = "123456";
}
