/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Adapter;

import DTO.PanelTypeEnum;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Dùng để tạo 1 Adapter liên quan tới Mouse (đổi màu khi hover hay nhấn)
 */
public class PanelButtonMouseAdapter extends MouseAdapter {

    // Panel cần thực hiện chức năng của Mouse
    JPanel jPanel;
    
    // PanelTypeEnum dùng để phân loại Panel 
    PanelTypeEnum type;
        
    Color mouseEnterColor = new Color(0, 0, 0);  
    Color mouseExitColor = new Color(51, 51, 51);
    Color mouseExitHomeColor = new Color(255, 51, 51);
    Color mouseExitWidnowColor = new Color(102, 102, 255);
    Color mousePressColor = new Color(60, 179, 113);

    public PanelButtonMouseAdapter(JPanel jpanel, PanelTypeEnum type) {
        this.jPanel = jpanel;
        this.type = type;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        jPanel.setBackground(mouseEnterColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        switch (type) {
            case Exit -> jPanel.setBackground(mouseExitWidnowColor);
            case Home -> jPanel.setBackground(mouseExitHomeColor);
            default -> jPanel.setBackground(mouseExitColor);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        jPanel.setBackground(mousePressColor);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        jPanel.setBackground(mouseEnterColor);
    }
}
