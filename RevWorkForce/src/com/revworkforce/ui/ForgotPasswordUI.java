package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.dao.EmployeeDAO;
import com.revworkforce.service.EmployeeService;

public class ForgotPasswordUI {

    public static void forgotPassword() {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();
        EmployeeService service = new EmployeeService();

        try {

            System.out.print("Employee ID: ");
            int empId = sc.nextInt();
            sc.nextLine();

          
            dao.getSecurityQuestions(empId);

            System.out.print("Answer 1: ");
            String ans1 = sc.nextLine();

            System.out.print("Answer 2: ");
            String ans2 = sc.nextLine();

            service.forgotPasswordWithSecurity(empId, ans1, ans2);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
