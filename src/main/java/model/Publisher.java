/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Vector;

/**
 *
 * @author Admin
 */
public class Publisher {

    private String publisherID;
    private String publisherName;
    private String publisherPhoneNumber;
    private String publisherAddress;

    public Publisher(String publisherID, String publisherName, String publisherPhoneNumber, String publisherAddress) {
        this.publisherID = publisherID;
        this.publisherName = publisherName;
        this.publisherPhoneNumber = publisherPhoneNumber;
        this.publisherAddress = publisherAddress;
    }

    public Publisher() {
    }

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
    
     public Vector convertToVector(){
        Vector vector = new Vector();
        vector.add(this.publisherID);
        vector.add(this.publisherName);
        vector.add(this.publisherPhoneNumber);
        vector.add(this.publisherAddress);
        return vector;
    }

}
