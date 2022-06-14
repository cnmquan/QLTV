/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View.Panel;

import Adapter.SupportFunction;
import Base.DIContainer;
import constant.BookStringConstant;
import constant.GeneralStringConstant;
import constant.TitleStringConstant;
import DAO.dao_impl.BookDaoImp;
import DAO.dao_impl.PublisherDaoImp;
import java.awt.Color;
import java.awt.Font;
import DTO.Book;
import DTO.Publisher;
import DTO.TypeFunctionEnum;
import constant.DatabaseStringConstant;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.table.TableRowSorter;

/**
 * ManageBooksPanel dùng để hiện thị thông tin sách, cũng như các chức năng
 * thêm, xoá, sửa sách
 */
public class ManageBooksPanel extends JPanel {

    // bookDaoImp dùng để xử lý những chức năng có liên quan tới Book
    private final BookDaoImp bookDaoImp;

    // publisherDaoImp dùng để xử lý những chức năng có liên quan tới Publisher
    private final PublisherDaoImp publisherDaoImp;

    // publisherList là danh sách Publisher và được lấy từ trong database
    private ArrayList<Publisher> publisherList;

    // listBook là danh sách Book và được lấy từ trong database
    private ArrayList<Book> listBook;

    // vctHeader dùng để đặt Header trong JTableBook
    private Vector vctHeader;

    // vctData dùng để đặt Row trong JTableBook
    private Vector vctData;

    public ManageBooksPanel() {
        /**
         * Khởi tạo giá trị cho bookDaoImp, publisherDaoImp thông qua
         * DIContainer
         *
         */
        this.bookDaoImp = DIContainer.getBookDao();
        this.publisherDaoImp = DIContainer.getPublisherDao();

        initComponents();

        // Bổ sung thêm cho initComponents
        myInitComponents();

        setBounds(0, 0, 1160, 740);
    }

    // Dùng để khởi tạo các giá trị lấy từ Database cũng như đặt các giá trị final vào các Label
    public void myInitComponents() {
        setDefaultText();
        setDefaultTable();
        getVectorData();
        showTableData(this.vctData);
        getListPublisherName();
        clearInfo();
    }

    // Dùng để gán các giá trị Book từ Database vào vctData thông qua bookDaoImp
    private void getVectorData() {
        this.listBook = bookDaoImp.getAll();
        this.vctData = new Vector();
        for (int i = 0; i < this.listBook.size(); i++) {
            Vector vctRow = this.listBook.get(i).convertToVector();
            vctData.add(vctRow);
        }
    }

