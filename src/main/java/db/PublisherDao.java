/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.util.ArrayList;
import model.Publisher;

/**
 *
 * @author Admin
 */
public interface PublisherDao extends iCRUD<Publisher>, iGetTitle, iValidation<Publisher>, iDeleteHandler<Publisher> {
   
}
