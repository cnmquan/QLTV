/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import DAO.dao_impl.ReaderDaoImp;
import DTO.TypeFunctionEnum;
import constant.GeneralStringConstant;
import constant.ReaderStringConstant;
import model.Reader;

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
                    || reader.getPhoneNumber().toLowerCase().contains(text.toLowerCase())
            ) {
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

        jScrollPanelReader = new javax.swing.JScrollPane();
        jTableReader = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanelReaderTitle = new javax.swing.JPanel();
        jSeparatorTitle = new javax.swing.JSeparator();
        jReaderLabel = new javax.swing.JLabel();
        jPanelDetail = new javax.swing.JPanel();
        jPanelButton = new javax.swing.JPanel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jSeparatorTitle1 = new javax.swing.JSeparator();
        jPanelTextField = new javax.swing.JPanel();
        jPanelPhoneNumber = new javax.swing.JPanel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jPanelName = new javax.swing.JPanel();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanelReader = new javax.swing.JPanel();
        jTextFieldName = new javax.swing.JTextField();
        jLabelReader = new javax.swing.JLabel();
        jPanelID = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPanelReader1 = new javax.swing.JScrollPane();
        jTableReader1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanelReaderTitle1 = new javax.swing.JPanel();
        jSeparatorTitle2 = new javax.swing.JSeparator();
        jReaderLabel1 = new javax.swing.JLabel();
        jPanelDetail1 = new javax.swing.JPanel();
        jPanelButton1 = new javax.swing.JPanel();
        jButtonInsert1 = new javax.swing.JButton();
        jButtonDelete1 = new javax.swing.JButton();
        jButtonUpdate1 = new javax.swing.JButton();
        jButtonClear1 = new javax.swing.JButton();
        jSeparatorTitle3 = new javax.swing.JSeparator();
        jPanelTextField1 = new javax.swing.JPanel();
        jPanelPhoneNumber1 = new javax.swing.JPanel();
        jLabelPhoneNumber1 = new javax.swing.JLabel();
        jTextFieldPhoneNumber1 = new javax.swing.JTextField();
        jPanelName1 = new javax.swing.JPanel();
        jPanelSearch1 = new javax.swing.JPanel();
        jLabelSearch1 = new javax.swing.JLabel();
        jTextFieldSearch1 = new javax.swing.JTextField();
        jPanelReader1 = new javax.swing.JPanel();
        jTextFieldName1 = new javax.swing.JTextField();
        jLabelReader1 = new javax.swing.JLabel();
        jPanelID1 = new javax.swing.JPanel();
        jLabelID1 = new javax.swing.JLabel();
        jTextFieldID1 = new javax.swing.JTextField();

        setMinimumSize(new java.awt.Dimension(1170, 630));
        setPreferredSize(new java.awt.Dimension(1170, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableReader.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableReaderMousePressed(evt);
            }
        });
        jTableReader.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableReaderKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableReaderKeyReleased(evt);
            }
        });
        jScrollPanelReader.setViewportView(jTableReader);

        add(jScrollPanelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1200, 120));

        jPanel3.setMinimumSize(new java.awt.Dimension(1170, 630));
        jPanel3.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelReaderTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanelReaderTitle.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 480, 4));

        jReaderLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jReaderLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jReaderLabel.setText("Quản lý độc giả");
        jReaderLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelReaderTitle.add(jReaderLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 5, 1170, 40));

        jPanel3.add(jPanelReaderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 50));

        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInsert.setBackground(new java.awt.Color(102, 102, 255));
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

        jButtonUpdate.setBackground(new java.awt.Color(204, 204, 0));
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

        jSeparatorTitle1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanelDetail.add(jSeparatorTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 400, 4));

        jPanelTextField.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPhoneNumber.setAutoscrolls(true);
        jPanelPhoneNumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPhoneNumber.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPhoneNumber.setText("Số điện thoại");
        jPanelPhoneNumber.add(jLabelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jTextFieldPhoneNumber.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPhoneNumber.setText("Số điện thoại");
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

        jPanelName.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSearch.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabelSearch.setText("Tìm kiếm");
        jPanelSearch.add(jLabelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jTextFieldSearch.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextFieldSearch.setText("Tìm kiếm");
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
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 735, 50));

        jPanelName.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 840, 70));

        jPanelTextField.add(jPanelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 690, 80));

        jPanelReader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName.setText("Tên độc giả");
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
        jPanelReader.add(jTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 570, 60));

        jLabelReader.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelReader.setText("Tên");
        jPanelReader.add(jLabelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 40, 40));

        jPanelTextField.add(jPanelReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 790, 60));

        jPanelID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID.setText("ID");
        jPanelID.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 40));

        jTextFieldID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldID.setText("ID");
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

        jPanelDetail.add(jPanelTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 960, 350));

        jPanel3.add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 360));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jPanel1.setMinimumSize(new java.awt.Dimension(1170, 630));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableReader1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableReader1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableReader1MousePressed(evt);
            }
        });
        jTableReader1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableReader1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableReader1KeyReleased(evt);
            }
        });
        jScrollPanelReader1.setViewportView(jTableReader1);

        jPanel1.add(jScrollPanelReader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1200, 120));

        jPanel4.setMinimumSize(new java.awt.Dimension(1170, 630));
        jPanel4.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelReaderTitle1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparatorTitle2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanelReaderTitle1.add(jSeparatorTitle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 480, 4));

        jReaderLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jReaderLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jReaderLabel1.setText("Quản lý độc giả");
        jReaderLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelReaderTitle1.add(jReaderLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 5, 1170, 40));

        jPanel4.add(jPanelReaderTitle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 50));

        jPanelDetail1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelButton1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInsert1.setBackground(new java.awt.Color(102, 102, 255));
        jButtonInsert1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonInsert1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInsert1.setText("Thêm");
        jButtonInsert1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInsert1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsert1ActionPerformed(evt);
            }
        });
        jPanelButton1.add(jButtonInsert1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 50));

        jButtonDelete1.setBackground(new java.awt.Color(255, 0, 51));
        jButtonDelete1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonDelete1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete1.setText("Xoá");
        jButtonDelete1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDelete1ActionPerformed(evt);
            }
        });
        jPanelButton1.add(jButtonDelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 50));

        jButtonUpdate1.setBackground(new java.awt.Color(204, 204, 0));
        jButtonUpdate1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonUpdate1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate1.setText("Cập nhật");
        jButtonUpdate1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonUpdate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdate1ActionPerformed(evt);
            }
        });
        jPanelButton1.add(jButtonUpdate1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 150, 50));

        jButtonClear1.setBackground(new java.awt.Color(255, 102, 51));
        jButtonClear1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonClear1.setForeground(new java.awt.Color(255, 255, 255));
        jButtonClear1.setText("Huỷ");
        jButtonClear1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClear1ActionPerformed(evt);
            }
        });
        jPanelButton1.add(jButtonClear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 50));

        jPanelDetail1.add(jPanelButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 180, 260));

        jSeparatorTitle3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 4, true));
        jPanelDetail1.add(jSeparatorTitle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 400, 4));

        jPanelTextField1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPhoneNumber1.setAutoscrolls(true);
        jPanelPhoneNumber1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPhoneNumber1.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPhoneNumber1.setText("Số điện thoại");
        jPanelPhoneNumber1.add(jLabelPhoneNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jTextFieldPhoneNumber1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPhoneNumber1.setText("Số điện thoại");
        jTextFieldPhoneNumber1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPhoneNumber1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPhoneNumber1FocusLost(evt);
            }
        });
        jTextFieldPhoneNumber1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPhoneNumber1MousePressed(evt);
            }
        });
        jTextFieldPhoneNumber1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPhoneNumber1KeyPressed(evt);
            }
        });
        jPanelPhoneNumber1.add(jTextFieldPhoneNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 190, 40));

        jPanelTextField1.add(jPanelPhoneNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 310, 60));

        jPanelName1.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelSearch1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelSearch1.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabelSearch1.setText("Tìm kiếm");
        jPanelSearch1.add(jLabelSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 50));

        jTextFieldSearch1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextFieldSearch1.setText("Tìm kiếm");
        jTextFieldSearch1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldSearch1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSearch1FocusLost(evt);
            }
        });
        jTextFieldSearch1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSearch1MousePressed(evt);
            }
        });
        jTextFieldSearch1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearch1KeyReleased(evt);
            }
        });
        jPanelSearch1.add(jTextFieldSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 735, 50));

        jPanelName1.add(jPanelSearch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 840, 70));

        jPanelTextField1.add(jPanelName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 690, 80));

        jPanelReader1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldName1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName1.setText("Tên độc giả");
        jTextFieldName1.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldName1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldName1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldName1FocusLost(evt);
            }
        });
        jTextFieldName1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldName1MousePressed(evt);
            }
        });
        jTextFieldName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldName1ActionPerformed(evt);
            }
        });
        jPanelReader1.add(jTextFieldName1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 570, 60));

        jLabelReader1.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelReader1.setText("Tên");
        jPanelReader1.add(jLabelReader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 40, 40));

        jPanelTextField1.add(jPanelReader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 790, 60));

        jPanelID1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID1.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID1.setText("ID");
        jPanelID1.add(jLabelID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, 40));

        jTextFieldID1.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldID1.setText("ID");
        jTextFieldID1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldID1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldID1FocusLost(evt);
            }
        });
        jTextFieldID1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldID1MousePressed(evt);
            }
        });
        jPanelID1.add(jTextFieldID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 190, 40));

        jPanelTextField1.add(jPanelID1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 330, 60));

        jPanelDetail1.add(jPanelTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 960, 350));

        jPanel4.add(jPanelDetail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 360));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void jTableReaderKeyPressed(KeyEvent evt) {

    }

    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusGained
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(ReaderStringConstant.READER_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldIDFocusGained

    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusLost
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldID.setText(ReaderStringConstant.READER_ID);
        }

    }//GEN-LAST:event_jTextFieldIDFocusLost

    private void jTextFieldIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDMousePressed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();

    }//GEN-LAST:event_jTextFieldIDMousePressed

    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(ReaderStringConstant.READER_NAME)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldNameFocusGained

    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusLost
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldName.setText(ReaderStringConstant.READER_NAME);
        }

    }//GEN-LAST:event_jTextFieldNameFocusLost

    private void jTextFieldNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNameMousePressed
        // TODO add your handling code here:
        String author = jTextFieldName.getText();

    }//GEN-LAST:event_jTextFieldNameMousePressed

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

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        resetData();
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jTextFieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNameActionPerformed

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


    private void jTableReaderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableReaderKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableReaderKeyReleased

    private void jTableReaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReaderMousePressed
        // TODO add your handling code here:
        int selectedRow = jTableReader.getSelectedRow();
        displayDetails(selectedRow);
    }//GEN-LAST:event_jTableReaderMousePressed

    private void jTableReader1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableReader1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableReader1MousePressed

    private void jTableReader1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableReader1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableReader1KeyPressed

    private void jTableReader1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableReader1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTableReader1KeyReleased

    private void jButtonInsert1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsert1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonInsert1ActionPerformed

    private void jButtonDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDelete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDelete1ActionPerformed

    private void jButtonUpdate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdate1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdate1ActionPerformed

    private void jButtonClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClear1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonClear1ActionPerformed

    private void jTextFieldPhoneNumber1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumber1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumber1FocusGained

    private void jTextFieldPhoneNumber1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumber1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumber1FocusLost

    private void jTextFieldPhoneNumber1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumber1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumber1MousePressed

    private void jTextFieldPhoneNumber1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumber1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPhoneNumber1KeyPressed

    private void jTextFieldSearch1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearch1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearch1FocusGained

    private void jTextFieldSearch1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearch1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearch1FocusLost

    private void jTextFieldSearch1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearch1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearch1MousePressed

    private void jTextFieldSearch1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearch1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearch1KeyReleased

    private void jTextFieldName1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldName1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1FocusGained

    private void jTextFieldName1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldName1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1FocusLost

    private void jTextFieldName1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldName1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1MousePressed

    private void jTextFieldName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldName1ActionPerformed

    private void jTextFieldID1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldID1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldID1FocusGained

    private void jTextFieldID1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldID1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldID1FocusLost

    private void jTextFieldID1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldID1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldID1MousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonClear1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonDelete1;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonInsert1;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtonUpdate1;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelID1;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelPhoneNumber1;
    private javax.swing.JLabel jLabelReader;
    private javax.swing.JLabel jLabelReader1;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelSearch1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelButton1;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelDetail1;
    private javax.swing.JPanel jPanelID;
    private javax.swing.JPanel jPanelID1;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JPanel jPanelName1;
    private javax.swing.JPanel jPanelPhoneNumber;
    private javax.swing.JPanel jPanelPhoneNumber1;
    private javax.swing.JPanel jPanelReader;
    private javax.swing.JPanel jPanelReader1;
    private javax.swing.JPanel jPanelReaderTitle;
    private javax.swing.JPanel jPanelReaderTitle1;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelSearch1;
    private javax.swing.JPanel jPanelTextField;
    private javax.swing.JPanel jPanelTextField1;
    private javax.swing.JLabel jReaderLabel;
    private javax.swing.JLabel jReaderLabel1;
    private javax.swing.JScrollPane jScrollPanelReader;
    private javax.swing.JScrollPane jScrollPanelReader1;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JSeparator jSeparatorTitle1;
    private javax.swing.JSeparator jSeparatorTitle2;
    private javax.swing.JSeparator jSeparatorTitle3;
    private javax.swing.JTable jTableReader;
    private javax.swing.JTable jTableReader1;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldID1;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldName1;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldPhoneNumber1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JTextField jTextFieldSearch1;
    // End of variables declaration//GEN-END:variables
}
