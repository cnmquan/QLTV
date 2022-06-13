/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import DAO.dao_impl.ReaderDaoImp;
import DTO.TypeFunctionEnum;
import constant.GeneralStringConstant;
import constant.ReaderStringConstant;
import DTO.Reader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author Admin
 */
public class ManageReaderPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageReaderPanel
     */
    private final ReaderDaoImp readerDaoImp;
    private ArrayList<Reader> readersList;
    private Vector vctHeader;
    private Vector vctData;

    public ManageReaderPanel() {
        initComponents();
        this.readerDaoImp = ReaderDaoImp.getInstance();

        resetData();
        setBounds(0, 0, 1170, 630);
    }

    void resetData() {
        setDefaultText();
        setDefaultTable();
        getVectorData();
        showTableData(vctData);
    }

    void getVectorData() {
        this.readersList = readerDaoImp.getAll();
        this.vctData = new Vector();
        for (int i = 0; i < this.readersList.size(); i++) {
            Vector vctRow = this.readersList.get(i).convertToVector();
            vctData.add(vctRow);
        }
    }

    public void showTableData(Vector vctData) {

        this.vctHeader = this.readerDaoImp.getTitleColumn();

        jTableReader.setModel(new DefaultTableModel(vctData, vctHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
    }

    private void setDefaultText() {

        jTextFieldID.setText(ReaderStringConstant.READER_ID);
        jTextFieldName.setText(ReaderStringConstant.READER_NAME);
        jTextFieldPhoneNumber.setText(ReaderStringConstant.READER_PHONE_NUMBER);

        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jButtonInsert.setText(GeneralStringConstant.GENERAL_INSERT);
        jButtonUpdate.setText(GeneralStringConstant.GENERAL_UPDATE);
        jButtonClear.setText(GeneralStringConstant.GENERAL_CLEAR);
        jButtonDelete.setText(GeneralStringConstant.GENERAL_DELETE);
    }

    void setDefaultTable() {
        jTableReader.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        jScrollPanelReader.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPanelReader.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableReader.setBackground(Color.WHITE);
        jTableReader.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableReader.setFillsViewportHeight(true);
    }

    void setTableBySearch(String text) {
        this.vctData.clear();
        for (Reader reader : this.readersList) {
            if (reader.getId().toLowerCase().contains(text.toLowerCase())
                    || reader.getName().toLowerCase().contains(text.toLowerCase())
                    || reader.getPhoneNumber().toLowerCase().contains(text.toLowerCase())) {
                Vector vctRow = reader.convertToVector();
                this.vctData.add(vctRow);
            }
        }
        showTableData(this.vctData);
    }

    private void displayDetails(int selectedIndex) {
        Vector vctSelectedRow = (Vector) this.vctData.get(selectedIndex);

        String id = (String) vctSelectedRow.get(0);
        String name = (String) vctSelectedRow.get(1);
        String phoneNumber = (String) vctSelectedRow.get(2);

        jTextFieldID.setText(id);
        jTextFieldName.setText(name);
        jTextFieldPhoneNumber.setText(phoneNumber);
    }

    private boolean validateReaderData(String id, String name, String phoneNumber, TypeFunctionEnum typeFunction) {
        String errorList = GeneralStringConstant.GENERAL_EMPTY;
        if (id.trim().isEmpty() || id.isEmpty() || id.equals(ReaderStringConstant.READER_ID)) {
            errorList = errorList + ReaderStringConstant.READER_ID_ERRROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (readerDaoImp.isExist(this.readersList, id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + ReaderStringConstant.READER_ID_INSERT_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (readerDaoImp.isExistDeleteList(id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + ReaderStringConstant.READER_ID_DELETED_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (!readerDaoImp.isExist(this.readersList, id) && (typeFunction == TypeFunctionEnum.Update || typeFunction == TypeFunctionEnum.Delete)) {
            errorList = errorList + ReaderStringConstant.READER_ID_UPDATE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (name.trim().isEmpty() || name.isEmpty()) {
            errorList = errorList + ReaderStringConstant.READER_NAME_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (phoneNumber.charAt(i) >= '0' && phoneNumber.charAt(i) <= '9') {
                continue;
            } else {
                errorList = errorList + ReaderStringConstant.READER_PHONE_NUMBER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
                break;
            }

        }
        if (phoneNumber.trim().isEmpty() || phoneNumber.isEmpty()) {
            errorList = errorList + ReaderStringConstant.READER_PHONE_NUMBER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (!errorList.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            JOptionPane.showMessageDialog(null, errorList);
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanelReaderTitle = new javax.swing.JPanel();
        jReaderLabel = new javax.swing.JLabel();
        jPanelDetail = new javax.swing.JPanel();
        jPanelButton = new javax.swing.JPanel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jPanelTextField = new javax.swing.JPanel();
        jPanelPhoneNumber = new javax.swing.JPanel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jPanelReader = new javax.swing.JPanel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelReader = new javax.swing.JLabel();
        jPanelID = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jSeparatorTitle = new javax.swing.JSeparator();
        jScrollPanelReader = new javax.swing.JScrollPane();
        jTableReader = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1170, 630));
        setPreferredSize(new java.awt.Dimension(1170, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMinimumSize(new java.awt.Dimension(1170, 630));
        jPanel3.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelReaderTitle.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReaderTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jReaderLabel.setBackground(new java.awt.Color(255, 255, 255));
        jReaderLabel.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jReaderLabel.setForeground(new java.awt.Color(255, 0, 0));
        jReaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jReaderLabel.setText("Quản lý độc giả");
        jReaderLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jReaderLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelReaderTitle.add(jReaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 5, 1170, 50));

        jPanel3.add(jPanelReaderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 50));

        jPanelDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelButton.setBackground(new java.awt.Color(255, 255, 255));
        jPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInsert.setBackground(new java.awt.Color(51, 153, 0));
        jButtonInsert.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonInsert.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInsert.setText("Thêm");
        jButtonInsert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 50));

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 51));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Xoá");
        jButtonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 50));

        jButtonUpdate.setBackground(new java.awt.Color(102, 102, 255));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Cập nhật");
        jButtonUpdate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 50));

        jButtonClear.setBackground(new java.awt.Color(255, 102, 51));
        jButtonClear.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonClear.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClear.setText("Huỷ");
        jButtonClear.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 50));

        jPanelDetail.add(jPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 180, 260));

        jPanelTextField.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTextField.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPhoneNumber.setAutoscrolls(true);
        jPanelPhoneNumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPhoneNumber.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPhoneNumber.setText("Số điện thoại");
        jPanelPhoneNumber.add(jLabelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jTextFieldPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPhoneNumber.setText("Số điện thoại");
        jTextFieldPhoneNumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldPhoneNumber.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPhoneNumberFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPhoneNumberFocusLost(evt);
            }
        });
        jTextFieldPhoneNumber.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPhoneNumberMousePressed(evt);
            }
        });
        jTextFieldPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPhoneNumberKeyPressed(evt);
            }
        });
        jPanelPhoneNumber.add(jTextFieldPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 190, 40));

        jPanelTextField.add(jPanelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 310, 60));

        jPanelReader.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName.setText("Tên độc giả");
        jTextFieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldName.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNameFocusLost(evt);
            }
        });
        jTextFieldName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNameMousePressed(evt);
            }
        });
        jTextFieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNameActionPerformed(evt);
            }
        });
        jPanelReader.add(jTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 570, 50));

        jLabelReader.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelReader.setText("Tên");
        jPanelReader.add(jLabelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 40, 40));

        jPanelTextField.add(jPanelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 790, 60));

        jPanelID.setBackground(new java.awt.Color(255, 255, 255));
        jPanelID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID.setText("ID");
        jPanelID.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 40));

        jTextFieldID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldID.setText("ID");
        jTextFieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDFocusLost(evt);
            }
        });
        jTextFieldID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldIDMousePressed(evt);
            }
        });
        jPanelID.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 190, 40));

        jPanelTextField.add(jPanelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 330, 60));

        jPanelSearch.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSearch.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabelSearch.setText("Tìm kiếm");
        jPanelSearch.add(jLabelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 50));

        jTextFieldSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextFieldSearch.setText("Tìm kiếm");
        jTextFieldSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSearchFocusLost(evt);
            }
        });
        jTextFieldSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSearchMousePressed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 570, 40));

        jPanelTextField.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 840, 70));

        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 4, true));
        jPanelTextField.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, -6, 360, 10));

        jPanelDetail.add(jPanelTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 960, 260));

        jScrollPanelReader.setBackground(new java.awt.Color(255, 255, 255));

        jTableReader.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableReader.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9"
            }
        ));
        jTableReader.setMaximumSize(new java.awt.Dimension(2147483647, 120));
        jTableReader.setMinimumSize(new java.awt.Dimension(60, 120));
        jTableReader.setPreferredSize(new java.awt.Dimension(60, 120));
        jTableReader.setRowHeight(40);
        jTableReader.setShowGrid(true);
        jTableReader.getTableHeader().setResizingAllowed(false);
        jTableReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableReaderMousePressed(evt);
            }
        });
        jTableReader.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableReaderKeyReleased(evt);
            }
        });
        jScrollPanelReader.setViewportView(jTableReader);

        jPanelDetail.add(jScrollPanelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 1130, 310));

        jPanel3.add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1170, 580));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTableReaderKeyPressed(KeyEvent evt) {

    }

    private void jTableReaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReaderMousePressed
        // TODO add your handling code here:
        int selectedRow = jTableReader.getSelectedRow();
        displayDetails(selectedRow);
    }//GEN-LAST:event_jTableReaderMousePressed

    private void jTableReaderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableReaderKeyReleased
        // TODO add your handling code here:
        //        int selectedRow = jTableBook.getSelectedRow();
        //        displayDetails(selectedRow);

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedRow = jTableReader.getSelectedRow();
            displayDetails(selectedRow);
        }
    }//GEN-LAST:event_jTableReaderKeyReleased

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        setTableBySearch(text);
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
    }//GEN-LAST:event_jTextFieldSearchFocusGained

    private void jTextFieldIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDMousePressed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
    }//GEN-LAST:event_jTextFieldIDMousePressed

    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusLost
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldID.setText(ReaderStringConstant.READER_ID);
        }
    }//GEN-LAST:event_jTextFieldIDFocusLost

    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusGained
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(ReaderStringConstant.READER_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDFocusGained

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameActionPerformed

    private void jTextFieldNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNameMousePressed
        // TODO add your handling code here:
        String author = jTextFieldName.getText();
    }//GEN-LAST:event_jTextFieldNameMousePressed

    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusLost
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldName.setText(ReaderStringConstant.READER_NAME);
        }
    }//GEN-LAST:event_jTextFieldNameFocusLost

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(ReaderStringConstant.READER_NAME)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldNameFocusGained

    private void jTextFieldPhoneNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberKeyPressed
        // TODO add your handling code here:
        //Action when key press
        String price = jTextFieldPhoneNumber.getText();
    }//GEN-LAST:event_jTextFieldPhoneNumberKeyPressed

    private void jTextFieldPhoneNumberMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberMousePressed
        // TODO add your handling code here:
        String price = jTextFieldPhoneNumber.getText();
    }//GEN-LAST:event_jTextFieldPhoneNumberMousePressed

    private void jTextFieldPhoneNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberFocusLost
        // TODO add your handling code here:
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (phoneNumber.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldPhoneNumber.setText(ReaderStringConstant.READER_PHONE_NUMBER);
        }
    }//GEN-LAST:event_jTextFieldPhoneNumberFocusLost

    private void jTextFieldPhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberFocusGained
        // TODO add your handling code here:
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (phoneNumber.equals(ReaderStringConstant.READER_PHONE_NUMBER)) {
            jTextFieldPhoneNumber.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPhoneNumberFocusGained

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        resetData();
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:

        {
            String id = jTextFieldID.getText();
            String name = jTextFieldName.getText();
            String phoneNumber = jTextFieldPhoneNumber.getText();
            if (validateReaderData(id, name, phoneNumber, TypeFunctionEnum.Update)) {
                Reader reader = new Reader(id, name, phoneNumber);
                boolean updateCheck = readerDaoImp.update(reader);
                if (updateCheck) {
                    JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_UPDATE_SUCCESS);
                    resetData();
                } else {
                    JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_UPDATE_ERROR);
                }
            }

        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (validateReaderData(id, name, phoneNumber, TypeFunctionEnum.Delete)) {
            Reader reader = new Reader(id, name, phoneNumber);

            int answer = JOptionPane.showConfirmDialog(null,
                    ReaderStringConstant.READER_DELETE_TITLE, GeneralStringConstant.GENERAL_DELETE,
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                boolean deleteCheck = readerDaoImp.moveToBin(reader.getId());
                if (deleteCheck) {
                    JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_DELETE_SUCCESS);
                    resetData();
                } else {
                    JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_DELETE_ERROR);
                }
            } else {

            }

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String phoneNumber = jTextFieldPhoneNumber.getText();

        if (validateReaderData(id, name, phoneNumber, TypeFunctionEnum.Insert)) {
            Reader reader = new Reader(id, name, phoneNumber);
            boolean insertCheck = readerDaoImp.insert(reader);
            if (insertCheck) {
                JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_INSERT_SUCCESS);
                resetData();
            } else {
                JOptionPane.showMessageDialog(null, ReaderStringConstant.READER_INSERT_ERROR);
            }
        }

    }//GEN-LAST:event_jButtonInsertActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelReader;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelID;
    private javax.swing.JPanel jPanelPhoneNumber;
    private javax.swing.JPanel jPanelReader;
    private javax.swing.JPanel jPanelReaderTitle;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelTextField;
    private javax.swing.JLabel jReaderLabel;
    private javax.swing.JScrollPane jScrollPanelReader;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JTable jTableReader;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
