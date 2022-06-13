/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Base.DIContainer;
import constant.GeneralStringConstant;
import constant.PublisherStringConstant;
import constant.TitleStringConstant;
import DAO.dao_impl.PublisherDaoImp;
import DTO.Publisher;
import DTO.TypeFunctionEnum;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 * ManagePublisherPanel dùng để hiển thị danh sách nhà xuất bản, có thể thêm,
 * xoá, sửa nhà xuất bản
 */
public class ManagePublisherPanel extends javax.swing.JPanel {

    public ManagePublisherPanel() {
        /**
         * Khởi tạo giá trị cho publisherDaoImp thông qua DIContainer   
         *
         */
        this.publisherDaoImp = DIContainer.getPublisherDao();

        initComponents();

        // Bổ sung thêm cho initComponents
        myInitComponents();

        setBounds(0, 0, 1160, 740);
    }

    // publisherDaoImp dùng để xử lý những chức năng có liên quan tới Publisher
    private final PublisherDaoImp publisherDaoImp;

    // publisherList là danh sách Publisher và được lấy từ trong database
    private ArrayList<Publisher> publisherList;

    // vctHeader dùng để đặt Header trong JTablePublisher
    private Vector vctHeader;

    // vctData dùng để đặt Row trong JTablePublisher
    private Vector vctData;

    // Dùng để khởi tạo các giá trị lấy từ Database cũng như đặt các giá trị final vào các Label
    public void myInitComponents() {
        setDefaultText();
        setDefaultTable();
        getVectorData();
        showTableData(this.vctData);
        clearInfo();
    }

    // Dùng để gán các giá trị Publisher từ Database vào vctData thông qua publisherDaoImp
    public void getVectorData() {
        this.publisherList = publisherDaoImp.getAll();
        this.vctData = new Vector();
        for (int i = 0; i < this.publisherList.size(); i++) {
            Vector vctRow = this.publisherList.get(i).convertToVector();
            vctData.add(vctRow);
        }
    }

