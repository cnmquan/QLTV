/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;

/**
 * This is Base Manager class. All manager will extend this class and overwrite 
 * method to interact with database and handle exception  
 * @author Nguyen Duy Phuc
 * @param <MODEL> is the model class
 */
public abstract class BaseManager<MODEL> {
    protected BaseDAO<MODEL> dao;
    
    public BaseManager(){
        dao=createDAO();
    }
    
    public abstract BaseDAO<MODEL> createDAO();
    
    public abstract void findALL();
    
    public abstract void findByID(String id);
    
    public abstract void create(MODEL model);
    
    public abstract void update(MODEL model);
    
    public abstract void delete(String id);
}
