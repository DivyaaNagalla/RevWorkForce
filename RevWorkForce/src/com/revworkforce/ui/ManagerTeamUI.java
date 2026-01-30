package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.service.EmployeeService;

public class ManagerTeamUI {

    public static void showMenu(int managerId) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {

            System.out.println("\n--- Team Management ---");
            System.out.println("1. View Team Hierarchy");
            System.out.println("2. View Team Attendance");
            System.out.println("3. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        service.viewTeamHierarchy(managerId);
                        break;
                    case 2:
                        service.viewTeamAttendance(managerId);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
