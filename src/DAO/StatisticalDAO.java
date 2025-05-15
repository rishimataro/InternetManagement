/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.StatisticalRecord;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Ngoc Thao
 */
public class StatisticalDAO {
    public static double revenue() {
        double total = 0;

        try {
            ResultSet rs = DbOperations.getData("SELECT SUM(amount) AS total FROM BILL_HISTORY WHERE status = 'paid' AND payment_date IS NOT NULL");
            while (rs.next()) {
                total = rs.getDouble("total");
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    public static int countSubscriber() {
        int count = 0;

        try{
            ResultSet rs = DbOperations.getData("SELECT COUNT(*) AS count FROM SUBSCRIBER");
            if (rs.next()) {
                count = rs.getInt("count");
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int countContract() {
        int count = 0;
        try{
            ResultSet rs = DbOperations.getData("SELECT COUNT(*) AS count FROM CONTRACT");
            if (rs.next()) {
                count = rs.getInt("count");
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int countPlan() {
        int count = 0;
        try{
            ResultSet rs = DbOperations.getData("SELECT COUNT(*) AS count FROM PLAN");
            if (rs.next()) {
                count = rs.getInt("count");
            }

            rs.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static ArrayList<StatisticalRecord> getAll() {
        return getByCondition(null, null, null);
    }

    public static ArrayList<StatisticalRecord> getByCondition(String customerName, Date startDate, Date endDate) {
        ArrayList<StatisticalRecord> records = new ArrayList<>();

        try {
            // First, get all subscribers with optional customer name filter
            StringBuilder subscriberQuery = new StringBuilder("SELECT DISTINCT s.subscriber_id, s.name AS customer_name FROM SUBSCRIBER s");

            if (customerName != null && !customerName.trim().isEmpty()) {
                subscriberQuery.append(" WHERE s.name LIKE '%").append(customerName.trim()).append("%'");
            }

            ResultSet rsSubscribers = DbOperations.getData(subscriberQuery.toString());

            while (rsSubscribers.next()) {
                int subscriberId = rsSubscribers.getInt("subscriber_id");
                String subName = rsSubscribers.getString("customer_name");

                // For each subscriber, get their latest contract details with optional date filter
                StringBuilder contractQueryBuilder = new StringBuilder();
                contractQueryBuilder.append("SELECT p.name AS plan_name, c.date_sign AS registration_date, ")
                        .append("CASE WHEN c.date_expiration IS NULL THEN 'Chưa đăng ký' ")
                        .append("     WHEN c.date_expiration > CURRENT_TIMESTAMP() THEN 'Hoạt động' ")
                        .append("     ELSE 'Hết hạn' END AS status, ")
                        .append("COALESCE(SUM(CASE WHEN bh.status = 'paid' THEN bh.amount ELSE 0 END), 0) AS amount_paid ")
                        .append("FROM SUBSCRIBER s ")
                        .append("LEFT JOIN CONTRACT c ON s.subscriber_id = c.subscriber_id ")
                        .append("LEFT JOIN PLAN p ON c.plan_id = p.plan_id ")
                        .append("LEFT JOIN BILL_HISTORY bh ON c.contract_id = bh.contract_id ")
                        .append("WHERE s.subscriber_id = ").append(subscriberId).append(" ");

                // Add date range filter if provided
                if (startDate != null && endDate != null) {
                    // Convert java.util.Date to java.sql.Date for SQL query
                    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
                    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());

                    contractQueryBuilder.append("AND c.date_sign >= '").append(sqlStartDate).append("' ")
                                       .append("AND c.date_sign <= '").append(sqlEndDate).append("' ");
                }

                contractQueryBuilder.append("GROUP BY p.name, c.date_sign, c.date_expiration ")
                                  .append("ORDER BY c.date_sign DESC LIMIT 1");

                ResultSet rsContract = DbOperations.getData(contractQueryBuilder.toString());

                String planName = "Chưa đăng ký";
                LocalDateTime registrationDate = null;
                String status = "Chưa đăng ký";
                long amountPaid = 0;

                if (rsContract.next()) {
                    planName = rsContract.getString("plan_name");
                    if (planName == null) planName = "Chưa đăng ký";

                    Timestamp registrationTimestamp = rsContract.getTimestamp("registration_date");
                    registrationDate = registrationTimestamp != null ? registrationTimestamp.toLocalDateTime() : null;

                    status = rsContract.getString("status");
                    amountPaid = rsContract.getLong("amount_paid");
                }

                StatisticalRecord record = new StatisticalRecord(subscriberId, subName, planName,
                        registrationDate, status, amountPaid);
                records.add(record);

                rsContract.close();
            }

            rsSubscribers.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return records;
    }
}
