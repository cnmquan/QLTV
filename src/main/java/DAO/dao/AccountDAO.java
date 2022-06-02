/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao;

import Base.BaseDAO;
import DTO.AccountDTO;
import java.sql.SQLException;

/**
 *
 * @author Asus
 */
public interface AccountDAO extends BaseDAO<AccountDTO> {
    public boolean isExistUsername(String username)throws SQLException;
    public boolean isExistEmail(String email)throws SQLException;
    public AccountDTO login(String username, String pwd)throws SQLException;
}
