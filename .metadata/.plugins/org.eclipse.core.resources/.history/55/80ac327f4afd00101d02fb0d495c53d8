package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.service.EmployeeService;

public class AdditionalDetailsUI {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n--- Additional Details ---");
            System.out.println("1. View Birthdays");
            System.out.println("2. View Work Anniversaries");
            System.out.println("3. Employee Directory");
            System.out.println("4. Back");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1:
                        service.viewBirthdays();
                        break;
                    case 2:
                        service.viewAnniversaries();
                        break;
                    case 3:
                        service.viewEmployeeDirectory();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
