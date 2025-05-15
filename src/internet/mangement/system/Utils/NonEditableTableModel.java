/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system.Utils;

import javax.swing.table.DefaultTableModel;

/**
 * A custom table model that prohibits editing of cells while allowing row selection.
 * This model extends DefaultTableModel and overrides the isCellEditable method to always return false.
 * 
 * @author Ngoc Thao
 */
public class NonEditableTableModel extends DefaultTableModel {
    
    public NonEditableTableModel() {
        super();
    }
    
    public NonEditableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        // Always return false to make all cells non-editable
        return false;
    }
}
