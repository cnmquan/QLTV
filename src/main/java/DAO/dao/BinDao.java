/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.dao;

import Base.iGetTitle;
import java.util.ArrayList;
import DTO.Bin;

/**
 *
 * @author Admin
 */
public interface BinDao extends iGetTitle{
    ArrayList<Bin> getBinList();
    boolean recoveryData(Bin bin);
    boolean deleteData(Bin bin);
}
