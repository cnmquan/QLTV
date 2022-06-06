package DTO;

import DTO.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Vector;

public class Reader {
    private String id;
    private String name;
    private String phoneNumber;

    public Reader(String id, String name, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public Reader() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Vector convertToVector() {
        Vector vector = new Vector();
        vector.add(this.id);
        vector.add(this.name);
        vector.add(this.phoneNumber);
        return vector;
    }

    public static Reader covertFromResultSet(ResultSet rs) throws SQLException {
        Reader reader = new Reader();
        reader.setId(rs.getString("reader_id"));
        reader.setName(rs.getString("reader_name"));
        reader.setPhoneNumber(rs.getString("reader_phone_number"));

        return reader;
    }
}
