/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View.Frame;

import Adapter.PanelButtonMouseAdapter;
import DTO.AccountDTO;
import View.Panel.*;
import constant.GeneralStringConstant;
import constant.TitleStringConstant;

import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * @author Admin
 */
public class HomeForm extends javax.swing.JFrame {

    AccountDTO account = null;

    /**
     * Creates new form HomeForm
     */
    public HomeForm() throws SQLException {
        initComponents();
        myInitComponents();
        addPanelToHomePage();
        this.setLocationRelativeTo(null);
    }

    public HomeForm(AccountDTO account) throws SQLException {
        initComponents();

        this.account = account;
        myInitComponents();
        addPanelToHomePage();
        this.setLocationRelativeTo(null);
        jLabelGreeting.setText("Xin chào, " + account.getName());
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
        jLabelMenu = new javax.swing.JLabel();
        jPanelIndication = new javax.swing.JPanel();
        jLabelProjectName = new javax.swing.JLabel();
        jLabelGreeting = new javax.swing.JLabel();
        jPanelExit = new javax.swing.JPanel();
        jLabelExit = new javax.swing.JLabel();
        jPanelMenuBar = new javax.swing.JPanel();
        jPanelLMSDashBoard = new javax.swing.JPanel();
        jLabelLMSDashBoard = new javax.swing.JLabel();
        jPanelIsueBook = new javax.swing.JPanel();
        jLabelIsueBook = new javax.swing.JLabel();
        jPanelReturnBook = new javax.swing.JPanel();
        jLabelReturnBook = new javax.swing.JLabel();
        jPanelViewRecord = new javax.swing.JPanel();
        jLabelViewRecord1 = new javax.swing.JLabel();
        jPanelBin = new javax.swing.JPanel();
        jLabelBin = new javax.swing.JLabel();
        jPanelViewIssuedBook = new javax.swing.JPanel();
        jLabelViewIssuedBook = new javax.swing.JLabel();
        jPanelLogout = new javax.swing.JPanel();
        jLabelLogOut = new javax.swing.JLabel();
        jPanelManageAccount = new javax.swing.JPanel();
        jLabelManageReader = new javax.swing.JLabel();
        jLabelViewRecord = new javax.swing.JLabel();
        jPanelManageBook = new javax.swing.JPanel();
        jLabelManageBook = new javax.swing.JLabel();
        jPanelManageReader = new javax.swing.JPanel();
        jLabelManageReader1 = new javax.swing.JLabel();
        jPanelPublisherList = new javax.swing.JPanel();
        jLabelPublisherList = new javax.swing.JLabel();
        jPanelMainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1520, 830));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitle.setBackground(new java.awt.Color(102, 102, 255));
        jPanelTitle.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jPanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_menu_48px_1.png"))); // NOI18N
        jPanelTitle.add(jLabelMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        jPanelIndication.setBackground(new java.awt.Color(51, 51, 51));
        jPanelIndication.setPreferredSize(new java.awt.Dimension(5, 0));

        javax.swing.GroupLayout jPanelIndicationLayout = new javax.swing.GroupLayout(jPanelIndication);
        jPanelIndication.setLayout(jPanelIndicationLayout);
        jPanelIndicationLayout.setHorizontalGroup(
            jPanelIndicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanelIndicationLayout.setVerticalGroup(
            jPanelIndicationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        jPanelTitle.add(jPanelIndication, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 0, 5, 60));

        jLabelProjectName.setBackground(new java.awt.Color(255, 255, 255));
        jLabelProjectName.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        jLabelProjectName.setForeground(new java.awt.Color(255, 255, 255));
        jLabelProjectName.setText("Library Management System");
        jPanelTitle.add(jLabelProjectName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 300, -1));

        jLabelGreeting.setBackground(new java.awt.Color(255, 255, 255));
        jLabelGreeting.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 20)); // NOI18N
        jLabelGreeting.setForeground(new java.awt.Color(255, 255, 255));
        jLabelGreeting.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N
        jLabelGreeting.setText("Welcome, Admin");
        jPanelTitle.add(jLabelGreeting, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 0, 230, 60));

        jPanelExit.setBackground(new java.awt.Color(102, 102, 255));
        jPanelExit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelExit.setBackground(new java.awt.Color(255, 255, 255));
        jLabelExit.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 48)); // NOI18N
        jLabelExit.setForeground(new java.awt.Color(255, 255, 255));
        jLabelExit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelExit.setText("X");
        jLabelExit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelExit.add(jLabelExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 40, 50));

        jPanelTitle.add(jPanelExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 0, 40, 50));

        getContentPane().add(jPanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1500, 60));

        jPanelMenuBar.setBackground(new java.awt.Color(51, 51, 51));
        jPanelMenuBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelLMSDashBoard.setBackground(new java.awt.Color(255, 51, 51));
        jPanelLMSDashBoard.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLMSDashBoard.setBackground(new java.awt.Color(153, 153, 255));
        jLabelLMSDashBoard.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabelLMSDashBoard.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLMSDashBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Home_26px_2.png"))); // NOI18N
        jLabelLMSDashBoard.setText("   LMS Dashboard");
        jPanelLMSDashBoard.add(jLabelLMSDashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 210, 60));

        jPanelMenuBar.add(jPanelLMSDashBoard, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 340, 60));

        jPanelIsueBook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelIsueBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIsueBook.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelIsueBook.setForeground(new java.awt.Color(153, 153, 153));
        jLabelIsueBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        jLabelIsueBook.setText("   Issue Book");
        jPanelIsueBook.add(jLabelIsueBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 60));

        jPanelMenuBar.add(jPanelIsueBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 340, 60));

        jPanelReturnBook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelReturnBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReturnBook.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelReturnBook.setForeground(new java.awt.Color(153, 153, 153));
        jLabelReturnBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Return_Purchase_26px.png"))); // NOI18N
        jLabelReturnBook.setText("   Return book");
        jPanelReturnBook.add(jLabelReturnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 60));

        jPanelMenuBar.add(jPanelReturnBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 340, 60));

        jPanelViewRecord.setBackground(new java.awt.Color(51, 51, 51));
        jPanelViewRecord.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelViewRecord1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelViewRecord1.setForeground(new java.awt.Color(153, 153, 153));
        jLabelViewRecord1.setText("   View Records");
        jPanelViewRecord.add(jLabelViewRecord1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 210, 60));

        jPanelMenuBar.add(jPanelViewRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 340, 60));

        jPanelBin.setBackground(new java.awt.Color(51, 51, 51));
        jPanelBin.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBin.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelBin.setForeground(new java.awt.Color(153, 153, 153));
        jLabelBin.setText("Thùng rác");
        jPanelBin.add(jLabelBin, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 220, 60));

        jPanelMenuBar.add(jPanelBin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 340, 60));

        jPanelViewIssuedBook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelViewIssuedBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelViewIssuedBook.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelViewIssuedBook.setForeground(new java.awt.Color(153, 153, 153));
        jLabelViewIssuedBook.setText("   View Issued Books ");
        jPanelViewIssuedBook.add(jLabelViewIssuedBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 220, 60));

        jPanelMenuBar.add(jPanelViewIssuedBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 340, 60));

        jPanelLogout.setBackground(new java.awt.Color(102, 102, 255));
        jPanelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelLogoutMouseClicked(evt);
            }
        });
        jPanelLogout.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelLogOut.setFont(new java.awt.Font("Yu Gothic UI Semilight", 1, 18)); // NOI18N
        jLabelLogOut.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLogOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px_2.png"))); // NOI18N
        jLabelLogOut.setText("   Logout");
        jLabelLogOut.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jLabelLogOut.setIconTextGap(5);
        jPanelLogout.add(jLabelLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 140, 60));

        jPanelMenuBar.add(jPanelLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 690, 340, 60));

        jPanelManageAccount.setBackground(new java.awt.Color(51, 51, 51));
        jPanelManageAccount.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelManageReader.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelManageReader.setForeground(new java.awt.Color(153, 153, 153));
        jLabelManageReader.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabelManageReader.setText("   Manage Account");
        jPanelManageAccount.add(jLabelManageReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 60));

        jPanelMenuBar.add(jPanelManageAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 340, 60));

        jLabelViewRecord.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelViewRecord.setForeground(new java.awt.Color(153, 153, 153));
        jLabelViewRecord.setText("Chức năng");
        jPanelMenuBar.add(jLabelViewRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 210, 60));

        jPanelManageBook.setBackground(new java.awt.Color(51, 51, 51));
        jPanelManageBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelManageBook.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelManageBook.setForeground(new java.awt.Color(153, 153, 153));
        jLabelManageBook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        jLabelManageBook.setText("   Danh sách nhà xuất bản");
        jPanelManageBook.add(jLabelManageBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 270, 60));

        jPanelMenuBar.add(jPanelManageBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 340, 60));

        jPanelManageReader.setBackground(new java.awt.Color(51, 51, 51));
        jPanelManageReader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelManageReader1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelManageReader1.setForeground(new java.awt.Color(153, 153, 153));
        jLabelManageReader1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Read_Online_26px.png"))); // NOI18N
        jLabelManageReader1.setText("   Manage Reader");
        jPanelManageReader.add(jLabelManageReader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 210, 60));

        jPanelMenuBar.add(jPanelManageReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 340, 60));

        jPanelPublisherList.setBackground(new java.awt.Color(51, 51, 51));
        jPanelPublisherList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPublisherList.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabelPublisherList.setForeground(new java.awt.Color(153, 153, 153));
        jLabelPublisherList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        jLabelPublisherList.setText("   Danh sách nhà xuất bản");
        jPanelPublisherList.add(jLabelPublisherList, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 270, 60));

        jPanelMenuBar.add(jPanelPublisherList, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 340, 60));

        getContentPane().add(jPanelMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 52, 340, 750));

        jPanelMainContent.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMainContent.setPreferredSize(new java.awt.Dimension(1170, 630));

        javax.swing.GroupLayout jPanelMainContentLayout = new javax.swing.GroupLayout(jPanelMainContent);
        jPanelMainContent.setLayout(jPanelMainContentLayout);
        jPanelMainContentLayout.setHorizontalGroup(
            jPanelMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1160, Short.MAX_VALUE)
        );
        jPanelMainContentLayout.setVerticalGroup(
            jPanelMainContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelMainContent, new org.netbeans.lib.awtextra.AbsoluteConstraints(339, 58, 1160, 740));

        setSize(new java.awt.Dimension(1504, 800));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLogoutMouseClicked
        // TODO add your handling code here:
        SigninPage signIn = new SigninPage();
        signIn.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jPanelLogoutMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new HomeForm().setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void setNavigationTitle() {
        jLabelManageBook.setText(TitleStringConstant.MANAGE_BOOK);
        jLabelPublisherList.setText(TitleStringConstant.MANAGE_PUBLISHER);
    }

    private void myInitComponents() {
        setNavigationTitle();

        jPanelExit.addMouseListener(new PanelButtonMouseAdapter(jPanelExit, -1) {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (JOptionPane.showConfirmDialog(null, GeneralStringConstant.GENERAL_EXIT, GeneralStringConstant.GENERAL_CONFIRMATION, JOptionPane.YES_NO_OPTION) == 0) {
                    HomeForm.this.dispose();
                }
            }
        });


        jPanelLMSDashBoard.addMouseListener(new PanelButtonMouseAdapter(jPanelLMSDashBoard, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickedMenu(panelHome);
            }
        });

        jPanelManageBook.addMouseListener(new PanelButtonMouseAdapter(jPanelManageBook, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelManageBooks.myInitComponents();
                clickedMenu(panelManageBooks);
            }
        });

        jPanelManageReader.addMouseListener(new PanelButtonMouseAdapter(jPanelManageReader, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickedMenu(panelManageReader);
            }
        });
        jPanelManageAccount.addMouseListener(new PanelButtonMouseAdapter(jPanelManageAccount, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickedMenu(panelManageAccount);
            }
        });

        jPanelPublisherList.addMouseListener(new PanelButtonMouseAdapter(jPanelPublisherList, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelManagePublishers.myInitComponents();
                clickedMenu(panelManagePublishers);
            }
        });

        jPanelIsueBook.addMouseListener(new PanelButtonMouseAdapter(jPanelIsueBook, 0));

        jPanelReturnBook.addMouseListener(new PanelButtonMouseAdapter(jPanelReturnBook, 0){
            @Override
            public void mouseClicked(MouseEvent e) {
                clickedMenu(panelManageBorrow);
            }
        });

        jPanelViewRecord.addMouseListener(new PanelButtonMouseAdapter(jPanelViewRecord, 0));

        jPanelViewIssuedBook.addMouseListener(new PanelButtonMouseAdapter(jPanelViewIssuedBook, 0));

        jPanelBin.addMouseListener(new PanelButtonMouseAdapter(jPanelBin, 0) {
            @Override
            public void mouseClicked(MouseEvent e) {
                panelManageBin.myInitComponents();
                clickedMenu(panelManageBin);
            }
        });

        jPanelLogout.addMouseListener(new PanelButtonMouseAdapter(jPanelLogout, -1));

    }

    private void addPanelToHomePage() throws SQLException {

        panelHome = new HomePanel();
        panelManageBooks = new ManageBooksPanel();
        panelManagePublishers = new ManagePublisherPanel();
        panelManageReader = new ManageReaderPanel();
        panelManageBin = new ManageBinPanel();
        panelManageBorrow = new ManageBorrowPanel();
        panelManageAccount = new ManageAccount(account);

        jPanelMainContent.add(panelHome);
        jPanelMainContent.add(panelManageBooks);
        jPanelMainContent.add(panelManagePublishers);
        jPanelMainContent.add(panelManageReader);
        jPanelMainContent.add(panelManageBin);
        jPanelMainContent.add(panelManageAccount);
        jPanelMainContent.add(panelManageBorrow);
        clickedMenu(panelHome);
    }

    private void clickedMenu(JPanel panel) {
        panelHome.setVisible(false);
        panelManageBooks.setVisible(false);
        panelManagePublishers.setVisible(false);
        panelManageReader.setVisible(false);
        panelManageBin.setVisible(false);
        panelManageAccount.setVisible(false);
        panelManageBorrow.setVisible(false);
        panel.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBin;
    private javax.swing.JLabel jLabelExit;
    private javax.swing.JLabel jLabelGreeting;
    private javax.swing.JLabel jLabelIsueBook;
    private javax.swing.JLabel jLabelLMSDashBoard;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelManageBook;
    private javax.swing.JLabel jLabelManageReader;
    private javax.swing.JLabel jLabelManageReader1;
    private javax.swing.JLabel jLabelMenu;
    private javax.swing.JLabel jLabelProjectName;
    private javax.swing.JLabel jLabelPublisherList;
    private javax.swing.JLabel jLabelReturnBook;
    private javax.swing.JLabel jLabelViewIssuedBook;
    private javax.swing.JLabel jLabelViewRecord;
    private javax.swing.JLabel jLabelViewRecord1;
    private javax.swing.JPanel jPanelBin;
    private javax.swing.JPanel jPanelExit;
    private javax.swing.JPanel jPanelIndication;
    private javax.swing.JPanel jPanelIsueBook;
    private javax.swing.JPanel jPanelLMSDashBoard;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelMainContent;
    private javax.swing.JPanel jPanelManageAccount;
    private javax.swing.JPanel jPanelManageBook;
    private javax.swing.JPanel jPanelManageReader;
    private javax.swing.JPanel jPanelMenuBar;
    private javax.swing.JPanel jPanelPublisherList;
    private javax.swing.JPanel jPanelReturnBook;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JPanel jPanelViewIssuedBook;
    private javax.swing.JPanel jPanelViewRecord;
    // End of variables declaration//GEN-END:variables

    private HomePanel panelHome;
    private ManageBooksPanel panelManageBooks;
    private ManagePublisherPanel panelManagePublishers;
    private ManageReaderPanel panelManageReader;
    private ManageBinPanel panelManageBin;
    private ManageAccount panelManageAccount;
    private ManageBorrowPanel panelManageBorrow;
}
