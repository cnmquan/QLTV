/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import DAO.dao_impl.BookDaoImp;
import DAO.dao_impl.BorrowDaoImp;
import DAO.dao_impl.ReaderDaoImp;
import DTO.*;
import constant.*;
import DTO.Reader;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author thinh
 */
public class ManageBorrowPanel extends javax.swing.JPanel {

    /**
     * Creates new form ManageBorrowPanel
     */
    public ManageBorrowPanel() throws SQLException {
        initComponents();
        borrowDaoImp = new BorrowDaoImp();
        myInitComponents();
        setBounds(0, 0, 1160, 740);
    }

    private final BorrowDaoImp borrowDaoImp;
    private ArrayList<Borrow> borrowList;
    private Vector vctHeader;
    private Vector vctData;

    public void myInitComponents() throws SQLException {
        jTextFieldReaderName.setEditable(false);
        jTextFieldBookTitle.setEditable(false);
        setDefaultText();
        setDefaultTable();
        getVectorData();
        showTableData(this.vctData);
        clearInfo();
    }

    void setDefaultText() {
        jLabelTitle.setText(TitleStringConstant.MANAGE_BORROW);

        jLabelIDBorrow.setText(BorrowStringConstant.BORROW_ID);
        jLabelIDReader.setText(ReaderStringConstant.READER_ID);
        jLabelReaderName.setText(ReaderStringConstant.READER_NAME);
        jLabelIDBook.setText(BookStringConstant.BOOK_ID);
        jLabelBookTitle.setText(BookStringConstant.BOOK_TITLE);
        jLabelQuantity.setText(BorrowStringConstant.BORROW_QUANTITY);
        jLabelBorrowDate.setText(BorrowStringConstant.BORROW_DATE);
        jLabelReturnDate.setText(BorrowStringConstant.RETURN_DATE);
    }

    private void setDefaultTable() {
        jTableBorrow.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        jScrollPanelTable.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPanelTable.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableBorrow.setBackground(Color.WHITE);
        jTableBorrow.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableBorrow.setFillsViewportHeight(true);
    }

    public void getVectorData() throws SQLException {
        this.borrowList = borrowDaoImp.getAll();

        this.vctData = new Vector();
        for (int i = 0; i < this.borrowList.size(); i++) {
            Vector vctRow = this.borrowList.get(i).convertToVector();
            vctData.add(vctRow);
        }
    }

    public void showTableData(Vector vctData) {
        this.vctHeader = this.borrowDaoImp.getTitleColumn();
        jTableBorrow.setModel(new DefaultTableModel(vctData, vctHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });

        DefaultTableModel model = (DefaultTableModel) jTableBorrow.getModel();
        //Add sorter
        var sorter = new TableRowSorter<DefaultTableModel>(model);
        jTableBorrow.setRowSorter(sorter);
    }

    void displayDetail(int selectedIndex) {
        Vector vctSelectedRow = (Vector) this.vctData.get(selectedIndex);

        String idBorrow = (String) vctSelectedRow.get(0);
        String idReader = (String) vctSelectedRow.get(1);
        String idBook = (String) vctSelectedRow.get(2);
        int quantity = (int) vctSelectedRow.get(3);
        String borrowDate = (String) vctSelectedRow.get(4);
        String returnDate = (String) vctSelectedRow.get(5);
        jTextFieldIDBorrow.setText(idBorrow);
        jTextFieldIDReader.setText(idReader);
        jTextFieldIDBook.setText(idBook);
        jTextFieldQuantity.setText(String.valueOf(quantity));
        jTextFieldBorrowDate.setText(borrowDate);
        jTextFieldReturnDate.setText(returnDate);
    }

    public void clearInfo() {
        jTableBorrow.clearSelection();
        jTextFieldIDBorrow.setText(BorrowStringConstant.BORROW_ID);
        jTextFieldQuantity.setText(BorrowStringConstant.BORROW_QUANTITY);
        jTextFieldIDReader.setText(ReaderStringConstant.READER_ID);
        jTextFieldIDBook.setText(BookStringConstant.BOOK_ID);
        jTextFieldBorrowDate.setText(BorrowStringConstant.BORROW_DATE);
        jTextFieldReturnDate.setText(BorrowStringConstant.RETURN_DATE);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
    }