    // Dùng để setText các jLabel thông qua TitleStringConstant, BookStringConstant, GeneralStringConstant
    private void setDefaultText() {
        jLabelTitle.setText(TitleStringConstant.MANAGE_BOOK);

        jLabelID.setText(BookStringConstant.BOOK_ID);
        jLabelName.setText(BookStringConstant.BOOK_TITLE);
        jLabelAuthor.setText(BookStringConstant.BOOK_AUTHOR);
        jLabelCategory.setText(BookStringConstant.BOOK_CATEGORY);
        jLabelPublisher.setText(BookStringConstant.BOOK_PUBLISHER);
        jLabelPublishYear.setText(BookStringConstant.BOOK_PUBLISH_YEAR);
        jLabelPageNum.setText(BookStringConstant.BOOK_PAGE_NUMBER);
        jLabelQuantity.setText(BookStringConstant.BOOK_QUANTITY);
        jLabelPrice.setText(BookStringConstant.BOOK_PRICE);
        jLabelSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jTextFieldID.setText(BookStringConstant.BOOK_ID);
        jTextFieldName.setText(BookStringConstant.BOOK_TITLE);
        jTextFieldAuthor.setText(BookStringConstant.BOOK_AUTHOR);
        jTextFieldCategory.setText(BookStringConstant.BOOK_CATEGORY);
        jComboBoxPublisher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{BookStringConstant.BOOK_PUBLISHER}));
        jTextFieldPublishYear.setText(BookStringConstant.BOOK_PUBLISH_YEAR);
        jTextFieldPageNum.setText(BookStringConstant.BOOK_PAGE_NUMBER);
        jTextFieldQuantity.setText(BookStringConstant.BOOK_QUANTITY);
        jTextFieldPrice.setText(BookStringConstant.BOOK_PRICE);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);

        jButtonInsert.setText(GeneralStringConstant.GENERAL_INSERT);
        jButtonUpdate.setText(GeneralStringConstant.GENERAL_UPDATE);
        jButtonClear.setText(GeneralStringConstant.GENERAL_CLEAR);
        jButtonDelete.setText(GeneralStringConstant.GENERAL_DELETE);
    }

    // Dùng để set những thông số mặc định của bảng
    private void setDefaultTable() {
        jTableBook.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        jScrollPanelTable.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jScrollPanelTable.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        jTableBook.setBackground(Color.WHITE);
        jTableBook.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        jTableBook.setFillsViewportHeight(true);
    }

    // Hiển thị bảng jTableBook được gán vctData
    private void showTableData(Vector vctData) {
        this.vctHeader = this.bookDaoImp.getTitleColumn();

        jTableBook.setModel(new DefaultTableModel(vctData, vctHeader) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        });

        // Permanent Column
        for (int i = 0; i < bookDaoImp.getTitleColumn().size(); i++) {
            switch (i) {
                case 1 ->
                    jTableBook.getColumnModel().getColumn(i).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_NAME, 0).length() * 8);
                case 2 ->
                    jTableBook.getColumnModel().getColumn(i).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_CATEGORY, 0).length() * 12);
                case 3 ->
                    jTableBook.getColumnModel().getColumn(i).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.BOOK_AUTHOR, 0).length() * 8);
                case 7 ->
                    jTableBook.getColumnModel().getColumn(i).setPreferredWidth(bookDaoImp.getLongestString(DatabaseStringConstant.PUBLISHER_NAME, 0).length() * 12);
                default ->
                    jTableBook.getColumnModel().getColumn(i).setPreferredWidth(150);

            }
        }

        DefaultTableModel model = (DefaultTableModel) jTableBook.getModel();
        //Add sorter
        var sorter = new TableRowSorter<DefaultTableModel>(model);
        jTableBook.setRowSorter(sorter);
    }

    // Gán danh sách Publisher lấy từ Database thông qua PublisherDaoImp vào PublisherComboBox
    private void getListPublisherName() {
        this.publisherList = publisherDaoImp.getAll();
        for (int i = 0; i < publisherList.size(); i++) {
            jComboBoxPublisher.addItem(publisherList.get(i).getPublisherName());
        }
    }

    // Hiển thị thông tin chi tiết của Book khi chọn 1 hàng trong jTableBook
    private void displayDetails(int selectedIndex) {
        int row = jTableBook.convertRowIndexToModel(selectedIndex);
        Vector vctSelectedRow = (Vector) this.vctData.get(row);
        
        String id = (String) vctSelectedRow.get(0);
        String name = (String) vctSelectedRow.get(1);
        String category = (String) vctSelectedRow.get(2);
        String author = (String) vctSelectedRow.get(3);
        String quantity = String.valueOf(vctSelectedRow.get(4));
        String pageNumber = String.valueOf(vctSelectedRow.get(5));
        String price = String.valueOf(vctSelectedRow.get(6));
        String publisherName = (String) vctSelectedRow.get(7);
        String publishYear = String.valueOf(vctSelectedRow.get(8));

        jTextFieldAuthor.setText(author);
        jTextFieldCategory.setText(category);
        jTextFieldID.setText(id);
        jTextFieldName.setText(name);
        jTextFieldPageNum.setText(pageNumber);
        jTextFieldPrice.setText(price);
        jTextFieldPublishYear.setText(publishYear);
        jTextFieldQuantity.setText(quantity);
        jComboBoxPublisher.setSelectedItem(publisherName);

        for (int i = 0; i < jComboBoxPublisher.getItemCount() + 1; i++) {
            if (i == jComboBoxPublisher.getItemCount()) {
                jComboBoxPublisher.addItem(publisherName);
                jComboBoxPublisher.setSelectedItem(publisherName);
                break;
            } else if (jComboBoxPublisher.getItemAt(i).equals(publisherName)) {
                jComboBoxPublisher.setSelectedIndex(i);
                break;
            } else {

            }
        }
    }

    // Xoá những giá trị đã điền khỏi jTextField
    private void clearInfo() {
        jTableBook.clearSelection();
        jTextFieldAuthor.setText(BookStringConstant.BOOK_AUTHOR);
        jTextFieldCategory.setText(BookStringConstant.BOOK_CATEGORY);
        jTextFieldID.setText(BookStringConstant.BOOK_ID);
        jTextFieldName.setText(BookStringConstant.BOOK_TITLE);
        jTextFieldPageNum.setText(BookStringConstant.BOOK_PAGE_NUMBER);
        jTextFieldPrice.setText(BookStringConstant.BOOK_PRICE);
        jTextFieldPublishYear.setText(BookStringConstant.BOOK_PUBLISH_YEAR);
        jTextFieldQuantity.setText(BookStringConstant.BOOK_QUANTITY);
        jComboBoxPublisher.setSelectedItem(BookStringConstant.BOOK_PUBLISHER);
        jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
    }

    // Kiểm tra khi nhấn vào nút Insert/Update/Delete
    private boolean validateBookData(String id, String name, String category,
            String author, String quantity, String pageNumber, String price,
            String publisherName, String publishYear, TypeFunctionEnum typeFunction) {

        // Khởi tạo chuỗi errorList
        String errorList = GeneralStringConstant.GENERAL_EMPTY;

        // Kiểm tra ID nhập vào có trống không
        if (id.isBlank() || id.isEmpty() || id.equals(BookStringConstant.BOOK_ID)) {
            errorList = errorList + BookStringConstant.BOOK_ID_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID nhập vào có trùng với ID đã có trong danh sách khi Insert không 
        else if (bookDaoImp.isExist(this.listBook, id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + BookStringConstant.BOOK_ID_INSERT_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID nhập vào có trùng với ID đã có trong danh sách thùng rác khi Insert không 
        else if (bookDaoImp.isExistDeleteList(id) && typeFunction == TypeFunctionEnum.Insert) {
            errorList = errorList + BookStringConstant.BOOK_ID_DELETED_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        } // Kiểm tra ID có khác với ID đã có trong danh sách khi Update/Delete không
        else if (!bookDaoImp.isExist(this.listBook, id) && (typeFunction == TypeFunctionEnum.Update || typeFunction == TypeFunctionEnum.Delete)) {
            errorList = errorList + BookStringConstant.BOOK_ID_UPDATE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Tên nhập vào có trống không
        if (name.isBlank() || name.isEmpty()) {
            errorList = errorList + BookStringConstant.BOOK_TITLE_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Thể loại nhập vào có trống không
        if (category.isBlank() || category.isEmpty()) {
            errorList = errorList + BookStringConstant.BOOK_CATEGORY_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Tác giả nhập vào có trống không
        if (author.isBlank() || author.isEmpty()) {
            errorList = errorList + BookStringConstant.BOOK_AUTHOR_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra Nhà xuất bản đã được chọn chưa
        if (publisherName.equals(BookStringConstant.BOOK_PUBLISHER)) {
            errorList = errorList + BookStringConstant.BOOK_PUBLISHER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra số lượng nhập vào có hợp lệ không (> 0)
        if ((quantity.equals(GeneralStringConstant.GENERAL_ZERO)) || quantity.isEmpty() || !SupportFunction.checkNumber(quantity)) {
            errorList = errorList + BookStringConstant.BOOK_QUANTITY_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra số trang nhập vào có hợp lê không (>0)
        if (pageNumber.equals(GeneralStringConstant.GENERAL_ZERO) || quantity.isEmpty() || !SupportFunction.checkNumber(quantity)) {
            errorList = errorList + BookStringConstant.BOOK_PAGE_NUMBER_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra giá tiền nhập vào có hợp lệ không (>0)
        if (price.equals(GeneralStringConstant.GENERAL_ZERO) || quantity.isEmpty() || !SupportFunction.checkPriceNumber(quantity)) {
            errorList = errorList + BookStringConstant.BOOK_PRICE + GeneralStringConstant.GENERAL_NEW_LINE;
        }

        // Kiểm tra năm nhập vào có hợp lệ không (>0)
        if (publishYear.equals(GeneralStringConstant.GENERAL_ZERO) || publishYear.isEmpty() || !SupportFunction.checkNumber(quantity)) {
            errorList = errorList + BookStringConstant.BOOK_PUBLISH_YEAR_EMPTY_ERROR + GeneralStringConstant.GENERAL_NEW_LINE;
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
        for (Book book : this.listBook) {
            if (book.getBookID().toLowerCase().contains(text.toLowerCase())
                    || book.getBookName().toLowerCase().contains(text.toLowerCase())
                    || book.getBookCategory().toLowerCase().contains(text.toLowerCase())
                    || book.getBookAuthor().toLowerCase().contains(text.toLowerCase())
                    || book.getPublisherName().toLowerCase().contains(text.toLowerCase())) {
                Vector vctRow = book.convertToVector();
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
        jPanelButton = new javax.swing.JPanel();
        jButtonInsert = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jPanelSearch = new javax.swing.JPanel();
        jLabelSearch = new javax.swing.JLabel();
        jTextFieldSearch = new javax.swing.JTextField();
        jSeparatorTitle = new javax.swing.JSeparator();
        jPanelMainDetail = new javax.swing.JPanel();
        jPanelID = new javax.swing.JPanel();
        jLabelID = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jPanelName = new javax.swing.JPanel();
        jLabelName = new javax.swing.JLabel();
        jScrollPaneTextFieldName = new javax.swing.JScrollPane();
        jTextFieldName = new javax.swing.JTextArea();
        jPanelQuantity = new javax.swing.JPanel();
        jLabelQuantity = new javax.swing.JLabel();
        jTextFieldQuantity = new javax.swing.JTextField();
        jPanelPrice = new javax.swing.JPanel();
        jLabelPrice = new javax.swing.JLabel();
        jTextFieldPrice = new javax.swing.JTextField();
        jPanelPublisher = new javax.swing.JPanel();
        jLabelPublisher = new javax.swing.JLabel();
        jComboBoxPublisher = new javax.swing.JComboBox<>();
        jPanelAuthor = new javax.swing.JPanel();
        jTextFieldAuthor = new javax.swing.JTextField();
        jLabelAuthor = new javax.swing.JLabel();
        jPanelPublishYear = new javax.swing.JPanel();
        jLabelPublishYear = new javax.swing.JLabel();
        jTextFieldPublishYear = new javax.swing.JTextField();
        jPanelPageNum = new javax.swing.JPanel();
        jLabelPageNum = new javax.swing.JLabel();
        jTextFieldPageNum = new javax.swing.JTextField();
        jPanelCategory = new javax.swing.JPanel();
        jLabelCategory = new javax.swing.JLabel();
        jTextFieldCategory = new javax.swing.JTextField();
        jPanelTitle = new javax.swing.JPanel();
        jLabelTitle = new javax.swing.JLabel();
        jScrollPanelTable = new javax.swing.JScrollPane();
        jTableBook = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setToolTipText("");
        setMinimumSize(new java.awt.Dimension(1170, 630));
        setName(""); // NOI18N
        setPreferredSize(new java.awt.Dimension(1170, 630));
        setRequestFocusEnabled(false);
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelButton.setBackground(new java.awt.Color(255, 255, 255));
        jPanelButton.setAutoscrolls(true);
        jPanelButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonInsert.setBackground(new java.awt.Color(51, 153, 0));
        jButtonInsert.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jButtonInsert.setForeground(new java.awt.Color(255, 255, 255));
        jButtonInsert.setText("Thêm");
        jButtonInsert.setAutoscrolls(true);
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
        jPanelSearch.add(jTextFieldSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 735, 50));

        jPanelDetail.add(jPanelSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 840, 50));

        jSeparatorTitle.setForeground(new java.awt.Color(255, 51, 51));
        jSeparatorTitle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 51, 51), 4, true));
        jPanelDetail.add(jSeparatorTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 400, 4));

        jPanelMainDetail.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMainDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelID.setBackground(new java.awt.Color(255, 255, 255));
        jPanelID.setAutoscrolls(true);
        jPanelID.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelID.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelID.setText("ID Sách");
        jPanelID.add(jLabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, 40));

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

        jPanelMainDetail.add(jPanelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, 60));

        jPanelName.setBackground(new java.awt.Color(255, 255, 255));
        jPanelName.setAutoscrolls(true);
        jPanelName.setPreferredSize(new java.awt.Dimension(300, 40));
        jPanelName.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelName.setBackground(new java.awt.Color(255, 255, 204));
        jLabelName.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelName.setText("Tiêu đề");
        jPanelName.add(jLabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 50));

        jScrollPaneTextFieldName.setBackground(new java.awt.Color(255, 255, 255));

        jTextFieldName.setColumns(20);
        jTextFieldName.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldName.setLineWrap(true);
        jTextFieldName.setRows(3);
        jTextFieldName.setText("Tiêu đề");
        jTextFieldName.setWrapStyleWord(true);
        jTextFieldName.setAlignmentX(1.0F);
        jTextFieldName.setAlignmentY(1.0F);
        jTextFieldName.setAutoscrolls(false);
        jTextFieldName.setBorder(null);
        jTextFieldName.setName(""); // NOI18N
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
        jScrollPaneTextFieldName.setViewportView(jTextFieldName);

        jPanelName.add(jScrollPaneTextFieldName, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 540, 90));

        jPanelMainDetail.add(jPanelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 690, 110));

        jPanelQuantity.setBackground(new java.awt.Color(255, 255, 255));
        jPanelQuantity.setAutoscrolls(true);
        jPanelQuantity.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelQuantity.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelQuantity.setText("Số lượng");
        jPanelQuantity.add(jLabelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 40));

        jTextFieldQuantity.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldQuantity.setText("Số lượng");
        jTextFieldQuantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldQuantity.setPreferredSize(new java.awt.Dimension(40, 22));
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
        jTextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldQuantityKeyPressed(evt);
            }
        });
        jPanelQuantity.add(jTextFieldQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, 40));

        jPanelMainDetail.add(jPanelQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 290, 60));

        jPanelPrice.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPrice.setAutoscrolls(true);
        jPanelPrice.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPrice.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPrice.setText("Giá tiền");
        jPanelPrice.add(jLabelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 40));

        jTextFieldPrice.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPrice.setText("Giá");
        jTextFieldPrice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPriceFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPriceFocusLost(evt);
            }
        });
        jTextFieldPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPriceMousePressed(evt);
            }
        });
        jTextFieldPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPriceKeyPressed(evt);
            }
        });
        jPanelPrice.add(jTextFieldPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, 40));

        jPanelMainDetail.add(jPanelPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 170, 290, 60));

        jPanelPublisher.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPublisher.setAutoscrolls(true);
        jPanelPublisher.setVerifyInputWhenFocusTarget(false);
        jPanelPublisher.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPublisher.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPublisher.setText("Nhà xuất bản");
        jPanelPublisher.add(jLabelPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 40));

        jComboBoxPublisher.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jComboBoxPublisher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Publisher" }));
        jComboBoxPublisher.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jComboBoxPublisher.setMinimumSize(new java.awt.Dimension(45, 22));
        jComboBoxPublisher.setPreferredSize(new java.awt.Dimension(45, 22));
        jPanelPublisher.add(jComboBoxPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 540, 40));

        jPanelMainDetail.add(jPanelPublisher, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 680, 60));

        jPanelAuthor.setBackground(new java.awt.Color(255, 255, 255));
        jPanelAuthor.setAutoscrolls(true);
        jPanelAuthor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldAuthor.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldAuthor.setText("Tác giả");
        jTextFieldAuthor.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldAuthor.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldAuthor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAuthorFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAuthorFocusLost(evt);
            }
        });
        jTextFieldAuthor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldAuthorMousePressed(evt);
            }
        });
        jPanelAuthor.add(jTextFieldAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 540, 40));

        jLabelAuthor.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelAuthor.setText("Tác giả");
        jPanelAuthor.add(jLabelAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 60, 40));

        jPanelMainDetail.add(jPanelAuthor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 680, 60));

        jPanelPublishYear.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPublishYear.setAutoscrolls(true);
        jPanelPublishYear.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPublishYear.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPublishYear.setText("Năm xuất bản");
        jPanelPublishYear.add(jLabelPublishYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 120, 40));

        jTextFieldPublishYear.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPublishYear.setText("Năm xuất bản");
        jTextFieldPublishYear.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldPublishYear.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldPublishYear.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPublishYearFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPublishYearFocusLost(evt);
            }
        });
        jTextFieldPublishYear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPublishYearMousePressed(evt);
            }
        });
        jTextFieldPublishYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPublishYearKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPublishYearKeyReleased(evt);
            }
        });
        jPanelPublishYear.add(jTextFieldPublishYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 150, 40));

        jPanelMainDetail.add(jPanelPublishYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 290, 60));

        jPanelPageNum.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPageNum.setAutoscrolls(true);
        jPanelPageNum.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPageNum.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelPageNum.setText("Số trang");
        jLabelPageNum.setToolTipText("");
        jPanelPageNum.add(jLabelPageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jTextFieldPageNum.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldPageNum.setText("Số trang");
        jTextFieldPageNum.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldPageNum.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldPageNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPageNumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPageNumFocusLost(evt);
            }
        });
        jTextFieldPageNum.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPageNumMousePressed(evt);
            }
        });
        jTextFieldPageNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldPageNumKeyPressed(evt);
            }
        });
        jPanelPageNum.add(jTextFieldPageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, 40));

        jPanelMainDetail.add(jPanelPageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 60, 290, 60));

        jPanelCategory.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCategory.setAutoscrolls(true);
        jPanelCategory.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelCategory.setFont(new java.awt.Font("Segoe UI", 3, 17)); // NOI18N
        jLabelCategory.setText("Thể loại");
        jPanelCategory.add(jLabelCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jTextFieldCategory.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jTextFieldCategory.setText("Thể loại");
        jTextFieldCategory.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(102, 102, 255)));
        jTextFieldCategory.setPreferredSize(new java.awt.Dimension(40, 22));
        jTextFieldCategory.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCategoryFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCategoryFocusLost(evt);
            }
        });
        jTextFieldCategory.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCategoryMousePressed(evt);
            }
        });
        jPanelCategory.add(jTextFieldCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 190, 40));

        jPanelMainDetail.add(jPanelCategory, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 290, 60));

        jPanelDetail.add(jPanelMainDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 280));

        add(jPanelDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1170, 360));

        jPanelTitle.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTitle.setPreferredSize(new java.awt.Dimension(1170, 50));
        jPanelTitle.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitle.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabelTitle.setForeground(new java.awt.Color(255, 51, 51));
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Quản lý danh sách sách");
        jLabelTitle.setRequestFocusEnabled(false);
        jPanelTitle.add(jLabelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 380, 40));

        add(jPanelTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 40));

        jScrollPanelTable.setViewportBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPanelTable.setAutoscrolls(true);
        jScrollPanelTable.setWheelScrollingEnabled(false);

        jTableBook.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableBook.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableBook.setMaximumSize(null);
        jTableBook.setMinimumSize(null);
        jTableBook.setPreferredSize(null);
        jTableBook.setRowHeight(40);
        jTableBook.setShowGrid(true);
        jTableBook.getTableHeader().setResizingAllowed(false);
        jTableBook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableBookMousePressed(evt);
            }
        });
        jTableBook.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableBookKeyReleased(evt);
            }
        });
        jScrollPanelTable.setViewportView(jTableBook);

        add(jScrollPanelTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 1120, 330));
    }// </editor-fold>//GEN-END:initComponents

    // Xử lý sự kiện khi released KeyBoard
    // Kiểm tra dòng hiện tại của Table và hiển thị thông tin của cột lên Panel Detail
    private void jTableBookKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableBookKeyReleased
        // TODO add your handling code here:
//        int selectedRow = jTableBook.getSelectedRow();
//        displayDetails(selectedRow);

        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN) {
            int selectedRow = jTableBook.getSelectedRow();
            displayDetails(selectedRow);
        }
    }//GEN-LAST:event_jTableBookKeyReleased

    // Xử lý sự kiện khi press Chuột
    // Hiển thị thông tin của cột đã được nhấn lên Panel Detail
    private void jTableBookMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBookMousePressed
        // TODO add your handling code here:
        int selectedRow = jTableBook.getSelectedRow();
        displayDetails(selectedRow);
    }//GEN-LAST:event_jTableBookMousePressed

    // Xử lý sự kiện khi nhấn vào Button Clear
    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        // TODO add your handling code here:
        myInitComponents();
    }//GEN-LAST:event_jButtonClearActionPerformed

    // Xử lý sự kiện khi nhấn vào Button Insert
    private void jButtonInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String category = jTextFieldCategory.getText();
        String author = jTextFieldAuthor.getText();
        String quantity = jTextFieldQuantity.getText();
        String pageNumber = jTextFieldPageNum.getText();
        String price = jTextFieldPrice.getText();
        String publisherName = jComboBoxPublisher.getSelectedItem().toString();
        String publishYear = jTextFieldPublishYear.getText();

        if (validateBookData(id, name, category, author, quantity, pageNumber, price, publisherName, publishYear, TypeFunctionEnum.Insert)) {
            int iQuantity = Integer.valueOf(quantity);
            int iPageNumber = Integer.valueOf(pageNumber);
            int iYear = Integer.valueOf(publishYear);
            double dPrice = Double.valueOf(price);

            Book book = new Book(id, name, author, category, iQuantity, iPageNumber, iYear, dPrice, publisherName);
            boolean insertCheck = bookDaoImp.insert(book);
            if (insertCheck) {
                JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_INSERT_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_INSERT_ERROR);
            }
        }

    }//GEN-LAST:event_jButtonInsertActionPerformed

    // Xử lý sự kiện nhấn phím vào Text Field Năm xuất bản
    private void jTextFieldPublishYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPublishYearKeyPressed
        // TODO add your handling code here:
        //Action when key press       
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            jTextFieldPublishYear.setEditable(true);
        } else {
            //Allow backspace and delete
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                jTextFieldPublishYear.setEditable(true);
            } else {
                jTextFieldPublishYear.setEditable(false);
            }
        }
    }//GEN-LAST:event_jTextFieldPublishYearKeyPressed

    // Xử lý sự kiện nhấn phím vào Text Field Số trang
    private void jTextFieldPageNumKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPageNumKeyPressed
        // TODO add your handling code here:
        //Action when key press
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            jTextFieldPageNum.setEditable(true);
        } else {
            //Allow backspace and delete
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                jTextFieldPageNum.setEditable(true);
            } else {
                jTextFieldPageNum.setEditable(false);
            }
        }
    }//GEN-LAST:event_jTextFieldPageNumKeyPressed

    // Xử lý sự kiện nhấn phím vào Text Field Số lượng
    private void jTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantityKeyPressed
        // TODO add your handling code here:
        //Action when key press
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            jTextFieldQuantity.setEditable(true);
        } else {
            //Allow backspace and delete
            if (evt.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode() == KeyEvent.VK_DELETE) {
                jTextFieldQuantity.setEditable(true);
            } else {
                jTextFieldQuantity.setEditable(false);
            }
        }
    }//GEN-LAST:event_jTextFieldQuantityKeyPressed

    // Xử lý sự kiện nhấn phím vào Text Field Giá tiền
    private void jTextFieldPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPriceKeyPressed
        // TODO add your handling code here:
        //Action when key press
        String price = jTextFieldPrice.getText();
        if (evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9') {
            jTextFieldPrice.setEditable(true);
        } else {
            //Allow backspace and delete
            switch (evt.getExtendedKeyCode()) {
                case KeyEvent.VK_BACK_SPACE, KeyEvent.VK_DELETE ->
                    jTextFieldPrice.setEditable(true);
                case '.', ',' -> {
                    if (price.contains(GeneralStringConstant.GENERAL_DOT) || price.contains(GeneralStringConstant.GENERAL_COMMA)) {
                        jTextFieldPrice.setEditable(false);
                    } else {
                        jTextFieldPrice.setEditable(true);
                    }
                }
                default ->
                    jTextFieldPrice.setEditable(false);
            }
        }
    }//GEN-LAST:event_jTextFieldPriceKeyPressed

    // Xử lý sự kiện khi press Chuột Năm xuất bản
    private void jTextFieldPublishYearMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPublishYearMousePressed
        // TODO add your handling code here:
        String year = jTextFieldPublishYear.getText();
        if (!SupportFunction.checkNumber(year)) {
            jTextFieldPublishYear.setText("");
        }
    }//GEN-LAST:event_jTextFieldPublishYearMousePressed

    // Xử lý sự kiện khi press Chuột Số trang
    private void jTextFieldPageNumMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPageNumMousePressed
        // TODO add your handling code here:
        String pageNum = jTextFieldPageNum.getText();
        if (!SupportFunction.checkNumber(pageNum)) {
            jTextFieldPageNum.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPageNumMousePressed

    // Xử lý sự kiện khi press Chuột Số lượng
    private void jTextFieldQuantityMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldQuantityMousePressed
        // TODO add your handling code here:
        String quantity = jTextFieldQuantity.getText();
        if (!SupportFunction.checkNumber(quantity)) {
            jTextFieldQuantity.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldQuantityMousePressed

    // Xử lý sự kiện khi press Chuột Giá tiền
    private void jTextFieldPriceMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPriceMousePressed
        // TODO add your handling code here:
        String price = jTextFieldPrice.getText();
        if (!SupportFunction.checkPriceNumber(price)) {
            jTextFieldPrice.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPriceMousePressed

    // Xử lý sự kiện khi released KeyBoard Năm xuất bản
    private void jTextFieldPublishYearKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPublishYearKeyReleased
        // TODO add your handling code here:
        String sYear = jTextFieldPublishYear.getText();
        int year = Integer.valueOf(sYear);
        if (year > java.time.LocalDate.now().getYear()) {
            JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_PUBLISH_YEAR_ERROR);
            jTextFieldPublishYear.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPublishYearKeyReleased

    // Xử lý sự kiện khi nhấn vào Button Update
    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String category = jTextFieldCategory.getText();
        String author = jTextFieldAuthor.getText();
        String quantity = jTextFieldQuantity.getText();
        String pageNumber = jTextFieldPageNum.getText();
        String price = jTextFieldPrice.getText();
        String publisherName = jComboBoxPublisher.getSelectedItem().toString();
        String publishYear = jTextFieldPublishYear.getText();
        if (validateBookData(id, name, category, author, quantity, pageNumber, price, publisherName, publishYear, TypeFunctionEnum.Update)) {
            int iQuantity = Integer.valueOf(quantity);
            int iPageNumber = Integer.valueOf(pageNumber);
            int iYear = Integer.valueOf(publishYear);
            double dPrice = Double.valueOf(price);

            Book book = new Book(id, name, author, category, iQuantity, iPageNumber, iYear, dPrice, publisherName);
            boolean updateCheck = bookDaoImp.update(book);
            if (updateCheck) {
                JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_UPDATE_SUCCESS);
                myInitComponents();
            } else {
                JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_UPDATE_ERROR);
            }
        }
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    // Xử lý sự kiện khi nhấn vào Button Delete
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String category = jTextFieldCategory.getText();
        String author = jTextFieldAuthor.getText();
        String quantity = jTextFieldQuantity.getText();
        String pageNumber = jTextFieldPageNum.getText();
        String price = jTextFieldPrice.getText();
        String publisherName = jComboBoxPublisher.getSelectedItem().toString();
        String publishYear = jTextFieldPublishYear.getText();
        if (validateBookData(id, name, category, author, quantity, pageNumber, price, publisherName, publishYear, TypeFunctionEnum.Delete)) {
            int iQuantity = Integer.valueOf(quantity);
            int iPageNumber = Integer.valueOf(pageNumber);
            int iYear = Integer.valueOf(publishYear);
            double dPrice = Double.valueOf(price);

            Book book = new Book(id, name, author, category, iQuantity, iPageNumber, iYear, dPrice, publisherName);

            int answer = JOptionPane.showConfirmDialog(null,
                    BookStringConstant.BOOK_DELETE_TITLE, GeneralStringConstant.GENERAL_DELETE,
                    JOptionPane.YES_NO_OPTION);

            if (answer == JOptionPane.NO_OPTION) {
                // do something
            } else if (answer == JOptionPane.YES_OPTION) {
                boolean deleteCheck = bookDaoImp.moveToBin(book.getBookID());
                if (deleteCheck) {
                    JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_DELETE_SUCCESS);
                    myInitComponents();
                } else {
                    JOptionPane.showMessageDialog(null, BookStringConstant.BOOK_DELETE_ERROR);
                }
            }
            // do something else

        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    // Xử lý sự kiện khi released KeyBoard
    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        setTableBySearch(text);

    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    // Xử lý sự kiện khi press Chuột Tìm kiếm
    private void jTextFieldSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSearchMousePressed
        // TODO add your handling code here:
        String search = jTextFieldSearch.getText();
        if (search.contains(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchMousePressed

    // Xử lý sự kiện khi press Chuột ID Sách
    private void jTextFieldIDMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldIDMousePressed
        // TODO add your handling code here:
        String id = jTextFieldID.getText();
        if (id.contains(BookStringConstant.BOOK_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDMousePressed

    // Xử lý sự kiện khi press Chuột Thể loại
    private void jTextFieldCategoryMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCategoryMousePressed
        // TODO add your handling code here:
        String category = jTextFieldCategory.getText();
        if (category.contains(BookStringConstant.BOOK_CATEGORY)) {
            jTextFieldCategory.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldCategoryMousePressed

    // Xử lý sự kiện khi press Chuột Tên sách
    private void jTextFieldNameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNameMousePressed
        // TODO add your handling code here:
        String name = jTextFieldName.getText();
        if (name.contains(BookStringConstant.BOOK_TITLE)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldNameMousePressed

    // Xử lý sự kiện khi press Chuột Tác giả
    private void jTextFieldAuthorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldAuthorMousePressed
        // TODO add your handling code here:
        String author = jTextFieldAuthor.getText();
        if (author.contains(BookStringConstant.BOOK_AUTHOR)) {
            jTextFieldAuthor.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldAuthorMousePressed

    // Xử lý sự kiện Focus Gain của Text Field ID Sách
    private void jTextFieldIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusGained
        // TODO add your handling code here:
        String text = jTextFieldID.getText();
        if (text.equals(BookStringConstant.BOOK_ID)) {
            jTextFieldID.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldIDFocusGained

    // Xử lý sự kiện Focus Lost của Text Field ID
    private void jTextFieldIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFocusLost
        // TODO add your handling code here:
        String text = jTextFieldID.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldID.setText(BookStringConstant.BOOK_ID);
        }
    }//GEN-LAST:event_jTextFieldIDFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Năm xuất bản
    private void jTextFieldPublishYearFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPublishYearFocusGained
        // TODO add your handling code here:
        String text = jTextFieldPublishYear.getText();
        if (text.equals(BookStringConstant.BOOK_PUBLISH_YEAR)) {
            jTextFieldPublishYear.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPublishYearFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Năm xuất bản
    private void jTextFieldPublishYearFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPublishYearFocusLost
        // TODO add your handling code here:
        String text = jTextFieldPublishYear.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldPublishYear.setText(BookStringConstant.BOOK_PUBLISH_YEAR);
        }
    }//GEN-LAST:event_jTextFieldPublishYearFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Thể loại
    private void jTextFieldCategoryFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCategoryFocusGained
        // TODO add your handling code here:
        String text = jTextFieldCategory.getText();
        if (text.equals(BookStringConstant.BOOK_CATEGORY)) {
            jTextFieldCategory.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldCategoryFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Thể loại
    private void jTextFieldCategoryFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCategoryFocusLost
        // TODO add your handling code here:
        String text = jTextFieldCategory.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldCategory.setText(BookStringConstant.BOOK_CATEGORY);
        }
    }//GEN-LAST:event_jTextFieldCategoryFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Tác giả
    private void jTextFieldAuthorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAuthorFocusGained
        // TODO add your handling code here:
        String text = jTextFieldAuthor.getText();
        if (text.equals(BookStringConstant.BOOK_AUTHOR)) {
            jTextFieldAuthor.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldAuthorFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Tác giả
    private void jTextFieldAuthorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAuthorFocusLost
        // TODO add your handling code here:
        String text = jTextFieldAuthor.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldAuthor.setText(BookStringConstant.BOOK_AUTHOR);
        }
    }//GEN-LAST:event_jTextFieldAuthorFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Số trang
    private void jTextFieldPageNumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPageNumFocusGained
        // TODO add your handling code here:
        String text = jTextFieldPageNum.getText();
        if (text.equals(BookStringConstant.BOOK_PAGE_NUMBER)) {
            jTextFieldPageNum.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPageNumFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Số trang
    private void jTextFieldPageNumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPageNumFocusLost
        // TODO add your handling code here:
        String text = jTextFieldPageNum.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldPageNum.setText(BookStringConstant.BOOK_PAGE_NUMBER);
        }
    }//GEN-LAST:event_jTextFieldPageNumFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Số lượng
    private void jTextFieldQuantityFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantityFocusGained
        // TODO add your handling code here:
        String text = jTextFieldQuantity.getText();
        if (text.equals(BookStringConstant.BOOK_QUANTITY)) {
            jTextFieldQuantity.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldQuantityFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Số lượng
    private void jTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantityFocusLost
        // TODO add your handling code here:
        String text = jTextFieldQuantity.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldQuantity.setText(BookStringConstant.BOOK_QUANTITY);
        }
    }//GEN-LAST:event_jTextFieldQuantityFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Giá tiền
    private void jTextFieldPriceFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPriceFocusGained
        // TODO add your handling code here:
        String text = jTextFieldPrice.getText();
        if (text.equals(BookStringConstant.BOOK_PRICE)) {
            jTextFieldPrice.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldPriceFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Giá tiền
    private void jTextFieldPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPriceFocusLost
        // TODO add your handling code here:
        String text = jTextFieldPrice.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldPrice.setText(BookStringConstant.BOOK_PRICE);
        }
    }//GEN-LAST:event_jTextFieldPriceFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Tên sách
    private void jTextFieldNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusGained
        // TODO add your handling code here:
        String text = jTextFieldName.getText();
        if (text.equals(BookStringConstant.BOOK_TITLE)) {
            jTextFieldName.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldNameFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Tên sách
    private void jTextFieldNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNameFocusLost
        // TODO add your handling code here:
        String text = jTextFieldName.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldName.setText(BookStringConstant.BOOK_TITLE);
        }
    }//GEN-LAST:event_jTextFieldNameFocusLost

    // Xử lý sự kiện Focus Gain của Text Field Tìm kiếm
    private void jTextFieldSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusGained
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        if (text.equals(GeneralStringConstant.GENERAL_SEARCH)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_EMPTY);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusGained

    // Xử lý sự kiện Focus Lost của Text Field Tìm kiếm
    private void jTextFieldSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSearchFocusLost
        // TODO add your handling code here:
        String text = jTextFieldSearch.getText();
        if (text.equals(GeneralStringConstant.GENERAL_EMPTY)) {
            jTextFieldSearch.setText(GeneralStringConstant.GENERAL_SEARCH);
        }
    }//GEN-LAST:event_jTextFieldSearchFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonInsert;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JComboBox<String> jComboBoxPublisher;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelCategory;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelPageNum;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelPublishYear;
    private javax.swing.JLabel jLabelPublisher;
    private javax.swing.JLabel jLabelQuantity;
    private javax.swing.JLabel jLabelSearch;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JPanel jPanelAuthor;
    private javax.swing.JPanel jPanelButton;
    private javax.swing.JPanel jPanelCategory;
    private javax.swing.JPanel jPanelDetail;
    private javax.swing.JPanel jPanelID;
    private javax.swing.JPanel jPanelMainDetail;
    private javax.swing.JPanel jPanelName;
    private javax.swing.JPanel jPanelPageNum;
    private javax.swing.JPanel jPanelPrice;
    private javax.swing.JPanel jPanelPublishYear;
    private javax.swing.JPanel jPanelPublisher;
    private javax.swing.JPanel jPanelQuantity;
    private javax.swing.JPanel jPanelSearch;
    private javax.swing.JPanel jPanelTitle;
    private javax.swing.JScrollPane jScrollPaneTextFieldName;
    private javax.swing.JScrollPane jScrollPanelTable;
    private javax.swing.JSeparator jSeparatorTitle;
    private javax.swing.JTable jTableBook;
    private javax.swing.JTextField jTextFieldAuthor;
    private javax.swing.JTextField jTextFieldCategory;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextArea jTextFieldName;
    private javax.swing.JTextField jTextFieldPageNum;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldPublishYear;
    private javax.swing.JTextField jTextFieldQuantity;
    private javax.swing.JTextField jTextFieldSearch;
    // End of variables declaration//GEN-END:variables

}
