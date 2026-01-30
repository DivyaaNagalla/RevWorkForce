package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class ChangePasswordUI {

    public static boolean changePassword(Employee emp) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        int attempts = 0;

        System.out.println("First time login – change password required");

        while (attempts < 3) {

            try {
                System.out.println("Attempt " + (attempts + 1) + "/3");

                System.out.print("New Password: ");
                String newPwd = sc.nextLine();

                System.out.print("Confirm Password: ");
                String confirmPwd = sc.nextLine();

                service.changePassword(
                    emp.getEmpId(),
                    newPwd,
                    confirmPwd
                );

                System.out.println("Password changed successfully");
                return true; 

            } catch (Exception e) {
                System.out.println(e.getMessage());
                attempts++;
            }
        }

        System.out.println(
            "Too many failed attempts. Please login again."
        );

        return false; 
    }
}
