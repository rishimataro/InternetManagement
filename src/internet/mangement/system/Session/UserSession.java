/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package internet.mangement.system.Session;

import Model.User;

/**
 *
 * @author Ngoc Thao
 */
public class UserSession {
    public static User currentUser;

    public UserSession() {
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        UserSession.currentUser = currentUser;
    }
    
    
}
