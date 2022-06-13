/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import DAO.dao_impl.*;

/**
 *
 * @author Asus
 */
public class DIContainer {

    public DIContainer() {
    }

    public static AccountDAOImpl getAccountDAO() {
        return new AccountDAOImpl();
    }

    // Dùng để khởi tạo 1 BookDaoImp duy nhất
    public static BookDaoImp getBookDao() {
        return new BookDaoImp();
    }

    // Dùng để khởi tạo 1 PublisherDaoImp duy nhất
    public static PublisherDaoImp getPublisherDao() {
        return new PublisherDaoImp();
    }

    // Dùng để khởi tạo 1 BinDaoImp duy nhất
    public static BinDaoImp getBinDao() {
        return new BinDaoImp();
    }

    public static ReaderDaoImp getReaderDao() {
        return new ReaderDaoImp();
    }

    public static BorrowDaoImp getBorrowDao() {
        return new BorrowDaoImp();
    }
}
