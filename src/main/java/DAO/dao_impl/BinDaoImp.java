/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import DAO.dao.BinDao;
import Adapter.DatabaseConnection;
import constant.BinStringConstant;
import constant.GeneralStringConstant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import model.Bin;
import model.BinTypeEnum;
import model.Book;
import model.Publisher;

/**
 *
 * @author Admin
 */
public class BinDaoImp implements BinDao {
    public BinDaoImp() {
    }         

    // Create singleton class for binDAO
    private static class SingletonHelper {

        private static final BinDaoImp INSTANCE = new BinDaoImp();
    }

    // Create binDAO Singleton
    public static BinDaoImp getInstance() {
        return SingletonHelper.INSTANCE;
    }
    
    private PublisherDaoImp publishDaoImp = PublisherDaoImp.getInstance();
    private BookDaoImp bookDaoImp = BookDaoImp.getInstance();

    @Override
    public ArrayList<Bin> getBinList() {                     
       ArrayList<Bin> binList = new ArrayList<>();
       ArrayList<Publisher> publisherDeleteList = publishDaoImp.getDeleteList();
       ArrayList<Book> bookDeleteList = bookDaoImp.getDeleteList();
       
       for(Publisher publisher : publisherDeleteList){
           Bin bin = new Bin(publisher.getPublisherID(), publisher.getPublisherName(), BinTypeEnum.Publisher);
           binList.add(bin);
       }   
       for(Book book : bookDeleteList){
           Bin bin = new Bin(book.getBookID(), book.getBookName(), BinTypeEnum.Book);
           binList.add(bin);
       } 
       return binList;
    }

    @Override
    public boolean recoveryData(Bin bin) {
        if(null == bin.getType()){
            return false;
        } else switch (bin.getType()) {
            case Book -> {
                return bookDaoImp.removeFromBin(bin.getId());
            }
            case Publisher -> {
                return publishDaoImp.removeFromBin(bin.getId());
            }
            default -> {
                    return false;
            }
        }
    }

    @Override
    public boolean deleteData(Bin bin) {
         if(null == bin.getType()){
            return false;
        } else switch (bin.getType()) {
            case Book -> {
                return bookDaoImp.delete(bin.getId());
            }
            case Publisher -> {
                ArrayList<Book> listBookByPublisher = bookDaoImp.getListByPublisher(bin.getId());
                for(Book book : listBookByPublisher){
                    bookDaoImp.delete(book.getBookID());
                }
                return publishDaoImp.delete(bin.getId());
            }
            default -> {
                    return false;
            }
        }
    }
    
     @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(BinStringConstant.BIN_ID);
        vector.add(BinStringConstant.BIN_NAME);
        vector.add(BinStringConstant.BIN_TYPE);
        return vector;
    }
    
}
