/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Adapter.SupportFunction;
import java.util.Vector;

/**
 * Lớp Bin dùng để chứa những thông tin liên quan tới Bin Có những hàm để hỗ
 * trợ convert sang các kiểu dữ liệu cần thiết
 */
public class Bin {
    
    public Bin(){
        
    }

    public Bin(String id, String name, BinTypeEnum type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
    
    private String id;
    private String name;
    private BinTypeEnum type;

    public BinTypeEnum getType() {
        return type;
    }

    public void setType(BinTypeEnum type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Vector convertToVector(){
        Vector vector = new Vector();
        vector.add(this.id);
        vector.add(this.name);
        vector.add(SupportFunction.convertBinTypeEnumToString(this.type));
        return vector;
    }
    
}