    // Dùng để setText các jLabel thông qua TitleStringConstant, PublisherStringConstant, GeneralStringConstant
    private void setDefaultText() {
        jLabelTitle.setText(TitleStringConstant.MANAGE_PUBLISHER);

        jLabelID.setText(PublisherStringConstant.PUBLISHER_ID);
        jLabelName.setText(PublisherStringConstant.PUBLISHER_NAME);
        jLabelPhoneNumber.setText(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        jLabelAddress.setText(PublisherStringConstant.PUBLISHER_ADDRESS);
        jLabelSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jTextFieldID.setText(PublisherStringConstant.PUBLISHER_ID);
        jTextFieldName.setText(PublisherStringConstant.PUBLISHER_NAME);
        jTextFieldPhoneNumber.setText(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        jTextFieldAddress.setText(PublisherStringConstant.PUBLISHER_ADDRESS);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jButtonInsert.setText(GeneralStringConstant.GENERAL_INSERT);
        jButtonUpdate.setText(GeneralStringConstant.GENERAL_UPDATE);
        jButtonClear.setText(GeneralStringConstant.GENERAL_CLEAR);
        jButtonDelete.setText(GeneralStringConstant.GENERAL_DELETE);
    }

    // Dùng để set những thông số mặc định của bảng
    private void setDefaultTable() {
        jTablePublisher.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jScrollPanelTable.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPanelTable.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTablePublisher.setBackground(Color.WHITE);
        jTablePublisher.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTablePublisher.setFillsViewportHeight(true);
    }

    // Hiển thị bảng jTablePublisher được gán vctData
    public void showTableData(Vector vctData) {

        this.vctHeader = this.publisherDaoImp.getTitleColumn();

        jTablePublisher.setModel(new DefaultTableModel(vctData, vctHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });

        DefaultTableModel model = (DefaultTableModel) jTablePublisher.getModel();
        //Add sorter
        var sorter = new TableRowSorter<DefaultTableModel>(model);
        jTablePublisher.setRowSorter(sorter);
    }

    // Hiển thị thông tin chi tiết của Publisher khi chọn 1 hàng trong jTablePublisher
    private void displayDetails(int selectedIndex) {
        Vector vctSelectedRow = (Vector) this.vctData.get(selectedIndex);

        String id = (String) vctSelectedRow.get(0);
        String name = (String) vctSelectedRow.get(1);
        String phoneNumber = (String) vctSelectedRow.get(2);
        String address = (String) vctSelectedRow.get(3);

        jTextFieldID.setText(id);
        jTextFieldName.setText(name);
        jTextFieldPhoneNumber.setText(phoneNumber);
        jTextFieldAddress.setText(address);
    }

    // Xoá những giá trị đã điền khỏi jTextField
    private void clearInfo() {
        jTablePublisher.clearSelection();
        jTextFieldID.setText(PublisherStringConstant.PUBLISHER_ID);
        jTextFieldName.setText(PublisherStringConstant.PUBLISHER_NAME);
        jTextFieldPhoneNumber.setText(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        jTextFieldAddress.setText(PublisherStringConstant.PUBLISHER_ADDRESS);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
    }

    // Kiểm tra khi nhấn vào nút Insert/Update/Delete
    private boolean validateBookData(String id, String name,
            String phoneNumber, String address, TypeFunctionEnum typeFunction) {

        // Khởi tạo chuỗi errorList
        String errorList = GeneralStringConstant.GENERAL_EMPTY;

        // Kiểm tra ID nhập vào có trống không
        if (id.isBlank() || id.isEmpty() || id.equals(PublisherStringConstant.PUBLISHER_ID)) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_ID_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID nhập vào có trùng với ID đã có trong danh sách khi Insert không 
        else if (publisherDaoImp.isExist(this.publisherList, id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_ID_INSERT_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID nhập vào có trùng với ID đã có trong danh sách thùng rác khi Insert không 
        else if (publisherDaoImp.isExistDeleteList(id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_ID_DELETED_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID có khác với ID đã có trong danh sách khi Update/Delete không
        else if (!publisherDaoImp.isExist(this.publisherList, id) && (typeFunction == TypeFunctionEnum.Update || typeFunction == TypeFunctionEnum.Delete)) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_ID_UPDATE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Tên nhập vào có trống không
        if (name.isBlank() || name.isEmpty()) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_NAME_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Số điện thoại nhập vào có lớn hơn 6 ký tự không
        if (phoneNumber.isBlank() || phoneNumber.isEmpty() || phoneNumber.length() < 6) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_PHONE_NUMBER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Địa chỉ nhập vào có trống không
        if (address.isBlank() || address.isEmpty()) {
            errorList = errorList + PublisherStringConstant.PUBLISHER_ADDRESS_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (!errorList.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            JOptionPane.showMessageDialog(null, errorList);
            return false;
        } else {
            return true;
        }
    }

    // Dùng để search Table khi nhập trong Tìm kiếm
    private void setTableBySearch(String text) {
        this.vctData.clear();
        for (Publisher publisher : this.publisherList) {
            if (publisher.getPublisherID().toLowerCase().contains(text.toLowerCase())
                    || publisher.getPublisherName().toLowerCase().contains(text.toLowerCase())
                    || publisher.getPublisherAddress().toLowerCase().contains(text.toLowerCase())
                    || publisher.getPublisherPhoneNumber().toLowerCase().contains(text.toLowerCase())) {
                Vector vctRow = publisher.convertToVector();
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

        jPanelDetail = new javax.swing.JPanel();
        jPanelID = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanelName = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jPanelPhoneNumber = new javax.swing.JPanel();
        jLabelPhoneNumber = new javax.swing.JLabel();
        jTextFieldPhoneNumber = new javax.swing.JTextField();
        jPanelButton = new javax.swing.JPanel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanelAddress = new javax.swing.JPanel();
        jTextFieldAddress = new javax.swing.JTextField();
        jLabelAddress = new javax.swing.JLabel();
        jSeparatorTitle = new javax.swing.JSeparator();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPanelTable = new javax.swing.JScrollPane();
        jTablePublisher = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1170, 630));
        setPreferredSize(new java.awt.Dimension(1170, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelID.setBackground(new java.awt.Color(255, 255, 255));
        jPanelID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID.setText("ID nhà xuất bản");
        jLabelID.setToolTipText("");
        jPanelID.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 130, 40));

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
        jPanelID.add(jTextFieldID, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 180, 40));

        jPanelDetail.add(jPanelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 370, 50));

        jPanelName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelName.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelName.setBackground(new java.awt.Color(255, 255, 204));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelName.setText("Tên nhà xuất bản");
        jPanelName.add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, -1, 40));

        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName.setText("Tên nhà xuất bản");
        jTextFieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
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
        jPanelName.add(jTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 540, 40));

        jPanelDetail.add(jPanelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 740, 50));

        jPanelPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPhoneNumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPhoneNumber.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPhoneNumber.setText("Số điện thoại");
        jPanelPhoneNumber.add(jLabelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 4, -1, 30));

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
        jPanelPhoneNumber.add(jTextFieldPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 180, 40));

        jPanelDetail.add(jPanelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 302, 50));

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
        jPanelButton.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, 50));

        jButtonUpdate.setBackground(new java.awt.Color(102, 102, 255));
        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonUpdate.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUpdate.setText("Cập nhật");
        jButtonUpdate.setAutoscrolls(true);
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
        jPanelButton.add(jButtonClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 150, 50));

        jPanelDetail.add(jPanelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 10, 180, 250));

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
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 540, 50));

        jPanelDetail.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 650, 70));

        jPanelAddress.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAddress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldAddress.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldAddress.setText("Địa chỉ");
        jTextFieldAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldAddress.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldAddress.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAddressFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAddressFocusLost(evt);
            }
        });
        jTextFieldAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldAddressMousePressed(evt);
            }
        });
        jPanelAddress.add(jTextFieldAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 540, 40));

        jLabelAddress.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelAddress.setText("Địa chỉ");
        jPanelAddress.add(jLabelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 40));

        jPanelDetail.add(jPanelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 690, -1));

        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 4, true));
        jPanelDetail.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 480, 4));

        add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 280));

        jPanelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(1170, 50));
        jPanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 51, 51));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Quản lý danh sách nhà xuất bản");
        jLabelTitle.setRequestFocusEnabled(false);
        jPanelTitle.add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 483, 40));

        add(jPanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        jScrollPanelTable.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPanelTable.setVerifyInputWhenFocusTarget(false);

        jTablePublisher.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTablePublisher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablePublisher.setRowHeight(40);
        jTablePublisher.setShowGrid(true);
        jTablePublisher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablePublisherMousePressed(evt);
            }
        });
        jTablePublisher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTablePublisherKeyReleased(evt);
            }
        });
        jScrollPanelTable.setViewportView(jTablePublisher);

        add(jScrollPanelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 1120, 390));
    }// </editor-fold>//GEN-END:initComponents

    // Xử lý sự kiện Mouse Pressed của jTable Publisher
    private void jTablePublisherMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePublisherMousePressed
        // TODO add your handling code here:
        int selectedRow = jTablePublisher.getSelectedRow();
        displayDetails(selectedRow);
    }//GEN-LAST:event_jTablePublisherMousePressed

    // Xử lý sự kiện Key Released của jTable Publisher
    private void jTablePublisherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTablePublisherKeyReleased
        // TODO add your handling code here:
        //        int selectedRow = jTableBook.getSelectedRow();
        //        displayDetails(selectedRow);

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedRow = jTablePublisher.getSelectedRow();
            displayDetails(selectedRow);
        }
    }//GEN-LAST:event_jTablePublisherKeyReleased

    // Xử lý sự kiện Press của Button Clear
    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        myInitComponents();
    }//GEN-LAST:event_jButtonClearActionPerformed

     // Xử lý sự kiện Press của Button Update
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String address = jTextFieldAddress.getText();
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (validateBookData(id, name, phoneNumber, address, TypeFunctionEnum.Update)) {
            Publisher publisher = new Publisher(id, name, phoneNumber, address);
            boolean updateCheck = publisherDaoImp.update(publisher);
            if (updateCheck) {
                JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_UPDATE_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_UPDATE_ERROR);
            }
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

     // Xử lý sự kiện Press của Button Delete
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String address = jTextFieldAddress.getText();
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (validateBookData(id, name, phoneNumber, address, TypeFunctionEnum.Delete)) {
            Publisher publisher = new Publisher(id, name, phoneNumber, address);

            int answer = JOptionPane.showConfirmDialog(null,
                    PublisherStringConstant.PUBLISHER_DELETE_TITLE, GeneralStringConstant.GENERAL_DELETE,
                    JOptionPane.YES_NO_OPTION);
            if (answer == JOptionPane.YES_OPTION) {
                boolean deleteCheck = publisherDaoImp.moveToBin(publisher.getPublisherID());
                if (deleteCheck) {
                    JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_DELETE_SUCCESS);
                    myInitComponents();
                } else {
                    JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_DELETE_ERROR);
                }
            } else {

            }

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

     // Xử lý sự kiện Press của Button Insert
    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String address = jTextFieldAddress.getText();
        String phoneNumber = jTextFieldPhoneNumber.getText();

        if (validateBookData(id, name, phoneNumber, address, TypeFunctionEnum.Insert)) {
            Publisher publisher = new Publisher(id, name, phoneNumber, address);
            boolean insertCheck = publisherDaoImp.insert(publisher);
            if (insertCheck) {
                JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_INSERT_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, PublisherStringConstant.PUBLISHER_INSERT_ERROR);
            }
        }
    }//GEN-LAST:event_jButtonInsertActionPerformed

     // Xử lý sự kiện Mouse Press của Text Field Địa chỉ
    private void jTextFieldAddressMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldAddressMousePressed
        // TODO add your handling code here:
        String author = jTextFieldAddress.getText();
        if (author.equals(PublisherStringConstant.PUBLISHER_ADDRESS)) {
            jTextFieldAddress.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldAddressMousePressed

    // Xử lý sự kiện Mouse Press của Text Field Tên nhà xuất bản
    private void jTextFieldNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNameMousePressed
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(PublisherStringConstant.PUBLISHER_NAME)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldNameMousePressed

    // Xử lý sự kiện Mouse Press của Text Field ID nhà xuất bản
    private void jTextFieldIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDMousePressed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(PublisherStringConstant.PUBLISHER_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDMousePressed

    // Xử lý sự kiện Mouse Press của Text Field Số điện thoại
    private void jTextFieldPhoneNumberMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberMousePressed
        // TODO add your handling code here:
        String id = jTextFieldPhoneNumber.getText();
        if (id.equals(PublisherStringConstant.PUBLISHER_PHONE_NUMBER)) {
            jTextFieldPhoneNumber.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPhoneNumberMousePressed

    // Xử lý sự kiện Key Released của Text Field Tìm kiếm
    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        setTableBySearch(text);
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    // Xử lý sự kiện Mouse Press của Text Field Tìm kiếm
    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    // Xử lý sự kiện Focus Gained của Text Field ID Nhà xuất bản
    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusGained
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(PublisherStringConstant.PUBLISHER_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDFocusGained

    // Xử lý sự kiện Focus Gained của Text Field Số điện thoại
    private void jTextFieldPhoneNumberFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberFocusGained
        // TODO add your handling code here:
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (phoneNumber.equals(PublisherStringConstant.PUBLISHER_PHONE_NUMBER)) {
            jTextFieldPhoneNumber.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPhoneNumberFocusGained

    // Xử lý sự kiện Focus Gained của Text Field Tên nhà xuất bản
    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(PublisherStringConstant.PUBLISHER_NAME)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldNameFocusGained

    // Xử lý sự kiện Focus Gained của Text Field Địa chỉ
    private void jTextFieldAddressFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAddressFocusGained
        // TODO add your handling code here:
        String address = jTextFieldAddress.getText();
        if (address.equals(PublisherStringConstant.PUBLISHER_ADDRESS)) {
            jTextFieldAddress.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldAddressFocusGained

    // Xử lý sự kiện Focus Lost của Text Field ID Nhà xuất bản
    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusLost
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldID.setText(PublisherStringConstant.PUBLISHER_ID);
        }
    }//GEN-LAST:event_jTextFieldIDFocusLost

    // Xử lý sự kiện Focus Lost của Text Field Số điện thoại
    private void jTextFieldPhoneNumberFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberFocusLost
        // TODO add your handling code here:
        String phoneNumber = jTextFieldPhoneNumber.getText();
        if (phoneNumber.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldPhoneNumber.setText(PublisherStringConstant.PUBLISHER_PHONE_NUMBER);
        }
    }//GEN-LAST:event_jTextFieldPhoneNumberFocusLost

    // Xử lý sự kiện Focus Lost của Text Field Tên nhà xuất bản
    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusLost
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldName.setText(PublisherStringConstant.PUBLISHER_NAME);
        }
    }//GEN-LAST:event_jTextFieldNameFocusLost

    // Xử lý sự kiện Focus Lost của Text Field Địa chỉ
    private void jTextFieldAddressFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAddressFocusLost
        // TODO add your handling code here:
        String address = jTextFieldAddress.getText();
        if (address.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldAddress.setText(PublisherStringConstant.PUBLISHER_ADDRESS);
        }
    }//GEN-LAST:event_jTextFieldAddressFocusLost

    // Xử lý sự kiện Focus Gained của Text Field Tìm kiếm
    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Tìm kiếm
    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    // Xử lý sự kiện Key Press của Text Field Số điện thoại
    private void jTextFieldPhoneNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPhoneNumberKeyPressed
        // TODO add your handling code here:
        String phoneNumber = jTextFieldPhoneNumber.getText();

        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9' && phoneNumber.length() <= 12) {
            jTextFieldPhoneNumber.setEditable(true);
        } else {
            //Allow backspace and delete
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                jTextFieldPhoneNumber.setEditable(true);
            } else {
                jTextFieldPhoneNumber.setEditable(false);
            }
        }

    }//GEN-LAST:event_jTextFieldPhoneNumberKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPhoneNumber;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelID;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JPanel jPanelPhoneNumber;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPanelTable;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JTable jTablePublisher;
    private javax.swing.JTextField jTextFieldAddress;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldPhoneNumber;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
