package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class EmployeeMenuUI {

    public static void showMenu(Employee emp, boolean isManager) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        while (true) {

            System.out.println("\n--- Employee Menu ---");
            System.out.println("1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. Leave Management");
            System.out.println("4. Performance Management");
            System.out.println("5. Additional Details");

            if (isManager)
                System.out.println("6. Manager Leave Menu");

            System.out.println("7. Logout");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        EmployeeProfileUI.viewProfile(emp);
                        break;

                    case 2:
                        emp = EmployeeProfileUI.updateProfile(emp);
                        break;

                    case 3:
                    	 EmployeeLeaveUI.showMenu(emp);
                        break;

                    case 4:
                        EmployeePerformanceUI.showMenu(emp);
                        break;

                    case 5:
                        AdditionalDetailsUI.showMenu();
                        break;

                    case 6:
                        if (isManager)
                            ManagerLeaveUI.showMenu(emp.getEmpId());
                        else
                            System.out.println("Invalid option");
                        break;

                    case 7:
                        return;

                    default:
                        System.out.println("Invalid choice");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
