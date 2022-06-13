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
 * BinDaoImp Dùng để thực thi hàm được implements từ BinDao
 */
public class BinDaoImp implements BinDao {

    // Default Constructor 
    public BinDaoImp() {
    }

    // Gọi PublishDaoImp để thực thi các hàm liên quan tới nhà xuất bản
    private final PublisherDaoImp publishDaoImp = DIContainer.getPublisherDao();

    // Gọi BookDaoImp để thực thi các hàm liên quan tới sách
    private final BookDaoImp bookDaoImp = DIContainer.getBookDao();

    // Dùng để lấy danh sách trong Bin
    @Override
    public ArrayList<Bin> getBinList() {
        ArrayList<Bin> binList = new ArrayList<>();

        // Lấy danh sách ẩn trong danh sách nhà xuất bản
        ArrayList<Publisher> publisherDeleteList = publishDaoImp.getDeleteList();

        // Lấy danh sách ẩn trong danh sách sách
        ArrayList<Book> bookDeleteList = bookDaoImp.getDeleteList();

        // Thêm danh sách nhà xuất bản vào danh sách thùng rác
        for (Publisher publisher : publisherDeleteList) {
            Bin bin = new Bin(publisher.getPublisherID(), publisher.getPublisherName(), BinTypeEnum.Publisher);
            binList.add(bin);
        }

        // Thêm danh sách sách vào danh sách thùng rác
        for (Book book : bookDeleteList) {
            Bin bin = new Bin(book.getBookID(), book.getBookName(), BinTypeEnum.Book);
            binList.add(bin);
        }
        return binList;
    }

    // Dùng để khôi phục data từ Bin
    @Override
    public boolean recoveryData(Bin bin) {
        // Nếu không xác nhận được dữ liệu thì trả về false
        if (null == bin.getType()) {
            return false;
        } else {
            switch (bin.getType()) {
                // Xác nhận dữ liệu sách thì khôi phục dữ liệu sách
                case Book -> {
                    return bookDaoImp.removeFromBin(bin.getId());
                }
                // Xác nhận dữ liệu nhà xuất bản thì khôi phục dữ liệu nhà xuất bản
                case Publisher -> {
                    return publishDaoImp.removeFromBin(bin.getId());
                }
                // Nếu không thì trả về false
                default -> {
                    return false;
                }
            }
        }
    }

    // Dùng để xoá data khỏi Bin
    @Override
    public boolean deleteData(Bin bin) {
        // Nếu không xác nhận được dữ liệu thì trả về false
        if (null == bin.getType()) {
            return false;
        } else {
            switch (bin.getType()) {
                // Xác nhận dữ liệu sách thì xoá dữ liệu sách
                case Book -> {
                    return bookDaoImp.delete(bin.getId());
                }
                // Xác nhận dữ liệu nhà xuất bản thì xoá dữ liệu nhà xuất bản
                case Publisher -> {
                    return publishDaoImp.delete(bin.getId());
                }
                // Nếu không thì trả về false
                default -> {
                    return false;
                }
            }
        }
    }

    // Lấy thông tin cột dưới dạng Vector
    @Override
    public Vector getTitleColumn() {
        Vector vector = new Vector();
        vector.add(BinStringConstant.BIN_ID);
        vector.add(BinStringConstant.BIN_NAME);
        vector.add(BinStringConstant.BIN_TYPE);
        return vector;
    }

}
