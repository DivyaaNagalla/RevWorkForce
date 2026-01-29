package com.revworkforce.ui;

import java.io.Console;
import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class LoginUI {

    public static Employee login() {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        System.out.print("Enter Employee ID: ");
        int empId = sc.nextInt();

        Console console = System.console();
        String password;

        if (console != null) {
            password = new String(console.readPassword("Enter Password: "));
        } else {
            System.out.print("Enter Password: ");
            password = sc.next();
        }

        try {
            return service.login(empId, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
