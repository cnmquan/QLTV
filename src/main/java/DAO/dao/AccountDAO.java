/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao;

import Base.iCRUD;
import DTO.AccountDTO;
import java.sql.SQLException;

/**
 * This interface is used to extends iCRUD and add more function
 *
 * @author Nguyễn Duy Phúc
 */
public interface AccountDAO extends iCRUD<AccountDTO> {

    /**
     * Check that there is any account with the same username
     *
     * @param username username want to check
     * @return result of check exist
     * @throws java.sql.SQLException Execption error when connect to database or
     * while querying
     */
    public boolean isExistUsername(String username) throws SQLException;

    /**
     * Verify account in database
     *
     * @param username username of account
     * @param pwd password of account
     * @return Account with that username and password
     * @throws java.sql.SQLException Execption error when connect to database or
     * while querying
     */
    public AccountDTO login(String username, String pwd) throws SQLException;

    /**
     * Recover password for one account
     *
     * @param id ID of account
     * @return result of update query
     * @throws java.sql.SQLException Execption error when connect to database or
     * while querying
     */
    public boolean recoverAccount(String id) throws SQLException;

    /**
     * Hash password
     *
     * @param pass Password want to hash
     * @return String of hash password
     */
    public String hashPassword(String pass);

    /**
     * Change password of account
     *
     * @param username username of account
     * @param pwd password of account
     * @return result of update query
     * @throws java.sql.SQLException Execption error when connect to database or
     * while querying
     */
    public boolean changePass(String username, String pwd) throws SQLException;

    /**
     * Validate when change password
     *
     * @param oldPwd old password
     * @param newPwd new password
     * @return Result of validate
     */
    public boolean validatePass(String oldPwd, String newPwd);
}
