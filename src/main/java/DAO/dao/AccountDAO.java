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

    public boolean isExistUsername(String username) throws SQLException;

    public AccountDTO login(String username, String pwd) throws SQLException;

    public boolean recoverAccount(String id) throws SQLException;

    public String hashPassword(String pass);

    public boolean changePass(String username, String pwd) throws SQLException;

    public boolean validatePass(String oldPwd, String newPwd);
}
