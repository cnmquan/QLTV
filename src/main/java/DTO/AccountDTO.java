/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Nguyen Duy Phuc
 */
public class AccountDTO {
    String name;
    String username;
    String password;
    String email;
    String contact;
    String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Constructor of account with resultSet
     *
     * @param rs ResultSet include information of account
     * @throws SQLException Exception when cannot get data
     */
    public AccountDTO(ResultSet rs) throws SQLException {
        this.name = rs.getString("name");
        this.username = rs.getString("username");
        this.password = rs.getString("password");
        this.email = rs.getString("email");
        this.contact = rs.getString("contact");
        this.id = rs.getString("id");
    }
    
    /**
     * Constructor of account with full field
     * @param name name of librarian
     * @param username username of librarian
     * @param password password of librarian
     * @param email email of librarian
     * @param contact telephone of librarian
     */
    public AccountDTO(String name, String username, String password, String email, String contact) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    } 
}
