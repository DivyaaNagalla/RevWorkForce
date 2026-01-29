package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;

public class EmployeeMenuUI {

    public static void showMenu(Employee emp, boolean isManager) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. Leave Management");
            System.out.println("2. Logout");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    LeaveUI.showMenu(emp, isManager);
                    break;

                case 2:
                    System.out.println("Logged out");
                    return;

                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
