/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.dao;

import Base.iGetTitle;
import java.util.ArrayList;
import DTO.Bin;

/**
 * BinDao Dùng để xử lí các chức năng liên quan tới Bin
 */
public interface BinDao extends iGetTitle{
    // Dùng để lấy danh sách trong Bin
    ArrayList<Bin> getBinList();
    
    // Dùng để khôi phục data từ Bin
    boolean recoveryData(Bin bin);
    
    // Dùng để xoá data khỏi Bin
    boolean deleteData(Bin bin);
}
