/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import Base.DIContainer;
import Base.DataProvider;
import DAO.dao.AccountDAO;
import DTO.AccountDTO;
import constant.AuthenStringConstant;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class to implement AccountDAO
 *
 * @author Nguyễn Duy Phúc
 */
public class AccountDAOImpl implements AccountDAO {

    /**
     * Insert new account to database
     *
     * @param account Account want to intsert
     * @return result of insert query
     */
    @Override
    public boolean insert(AccountDTO account) {
        //Declare query
        String query = "INSERT INTO \"public\".account ("
                + "name, "
                + "username, "
                + "password, "
                + "email, "
                + "contact,"
                + "question,"
                + "answer,"
                + "role) "
                + "VALUES "
                + "(?,?,?,?,?,?,?,?);";
        //Hash password
        String hashPass = DIContainer.getAccountDAO().hashPassword(account.getPassword());

        //Call function to execute insert query
        boolean result = DataProvider.ExecuteNonQuery(query, new Object[]{
            account.getName(),
            account.getUsername(),
            hashPass,
            account.getEmail(),
            account.getContact(),
            account.getQuestion(),
            account.getAnswer(),
            "LIBRARIAN"});
        if (result) {
            System.out.println("Insert successful");
        } else {
            System.out.println("Insert failed");
        }
        return result;
    }

    /**
     * Get all account in database
     *
     * @return List of AccountDTO
     */
    @Override
    public ArrayList<AccountDTO> getAll() {
        //Declare variables
        String query = "select * from \"public\".account order by id;";
        ArrayList<AccountDTO> lAccounts = new ArrayList<>();

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            while (data.next()) {
                //Add student to list
                AccountDTO acccount = new AccountDTO(data);
                lAccounts.add(acccount);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return lAccounts;
    }

    /**
     * Find one Account with one atribute
     *
     * @param attribute name of attribute
     * @param s String that want to check
     * @return Suitable account
     */
    @Override
    public AccountDTO getAttribute(String attribute, String s) {
        // Declare variable
        String query = "select * from \"public\".account where " + attribute + " = '" + s + "'";
        AccountDTO account = null;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            while (data.next()) {
                account = new AccountDTO(data);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return account;
    }

    /**
     * Update account to database
     *
     * @param account Account want to update
     * @return Result of update query
     */
    @Override
    public boolean update(AccountDTO account) {
        //Declare query
        String query = "UPDATE  \"public\".account SET "
                + "name=?, "
                + "username=?, "
                + "password=?, "
                + "email=?, "
                + "contact=?, "
                + "question=?, "
                + "answer=?, "
                + "role=? "
                + "WHERE id = ?";
        //Hash password
        String hashPass = DIContainer.getAccountDAO().hashPassword(account.getPassword());

        //Call function to execute update query
        Object[] parameter = new Object[]{
            account.getName(),
            account.getUsername(),
            hashPass,
            account.getEmail(),
            account.getContact(),
            account.getQuestion(),
            account.getAnswer(),
            account.getRole(),
            Integer.parseInt(account.getId())};
        boolean result = DataProvider.ExecuteNonQuery(query, parameter);
        if (result) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }
        return result;
    }

    /**
     * Delete one account from database
     *
     * @param account Account want to delete
     * @return result of delete query
     */
    @Override
    public boolean delete(AccountDTO account) {
        //Declare query
        String query = "delete from \"public\".account where id = '" + account.getId() + "';";

        //Call function to execute to delete query
        boolean result = DataProvider.ExecuteNonQuery(query, null);
        if (result) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed");
        }
        return result;
    }

    /**
     * Check that there is any account with the same username
     *
     * @param username username want to check
     * @return result of check exist
     */
    @Override
    public boolean isExistUsername(String username) {
        // Declare variable
        String query = "select * from \"public\".account where username = '" + username + "';";
        boolean isExist = false;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            isExist = data.next();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return isExist;
    }

    /**
     * Verify account in database
     *
     * @param username username of account
     * @param pwd password of account
     * @return Account with that username and password
     */
    @Override
    public AccountDTO login(String username, String pwd) {
        // Declare variable
        String hashPass = DIContainer.getAccountDAO().hashPassword(pwd);
        String query = "select * from \"public\".account where username = '" + username + "' and password = '" + hashPass + "'";
        AccountDTO account = null;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            while (data.next()) {
                account = new AccountDTO(data);
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return account;
    }

    /**
     * Delete account by its ID
     *
     * @param id ID of account
     * @return result of delete query
     */
    @Override
    public boolean delete(String id) {
        //Declare query
        String query = "delete from \"public\".account where id = '" + id + "';";

        //Call function to execute to delete query
        boolean result = DataProvider.ExecuteNonQuery(query, null);
        if (result) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed");
        }
        return result;
    }

    /**
     * Recover password for one account
     *
     * @param id ID of account
     * @return result of update query
     */
    @Override
    public boolean recoverAccount(String id) {
        //Declare query
        String defaultPwd = AuthenStringConstant.DEFAULT_PWD;
        String hashPass = DIContainer.getAccountDAO().hashPassword(defaultPwd);
        String query = "UPDATE  \"public\".account SET "
                + "password=? "
                + "WHERE id =?;";

        //Call function to execute update query
        boolean result = DataProvider.ExecuteNonQuery(query, new Object[]{hashPass, Integer.parseInt(id)});
        if (result) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }
        return result;
    }

    /**
     * Hash password
     *
     * @param pass Password want to hash
     * @return String of hash password
     */
    @Override
    public String hashPassword(String pass) {
        //Source: https://www.geeksforgeeks.org/md5-hash-in-java/
        try {
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(pass.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashPass = no.toString(16);
            while (hashPass.length() < 32) {
                hashPass = "0" + hashPass;
            }
            return hashPass;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Change password of account
     *
     * @param username username of account
     * @param pwd password of account
     * @return result of update query
     */
    @Override
    public boolean changePass(String username, String pwd) {
        //Declare query
        String hashPass = DIContainer.getAccountDAO().hashPassword(pwd);
        String query = "UPDATE  \"public\".account SET "
                + "password=? "
                + "WHERE username =?;";

        //Call function to execute update query
        boolean result = DataProvider.ExecuteNonQuery(query, new Object[]{hashPass, username});
        if (result) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }
        return result;
    }

    /**
     * Validate when change password
     *
     * @param oldPwd old password
     * @param newPwd new password
     * @return Result of validate
     */
    @Override
    public boolean validatePass(String oldPwd, String newPwd) {
        String hashNewPass = DIContainer.getAccountDAO().hashPassword(newPwd);
        return hashNewPass == null ? oldPwd == null : hashNewPass.equals(oldPwd);
    }
}
