/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import Base.DataProvider;
import DAO.dao.AccountDAO;
import DTO.AccountDTO;
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
    public int create(AccountDTO account) {
        //Declare query
        String query = "INSERT INTO \"public\".account ("
                + "name, "
                + "username, "
                + "password, "
                + "email, "
                + "contact) "
                + "VALUES "
                + "(?,?,?,?,?);";

        //Call function to execute insert query
        int result = DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{account.getName(), account.getUsername(), account.getPassword(), account.getEmail(), account.getContact()});
        if (result > 0) {
            System.out.println("Insert successful");
        } else {
            System.out.println("Insert failed");
        }
        return result;
    }

    @Override
    public ArrayList<AccountDTO> findAll() {
        //Declare variables
        String query = "select * from \"public\".account;";
        ArrayList<AccountDTO> lAccounts = new ArrayList<>();

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.getInstance().ExecuteQuery(query, null);
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
    public AccountDTO findByID(String id) {
        // Declare variable
        String query = "select * from \"public\".account where id = '" + id + "'";
        AccountDTO account = null;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.getInstance().ExecuteQuery(query, null);
            while (data.next()) {
                account = new AccountDTO(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return account;
    }

    @Override
    public int update(AccountDTO account) {
        //Declare query
        String query = "UPDATE  \"public\".account SET "
                + "name=?, "
                + "username=?, "
                + "password=?, "
                + "email=?, "
                + "contact=? "
                + "WHERE id = ?";

        //Call function to execute update query
        Object[] parameter = new Object[]{
            account.getName(),
            account.getUsername(),
            account.getPassword(),
            account.getEmail(),
            account.getContact(),
            Integer.parseInt(account.getId())};
        int result = DataProvider.getInstance().ExecuteNonQuery(query, parameter);
        if (result > 0) {
            System.out.println("Update successful");
        } else {
            System.out.println("Update failed");
        }
        return result;
    }

    @Override
    public int delete(AccountDTO account) {
        //Declare query
        String query = "delete from \"public\".account where id = '" + account.getId() + "';";

        //Call function to execute to delete query
        int result = DataProvider.getInstance().ExecuteNonQuery(query, null);
        if (result > 0) {
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
            ResultSet data = DataProvider.getInstance().ExecuteQuery(query, null);
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
    public boolean isExistEmail(String email) {
        // Declare variable
        String query = "select * from \"public\".account where email = '" + email + "'";
        boolean isExist = false;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.getInstance().ExecuteQuery(query, null);
            isExist = data.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isExist;
    }

    @Override
    public AccountDTO login(String username, String pwd) {
        // Declare variable
        String query = "select * from \"public\".account where username = '" + username + "' and password = '" + pwd + "'";
        AccountDTO account = null;

        //Call function to execute select query
        try {
            ResultSet data = DataProvider.getInstance().ExecuteQuery(query, null);
            while (data.next()) {
                account = new AccountDTO(data);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return account;
    }

    @Override
    public int delete(String id) {
        //Declare query
        String query = "delete from \"public\".account where id = '" + id + "';";

        //Call function to execute to delete query
        int result = DataProvider.getInstance().ExecuteNonQuery(query, null);
        if (result > 0) {
            System.out.println("Delete successful");
        } else {
            System.out.println("Delete failed");
        }
        return result;
    }

    @Override
    public int recoverAccount(String id) {
        //Declare query
        String query = "UPDATE  \"public\".account SET "
                + "password='123456' "
                + "WHERE id =?;";

        //Call function to execute update query
        int result = DataProvider.getInstance().ExecuteNonQuery(query, new Object[]{id});
        if (result > 0) {
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
}
