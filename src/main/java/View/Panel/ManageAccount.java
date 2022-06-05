/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Base.DIContainer;
import DTO.AccountDTO;
import Validate.OnlyNum;
import Validate.Validator;
import constant.AccountStringConstant;
import constant.AuthenStringConstant;
import constant.GeneralStringConstant;
import java.awt.Color;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.PlainDocument;

/**
 *
 * @email Asus
 */
public class ManageAccount extends javax.swing.JPanel {
    
    AccountDTO account = null;

    /**
     * Creates new form ManageAccount
     *
     * @param account Account of current user
     */
    public ManageAccount(AccountDTO account, boolean isShowMyInfo) {
        initComponents();
        
        this.account = account;
        setBounds(0, 0, 1160, 740);
        setDefaultTable();
        setAccountDetailToTable();
        initUI();
        if (isShowMyInfo) {
            setDataToField(account.getId());
            showFunction(account.getId());
        }        
    }
    
    private void initUI() {
        txtUsername.setText(AccountStringConstant.ACCOUNT_INPUT_USERNAME);
        txtContact.setText(AccountStringConstant.ACCOUNT_INPUT_CONTACT);
        txtEmail.setText(AccountStringConstant.ACCOUNT_INPUT_EMAIL);
        txtName.setText(AccountStringConstant.ACCOUNT_INPUT_NAME);
        txtID.setText(AccountStringConstant.ACCOUNT_INPUT_ID);
        
        lblID.setText(AccountStringConstant.ACCOUNT_ID);
        lblUsername.setText(AccountStringConstant.ACCOUNT_USERNAME);
        lblName.setText(AccountStringConstant.ACCOUNT_NAME);
        lblContact.setText(AccountStringConstant.ACCOUNT_CONTACT);
        lblEmail.setText(AccountStringConstant.ACCOUNT_EMAIL);
        lblQuestion.setText(AccountStringConstant.ACCOUNT_QUESTION);
        lblAnswer.setText(AccountStringConstant.ACCOUNT_ANSWER);
        
        cmbQuestion.setModel(new DefaultComboBoxModel(AuthenStringConstant.QUESTIONS));
        cmbRole.setModel(new DefaultComboBoxModel(AuthenStringConstant.ROLES));
        
        PlainDocument doc = (PlainDocument) txtContact.getDocument();
        doc.setDocumentFilter(new OnlyNum());
        jButtonInsert.setVisible(false);
    }

