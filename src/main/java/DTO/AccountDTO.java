/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to retrieve data from a database
 *
 * @author Nguyen Duy Phuc
 */
public class AccountDTO {

    //Variables of AccountDTO
    String name;
    String username;
    String password;
    String email;
    String contact;
    String role;
    String question;
    String answer;
    String id;

    //Getter & Setter
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

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
        this.role = rs.getString("role");
        this.question = rs.getString("question");
        this.answer = rs.getString("answer");
        this.id = rs.getString("id");
    }

    /**
     * Constructor of account with almost field (for create new account)
     *
     * @param name name of librarian
     * @param username username of librarian
     * @param password password of librarian
     * @param email email of librarian
     * @param contact telephone of librarian
     * @param question question security
     * @param answer answer for question security
     */
    public AccountDTO(String name, String username, String password, String email, String contact, String question, String answer) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.question = question;
        this.answer = answer;
    }

    /**
     * Constructor of account with full field
     *
     * @param name name of librarian
     * @param username username of librarian
     * @param password password of librarian
     * @param email email of librarian
     * @param contact telephone of librarian
     * @param role role of account
     * @param question question security
     * @param answer answer for question security
     * @param id id of account
     */
    public AccountDTO(String id, String name, String username, String password, String email, String contact, String question, String answer, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
        this.role = role;
        this.question = question;
        this.answer = answer;
        this.id = id;
    }
}
