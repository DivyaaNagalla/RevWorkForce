package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.EmployeeService;

public class EmployeeProfileUI {

    public static void viewProfile(Employee emp) {
        System.out.println("\n--- My Profile ---");
        System.out.println("Employee ID : " + emp.getEmpId());
        System.out.println("Name        : " + emp.getName());
        System.out.println("Email       : " + emp.getEmail());
        System.out.println("Phone       : " + emp.getPhone());
        System.out.println("Address     : " + emp.getAddress());
        System.out.println("Emergency   : " + emp.getEmergencyContact());
        System.out.println("Manager     : " + emp.getManagerName());
    }

    public static void editProfile(Employee emp) {

        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeService();

        System.out.print("New Phone (10 digits): ");
        String phone = sc.nextLine();

        System.out.print("New Address: ");
        String address = sc.nextLine();

        System.out.print("Emergency Contact: ");
        String emergency = sc.nextLine();

        try {
            service.updateProfile(emp.getEmpId(), phone, address, emergency);
            emp.setPhone(phone);
            emp.setAddress(address);
            emp.setEmergencyContact(emergency);
            System.out.println("Profile updated successfully");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