    /**
     * This function use for validate input of user
     *
     * @return resutlt of validate
     */
    public boolean validateInput() {
        String name = txtName.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        
        if (Validator.inputString("[a-zA-Z]+([ '-][a-zA-Z]+)*", name)) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_ERROR_NAME);
            return false;
        }
        if (Validator.inputString("[a-zA-Z0-9!@#$%^&*\\.]+", username)) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_ERROR_USERNAME);
            return false;
        }
        if (Validator.inputString("^.+@.+\\..+$", email)) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_ERROR_EMAIL);
            return false;
        }
        if (Validator.inputString("^[0-9]{10}$", contact)) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_ERROR_CONTACT);
            return false;
        }
        return true;
    }
    
    public boolean checkExistUsername() {
        String username = txtUsername.getText();
        boolean isExist;
        
        if (!username.equals("") && DIContainer.getAccountDAO().isExistUsername(username)) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_EXIST_USERNAME);
            isExist = true;
        } else {
            isExist = false;
        }
        return isExist;
    }
    
    private void setDefaultTable() {
        tblAccountDetail.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        jScrollPanelTable.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        jScrollPanelTable.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        tblAccountDetail.setBackground(Color.WHITE);
        tblAccountDetail.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblAccountDetail.setFillsViewportHeight(true);

        //Add column header
        DefaultTableModel model = (DefaultTableModel) tblAccountDetail.getModel();
        String[] titles
                = {AccountStringConstant.ACCOUNT_ID, AccountStringConstant.ACCOUNT_NAME, AccountStringConstant.ACCOUNT_USERNAME, AccountStringConstant.ACCOUNT_EMAIL, AccountStringConstant.ACCOUNT_CONTACT, AccountStringConstant.ACCOUNT_ROLE};
        for (String title : titles) {
            model.addColumn(title);
        }
        
        model.setColumnCount(titles.length);
        tblAccountDetail.setDefaultEditor(Object.class, null);
        tblAccountDetail.setRowSelectionAllowed(true);
    }

    /**
     * To set account details to table
     */
    private void setAccountDetailToTable() {
        DefaultTableModel model = (DefaultTableModel) tblAccountDetail.getModel();
        ArrayList<AccountDTO> lAccounts = new ArrayList<AccountDTO>();
        
        model.setRowCount(0);
        lAccounts = DIContainer.getAccountDAO().findAll();
        lAccounts.forEach((AccountDTO account) -> {
            Object[] object = {account.getId(), account.getName(), account.getUsername(), account.getEmail(), account.getContact(), account.getRole()};
            model.addRow(object);
        });
    }
    
    private void setDataToField(String id) {
        AccountDTO currentAcc = DIContainer.getAccountDAO().findByID(id);
        
        txtID.setText(currentAcc.getId());
        txtName.setText(currentAcc.getName());
        txtUsername.setText(currentAcc.getUsername());
        txtEmail.setText(currentAcc.getEmail());
        txtContact.setText(currentAcc.getContact());
        txtAnswer.setText(currentAcc.getAnswer());
        
        cmbRole.setSelectedItem(currentAcc.getRole());
        cmbQuestion.setSelectedItem(currentAcc.getQuestion());
        
        
    }
    
    private void showFunction(String id) {
        if (account.getRole().equals("ADMIN")) {
            if (account.getId().equals(id)) {
                cmbRole.setEnabled(false);
                jButtonDelete.setVisible(false);
            } else {
                cmbRole.setEnabled(true);
                jButtonDelete.setVisible(true);
            }
        }
        if (!account.getRole().equals("ADMIN")) {
            jButtonDelete.setVisible(false);
            jButtonRecover.setVisible(false);
            jButtonUpdate.setVisible(false);
            
            lblQuestion.setVisible(false);
            lblAnswer.setVisible(false);
            cmbQuestion.setVisible(false);
            txtAnswer.setVisible(false);
            
            cmbRole.setEnabled(false);
            if (account.getId().equals(txtID.getText())) {
                jButtonUpdate.setVisible(true);
                
                lblQuestion.setVisible(true);
                lblAnswer.setVisible(true);
                cmbQuestion.setVisible(true);
                txtAnswer.setVisible(true);
            }
        }
    }
    
    private void updateAccount() {
        String id = txtID.getText();
        String name = txtName.getText();
        String username = txtUsername.getText();
        String email = txtEmail.getText();
        String contact = txtContact.getText();
        String question = (String) cmbQuestion.getSelectedItem();
        String answer = txtAnswer.getText();
        String role = (String) cmbRole.getSelectedItem();
        
        AccountDTO oldAccount = DIContainer.getAccountDAO().findByID(id);
        AccountDTO newAccount = new AccountDTO(id, name, username, oldAccount.getPassword(), email, contact, question, answer, role);
        boolean result = DIContainer.getAccountDAO().update(newAccount);
        if (result) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_UPDATE_SUCCESS);
            setAccountDetailToTable();
        } else {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_UPDATE_FAIL);
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

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtUsername = new javax.swing.JTextField();
        txtName = new javax.swing.JTextField();
        txtID = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtContact = new javax.swing.JTextField();
        txtAnswer = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblID = new javax.swing.JLabel();
        lblContact = new javax.swing.JLabel();
        lblAnswer = new javax.swing.JLabel();
        lblQuestion = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        cmbRole = new javax.swing.JComboBox<>();
        cmbQuestion = new javax.swing.JComboBox<>();
        jButtonInsert = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonRecover = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jScrollPanelTable = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAccountDetail = new javax.swing.JTable();

        setBorder(new javax.swing.border.MatteBorder(null));
        setMinimumSize(new java.awt.Dimension(1170, 630));
        setPreferredSize(new java.awt.Dimension(1170, 630));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/icons8_Edit_Property_50px.png"))); // NOI18N
        jLabel1.setText("Quản lý tài khoản");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 51, 51));
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 390, 5));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtUsername.setEditable(false);
        txtUsername.setBackground(new java.awt.Color(255, 255, 255));
        txtUsername.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtUsername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtUsername.setMargin(new java.awt.Insets(3, 6, 3, 6));
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 300, 40));

        txtName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtName.setMargin(new java.awt.Insets(3, 6, 3, 6));
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        jPanel1.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 300, 40));

        txtID.setEditable(false);
        txtID.setBackground(new java.awt.Color(255, 255, 255));
        txtID.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtID.setMargin(new java.awt.Insets(3, 6, 3, 6));
        jPanel1.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 300, 40));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtEmail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtEmail.setMargin(new java.awt.Insets(3, 6, 3, 6));
        txtEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtEmailFocusLost(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 300, 40));

        txtContact.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtContact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtContact.setMargin(new java.awt.Insets(3, 6, 3, 6));
        txtContact.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtContactFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtContactFocusLost(evt);
            }
        });
        jPanel1.add(txtContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 300, 40));

        txtAnswer.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        txtAnswer.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        txtAnswer.setMargin(new java.awt.Insets(3, 6, 3, 6));
        txtAnswer.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAnswerFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAnswerFocusLost(evt);
            }
        });
        jPanel1.add(txtAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 760, 40));

        lblUsername.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(51, 51, 51));
        lblUsername.setText("Tài khoản:");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblName.setForeground(new java.awt.Color(51, 51, 51));
        lblName.setText("Chủ tài khoản");
        jPanel1.add(lblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        jPanel1.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, -1, -1));

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblID.setForeground(new java.awt.Color(51, 51, 51));
        lblID.setText("ID");
        jPanel1.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        lblContact.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblContact.setForeground(new java.awt.Color(51, 51, 51));
        lblContact.setText("Số điện thoại");
        jPanel1.add(lblContact, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, -1, -1));

        lblAnswer.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblAnswer.setForeground(new java.awt.Color(51, 51, 51));
        lblAnswer.setText("Đáp án");
        jPanel1.add(lblAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        lblQuestion.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblQuestion.setForeground(new java.awt.Color(51, 51, 51));
        lblQuestion.setText("Câu hỏi bảo mật");
        jPanel1.add(lblQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, -1, -1));

        lblRole.setFont(new java.awt.Font("Segoe UI", 1, 17)); // NOI18N
        lblRole.setForeground(new java.awt.Color(51, 51, 51));
        lblRole.setText("Vị trí");
        jPanel1.add(lblRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, -1, -1));

        cmbRole.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        cmbRole.setBorder(null);
        jPanel1.add(cmbRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 300, 40));

        cmbQuestion.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        cmbQuestion.setBorder(null);
        jPanel1.add(cmbQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 880, 40));

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
        jPanel1.add(jButtonInsert, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 10, 150, 50));

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
        jPanel1.add(jButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 150, 50));

        jButtonRecover.setBackground(new java.awt.Color(255, 102, 51));
        jButtonRecover.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonRecover.setForeground(new java.awt.Color(255, 255, 255));
        jButtonRecover.setText("Khôi phục");
        jButtonRecover.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRecover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRecoverActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonRecover, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 190, 150, 50));

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
        jPanel1.add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 130, 150, 50));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1170, 360));

        tblAccountDetail.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        tblAccountDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblAccountDetail.setRowHeight(40);
        tblAccountDetail.getTableHeader().setReorderingAllowed(false);
        tblAccountDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAccountDetailMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblAccountDetail);

        jScrollPanelTable.setViewportView(jScrollPane2);

        jPanel2.add(jScrollPanelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 1120, 270));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 740));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusGained
        // TODO add your handling code here:
        String name = txtName.getText();
        if (name.equals(AccountStringConstant.ACCOUNT_INPUT_NAME)) {
            txtName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_txtNameFocusGained

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        // TODO add your handling code here:
        String name = txtName.getText();
        if (name.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            txtName.setText(AccountStringConstant.ACCOUNT_INPUT_NAME);
        }
    }//GEN-LAST:event_txtNameFocusLost

    private void txtEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusGained
        // TODO add your handling code here:
        String email = txtEmail.getText();
        if (email.equals(AccountStringConstant.ACCOUNT_INPUT_EMAIL)) {
            txtEmail.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_txtEmailFocusGained

    private void txtEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtEmailFocusLost
        // TODO add your handling code here:
        String email = txtEmail.getText();
        if (email.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            txtEmail.setText(AccountStringConstant.ACCOUNT_INPUT_EMAIL);
        }
    }//GEN-LAST:event_txtEmailFocusLost

    private void txtContactFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContactFocusGained
        // TODO add your handling code here:
        String contact = txtContact.getText();
        if (contact.equals(AccountStringConstant.ACCOUNT_INPUT_CONTACT)) {
            txtContact.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_txtContactFocusGained

    private void txtContactFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtContactFocusLost
        // TODO add your handling code here:
        String contact = txtContact.getText();
        if (contact.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            txtContact.setText(AccountStringConstant.ACCOUNT_INPUT_CONTACT);
        }
    }//GEN-LAST:event_txtContactFocusLost

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:     
        if (validateInput()) {
            updateAccount();
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonRecoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRecoverActionPerformed
        // TODO add your handling code here:
        String id = txtID.getText();
        boolean result = DIContainer.getAccountDAO().recoverAccount(id);
        if (result) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_RECOVER_SUCCESS);
        } else {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_RECOVER_FAIL);
        }
    }//GEN-LAST:event_jButtonRecoverActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        String id = txtID.getText();
        boolean result = DIContainer.getAccountDAO().delete(id);
        if (result) {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_DELETE_SUCCESS);
            setAccountDetailToTable();
        } else {
            JOptionPane.showMessageDialog(this, AccountStringConstant.ACCOUNT_DELETE_FAIL);
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void tblAccountDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAccountDetailMouseClicked
        // TODO add your handling code here:
        int rowNo = tblAccountDetail.getSelectedRow();
        TableModel model = tblAccountDetail.getModel();
        String id = model.getValueAt(rowNo, 0).toString();
        
        setDataToField(id);
        showFunction(id);
    }//GEN-LAST:event_tblAccountDetailMouseClicked

    private void txtAnswerFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnswerFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnswerFocusGained

    private void txtAnswerFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAnswerFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAnswerFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbQuestion;
    private javax.swing.JComboBox<String> cmbRole;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonRecover;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPanelTable;
    private javax.swing.JLabel lblAnswer;
    private javax.swing.JLabel lblContact;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblQuestion;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JTable tblAccountDetail;
    private javax.swing.JTextField txtAnswer;
    private javax.swing.JTextField txtContact;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
