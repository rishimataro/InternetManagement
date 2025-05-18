/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package internet.mangement.system.Admin;
import DAO.ContractDAO;
import DAO.PlanDAO;
import DAO.SubscriberDAO;
import Model.Contract;
import Model.Plan;
import Model.Subscriber;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

/**
 *
 * @author hoang
 */
public class ContractManagement extends javax.swing.JFrame {

    /**
     * Creates new form ContractManagement
     */
    public ContractManagement() {
        initComponents();
        InitPlanComboBox();
        try{
            ds = ContractDAO.getAll();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
        setupTableSelectionListener();
        table_contract.getSelectionModel().addListSelectionListener(e -> {
            if(e.getValueIsAdjusting()){
                int selectedRow = table_contract.getSelectedRow();
                if(selectedRow < 0){
                    return;
                }
                int contract_id = (int) table_contract.getValueAt(selectedRow, 0);
//                Contract contract = ds.stream().findAny().filter(c -> c.getContract_id() == contract_id).orElse(null);
                Contract contract = ds.stream().filter(c -> c.getContract_id() == contract_id).findFirst().orElse(null);
                if(contract == null){
                    return;
                }
                //set TxtField Data
                txt_detail_subName.setText(sub_map.get(contract.getSub_id()).getFullName());
                txt_detail_subPhone.setText(sub_map.get(contract.getSub_id()).getPhone());
                txt_detail_signDate.setText(contract.getDate_sign().toLocalDate().toString());
                txt_detail_expireDate.setText(contract.getDate_expiration().toLocalDate().toString());
                txt_detail_conAddr.setText(contract.getAddress());
                txt_detail_conPlan.setText(Plan_map.get(contract.getPackage_id()).getName());
                txt_detail_subAddr.setText(sub_map.get(contract.getSub_id()).getAddress());

            };
        });
    }
    private Double parseDouble(String s) {
        try {
            return (s == null || s.trim().isEmpty()) ? null : Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Integer parseInt(String s) {
        try {
            return (s == null || s.trim().isEmpty()) ? null : Integer.parseInt(s.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    private List<Contract> ds = new ArrayList<>();
    private Map<Integer, Subscriber> sub_map = new TreeMap<>();
    private void setupTableSelectionListener() {
        DefaultTableModel model = (DefaultTableModel) table_contract.getModel();
        model.setRowCount(0); // Xoá dữ liệu cũ nếu có
        try{
//            List<Contract> ds = ContractDAO.getAll();
//            List<Contract> ds = ContractDAO.searchContracts(txt_sub_name.getText(), null, null, null, null, null);
            List<Subscriber> subscribers = SubscriberDAO.getAll();

            for(Subscriber sub : subscribers){
                sub_map.put(sub.getSubscriber_id(), sub);
            }
            for (Contract pl : ds){
                Object[] row = {
                        pl.getContract_id(),
                        sub_map.get(pl.getSub_id()).getPhone(),
                        sub_map.get(pl.getSub_id()).getFullName(),
                        Plan_map.get(pl.getPackage_id()).getName(),
                        pl.getDate_sign().toLocalDate().toString(),
                        pl.getDate_expiration().toLocalDate().toString(),
                        pl.getAddress(),

                };
                model.addRow(row);
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }

    }
    private Map<Integer, Plan> Plan_map = new TreeMap<>();
    private void InitPlanComboBox(){
//        Plan_map = new HashMap<>();
//        List<Plan> plans = new ArrayList<>();
//        try{
//            plans = PlanDAO.getAll();
//            for(Plan plan : plans){
//                Plan_map.put(plan.getName(), plan);
//                combo_plan.addItem(plan.getName());
//            }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        DefaultComboBoxModel<Plan> model = new DefaultComboBoxModel<>();
        try{
            for(Plan plan : PlanDAO.getAll()){
                Plan_map.put(plan.getPlan_id(), plan);
                model.addElement(plan);
            }
            combo_plan.setModel(model);
        }catch(Exception e){
            e.printStackTrace();
        }
        combo_plan.setEditable(true);
        AutoCompleteDecorator.decorate(combo_plan);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        right_panel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_sub_name = new javax.swing.JTextField();
        btn_SubName_search = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        combo_plan = new javax.swing.JComboBox<>();
        checkBox_plan_filter = new javax.swing.JCheckBox();
        checkBox_signDate = new javax.swing.JCheckBox();
        datePicker_signDateTo = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        datePicker_signDateFrom = new org.jdesktop.swingx.JXDatePicker();
        checkBox_expireDate = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        datePicker_expireDateFrom = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        datePicker_expireDateTo = new org.jdesktop.swingx.JXDatePicker();
        btn_filter = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_detail_subName = new javax.swing.JTextField();
        txt_detail_subPhone = new javax.swing.JTextField();
        txt_detail_signDate = new javax.swing.JTextField();
        txt_detail_expireDate = new javax.swing.JTextField();
        txt_detail_conAddr = new javax.swing.JTextField();
        txt_detail_subAddr = new javax.swing.JTextField();
        txt_detail_conPlan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        left_panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_contract = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 768));

        right_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tìm kiếm"));

        jLabel1.setText("Tìm theo tên khách hàng:");

        btn_SubName_search.setText("Tìm kiếm");
        btn_SubName_search.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_SubName_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SubName_searchActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Lọc"));

        jLabel2.setText("Chọn gói cước:");

        checkBox_plan_filter.setText("Lọc theo gói cước");

        checkBox_signDate.setText("Lọc theo ngày ký");
        checkBox_signDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_signDateActionPerformed(evt);
            }
        });

        jLabel3.setText("Từ ngày");

        jLabel4.setText("Tới ngày");

        checkBox_expireDate.setText("Lọc theo ngày hết hạn");
        checkBox_expireDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBox_expireDateActionPerformed(evt);
            }
        });

