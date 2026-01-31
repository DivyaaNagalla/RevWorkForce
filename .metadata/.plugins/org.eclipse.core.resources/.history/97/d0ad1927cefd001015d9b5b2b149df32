package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class ProfileUI {

    public static void viewProfile(Employee emp) {

        System.out.println("\n--- My Profile ---");
        System.out.println("ID       : " + emp.getEmpId());
        System.out.println("Name     : " + emp.getName());
        System.out.println("Email    : " + emp.getEmail());
        System.out.println("Phone    : " + emp.getPhone());
        System.out.println("Address  : " + emp.getAddress());
        System.out.println("Manager  : " + emp.getManagerName());
    }

    public static void updateProfile(Employee emp) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        System.out.print("Phone Number       : ");
        String phone = sc.next();

        sc.nextLine();
        System.out.print("Address            : ");
        String address = sc.nextLine();

        System.out.print("Emergency Contact  : ");
        String emergency = sc.next();

        try {
            service.updateProfile(
                emp.getEmpId(), phone, address, emergency);
            System.out.println("Profile updated successfully");

            emp.setPhone(phone);
            emp.setAddress(address);
            emp.setEmergencyContact(emergency);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
