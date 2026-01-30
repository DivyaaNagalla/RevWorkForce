package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class LoginUI {

public static Employee login() {

    Scanner sc = new Scanner(System.in);
    EmployeeService service = new EmployeeService();

    System.out.print("Enter Employee ID: ");
    int empId = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter Password: ");
    String pwd = sc.nextLine();

    try {
        Employee emp = service.login(empId, pwd);

        if ("Y".equalsIgnoreCase(emp.getFirstLogin())) {
            System.out.println("First time login – change password required");

            for (int i = 1; i <= 3; i++) {
                System.out.println("Attempt " + i + "/3");

                System.out.print("New Password: ");
                String newPwd = sc.nextLine();

                System.out.print("Confirm Password: ");
                String confirmPwd = sc.nextLine();

                try {
                    service.changePassword(emp.getEmpId(), newPwd, confirmPwd);
                    break;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    if (i == 3) return null;
                }
            }
        }

        return emp;

    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}
}