        jLabel5.setText("Từ ngày");

        jLabel6.setText("Tới ngày");

        btn_filter.setText("Lọc");
        btn_filter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_filterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(combo_plan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBox_plan_filter)
                            .addComponent(checkBox_signDate)
                            .addComponent(checkBox_expireDate))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePicker_signDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePicker_signDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datePicker_expireDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(datePicker_expireDateTo, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                                    .addComponent(btn_filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBox_plan_filter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(combo_plan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkBox_signDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePicker_signDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(datePicker_signDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkBox_expireDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datePicker_expireDateTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(datePicker_expireDateFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_filter)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Chi tiết"));

        jLabel7.setText("Tên khách hàng:");

        jLabel8.setText("Số điện thoại:");

        jLabel9.setText("Ngày ký:");

        jLabel10.setText("Ngày hết hạn:");

        jLabel11.setText("Địa chỉ lắp đặt:");

        jLabel12.setText("Địa chỉ khách hàng");

        txt_detail_subName.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_detail_subName.setRequestFocusEnabled(false);

        txt_detail_subPhone.setRequestFocusEnabled(false);

        txt_detail_signDate.setRequestFocusEnabled(false);

        txt_detail_expireDate.setRequestFocusEnabled(false);

        txt_detail_conAddr.setRequestFocusEnabled(false);

        txt_detail_subAddr.setRequestFocusEnabled(false);

        txt_detail_conPlan.setRequestFocusEnabled(false);

        jLabel13.setText("Gói cước đăng ký:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(txt_detail_signDate)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_detail_expireDate)
                                    .addGap(1, 1, 1))
                                .addComponent(txt_detail_subName, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_detail_subPhone, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_detail_subAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_detail_conAddr, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_detail_conPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_detail_subName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_detail_subPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_detail_conPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(txt_detail_signDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_detail_expireDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txt_detail_conAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txt_detail_subAddr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout right_panelLayout = new javax.swing.GroupLayout(right_panel);
        right_panel.setLayout(right_panelLayout);
        right_panelLayout.setHorizontalGroup(
            right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(right_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_sub_name)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_SubName_search))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, right_panelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        right_panelLayout.setVerticalGroup(
            right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(right_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(right_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_sub_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_SubName_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(right_panel, java.awt.BorderLayout.LINE_END);

        left_panel.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh sách hợp đồng"));

        table_contract.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Số điện thoại", "Tên khách hàng", "Tên gói", "Ngày ký", "Ngày hết hạn", "Địa chỉ"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table_contract);
        if (table_contract.getColumnModel().getColumnCount() > 0) {
            table_contract.getColumnModel().getColumn(0).setMinWidth(30);
            table_contract.getColumnModel().getColumn(0).setPreferredWidth(40);
            table_contract.getColumnModel().getColumn(0).setMaxWidth(60);
            table_contract.getColumnModel().getColumn(1).setMinWidth(30);
            table_contract.getColumnModel().getColumn(1).setPreferredWidth(30);
            table_contract.getColumnModel().getColumn(2).setMinWidth(70);
            table_contract.getColumnModel().getColumn(2).setPreferredWidth(80);
            table_contract.getColumnModel().getColumn(3).setMinWidth(80);
            table_contract.getColumnModel().getColumn(3).setPreferredWidth(100);
            table_contract.getColumnModel().getColumn(4).setMinWidth(80);
            table_contract.getColumnModel().getColumn(4).setPreferredWidth(80);
            table_contract.getColumnModel().getColumn(5).setMinWidth(80);
            table_contract.getColumnModel().getColumn(5).setPreferredWidth(80);
            table_contract.getColumnModel().getColumn(6).setMinWidth(80);
            table_contract.getColumnModel().getColumn(6).setPreferredWidth(80);
        }

        javax.swing.GroupLayout left_panelLayout = new javax.swing.GroupLayout(left_panel);
        left_panel.setLayout(left_panelLayout);
        left_panelLayout.setHorizontalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, left_panelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1127, Short.MAX_VALUE)
                .addContainerGap())
        );
        left_panelLayout.setVerticalGroup(
            left_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
        );

        getContentPane().add(left_panel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void checkBox_signDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_signDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_signDateActionPerformed

    private void checkBox_expireDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBox_expireDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBox_expireDateActionPerformed

    private void btn_SubName_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SubName_searchActionPerformed
        // TODO add your handling code here:
        try{
            ds = ContractDAO.searchContracts(txt_sub_name.getText(), null, null, null, null, null);
            setupTableSelectionListener();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_SubName_searchActionPerformed

    private void btn_filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_filterActionPerformed
        // TODO add your handling code here:
        LocalDateTime signFrom = null;
        LocalDateTime signTo = null;
        LocalDateTime expireFrom = null;
        LocalDateTime expireTo = null;
        Integer planId = null;
        if(checkBox_signDate.isSelected()){
            signFrom = datePicker_signDateFrom.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            signTo = datePicker_signDateTo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        }
        if(checkBox_expireDate.isSelected()){
            expireFrom = datePicker_expireDateFrom.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            expireTo = datePicker_expireDateTo.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        }
        if(checkBox_plan_filter.isSelected()){
            planId = ((Plan) combo_plan.getSelectedItem()).getPlan_id();
        }
        try{
            ds = ContractDAO.searchContracts(null, planId, signFrom, signTo, expireFrom, expireTo);
            setupTableSelectionListener();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btn_filterActionPerformed

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
            java.util.logging.Logger.getLogger(ContractManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContractManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContractManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContractManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContractManagement().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_SubName_search;
    private javax.swing.JButton btn_filter;
    private javax.swing.JCheckBox checkBox_expireDate;
    private javax.swing.JCheckBox checkBox_plan_filter;
    private javax.swing.JCheckBox checkBox_signDate;
    private javax.swing.JComboBox<Plan> combo_plan;
    private org.jdesktop.swingx.JXDatePicker datePicker_expireDateFrom;
    private org.jdesktop.swingx.JXDatePicker datePicker_expireDateTo;
    private org.jdesktop.swingx.JXDatePicker datePicker_signDateFrom;
    private org.jdesktop.swingx.JXDatePicker datePicker_signDateTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel left_panel;
    private javax.swing.JPanel right_panel;
    private javax.swing.JTable table_contract;
    private javax.swing.JTextField txt_detail_conAddr;
    private javax.swing.JTextField txt_detail_conPlan;
    private javax.swing.JTextField txt_detail_expireDate;
    private javax.swing.JTextField txt_detail_signDate;
    private javax.swing.JTextField txt_detail_subAddr;
    private javax.swing.JTextField txt_detail_subName;
    private javax.swing.JTextField txt_detail_subPhone;
    private javax.swing.JTextField txt_sub_name;
    // End of variables declaration//GEN-END:variables
}
