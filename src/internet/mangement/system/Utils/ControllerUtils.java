/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system.Utils;

import java.text.DecimalFormat;

/**
 *
 * @author Ngoc Thao
 */
public class ControllerUtils {
    public static String formatMoney(double amount) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(amount) + " VNĐ";
    }
}
