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
 *
 * @author Asus
 */
public class AccountDAOImpl implements AccountDAO {

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
            ex.printStackTrace();
        }
        return lAccounts;
    }

    @Override
    public AccountDTO getAttribute(String atribute, String s ) {
        // Declare variable
        String query = "select * from \"public\".account where "+atribute+" = '" + s + "'";
        AccountDTO account = null;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            while (data.next()) {
                account = new AccountDTO(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return account;
    }

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

    @Override
    public boolean isExistUsername(String username) {
        // Declare variable
        String query = "select * from \"public\".account where username = '" + username + "';";
        boolean isExist = false;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.ExecuteQuery(query, null);
            if (data.next()) {
                isExist = true;
            } else {
                isExist = false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isExist;
    }

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
            ex.printStackTrace();
        }
        return account;
    }

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

    @Override
    public boolean changePass(String username, String pwd)  {
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

    @Override
    public boolean validatePass(String oldPwd, String newPwd) {
        String hashNewPass = DIContainer.getAccountDAO().hashPassword(newPwd);
        return hashNewPass == null ? oldPwd == null : hashNewPass.equals(oldPwd);
    }
}
