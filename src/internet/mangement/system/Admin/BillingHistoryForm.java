/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package internet.mangement.system.Admin;

import DAO.BillingDAO;
import Model.Billing;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatDarculaLaf;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 * Form for managing billing history
 * @author ADMIN
 */
public class BillingHistoryForm extends javax.swing.JFrame {

    private List<Billing> billingList;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM/yyyy");
    private final NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale.Builder().setLanguage("vi").setRegion("VN").build());
    private final String[] statuses = {"paid", "unpaid", "late"};
    private final String[] statusesWithAll = {"Tất cả", "paid", "unpaid", "late"};
    private final String[] months = {"Tất cả", "01/2023", "02/2023", "03/2023", "04/2023", "05/2023", "06/2023",
                                    "07/2023", "08/2023", "09/2023", "10/2023", "11/2023", "12/2023",
                                    "01/2024", "02/2024", "03/2024", "04/2024", "05/2024", "06/2024"};

    // FlatLaf themes
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu themeMenu;
    private javax.swing.JMenuItem lightThemeItem;
    private javax.swing.JMenuItem darkThemeItem;
    private javax.swing.JMenuItem intellijThemeItem;
    private javax.swing.JMenuItem darculaThemeItem;

    /**
     * Creates new form BillingHistoryForm
     */
    public BillingHistoryForm() {
        // Set up FlatLaf Look and Feel
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
        }

        initComponents();
        setTitle("Quản lý lịch sử gói cước");
        setupComponents();
        setupThemeMenu();
        loadBillingData();

        // Add row selection listener to populate detail fields
        jTable2.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && jTable2.getSelectedRow() != -1) {
                displaySelectedBilling();
            }
        });
    }

    /**
     * Set up the theme menu
     */
    private void setupThemeMenu() {
        // Create menu bar if it doesn't exist
        if (menuBar == null) {
            menuBar = new javax.swing.JMenuBar();
            setJMenuBar(menuBar);
        }

        // Create theme menu
        themeMenu = new javax.swing.JMenu("Giao diện");
        menuBar.add(themeMenu);

        // Create theme menu items
        lightThemeItem = new javax.swing.JMenuItem("Light");
        darkThemeItem = new javax.swing.JMenuItem("Dark");
        intellijThemeItem = new javax.swing.JMenuItem("IntelliJ");
        darculaThemeItem = new javax.swing.JMenuItem("Darcula");

        // Add theme menu items to theme menu
        themeMenu.add(lightThemeItem);
        themeMenu.add(darkThemeItem);
        themeMenu.add(intellijThemeItem);
        themeMenu.add(darculaThemeItem);

        // Add action listeners to theme menu items
        lightThemeItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatLightLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatLightLaf");
            }
        });

        darkThemeItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatDarkLaf");
            }
        });

        intellijThemeItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatIntelliJLaf");
            }
        });

        darculaThemeItem.addActionListener(e -> {
            try {
                UIManager.setLookAndFeel(new FlatDarculaLaf());
                javax.swing.SwingUtilities.updateComponentTreeUI(this);
            } catch (Exception ex) {
                System.err.println("Failed to initialize FlatDarculaLaf");
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý lịch sử gói cước", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Kỳ thanh toán", "Số tiền", "Ngày thanh toán", "Trạng thái"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel1.setText("Nhập mã hợp đồng:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel2.setText("Tìm kiếm theo:");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Kỳ thanh toán:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Trạng thái:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/undo.png"))); // NOI18N
        jButton1.setText("Làm mới");

        jButton2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/search.png"))); // NOI18N
        jButton2.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Mã hóa đơn:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel6.setText("Mã hợp đồng:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel7.setText("Kỳ thanh toán:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel8.setText("Số tiền:");

        jTextField5.setToolTipText("");

        jLabel9.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel9.setText("Trạng thái");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel10.setText("Ngày thanh toán:");
        jLabel10.setToolTipText("");

        jButton3.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/update.png"))); // NOI18N
        jButton3.setText("Cập nhật trạng thái");

        jButton4.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/more.png"))); // NOI18N
        jButton4.setText("Thêm");
        jButton4.setToolTipText("");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/edit.png"))); // NOI18N
        jButton5.setText("Sửa");

        jButton6.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/delete.png"))); // NOI18N
        jButton6.setText("Xóa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6)
                            .addComponent(jTextField5)
                            .addComponent(jTextField4)
                            .addComponent(jTextField3)
                            .addComponent(jTextField2)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)))
                .addGap(41, 41, 41))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jButton7.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/return.png"))); // NOI18N
        jButton7.setText("Quay trở về Dashboard");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7)
                        .addGap(75, 75, 75))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Set up the form components
     */
    private void setupComponents() {
        // Set up the combo boxes
        jComboBox1.setModel(new DefaultComboBoxModel<>(months));
        jComboBox2.setModel(new DefaultComboBoxModel<>(statusesWithAll));
        jComboBox3.setModel(new DefaultComboBoxModel<>(statuses));

        // Set up the table
        setupTable();

        // Add action listeners to buttons
        jButton1.addActionListener(e -> clearSearch());
        jButton2.addActionListener(e -> searchBillings());
        jButton3.addActionListener(e -> updateBillingStatus());

        // Create a new "Làm mới" button to replace the Add, Edit, Delete buttons
        jButton4.setText("Làm mới");
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/undo.png")));
        jButton4.addActionListener(e -> {
            // Refresh data
            loadBillingData();
            // Clear detail fields
            clearDetailFields();
        });

        // Hide the Edit button but keep Delete button
        jButton5.setVisible(false);

        // Configure Delete button
        jButton6.setVisible(true);
        jButton6.addActionListener(e -> deleteBilling());

        jButton7.addActionListener(e -> this.dispose());
    }

    /**
     * Set up the table columns and properties
     */
    private void setupTable() {
        // Set column names and properties
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setColumnIdentifiers(new String[] {
            "Mã hóa đơn", "Mã hợp đồng", "Kỳ thanh toán", "Số tiền (VND)", "Trạng thái", "Ngày thanh toán"
        });

        // Make table cells non-editable
        jTable2.setDefaultEditor(Object.class, null);

        // Set column widths
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        jTable2.getColumnModel().getColumn(1).setPreferredWidth(80);  // Contract ID
        jTable2.getColumnModel().getColumn(2).setPreferredWidth(100); // Billing Period
        jTable2.getColumnModel().getColumn(3).setPreferredWidth(150); // Amount
        jTable2.getColumnModel().getColumn(4).setPreferredWidth(100); // Status
        jTable2.getColumnModel().getColumn(5).setPreferredWidth(150); // Payment Date
    }

    /**
     * Load billing data from the database
     */
    private void loadBillingData() {
        billingList = BillingDAO.getAllBillings();
        displayBillingsInTable(billingList);
    }

    /**
     * Display billings in the table
     * @param billings List of billings to display
     */
    private void displayBillingsInTable(List<Billing> billings) {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0); // Clear existing rows

        for (Billing billing : billings) {
            Vector<Object> row = new Vector<>();
            row.add(billing.getBillingId());
            row.add(billing.getContractId());

            if (billing.getBillingPeriod() != null) {
                row.add(billing.getBillingPeriod().format(dateFormatter));
            } else {
                row.add("");
            }

            row.add(currencyFormatter.format(billing.getAmount()));
            row.add(billing.getStatus());

            if (billing.getPaymentDate() != null) {
                row.add(billing.getPaymentDate().format(dateTimeFormatter));
            } else {
                row.add("");
            }

            model.addRow(row);
        }
    }

    /**
     * Display the selected billing's details in the form fields
     */
    private void displaySelectedBilling() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow != -1) {
            int billingId = (int) jTable2.getValueAt(selectedRow, 0);
            Billing billing = BillingDAO.getBillingById(billingId);

            if (billing != null) {
                // Update the detail fields with billing information
                jTextField2.setText(String.valueOf(billing.getBillingId()));
                jTextField2.setEditable(false);

                jTextField3.setText(String.valueOf(billing.getContractId()));
                jTextField3.setEditable(false);

                // Display billing period
                if (billing.getBillingPeriod() != null) {
                    jTextField4.setText(billing.getBillingPeriod().format(dateFormatter));
                } else {
                    jTextField4.setText("");
                }
                jTextField4.setEditable(false);

                jTextField5.setText(currencyFormatter.format(billing.getAmount()));
                jTextField5.setEditable(false);

                // Set status in combo box
                for (int i = 0; i < statuses.length; i++) {
                    if (statuses[i].equals(billing.getStatus())) {
                        jComboBox3.setSelectedIndex(i);
                        break;
                    }
                }

                // Display payment date
                if (billing.getPaymentDate() != null) {
                    jTextField6.setText(billing.getPaymentDate().format(dateTimeFormatter));
                } else {
                    jTextField6.setText("");
                }
                jTextField6.setEditable(false);
            }
        }
    }

    /**
     * Search for billings based on criteria
     */
    private void searchBillings() {
        String contractIdStr = jTextField1.getText().trim();
        String selectedMonth = (String) jComboBox1.getSelectedItem();
        String status = (String) jComboBox2.getSelectedItem();

        List<Billing> searchResults;
        if (!contractIdStr.isEmpty()) {
            try {
                int contractId = Integer.parseInt(contractIdStr);
                searchResults = BillingDAO.getBillingsByContractId(contractId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Mã hợp đồng phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            // Search by month and status
            searchResults = new ArrayList<>();
            for (Billing billing : billingList) {
                boolean matchStatus = status.equals("Tất cả") || billing.getStatus().equals(status);
                boolean matchMonth = true; // Default to true for "Tất cả"

                // If a specific month is selected, check if the billing period matches
                if (!selectedMonth.equals("Tất cả") && billing.getBillingPeriod() != null) {
                    String billingMonth = billing.getBillingPeriod().format(monthFormatter);
                    matchMonth = billingMonth.equals(selectedMonth);
                }

                if (matchStatus && matchMonth) {
                    searchResults.add(billing);
                }
            }
        }

        displayBillingsInTable(searchResults);
    }

    /**
     * Clear search fields and refresh the table
     */
    private void clearSearch() {
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0); // Reset month filter to "Tất cả"
        jComboBox2.setSelectedIndex(0); // Reset status filter to first option
        loadBillingData();
    }

    /**
     * Add a new billing
     */
    private void addBilling() {
        // Get values from form fields
        String contractIdStr = jTextField3.getText().trim();
        String billingPeriodStr = jTextField4.getText().trim();
        String amountStr = jTextField5.getText().trim();
        String status = (String) jComboBox3.getSelectedItem();
        String paymentDateStr = jTextField6.getText().trim();

        // Validate input
        if (contractIdStr.isEmpty() || billingPeriodStr.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin hóa đơn", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int contractId = Integer.parseInt(contractIdStr);

            // Parse amount from currency format
            String cleanedAmount = amountStr.replaceAll("[^\\d]", "");
            double amount = Double.parseDouble(cleanedAmount);

            // Create new billing
            Billing newBilling = new Billing();
            newBilling.setContractId(contractId);
            newBilling.setAmount(amount);
            newBilling.setStatus(status);

            // Parse billing period
            try {
                LocalDate billingPeriod = LocalDate.parse(billingPeriodStr, dateFormatter);
                newBilling.setBillingPeriod(billingPeriod);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Kỳ thanh toán không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse payment date if provided
            if (!paymentDateStr.isEmpty()) {
                try {
                    LocalDateTime paymentDate = LocalDateTime.parse(paymentDateStr, dateTimeFormatter);
                    newBilling.setPaymentDate(paymentDate);
                } catch (DateTimeParseException e) {
                    try {
                        // Try parsing as date only and add default time
                        LocalDate paymentDate = LocalDate.parse(paymentDateStr, dateFormatter);
                        newBilling.setPaymentDate(paymentDate.atTime(0, 0, 0));
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Ngày thanh toán không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            boolean success = BillingDAO.addBilling(newBilling);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearDetailFields();
                loadBillingData();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã hợp đồng và số tiền phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Edit the selected billing
     */
    private void editBilling() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để sửa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get values from form fields
        String billingIdStr = jTextField2.getText().trim();
        String contractIdStr = jTextField3.getText().trim();
        String billingPeriodStr = jTextField4.getText().trim();
        String amountStr = jTextField5.getText().trim();
        String status = (String) jComboBox3.getSelectedItem();
        String paymentDateStr = jTextField6.getText().trim();

        // Validate input
        if (billingIdStr.isEmpty() || contractIdStr.isEmpty() || billingPeriodStr.isEmpty() || amountStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin hóa đơn", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int billingId = Integer.parseInt(billingIdStr);
            int contractId = Integer.parseInt(contractIdStr);

            // Parse amount from currency format
            String cleanedAmount = amountStr.replaceAll("[^\\d]", "");
            double amount = Double.parseDouble(cleanedAmount);

            // Get the existing billing
            Billing billing = BillingDAO.getBillingById(billingId);
            if (billing == null) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy hóa đơn", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update billing
            billing.setContractId(contractId);
            billing.setAmount(amount);
            billing.setStatus(status);

            // Parse billing period
            try {
                LocalDate billingPeriod = LocalDate.parse(billingPeriodStr, dateFormatter);
                billing.setBillingPeriod(billingPeriod);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(this, "Kỳ thanh toán không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Parse payment date if provided
            if (!paymentDateStr.isEmpty()) {
                try {
                    LocalDateTime paymentDate = LocalDateTime.parse(paymentDateStr, dateTimeFormatter);
                    billing.setPaymentDate(paymentDate);
                } catch (DateTimeParseException e) {
                    try {
                        // Try parsing as date only and add default time
                        LocalDate paymentDate = LocalDate.parse(paymentDateStr, dateFormatter);
                        billing.setPaymentDate(paymentDate.atTime(0, 0, 0));
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(this, "Ngày thanh toán không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } else {
                billing.setPaymentDate(null);
            }

            boolean success = BillingDAO.updateBilling(billing);
            if (success) {
                JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadBillingData();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Mã hóa đơn, mã hợp đồng và số tiền phải là số", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Delete the selected billing
     */
    private void deleteBilling() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int billingId = (int) jTable2.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn xóa hóa đơn này?",
                "Xác nhận xóa",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean success = BillingDAO.deleteBilling(billingId);
            if (success) {
                JOptionPane.showMessageDialog(this, "Xóa hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearDetailFields();
                loadBillingData();
            }
        }
    }

    /**
     * Update the status of the selected billing
     */
    private void updateBillingStatus() {
        int selectedRow = jTable2.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một hóa đơn để cập nhật trạng thái", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int billingId = (int) jTable2.getValueAt(selectedRow, 0);
        String status = (String) jComboBox3.getSelectedItem();

        boolean success = BillingDAO.updateBillingStatus(billingId, status);
        if (success) {
            JOptionPane.showMessageDialog(this, "Cập nhật trạng thái hóa đơn thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            loadBillingData();
        }
    }

    /**
     * Clear the detail fields
     */
    private void clearDetailFields() {
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox3.setSelectedIndex(0);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // No action needed
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // No action needed
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadBillingData();
        clearDetailFields();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the FlatLaf look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            // Try to use FlatLaf if available
            try {
                Class.forName("com.formdev.flatlaf.FlatLightLaf");
                UIManager.setLookAndFeel(new FlatLightLaf());
            } catch (ClassNotFoundException ex) {
                // FlatLaf not available, use Nimbus instead
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillingHistoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillingHistoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillingHistoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillingHistoryForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillingHistoryForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
