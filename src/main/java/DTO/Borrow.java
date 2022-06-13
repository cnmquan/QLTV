package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class Borrow {

    private String borrowId;
    private String readerId;
    private String bookId;
    private int quantity;
    private Date borrowDate;
    private Date returnDate;

    public Borrow(String borrowId, String readerId, String bookId, int quantity, Date borrowDate, Date returnDate) {
        this.borrowId = borrowId;
        this.readerId = readerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrow() {
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Vector convertToVector() {
        Vector vector = new Vector();
        vector.add(this.borrowId);
        vector.add(this.readerId);
        vector.add(this.bookId);
        vector.add(this.quantity);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        vector.add(simpleDateFormat.format(this.borrowDate));
        if (returnDate != null) {

            vector.add(simpleDateFormat.format(this.returnDate));
        } else {

            vector.add("");
        }
        return vector;
    }

    public static Borrow convertFromResultSet(ResultSet rs) throws SQLException, ParseException {
        Borrow borrow = new Borrow();

        borrow.setBorrowId(rs.getString("borrow_id"));
        borrow.setReaderId(rs.getString("reader_id"));
        borrow.setBookId(rs.getString("book_id"));
        borrow.setQuantity(Integer.parseInt(rs.getString("quantity")));
        borrow.setBorrowDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("borrow_date")));
        if (rs.getString("return_date") != null) {

            borrow.setReturnDate(new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("return_date")));
        } else {

            borrow.setReturnDate(null);
        }
        return borrow;
    }
}
