package com.revworkforce.main;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.ui.AdminMenuUI;
import com.revworkforce.ui.EmployeeMenuUI;
import com.revworkforce.ui.LoginUI;

public class RevWorkForceApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("================================");
            System.out.println("      RevWorkForce System        ");
            System.out.println("================================");
            System.out.println("1. Admin");
            System.out.println("2. Employee");
            System.out.println("3. Exit");
            System.out.print("Choose role: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    AdminMenuUI.showMenu();
                    break;

                case 2:
                    Employee emp = LoginUI.login();

                    if (emp != null) {
                        boolean isManager =
                            emp.getManagerName() != null;
                        EmployeeMenuUI.showMenu(emp, isManager);
                    }
                    break;

                case 3:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
