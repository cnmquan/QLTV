/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class PanelButtonMouseAdapter extends MouseAdapter {

    JPanel jPanel;
    int type;
    Color mouseEnterColor = new Color(0, 0, 0);
    Color mouseExitColor = new Color(51, 51, 51);
    Color mouseExitHomeColor = new Color(255, 51, 51);
    Color mouseExitWidnowColor = new Color(102, 102, 255);
    Color mousePressColor = new Color(60, 179, 113);

    public PanelButtonMouseAdapter(JPanel jpanel, int type) {
        this.jPanel = jpanel;
        this.type = type;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        jPanel.setBackground(mouseEnterColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (type == -1) {
            jPanel.setBackground(mouseExitWidnowColor);
        } else if (type == 1) {
            jPanel.setBackground(mouseExitHomeColor);
        } else {
            jPanel.setBackground(mouseExitColor);
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
