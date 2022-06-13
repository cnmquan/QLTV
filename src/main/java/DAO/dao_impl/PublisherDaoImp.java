/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import DAO.dao.PublisherDao;
import Base.DataProvider;
import constant.PublisherStringConstant;
import DTO.Publisher;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import constant.GeneralStringConstant;

/**
 * PublisherDaoImp Dùng để thực thi hàm được implements từ PublisherDao
 */
public class PublisherDaoImp implements PublisherDao {

    // Default Constructor
    public PublisherDaoImp() {
    }

    // Lấy toàn bộ danh sách từ database của bảng Nhà xuất bản
    @Override
    public ArrayList<Publisher> getAll() {
        ArrayList<Publisher> list = new ArrayList<>();

        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address "
                + "FROM publishers "
                + "WHERE publisher_is_deleted = false "
                + "ORDER BY publisher_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);
        try {
            while (rs.next()) {
                Publisher publisher = Publisher.convertFromResultSet(rs);
                list.add(publisher);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    // Lấy Nhà xuất bản từ giá trị a của thuộc tính attribute đã chọn
    @Override
    public Publisher getAttribute(String attribute, String a) {
        Publisher publisher = new Publisher();
        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address "
                + "from publishers "
                + "WHERE " + attribute + " = ?";

        ResultSet rs = DataProvider.ExecuteQuery(sql, new Object[]{
            a
        });

        try {
            while (rs.next()) {
                publisher = Publisher.convertFromResultSet(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return publisher;
    }

    // Lấy danh sách các đối tượng Nhà xuất bản đã bị ẩn trong database
    @Override
    public ArrayList<Publisher> getDeleteList() {
        ArrayList<Publisher> list = new ArrayList<>();
        String sql = "SELECT "
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number,"
                + "publisher_address "
                + "FROM publishers "
                + "WHERE publisher_is_deleted = true "
                + "ORDER BY publisher_id";

        ResultSet rs = DataProvider.ExecuteQuery(sql, null);

        try {
            while (rs.next()) {
                Publisher publisher = Publisher.convertFromResultSet(rs);
                list.add(publisher);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return list;
    }

    // Chèn phần từ nhà xuất bản vào bảng trong database
    @Override
    public boolean insert(Publisher t) {
        boolean rowInserted;
        String sql = "INSERT INTO publishers("
                + "publisher_id, "
                + "publisher_name, "
                + "publisher_phone_number, "
                + "publisher_address, "
                + "publisher_is_deleted) "
                + "VALUES("
                + "?, "
                + "?, "
                + "?, "
                + "?, "
                + "false)";

        rowInserted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherID(),
            t.getPublisherName(),
            t.getPublisherPhoneNumber(),
            t.getPublisherAddress()
        });

        return rowInserted;
    }

    // Cập nhật phần tử nhà xuất bản của bảng trong database
    @Override
    public boolean update(Publisher t) {
        boolean rowUpdated;
        String sql = "UPDATE publishers "
                + "SET publisher_name = ?, "
                + "publisher_phone_number = ?, "
                + "publisher_address = ? "
                + "WHERE publisher_id = ?";

        rowUpdated = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherName(),
            t.getPublisherPhoneNumber(),
            t.getPublisherAddress(),
            t.getPublisherID()
        });

        return rowUpdated;
    }

    // Xoá phần tử với ID tương ứng của bảng Nhà xuất bản trong database
    @Override
    public boolean delete(String id) {
        boolean rowDeleted;
        String sql = "DELETE FROM publishers "
                + "WHERE publisher_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowDeleted;
    }

    // Xoá phần tử nhà xuất bản ra khỏi bảng trong database
    @Override
    public boolean delete(Publisher t) throws SQLException {
        boolean rowDeleted;
        String sql = "DELETE FROM publishers "
                + "WHERE publisher_id = ?";

        rowDeleted = DataProvider.ExecuteNonQuery(sql, new Object[]{
            t.getPublisherID()
        });

        return rowDeleted;
    }

    // Chuyển một đối tượng nhà xuất bản vào thùng rác
    @Override
    public boolean moveToBin(String id) {
        boolean rowMovedToBin;
        String sql = "UPDATE publishers "
                + "SET publisher_is_deleted = true "
                + "FWHERE publisher_id = ?";

        rowMovedToBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowMovedToBin;
    }

    // Chuyển một đối tượng nhà xuất bản ra khỏi thùng rác
    @Override
    public boolean removeFromBin(String id) {
        boolean rowRemovedFromBin;
        String sql = "UPDATE publishers "
                + "SET publisher_is_deleted = false  "
                + "WHERE publisher_id = ?";

        rowRemovedFromBin = DataProvider.ExecuteNonQuery(sql, new Object[]{
            id
        });

        return rowRemovedFromBin;
    }

    // Lấy thông tin cột dưới dạng Vector
    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(PublisherStringConstant.PUBLISHER_ID);
        vector.add(PublisherStringConstant.PUBLISHER_NAME);
        vector.add(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        vector.add(PublisherStringConstant.PUBLISHER_ADDRESS);
        return vector;
    }

    // Kiểm tra xem chuỗi S có tồn tại trong danh sách bảng nhà xuất bản trong databse
    @Override
    public boolean isExist(ArrayList<Publisher> t, String s) {
        for (Publisher publisher : t) {
            if (publisher.getPublisherID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra xem chuỗi s này có trùng với id trong danh sách ẩn 
    @Override
    public boolean isExistDeleteList(String s) {
        ArrayList<Publisher> deleteList = getDeleteList();
        for (Publisher publisher : deleteList) {
            if (publisher.getPublisherID().compareTo(s) == 0) {
                return true;
            }
        }
        return false;
    }

    // Kiểm tra chuỗi s có phải là chuỗi hợp lệ để thêm vào databse
    @Override
    public boolean validateString(String s) {
        return s.isBlank() || s.isEmpty();
    }

}
