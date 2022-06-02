/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import DAO.dao.AccountDAO;
import DAO.dao_impl.AccountDAOImpl;

/**
 *
 * @author Asus
 */
public class DIContainer {

    public DIContainer() {
    }
    
    public static AccountDAOImpl getAccountDAO(){
        return new AccountDAOImpl();
    }
}
