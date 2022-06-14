/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Base.DIContainer;
import DAO.dao_impl.BookDaoImp;
import DAO.dao_impl.BorrowDaoImp;
import DAO.dao_impl.ReaderDaoImp;
import DTO.Book;
import DTO.Reader;
import constant.DatabaseStringConstant;
import constant.HomeStringConstant;

import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * HomePanel hiển thị những thông tin bao gồm số lượng sách còn, số lượng độc
 * giả, số lượng sách mượn
 */
public class HomePanel extends javax.swing.JPanel {

    /**
     * Creates new form HomePanel
     */
    public HomePanel() {
        /**
         * Khởi tạo giá trị cho bookDaoImp, readerDaoImp, borrowDaoImp thông qua
         * DIContainer
         *
         */
        this.bookDaoImp = DIContainer.getBookDao();
        this.readerDaoImp = DIContainer.getReaderDao();
        this.borrowDaoImp = DIContainer.getBorrowDao();

        initComponents();

        // Bổ sung thêm cho initComponents
        myInitComponents();

        setBounds(0, 0, 1160, 740);
    }

    // bookDaoImp dùng để xử lý những chức năng có liên quan tới Book
    private final BookDaoImp bookDaoImp;

    // readerDaoImp dùng để xử lý những chức năng có liên quan tới Reader
    private final ReaderDaoImp readerDaoImp;

    // borrowDaoImp dùng để xử lý những chức năng có liên quan tới Borrow   
    private final BorrowDaoImp borrowDaoImp;

    // numBook là số lượng sách còn lại (khởi tạo ban đầu = 0)
    private int numBook = 0;

    // numBorrow là số lượng sách đã mượn (khởi tạo ban đầu = 0)
    private int numBorrow = 0;

    // listBook là danh sách Book và được lấy từ trong database
    private ArrayList<Book> listBook;

    // listReader là danh sách Reader và được lấy từ trong database   
    private ArrayList<Reader> listReader;

    // vctBookHeader dùng để đặt Header trong JTableBook
    private Vector vctBookHeader;

    // vctBookData dùng để đặt Row trong JTableBook
    private Vector vctBookData;

    // vctReaderHeader dùng để đặt Header trong JTableReader
    private Vector vctReaderHeader;

    // vctReaderData dùng để đặt Row trong JTableReader
    private Vector vctReaderData;

    // Dùng để khởi tạo các giá trị lấy từ Database cũng như đặt các giá trị final vào các Label
    public void myInitComponents() {
        setLableText();
        setBookValue();
        setReaderValue();
        setBorrowValue();
        setDefaultTable();
        getVectorReaderData();
        getVectorBookData();
        setPieChart();
        showTableBookData(this.vctBookData);
        showTableReaderData(this.vctReaderData);
    }

    // Dùng để setText các jLabel thông qua HomeStringConstant
    private void setLableText() {
        jLabelNoOfBookTitle.setText(HomeStringConstant.HOME_NO_OF_BOOK);
        jLabelNoOfReaderTitle.setText(HomeStringConstant.HOME_NO_OF_READER);
        jLabelDefaultlerListTitle.setText(HomeStringConstant.HOME_NO__OF_RETURN_BOOK);
        jLabelReaderDetailsTitle.setText(HomeStringConstant.HOME_TITLE_READER_TABLE);
        jLabelBookDetailsTitle.setText(HomeStringConstant.HOME_TITLE_BOOK_TABLE);
    }

    // Dùng để set số lượng sách còn lại jLabel
    private void setBookValue() {
        numBook = bookDaoImp.getSumBook();
        jLabelNoOfBook.setText(String.valueOf(numBook));
    }

    // Dùng để set số lượng reader jLabel
    private void setReaderValue() {
        int numReader = readerDaoImp.getAll().size();
        jLabelNoOfReader.setText(String.valueOf(numReader));
    }

