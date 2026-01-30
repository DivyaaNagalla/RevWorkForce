package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.service.LeaveAdminService;

public class AdminLeaveConfigUI {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        LeaveAdminService service = new LeaveAdminService();

        while (true) {

            System.out.println("\n--- Leave Configuration ---");
            System.out.println("1. Add Leave Type");
            System.out.println("2. Assign Leave Quota");
            System.out.println("3. Adjust Leave Balance");
            System.out.println("4. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {

                    case 1:
                        System.out.print("Leave Name: ");
                        String name = sc.next();
                        System.out.print("Max Quota: ");
                        int quota = sc.nextInt();
                        service.addLeaveType(name, quota);
                        System.out.println("Leave type added");
                        break;

                    case 2:
                        System.out.print("Emp ID: ");
                        int empId = sc.nextInt();
                        System.out.print("Leave Type ID: ");
                        int typeId = sc.nextInt();
                        System.out.print("Days: ");
                        int days = sc.nextInt();
                        service.assignLeaveQuota(empId, typeId, days);
                        System.out.println("Quota assigned");
                        break;

                    case 3:
                        System.out.print("Emp ID: ");
                        int eId = sc.nextInt();
                        System.out.print("Leave Type ID: ");
                        int lId = sc.nextInt();
                        System.out.print("New Balance: ");
                        int bal = sc.nextInt();
                        service.adjustLeaveBalance(eId, lId, bal);
                        System.out.println("Balance updated");
                        break;

                    case 4:
                        return;

                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
