package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.service.EmployeeService;

public class AdminMenuUI {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Assign Manager");
            System.out.println("3. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    AddEmployeeUI.addEmployee();
                    break;

                case 2:
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();
                    System.out.print("Manager ID: ");
                    int mgrId = sc.nextInt();
                    try {
                        service.assignManager(empId, mgrId);
                        System.out.println("Manager assigned");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    return;
            }
        }
    }
}
