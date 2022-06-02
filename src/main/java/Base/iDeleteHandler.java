/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Base;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public interface iDeleteHandler<T> {
    
   ArrayList<T> getDeleteList();
   boolean isExistDeleteList(String s);
   boolean moveToBin(String id);
   boolean removeFromBin(String id);
}
