/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package db;

import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public interface iValidation <T> {
    boolean isExist(ArrayList<T> t, String s);    
    boolean validateString(String s);    
}
