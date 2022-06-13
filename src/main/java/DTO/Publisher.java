/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

/**
 * Lớp Publisher dùng để chứa những thông tin liên quan tới Publisher
 * Có những hàm để hỗ  trợ convert sang các kiểu dữ liệu cần thiết
 */
public class Publisher {

    // ID của Nhà xuất bản
    private String publisherID;
    
    // Tên của Nhà xuất bản
    private String publisherName;
    
    // Số điện thoại của Nhà xuất bản
    private String publisherPhoneNumber;
    
    // Địa chỉ của Nhà xuất bản
    private String publisherAddress;

    // Constructor Đầy đủ tham số
    public Publisher(String publisherID, String publisherName, String publisherPhoneNumber, String publisherAddress) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.publisherPhoneNumber = publisherPhoneNumber;
        this.publisherAddress = publisherAddress;
    }

    // Default Constructors
    public Publisher() {
    }

    /**
     * Các hàm Get/Set cho các thuộc tính của Nhà xuất bản
     */
    public String getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(String publisherID) {
        this.publisherID = publisherID;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherPhoneNumber() {
        return publisherPhoneNumber;
    }

    public void setPublisherPhoneNumber(String publisherPhoneNumber) {
        this.publisherPhoneNumber = publisherPhoneNumber;
    }

    public String getPublisherAddress() {
        return publisherAddress;
    }

    public void setPublisherAddress(String publisherAddress) {
        this.publisherAddress = publisherAddress;
    }

    // Convert to Vector
    public Vector convertToVector() {
        Vector vector = new Vector();
        vector.add(this.publisherID);
        vector.add(this.publisherName);
        vector.add(this.publisherPhoneNumber);
        vector.add(this.publisherAddress);
        return vector;
    }

    // Convert From ResultSet
    public static Publisher convertFromResultSet(ResultSet rs) throws SQLException {
        Publisher publisher = new Publisher();

        publisher.setPublisherID(rs.getString("publisher_id"));
        publisher.setPublisherName(rs.getString("publisher_name"));
        publisher.setPublisherPhoneNumber(rs.getString("publisher_phone_number"));
        publisher.setPublisherAddress(rs.getString("publisher_address"));

        return publisher;
    }
}
