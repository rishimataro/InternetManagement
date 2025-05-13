/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Ngoc Thao
 */
public class LookAndFeelSetup {
    public static void applySystemLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Không thể áp dụng FlatLaf Light");
        }
    }
}