    // Dùng để set số lượng sách đã mượn jLabel
    private void setBorrowValue() {
        try {
            numBorrow = borrowDaoImp.getAll().size();
            jLabelDefautlerList.setText(String.valueOf(numBorrow));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Dùng để set những thông số mặc định của bảng
    private void setDefaultTable() {

        // Bảng Reader (set Header, set Scrollbar, set Background, ...)
        jTableReaderDetail.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jScrollPaneReaderDetail.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPaneReaderDetail.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableReaderDetail.setBackground(Color.WHITE);
        jTableReaderDetail.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableReaderDetail.setFillsViewportHeight(true);

        // Bảng Book (set Header, set Scrollbar, set Background, ...)
        jTableBookDetail.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        jScrollPaneBookDetail.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPaneBookDetail.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableBookDetail.setBackground(Color.WHITE);
        jTableBookDetail.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableBookDetail.setFillsViewportHeight(true);
    }

    // Dùng để gán các giá trị Book từ Database vào vctBook thông qua bookDaoImp
    private void getVectorBookData() {
        this.listBook = bookDaoImp.getNewestFiveBook();
        this.vctBookData = new Vector();
        for (int i = 0; i < this.listBook.size(); i++) {
            Vector vctRow = this.listBook.get(i).convertToVector();
            vctBookData.add(vctRow);
        }
    }

    // Dùng để gán các giá trị Book từ Database vào vctReader thông qua ReaderImp
    private void getVectorReaderData() {
        this.listReader = readerDaoImp.getAll();
        this.vctReaderData = new Vector();
        for (int i = 0; i < this.listReader.size(); i++) {
            Vector vctRow = this.listReader.get(i).convertToVector();
            vctReaderData.add(vctRow);
        }
    }

    // Hiển thị bảng jTable Reader được gán vctReader
    private void showTableReaderData(Vector vctData) {
        this.vctReaderHeader = readerDaoImp.getTitleColumn();
        jTableReaderDetail.setModel(new DefaultTableModel(vctData, vctReaderHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    // Hiển thị bảng jTable Book được gán vctBook
    private void showTableBookData(Vector vctData) {
        this.vctBookHeader = this.bookDaoImp.getTitleColumn();

        jTableBookDetail.setModel(new DefaultTableModel(vctData, vctBookHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }

        });

        // Permanent Column
        jTableBookDetail.getColumnModel().getColumn(1).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_NAME,5).length() * 8);
        jTableBookDetail.getColumnModel().getColumn(2).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_CATEGORY, 5).length() * 8);
        jTableBookDetail.getColumnModel().getColumn(3).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_AUTHOR,5).length() * 8);
        jTableBookDetail.getColumnModel().getColumn(7).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.PUBLISHER_NAME, 5).length() * 8);
        jTableBookDetail.getColumnModel().getColumn(8).setPreferredWidth(150);
    }

    // Khởi tạo PieChart và gán PieChart vào Panel
    private void setPieChart() {
        JPanel pieChartPanel = PieChartPanel.showChart(numBook, numBorrow);
        jPanelPieChart.add(pieChartPanel);
        pieChartPanel.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPieChart = new javax.swing.JPanel();
        jLabelDefaultlerListTitle = new javax.swing.JLabel();
        jPanelNoOfBook = new javax.swing.JPanel();
        jLabelNoOfBook = new javax.swing.JLabel();
        jPanelDefautlerList = new javax.swing.JPanel();
        jLabelDefautlerList = new javax.swing.JLabel();
        jLabelNoOfBookTitle = new javax.swing.JLabel();
        jLabelNoOfReaderTitle = new javax.swing.JLabel();
        jPanelNoOfReader = new javax.swing.JPanel();
        jLabelNoOfReader = new javax.swing.JLabel();
        jPanelBookDetail = new javax.swing.JPanel();
        jLabelBookDetailsTitle = new javax.swing.JLabel();
        jScrollPaneBookDetail = new javax.swing.JScrollPane();
        jTableBookDetail = new javax.swing.JTable();
        jPanelReaderDetail = new javax.swing.JPanel();
        jLabelReaderDetailsTitle = new javax.swing.JLabel();
        jScrollPaneReaderDetail = new javax.swing.JScrollPane();
        jTableReaderDetail = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(1288, 800));
        setPreferredSize(new java.awt.Dimension(1288, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPieChart.setBackground(new java.awt.Color(204, 255, 204));
        jPanelPieChart.setLayout(new java.awt.BorderLayout());
        add(jPanelPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 180, 447, 540));

        jLabelDefaultlerListTitle.setBackground(new java.awt.Color(102, 102, 102));
        jLabelDefaultlerListTitle.setFont(new java.awt.Font("Segoe Pro", 1, 20)); // NOI18N
        jLabelDefaultlerListTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelDefaultlerListTitle.setText("Defaulter List");
        add(jLabelDefaultlerListTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, 250, -1));

        jPanelNoOfBook.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(255, 51, 51)));
        jPanelNoOfBook.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanelNoOfBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNoOfBook.setBackground(new java.awt.Color(102, 102, 102));
        jLabelNoOfBook.setFont(new java.awt.Font("Segoe Pro Black", 0, 50)); // NOI18N
        jLabelNoOfBook.setForeground(new java.awt.Color(102, 102, 102));
        jLabelNoOfBook.setText("10000");
        jPanelNoOfBook.add(jLabelNoOfBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, 150, -1));

        add(jPanelNoOfBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 210, 100));

        jPanelDefautlerList.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanelDefautlerList.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanelDefautlerList.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelDefautlerList.setBackground(new java.awt.Color(102, 102, 102));
        jLabelDefautlerList.setFont(new java.awt.Font("Segoe Pro Black", 0, 50)); // NOI18N
        jLabelDefautlerList.setForeground(new java.awt.Color(102, 102, 102));
        jLabelDefautlerList.setText("10000");
        jPanelDefautlerList.add(jLabelDefautlerList, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        add(jPanelDefautlerList, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 60, 210, 100));

        jLabelNoOfBookTitle.setBackground(new java.awt.Color(102, 102, 102));
        jLabelNoOfBookTitle.setFont(new java.awt.Font("Segoe Pro", 1, 20)); // NOI18N
        jLabelNoOfBookTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelNoOfBookTitle.setText("No Of Books");
        add(jLabelNoOfBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 190, -1));

        jLabelNoOfReaderTitle.setBackground(new java.awt.Color(102, 102, 102));
        jLabelNoOfReaderTitle.setFont(new java.awt.Font("Segoe Pro", 1, 20)); // NOI18N
        jLabelNoOfReaderTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelNoOfReaderTitle.setText("No Of Reader");
        add(jLabelNoOfReaderTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 190, -1));

        jPanelNoOfReader.setBorder(javax.swing.BorderFactory.createMatteBorder(15, 0, 0, 0, new java.awt.Color(102, 102, 255)));
        jPanelNoOfReader.setPreferredSize(new java.awt.Dimension(260, 1));
        jPanelNoOfReader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNoOfReader.setBackground(new java.awt.Color(102, 102, 102));
        jLabelNoOfReader.setFont(new java.awt.Font("Segoe Pro Black", 0, 50)); // NOI18N
        jLabelNoOfReader.setForeground(new java.awt.Color(102, 102, 102));
        jLabelNoOfReader.setText("10000");
        jPanelNoOfReader.add(jLabelNoOfReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 28, -1, -1));

        add(jPanelNoOfReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 210, 100));

        jPanelBookDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelBookDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBookDetailsTitle.setBackground(new java.awt.Color(102, 102, 102));
        jLabelBookDetailsTitle.setFont(new java.awt.Font("Segoe Pro", 1, 20)); // NOI18N
        jLabelBookDetailsTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelBookDetailsTitle.setText("Book  Details");
        jPanelBookDetail.add(jLabelBookDetailsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 190, -1));

        jTableBookDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableBookDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableBookDetail.setRowHeight(40);
        jScrollPaneBookDetail.setViewportView(jTableBookDetail);

        jPanelBookDetail.add(jScrollPaneBookDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 590, 250));

        add(jPanelBookDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 610, 300));

        jPanelReaderDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelReaderDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReaderDetailsTitle.setBackground(new java.awt.Color(102, 102, 102));
        jLabelReaderDetailsTitle.setFont(new java.awt.Font("Segoe Pro", 1, 20)); // NOI18N
        jLabelReaderDetailsTitle.setForeground(new java.awt.Color(102, 102, 102));
        jLabelReaderDetailsTitle.setText("Reader Details");
        jPanelReaderDetail.add(jLabelReaderDetailsTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, -1));

        jTableReaderDetail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTableReaderDetail.setModel(new javax.swing.table.DefaultTableModel(
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
        jTableReaderDetail.setRowHeight(40);
        jScrollPaneReaderDetail.setViewportView(jTableReaderDetail);

        jPanelReaderDetail.add(jScrollPaneReaderDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 590, 220));

        add(jPanelReaderDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 610, 260));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelBookDetailsTitle;
    private javax.swing.JLabel jLabelDefaultlerListTitle;
    private javax.swing.JLabel jLabelDefautlerList;
    private javax.swing.JLabel jLabelNoOfBook;
    private javax.swing.JLabel jLabelNoOfBookTitle;
    private javax.swing.JLabel jLabelNoOfReader;
    private javax.swing.JLabel jLabelNoOfReaderTitle;
    private javax.swing.JLabel jLabelReaderDetailsTitle;
    private javax.swing.JPanel jPanelBookDetail;
    private javax.swing.JPanel jPanelDefautlerList;
    private javax.swing.JPanel jPanelNoOfBook;
    private javax.swing.JPanel jPanelNoOfReader;
    private javax.swing.JPanel jPanelPieChart;
    private javax.swing.JPanel jPanelReaderDetail;
    private javax.swing.JScrollPane jScrollPaneBookDetail;
    private javax.swing.JScrollPane jScrollPaneReaderDetail;
    private javax.swing.JTable jTableBookDetail;
    private javax.swing.JTable jTableReaderDetail;
    // End of variables declaration//GEN-END:variables
}
