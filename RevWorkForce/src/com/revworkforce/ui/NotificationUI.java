package com.revworkforce.ui;

import com.revworkforce.service.NotificationService;

public class NotificationUI {

    public static void showNotifications(int empId) {

        NotificationService service = new NotificationService();

        try {
            service.showUnreadOnLogin(empId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
