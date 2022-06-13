/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Adapter.SupportFunction;
import Base.DIContainer;
import constant.BinStringConstant;
import constant.GeneralStringConstant;
import constant.TitleStringConstant;
import DAO.dao_impl.BinDaoImp;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DTO.Bin;
import javax.swing.table.TableRowSorter;

/**
 * ManageBinPanel dùng hiển thị danh sách quyển sách, nhà xuất bản tạm xoá, 
 * có thể khôi phục lại,
 * cũng như xoá hoàn toàn khỏi database
 */
public class ManageBinPanel extends javax.swing.JPanel {

    public ManageBinPanel() {
        /**
         * Khởi tạo giá trị cho 
         * binDaoImp
         * thông qua DIContainer   
         **/
        this.binDaoImp = DIContainer.getBinDao();
        
        initComponents();
        
        // Bổ sung thêm cho initComponents
        myInitComponents();
        
        setBounds(0, 0, 1160, 740);
    }

    // binDaoImp dùng để xử lý những chức năng có liên quan tới Bin
    private final BinDaoImp binDaoImp;
    
    // binList là danh sách Bin(Book, Publisher) và được lấy từ trong database
    private ArrayList<Bin> binList;
    
    // vctHeader dùng để đặt Header trong JTableBin
    private Vector vctHeader;
    
    // vctData dùng để đặt Row trong JTableBin
    private Vector vctData;

    // Dùng để khởi tạo các giá trị lấy từ Database cũng như đặt các giá trị final vào các Label
    public void myInitComponents() {
        setDefaultText();
        setDefaultTable();
        getVectorData();
        showTableData(this.vctData);
        clearInfo();
    }

     // Dùng để setText các jLabel thông qua TitleStringConstant, BinStringConstant, GeneralStringConstant
    private void setDefaultText() {
        jLabelTitle.setText(TitleStringConstant.MANAGE_BIN);

        jLabelID.setText(BinStringConstant.BIN_ID);
        jLabelName.setText(BinStringConstant.BIN_NAME);
        jLabelType.setText(BinStringConstant.BIN_TYPE);
        jLabelSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jTextFieldID.setText(BinStringConstant.BIN_ID);
        jTextFieldName.setText(BinStringConstant.BIN_NAME);
        jTextFieldType.setText(BinStringConstant.BIN_TYPE);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jButtonRecover.setText(GeneralStringConstant.GENERAL_RECOVERY);
        jButtonDelete.setText(GeneralStringConstant.GENERAL_DELETE);
    }

    // Dùng để set những thông số mặc định của bảng
    private void setDefaultTable() {
        jTableDelete.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jScrollPanelTable.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPanelTable.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableDelete.setBackground(Color.WHITE);
        jTableDelete.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDelete.setFillsViewportHeight(true);
    }

    // Hiển thị bảng jTableDelete được gán vctData
    private void showTableData(Vector vctData) {

        this.vctHeader = this.binDaoImp.getTitleColumn();

        jTableDelete.setModel(new DefaultTableModel(vctData, vctHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });
        
        DefaultTableModel model = (DefaultTableModel) jTableDelete.getModel();
        //Add sorter
        var sorter = new TableRowSorter<DefaultTableModel>(model);
        jTableDelete.setRowSorter(sorter); 
    }

    // Hiển thị thông tin chi tiết của Bin khi chọn 1 hàng trong jTableDelete
    private void displayDetails(int selectedIndex) {
        if(vctData.isEmpty()){
            return;
        }
        Vector vctSelectedRow = (Vector) this.vctData.get(selectedIndex);

        String id = (String) vctSelectedRow.get(0);
        String name = (String) vctSelectedRow.get(1);
        String type = (String) vctSelectedRow.get(2);

        jTextFieldID.setText(id);
        jTextFieldName.setText(name);
        jTextFieldType.setText(type);
    }

    // Dùng để gán các giá trị Bin từ Database vào vctData thông qua binDaoImp
    private void getVectorData() {
        this.binList = binDaoImp.getBinList();
        this.vctData = new Vector();
        for (int i = 0; i < this.binList.size(); i++) {
            Vector vctRow = this.binList.get(i).convertToVector();
            vctData.add(vctRow);
        }
    }

    // Xoá những giá trị đã điền khỏi jTextField
    private void clearInfo() {
        jTableDelete.clearSelection();
        jTextFieldID.setText(BinStringConstant.BIN_ID);
        jTextFieldName.setText(BinStringConstant.BIN_NAME);
        jTextFieldType.setText(BinStringConstant.BIN_TYPE);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
    }
    
