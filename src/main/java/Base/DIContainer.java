/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import DAO.dao_impl.AccountDAOImpl;
import DAO.dao_impl.BinDaoImp;
import DAO.dao_impl.BookDaoImp;
import DAO.dao_impl.PublisherDaoImp;

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
    
    public static BookDaoImp  getBookDao(){
        return new BookDaoImp();
    }
    
    public static PublisherDaoImp getPublisherDao(){
        return new PublisherDaoImp();
    }   
    
    public static BinDaoImp getBinDao(){
        return new BinDaoImp();
    }
}
