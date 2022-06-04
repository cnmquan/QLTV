package model;

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
}
