/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

import DAO.dao_impl.*;

/**
 * This class is used to create an instance
 *
 * @author Nguyễn Duy Phúc
 */
public class DIContainer {

    /**
     * Empty constructor
     */
    public DIContainer() {
    }

    /**
     * Get instance of AccountDAOImpl
     *
     * @return instance of AccountDAOImpl
     */
    public static AccountDAOImpl getAccountDAO() {
        return new AccountDAOImpl();
    }

    /**
     * Get instance of AccountDAOImpl
     *
     * @return instance of AccountDAOImpl
     */
    public static BookDaoImp getBookDao() {
        return new BookDaoImp();
    }

    /**
     * Get instance of PublisherDaoImp
     *
     * @return instance of PublisherDaoImp
     */
    public static PublisherDaoImp getPublisherDao() {
        return new PublisherDaoImp();
    }

    /**
     * Get instance of BinDaoImp
     *
     * @return instance of BinDaoImp
     */
    public static BinDaoImp getBinDao() {
        return new BinDaoImp();
    }

    /**
     * Get instance of ReaderDaoImp
     *
     * @return instance of ReaderDaoImp
     */
    public static ReaderDaoImp getReaderDao() {
        return new ReaderDaoImp();
    }

    /**
     * Get instance of BorrowDaoImp
     *
     * @return instance of BorrowDaoImp
     */
    public static BorrowDaoImp getBorrowDao() {
        return new BorrowDaoImp();
    }
}