    private void setTableBySearch(String text) {
        this.vctData.clear();
        for (Borrow borrow : this.borrowList) {
            BookDaoImp bookDaoImp = new BookDaoImp();
            Book borrowedBook = bookDaoImp.getAttribute("book_id", borrow.getBookId());
            Reader reader = ReaderDaoImp.getInstance().getAttribute("reader_id", borrow.getReaderId());
            if (borrow.getBorrowId().toLowerCase().contains(text.toLowerCase())
                    || borrow.getReaderId().toLowerCase().contains(text.toLowerCase())
                    || borrow.getBookId().toLowerCase().contains(text.toLowerCase())
                    || borrow.getBorrowDate().toString().contains(text.toLowerCase())
                    || borrow.getReturnDate().toString().contains(text.toLowerCase())
                    || borrowedBook.getBookName().toLowerCase().contains(text.toLowerCase())
                    || reader.getName().toLowerCase().contains(text.toLowerCase())) {
                Vector vctRow = borrow.convertToVector();
                this.vctData.add(vctRow);
            }
        }
        showTableData(this.vctData);
    }

    private boolean validateBorrowData(String borrowID, String readerName, String bookTitle, int quantity, int leftInStock, Date borrowDate, Date returnDate, TypeFunctionEnum typeFunction) {
        String errorList = GeneralStringConstant.GENERAL_EMPTY;
        if (borrowID.isEmpty() || borrowID.isBlank() || borrowID.equals(BorrowStringConstant.BORROW_ID)) {
            errorList = errorList + BorrowStringConstant.BORROW_ID_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (borrowDaoImp.isExist(this.borrowList, borrowID) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + BorrowStringConstant.BORROW_ID_INSERT_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (borrowDaoImp.isExistDeleteList(borrowID) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + BorrowStringConstant.BORROW_ID_DELETED_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } else if (!borrowDaoImp.isExist(this.borrowList, borrowID) && (typeFunction == TypeFunctionEnum.Update || typeFunction == TypeFunctionEnum.Delete)) {
            errorList = errorList + BorrowStringConstant.BORROW_ID_UPDATE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (readerName.isBlank() || readerName.isEmpty()) {
            errorList = errorList + BorrowStringConstant.BORROW_READER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (bookTitle.isEmpty() || bookTitle.isBlank()) {
            errorList = errorList + BorrowStringConstant.BORROW_BOOK_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }
        if (quantity > leftInStock && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + BorrowStringConstant.BORROW_QUANTITY_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }
        Date currentDate = new Date();
        if (borrowDate == null || borrowDate.after(currentDate)) {
            errorList = errorList + "Ngày mượn sách không hợp lệ" + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (returnDate != null && returnDate.before(borrowDate)) {
            errorList = errorList + BorrowStringConstant.BORROW_DATE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        if (!errorList.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            JOptionPane.showMessageDialog(null, errorList);
            return false;

        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelDetail = new javax.swing.JPanel();
        jPanelQuantity = new javax.swing.JPanel();
        jLabelQuantity = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jPanelIDReader = new javax.swing.JPanel();
        jLabelIDReader = new javax.swing.JLabel();
        jTextFieldIDReader = new javax.swing.JTextField();
        jPanelName = new javax.swing.JPanel();
        jLabelBookTitle = new javax.swing.JLabel();
        jTextFieldBookTitle = new javax.swing.JTextField();
        jPanelPhoneNumber = new javax.swing.JPanel();
        jLabelReaderName = new javax.swing.JLabel();
        jTextFieldReaderName = new javax.swing.JTextField();
        jPanelButton = new javax.swing.JPanel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jPanelAddress = new javax.swing.JPanel();
        jTextFieldBorrowDate = new javax.swing.JTextField();
        jLabelBorrowDate = new javax.swing.JLabel();
        jPanelAddress1 = new javax.swing.JPanel();
        jTextFieldReturnDate = new javax.swing.JTextField();
        jLabelReturnDate = new javax.swing.JLabel();
        jSeparatorTitle = new javax.swing.JSeparator();
        jPanelIDBorrow = new javax.swing.JPanel();
        jLabelIDBorrow = new javax.swing.JLabel();
        jTextFieldIDBorrow = new javax.swing.JTextField();
        jPanelIDBook = new javax.swing.JPanel();
        jLabelIDBook = new javax.swing.JLabel();
        jTextFieldIDBook = new javax.swing.JTextField();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPanelTable = new javax.swing.JScrollPane();
        jTableBorrow = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(1170, 630));
        jPanel1.setPreferredSize(new java.awt.Dimension(1170, 630));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelQuantity.setBackground(new java.awt.Color(255, 255, 255));
        jPanelQuantity.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelQuantity.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelQuantity.setText("Số lượng");
        jLabelQuantity.setToolTipText("");
        jPanelQuantity.add(jLabelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 100, 40));

        jTextFieldQuantity.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldQuantity.setText("Số lượng");
        jTextFieldQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldQuantityFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldQuantityFocusLost(evt);
            }
        });
        jTextFieldQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldQuantityMousePressed(evt);
            }
        });
        jPanelQuantity.add(jTextFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 200, 40));

        jPanelDetail.add(jPanelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 370, 40));

        jPanelIDReader.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIDReader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIDReader.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelIDReader.setText("ID độc giả");
        jLabelIDReader.setToolTipText("");
        jPanelIDReader.add(jLabelIDReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 100, 40));

        jTextFieldIDReader.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldIDReader.setText("ID độc giả");
        jTextFieldIDReader.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldIDReader.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDReaderFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDReaderFocusLost(evt);
            }
        });
        jTextFieldIDReader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldIDReaderMousePressed(evt);
            }
        });
        jTextFieldIDReader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDReaderActionPerformed(evt);
            }
        });
        jPanelIDReader.add(jTextFieldIDReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 200, 40));

        jPanelDetail.add(jPanelIDReader, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 350, 40));

        jPanelName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelName.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBookTitle.setBackground(new java.awt.Color(255, 255, 204));
        jLabelBookTitle.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelBookTitle.setText("Tiêu đề sách");
        jPanelName.add(jLabelBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 40));

        jTextFieldBookTitle.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldBookTitle.setText("Tiêu đề sách");
        jTextFieldBookTitle.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldBookTitle.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBookTitleFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBookTitleFocusLost(evt);
            }
        });
        jTextFieldBookTitle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBookTitleMousePressed(evt);
            }
        });
        jPanelName.add(jTextFieldBookTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 200, 40));

        jPanelDetail.add(jPanelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 120, 370, 50));

        jPanelPhoneNumber.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPhoneNumber.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelReaderName.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelReaderName.setText("Tên độc giả");
        jPanelPhoneNumber.add(jLabelReaderName, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        jTextFieldReaderName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldReaderName.setText("Tên độc giả");
        jTextFieldReaderName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldReaderName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldReaderNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldReaderNameFocusLost(evt);
            }
        });
        jTextFieldReaderName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldReaderNameMousePressed(evt);
            }
        });
        jPanelPhoneNumber.add(jTextFieldReaderName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 540, 50));

        jPanelDetail.add(jPanelPhoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 320, 50));

        jPanelButton.setBackground(new java.awt.Color(255, 255, 255));
        jPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInsert.setBackground(new java.awt.Color(51, 153, 0));
        jButtonInsert.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonInsert.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInsert.setText("Thêm");
        jButtonInsert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jButtonInsertActionPerformed(evt);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
                try {
                    jButtonDeleteActionPerformed(evt);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
                try {
                    jButtonUpdateActionPerformed(evt);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 540, 50));

        jPanelDetail.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 650, 60));

        jPanelAddress.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAddress.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldBorrowDate.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldBorrowDate.setText("Ngày mượn");
        jTextFieldBorrowDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldBorrowDate.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldBorrowDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBorrowDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBorrowDateFocusLost(evt);
            }
        });
        jTextFieldBorrowDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBorrowDateMousePressed(evt);
            }
        });
        jPanelAddress.add(jTextFieldBorrowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 200, 40));

        jLabelBorrowDate.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelBorrowDate.setText("Ngày mượn");
        jPanelAddress.add(jLabelBorrowDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanelAddress1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAddress1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldReturnDate.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldReturnDate.setText("Ngày trả");
        jTextFieldReturnDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldReturnDate.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldReturnDate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldReturnDateFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldReturnDateFocusLost(evt);
            }
        });
        jTextFieldReturnDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldReturnDateMousePressed(evt);
            }
        });
        jPanelAddress1.add(jTextFieldReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 200, 40));

        jLabelReturnDate.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelReturnDate.setText("Ngày trả");
        jPanelAddress1.add(jLabelReturnDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 40));

        jPanelAddress.add(jPanelAddress1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 300, 40));

        jPanelDetail.add(jPanelAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, 650, 50));

        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 4, true));
        jPanelDetail.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 480, 4));

        jPanelIDBorrow.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIDBorrow.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIDBorrow.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelIDBorrow.setText("ID mượn");
        jLabelIDBorrow.setToolTipText("");
        jPanelIDBorrow.add(jLabelIDBorrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 80, 40));

        jTextFieldIDBorrow.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldIDBorrow.setText("ID mượn");
        jTextFieldIDBorrow.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldIDBorrow.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDBorrowFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDBorrowFocusLost(evt);
            }
        });
        jTextFieldIDBorrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldIDBorrowMousePressed(evt);
            }
        });
        jPanelIDBorrow.add(jTextFieldIDBorrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 180, 40));

        jPanelDetail.add(jPanelIDBorrow, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 350, 40));

        jPanelIDBook.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIDBook.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelIDBook.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelIDBook.setText("ID sách");
        jLabelIDBook.setToolTipText("");
        jPanelIDBook.add(jLabelIDBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 70, 40));

        jTextFieldIDBook.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldIDBook.setText("ID sách");
        jTextFieldIDBook.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldIDBook.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldIDBookFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDBookFocusLost(evt);
            }
        });
        jTextFieldIDBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldIDBookMousePressed(evt);
            }
        });
        jTextFieldIDBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDBookActionPerformed(evt);
            }
        });
        jPanelIDBook.add(jTextFieldIDBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 200, 40));

        jPanelDetail.add(jPanelIDBook, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 120, 350, 40));

        jPanel1.add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 320));

        jPanelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(1170, 50));
        jPanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 51, 51));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Quản lý danh sách mượn trả");
        jLabelTitle.setRequestFocusEnabled(false);
        jPanelTitle.add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 483, 40));

        jPanel1.add(jPanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        jScrollPanelTable.setBackground(new java.awt.Color(204, 255, 255));
        jScrollPanelTable.setVerifyInputWhenFocusTarget(false);

        jTableBorrow.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableBorrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableBorrow.setRowHeight(40);
        jTableBorrow.setShowGrid(true);
        jTableBorrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableBorrowMousePressed(evt);
            }
        });
        jTableBorrow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableBorrowKeyReleased(evt);
            }
        });
        jScrollPanelTable.setViewportView(jTableBorrow);

        jPanel1.add(jScrollPanelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 1120, 310));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1167, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIDReaderFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDReaderFocusGained
        // TODO add your handling code here:
        String id = jTextFieldIDReader.getText();
        if (id.trim().equals(ReaderStringConstant.READER_ID)) {
            jTextFieldIDReader.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldIDReaderFocusGained

    private void jTextFieldIDReaderFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDReaderFocusLost
        // TODO add your handling code here:
        String id = jTextFieldIDReader.getText();
        if (id.trim().equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldIDReader.setText(ReaderStringConstant.READER_ID);
        }

    }//GEN-LAST:event_jTextFieldIDReaderFocusLost

    private void jTextFieldIDReaderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDReaderMousePressed
        // TODO add your handling code here:
        String id = jTextFieldIDReader.getText();

    }//GEN-LAST:event_jTextFieldIDReaderMousePressed

    private void jTextFieldBookTitleFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBookTitleFocusGained
        // TODO add your handling code here:
        String name = jTextFieldBookTitle.getText();

    }//GEN-LAST:event_jTextFieldBookTitleFocusGained

    private void jTextFieldBookTitleFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBookTitleFocusLost
        // TODO add your handling code here:
        String name = jTextFieldBookTitle.getText();

    }//GEN-LAST:event_jTextFieldBookTitleFocusLost

    private void jTextFieldBookTitleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBookTitleMousePressed
        // TODO add your handling code here:
        String name = jTextFieldBookTitle.getText();

    }//GEN-LAST:event_jTextFieldBookTitleMousePressed

    private void jTextFieldReaderNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldReaderNameFocusGained
        // TODO add your handling code here:
        String phoneNumber = jTextFieldReaderName.getText();

    }//GEN-LAST:event_jTextFieldReaderNameFocusGained

    private void jTextFieldReaderNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldReaderNameFocusLost
        // TODO add your handling code here:
        String phoneNumber = jTextFieldReaderName.getText();

    }//GEN-LAST:event_jTextFieldReaderNameFocusLost

    private void jTextFieldReaderNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldReaderNameMousePressed
        // TODO add your handling code here:
        String id = jTextFieldReaderName.getText();

    }//GEN-LAST:event_jTextFieldReaderNameMousePressed

    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        BookDaoImp bookDaoImp = new BookDaoImp();
        String borrowId = jTextFieldIDBorrow.getText();
        String readerId = jTextFieldIDReader.getText();
        String bookId = jTextFieldIDBook.getText();
        int quantity = Integer.parseInt(jTextFieldQuantity.getText());
        Book book = bookDaoImp.getAttribute("book_id", bookId);
        String borrowDateStr = jTextFieldBorrowDate.getText();
        String returnDateStr = jTextFieldReturnDate.getText();
        String readerName = jTextFieldReaderName.getText();
        String bookTitle = jTextFieldBookTitle.getText();
        Date borrowDate = new SimpleDateFormat("dd/MM/yyyy").parse(borrowDateStr);
        Date returnDate = (returnDateStr.trim().isBlank() || returnDateStr.equals(BorrowStringConstant.RETURN_DATE))
                ? null
                : new SimpleDateFormat("dd/MM/yyyy").parse(returnDateStr);
        Borrow borrow = new Borrow(borrowId, readerId, bookId, quantity, borrowDate, returnDate);
        try {
            if (validateBorrowData(borrowId, readerName, bookTitle, quantity, book.getBookQuantity(), borrowDate, returnDate, TypeFunctionEnum.Insert)) {

                boolean insertCheck = borrowDaoImp.insert(borrow);
                if (insertCheck) {
                    JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_INSERT_SUCCESS);
                    myInitComponents();
                } else {
                    JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_INSERT_ERROR);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_jButtonInsertActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        BookDaoImp bookDaoImp = new BookDaoImp();
        String borrowId = jTextFieldIDBorrow.getText();
        String readerId = jTextFieldIDReader.getText();
        String bookId = jTextFieldIDBook.getText();
        int quantity = Integer.parseInt(jTextFieldQuantity.getText());
        Book book = bookDaoImp.getAttribute("book_id", bookId);
        String borrowDateStr = jTextFieldBorrowDate.getText();
        String returnDateStr = jTextFieldReturnDate.getText();
        String readerName = jTextFieldReaderName.getText();
        String bookTitle = jTextFieldBookTitle.getText();
        Date borrowDate = new SimpleDateFormat("dd/MM/yyyy").parse(borrowDateStr);
        Date returnDate = (returnDateStr.trim().isBlank() || returnDateStr.equals(BorrowStringConstant.RETURN_DATE))
                ? null
                : new SimpleDateFormat("dd/MM/yyyy").parse(returnDateStr);
        Borrow borrow = new Borrow(borrowId, readerId, bookId, quantity, borrowDate, returnDate);
        if (validateBorrowData(borrowId, readerName, bookTitle, quantity, book.getBookQuantity(), borrowDate, returnDate, TypeFunctionEnum.Delete)) {
            try {
                int answer = JOptionPane.showConfirmDialog(null,
                        BorrowStringConstant.BORROW_DELETE_TITLE, GeneralStringConstant.GENERAL_DELETE,
                        JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    boolean deleteCheck = borrowDaoImp.delete(borrow.getBorrowId());
                    if (deleteCheck) {
                        JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_DELETE_SUCCESS);
                        myInitComponents();
                    } else {
                        JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_DELETE_ERROR);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) throws ParseException {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        BookDaoImp bookDaoImp = new BookDaoImp();
        String borrowId = jTextFieldIDBorrow.getText();
        String readerId = jTextFieldIDReader.getText();
        String bookId = jTextFieldIDBook.getText();
        int quantity = Integer.parseInt(jTextFieldQuantity.getText());
        Book book = bookDaoImp.getAttribute("book_id", bookId);
        String borrowDateStr = jTextFieldBorrowDate.getText();
        String returnDateStr = jTextFieldReturnDate.getText();
        String readerName = jTextFieldReaderName.getText();
        String bookTitle = jTextFieldBookTitle.getText();
        Date borrowDate = new SimpleDateFormat("dd/MM/yyyy").parse(borrowDateStr);
        Date returnDate = (returnDateStr.trim().isBlank() || returnDateStr.equals(BorrowStringConstant.RETURN_DATE))
                ? null
                : new SimpleDateFormat("dd/MM/yyyy").parse(returnDateStr);
        Borrow borrow = new Borrow(borrowId, readerId, bookId, quantity, borrowDate, returnDate);
        try {
            if (validateBorrowData(borrowId, readerName, bookTitle, quantity, book.getBookQuantity(), borrowDate, returnDate, TypeFunctionEnum.Update)) {
                boolean updateCheck;
                if (returnDate == null) {
                    updateCheck = borrowDaoImp.update(borrow);
                } else {
                    updateCheck = borrowDaoImp.updateNoIncreaseBookQuantity(borrow);
                }
                if (updateCheck) {
                    JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_UPDATE_SUCCESS);
                } else {
                    JOptionPane.showMessageDialog(null, BorrowStringConstant.BORROW_UPDATE_ERROR);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            myInitComponents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        try {
            myInitComponents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldSearchFocusGained

    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
        }

    }//GEN-LAST:event_jTextFieldSearchFocusLost

    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldSearchMousePressed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        setTableBySearch(text);

    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jTextFieldBorrowDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBorrowDateFocusGained
        // TODO add your handling code here:
        String borrowDateText = jTextFieldBorrowDate.getText();
        if (borrowDateText.equals(BorrowStringConstant.BORROW_DATE)) {
            jTextFieldBorrowDate.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldBorrowDateFocusGained

    private void jTextFieldBorrowDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBorrowDateFocusLost
        // TODO add your handling code here:
        String borrowDateText = jTextFieldBorrowDate.getText();
        if (borrowDateText.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldBorrowDate.setText(BorrowStringConstant.BORROW_DATE);
        }

    }//GEN-LAST:event_jTextFieldBorrowDateFocusLost

    private void jTextFieldBorrowDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBorrowDateMousePressed
        // TODO add your handling code here:
        String borrowDateText = jTextFieldBorrowDate.getText();
        if (borrowDateText.equals(BorrowStringConstant.BORROW_DATE)) {
            jTextFieldBorrowDate.setText(GeneralStringConstant.GENERAL_EMPTY);
        }

    }//GEN-LAST:event_jTextFieldBorrowDateMousePressed

    private void jTableBorrowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBorrowMousePressed
        // TODO add your handling code here:
        int selectedRow = jTableBorrow.getSelectedRow();
        displayDetail(selectedRow);

    }//GEN-LAST:event_jTableBorrowMousePressed

    private void jTableBorrowKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableBorrowKeyReleased
        // TODO add your handling code here:
        //        int selectedRow = jTableBook.getSelectedRow();
        //        displayDetails(selectedRow);

    }//GEN-LAST:event_jTableBorrowKeyReleased

    private void jTextFieldReturnDateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldReturnDateFocusGained
        String returnDateText = jTextFieldReturnDate.getText();
        if (returnDateText.equals(BorrowStringConstant.RETURN_DATE)) {
            jTextFieldReturnDate.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldReturnDateFocusGained

    private void jTextFieldReturnDateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldReturnDateFocusLost
        // TODO add your handling code here:
        String returnDateText = jTextFieldReturnDate.getText();
        if (returnDateText.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldReturnDate.setText(BorrowStringConstant.RETURN_DATE);
        }
    }//GEN-LAST:event_jTextFieldReturnDateFocusLost

    private void jTextFieldReturnDateMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldReturnDateMousePressed
        // TODO add your handling code here:
        String returnDateText = jTextFieldReturnDate.getText();
        if (returnDateText.equals(BorrowStringConstant.RETURN_DATE)) {
            jTextFieldReturnDate.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldReturnDateMousePressed

    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantityFocusGained
        // TODO add your handling code here:
        String quantityText = jTextFieldQuantity.getText();
        if (quantityText.equals(BorrowStringConstant.BORROW_QUANTITY)) {
            jTextFieldQuantity.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldQuantityFocusGained

    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantityFocusLost
        // TODO add your handling code here:
        String quantityText = jTextFieldQuantity.getText();
        if (quantityText.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldQuantity.setText(BorrowStringConstant.BORROW_QUANTITY);
        }
    }//GEN-LAST:event_jTextFieldQuantityFocusLost

    private void jTextFieldQuantityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldQuantityMousePressed
        // TODO add your handling code here:
        String quantityText = jTextFieldQuantity.getText();
        if (quantityText.equals(BorrowStringConstant.BORROW_QUANTITY)) {
            jTextFieldQuantity.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldQuantityMousePressed

    private void jTextFieldIDBorrowFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDBorrowFocusGained
        // TODO add your handling code here:
        String idBorrowText = jTextFieldIDBorrow.getText();
        if (idBorrowText.equals(BorrowStringConstant.BORROW_ID)) {
            jTextFieldIDBorrow.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDBorrowFocusGained

    private void jTextFieldIDBorrowFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDBorrowFocusLost
        // TODO add your handling code here:
        String idBorrowText = jTextFieldIDBorrow.getText();
        if (idBorrowText.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldIDBorrow.setText(BorrowStringConstant.BORROW_ID);
        }
    }//GEN-LAST:event_jTextFieldIDBorrowFocusLost

    private void jTextFieldIDBorrowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDBorrowMousePressed
        // TODO add your handling code here:
        String idBorrowText = jTextFieldIDBorrow.getText();
        if (idBorrowText.equals(BorrowStringConstant.BORROW_ID)) {
            jTextFieldIDBorrow.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDBorrowMousePressed

    private void jTextFieldIDBookFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDBookFocusGained
        // TODO add your handling code here:
        String idBookText = jTextFieldIDBook.getText();
        if (idBookText.equals(BookStringConstant.BOOK_ID)) {
            jTextFieldIDBook.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDBookFocusGained

    private void jTextFieldIDBookFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDBookFocusLost
        // TODO add your handling code here:
        String idBookText = jTextFieldIDBook.getText();

        if (idBookText.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldIDBook.setText(BookStringConstant.BOOK_ID);
        }
    }//GEN-LAST:event_jTextFieldIDBookFocusLost

    private void jTextFieldIDBookMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDBookMousePressed
        // TODO add your handling code here:
        String idBookText = jTextFieldIDBook.getText();
        if (idBookText.equals(BookStringConstant.BOOK_ID)) {
            jTextFieldIDBook.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDBookMousePressed

    private void jTextFieldIDReaderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDReaderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDReaderActionPerformed

    private void jTextFieldIDBookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDBookActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDBookActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabelBookTitle;
    private javax.swing.JLabel jLabelBorrowDate;
    private javax.swing.JLabel jLabelIDBook;
    private javax.swing.JLabel jLabelIDBorrow;
    private javax.swing.JLabel jLabelIDReader;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelReaderName;
    private javax.swing.JLabel jLabelReturnDate;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelAddress;
    private javax.swing.JPanel jPanelAddress1;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelIDBook;
    private javax.swing.JPanel jPanelIDBorrow;
    private javax.swing.JPanel jPanelIDReader;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JPanel jPanelPhoneNumber;
    private javax.swing.JPanel jPanelQuantity;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPanelTable;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JTable jTableBorrow;
    private javax.swing.JTextField jTextFieldBookTitle;
    private javax.swing.JTextField jTextFieldBorrowDate;
    private javax.swing.JTextField jTextFieldIDBook;
    private javax.swing.JTextField jTextFieldIDBorrow;
    private javax.swing.JTextField jTextFieldIDReader;
    private javax.swing.JTextField jTextFieldQuantity;
    private javax.swing.JTextField jTextFieldReaderName;
    private javax.swing.JTextField jTextFieldReturnDate;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables
}