    // Dùng để search Table khi nhập trong Tìm kiếm
    private void setTableBySearch(String text) {
        this.vctData.clear();
        for (Bin bin : this.binList) {
            if ( bin.getId().toLowerCase().contains(text.toLowerCase())
                    || bin.getName().toLowerCase().contains(text.toLowerCase())) {
                Vector vctRow = bin.convertToVector();
                this.vctData.add(vctRow);
            }
        }
        showTableData(this.vctData);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPanelTable = new javax.swing.JScrollPane();
        jTableDelete = new javax.swing.JTable();
        jPanelDetail = new javax.swing.JPanel();
        jPanelID = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanelType = new javax.swing.JPanel();
        jLabelType = new javax.swing.JLabel();
        jTextFieldType = new javax.swing.JTextField();
        jPanelButton = new javax.swing.JPanel();
        jButtonRecover = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jSeparatorTitle = new javax.swing.JSeparator();
        jPanelName = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(1170, 50));
        jPanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 51, 51));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Thùng Rác");
        jLabelTitle.setRequestFocusEnabled(false);
        jPanelTitle.add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 483, 40));

        add(jPanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        jScrollPanelTable.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPanelTable.setVerifyInputWhenFocusTarget(false);

        jTableDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableDelete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableDelete.setRowHeight(40);
        jTableDelete.setShowGrid(true);
        jTableDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableDeleteMousePressed(evt);
            }
        });
        jTableDelete.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableDeleteKeyReleased(evt);
            }
        });
        jScrollPanelTable.setViewportView(jTableDelete);

        add(jScrollPanelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1130, 380));

        jPanelDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelID.setBackground(new java.awt.Color(255, 255, 255));
        jPanelID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID.setText("ID ");
        jLabelID.setToolTipText("");
        jPanelID.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 30, 40));

        jTextFieldID.setEditable(false);
        jTextFieldID.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldID.setText("ID");
        jTextFieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jPanelID.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 540, 40));

        jPanelDetail.add(jPanelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 740, 50));

        jPanelType.setBackground(new java.awt.Color(255, 255, 255));
        jPanelType.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelType.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelType.setBackground(new java.awt.Color(255, 255, 204));
        jLabelType.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelType.setText("Loại");
        jPanelType.add(jLabelType, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 40));

        jTextFieldType.setEditable(false);
        jTextFieldType.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldType.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldType.setText("Loại");
        jTextFieldType.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jPanelType.add(jTextFieldType, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 540, 40));

        jPanelDetail.add(jPanelType, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 740, 50));

        jPanelButton.setBackground(new java.awt.Color(255, 255, 255));
        jPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonRecover.setBackground(new java.awt.Color(102, 102, 255));
        jButtonRecover.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonRecover.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRecover.setText("Khôi Phục");
        jButtonRecover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRecover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecoverActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonRecover, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 50));

        jButtonDelete.setBackground(new java.awt.Color(255, 0, 51));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Huỷ");
        jButtonDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        jPanelButton.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 50));

        jPanelDetail.add(jPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 180, 130));

        jPanelSearch.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSearch.setFont(new java.awt.Font("Segoe UI", 2, 17)); // NOI18N
        jLabelSearch.setText("Tìm kiếm");
        jPanelSearch.add(jLabelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        jTextFieldSearch.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
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
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 540, 50));

        jPanelDetail.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 650, 70));

        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 4, true));
        jPanelDetail.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 480, 4));

        jPanelName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelName.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelName.setBackground(new java.awt.Color(255, 255, 204));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelName.setText("Tên");
        jPanelName.add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 40));

        jTextFieldName.setEditable(false);
        jTextFieldName.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName.setText("Tên");
        jTextFieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jPanelName.add(jTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 540, 40));

        jPanelDetail.add(jPanelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 740, 50));

        add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 260));
    }// </editor-fold>//GEN-END:initComponents

    // Xử lý sự kiện khi nhấn vào Button Khôi phục
    private void jButtonRecoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecoverActionPerformed
        // TODO add your handling code here:
        
        //Check Data in Table 
        if(vctData.isEmpty()){
            return;
        }
        
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String type = jTextFieldType.getText();

        int answer = JOptionPane.showConfirmDialog(null,
                BinStringConstant.BIN_RECOVERY_TITLE, GeneralStringConstant.GENERAL_RECOVERY,
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            Bin bin = new Bin(id, name, SupportFunction.convertStringToBinType(type));
            boolean recoveryCheck = binDaoImp.recoveryData(bin);
            if (recoveryCheck) {
                JOptionPane.showMessageDialog(null, BinStringConstant.BIN_RECOVERY_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, BinStringConstant.BIN_RECOVERY_FAILED);
            }
        } else {

        }
    }//GEN-LAST:event_jButtonRecoverActionPerformed

    // Xử lý sự kiện khi nhấn vào Button Huỷ
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        
        //Check Data in Table 
        if(vctData.isEmpty()){
            return;
        }
        
        
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String type = jTextFieldType.getText();
        int answer = JOptionPane.showConfirmDialog(null,
                BinStringConstant.BIN_DELETE_TITLE, GeneralStringConstant.GENERAL_DELETE,
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            Bin bin = new Bin(id, name, SupportFunction.convertStringToBinType(type));
            boolean deleteCheck = binDaoImp.deleteData(bin);
            if (deleteCheck) {
                JOptionPane.showMessageDialog(null, BinStringConstant.BIN_DELETE_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, BinStringConstant.BIN_DELETE_FAILED);
            }
        } else {

        }        
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    // Xử lý sự kiện Focus Gained Text Field Tìm kiếm
    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusGained

    // Xử lý sự kiện Focus Lost Text Field Tìm kiếm
    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    // Xử lý sự kiện Pressed Mouse Text Field Tìm kiếm
    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    // Xử lý sự kiện Key Released Text Field Tìm kiếm
    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        setTableBySearch(text);
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    // Xử lý sự kiện Mouse Press của jTableDelete
    private void jTableDeleteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableDeleteMousePressed
        // TODO add your handling code here:
        int selectedRow = jTableDelete.getSelectedRow();
        displayDetails(selectedRow);
    }//GEN-LAST:event_jTableDeleteMousePressed

    // Xử lý sự kiện Key Realeased của jTableDelete
    private void jTableDeleteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableDeleteKeyReleased
        // TODO add your handling code here:
        //        int selectedRow = jTableBook.getSelectedRow();
        //        displayDetails(selectedRow);

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedRow = jTableDelete.getSelectedRow();
            displayDetails(selectedRow);
        }
    }//GEN-LAST:event_jTableDeleteKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRecover;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelID;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JPanel jPanelType;
    private javax.swing.JScrollPane jScrollPanelTable;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JTable jTableDelete;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldType;
    // End of variables declaration//GEN-END:variables
}
