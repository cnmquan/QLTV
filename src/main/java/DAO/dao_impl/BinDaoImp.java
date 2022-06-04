/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.dao_impl;

import DAO.dao.BinDao;
import Base.DIContainer;
import constant.BinStringConstant;
import java.util.ArrayList;
import java.util.Vector;
import DTO.Bin;
import DTO.BinTypeEnum;
import DTO.Book;
import DTO.Publisher;

/**
 *
 * @author Admin
 */
public class BinDaoImp implements BinDao {
     public BinDaoImp() {
        
    }         
    
    private final PublisherDaoImp publishDaoImp = DIContainer.getPublisherDao();
    private final BookDaoImp bookDaoImp = DIContainer.getBookDao();

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
