package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbOperations {
    public static void setDataOrDelete(String query, String msg) {
        Connection conn = null;
        Statement st = null;
        try {
            conn = ConnectionProvider.getConn();
            st = conn.createStatement();
            st.executeUpdate(query);

            if(!msg.equals("")) {
                JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Message", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static int getNextId(String tableName, String idColumnName) {
        int nextId = 1;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionProvider.getConn();
            stmt = conn.createStatement();
            String query = "SELECT MAX(" + idColumnName + ") AS max_id FROM " + tableName;
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                int maxId = rs.getInt("max_id");
                nextId = maxId + 1;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return nextId;
    }

    public static boolean executeTransaction(SqlOperation[] operations, String successMsg) {
        Connection conn = null;
        boolean success = false;

        try {
            conn = ConnectionProvider.getConn();
            conn.setAutoCommit(false);

            for (SqlOperation operation : operations) {
                PreparedStatement ps = conn.prepareStatement(operation.getSql());
                operation.setParameters(ps);
                ps.executeUpdate();
                ps.close();
            }

            conn.commit();
            success = true;

            if (successMsg != null && !successMsg.isEmpty()) {
                JOptionPane.showMessageDialog(null, successMsg, "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            try {
                if (conn != null) conn.rollback();
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return success;
    }

    public interface SqlOperation {
        String getSql();
        void setParameters(PreparedStatement ps) throws Exception;
    }
}
