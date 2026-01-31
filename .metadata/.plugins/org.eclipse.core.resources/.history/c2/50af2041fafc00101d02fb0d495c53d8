package com.revworkforce.ui;

import java.io.Console;
import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class ChangePasswordUI {

    public static void changePassword(Employee emp) {

        EmployeeService service = new EmployeeService();
        Console console = System.console();
        Scanner sc = new Scanner(System.in);

        String newPwd;
        String confirmPwd;

        if (console != null) {
            newPwd = new String(console.readPassword("New Password: "));
            confirmPwd = new String(console.readPassword("Confirm Password: "));
        } else {
            // fallback (Eclipse debug)
            System.out.print("New Password: ");
            newPwd = sc.next();
            System.out.print("Confirm Password: ");
            confirmPwd = sc.next();
        }

        try {
            service.changePassword(emp.getEmpId(), newPwd, confirmPwd);
            System.out.println("Password changed successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
