package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.service.AdminConfigService;

public class AdminConfigUI {

    public static void showMenu() {

        Scanner sc = new Scanner(System.in);
        AdminConfigService service = new AdminConfigService();

        while (true) {

            System.out.println("\n--- System Configuration ---");
            System.out.println("1. Add Department");
            System.out.println("2. Add Designation");
            System.out.println("3. Back");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1:
                        System.out.print("Department Name: ");
                        service.addDepartment(sc.nextLine());
                        System.out.println("Department added");
                        break;

                    case 2:
                        System.out.print("Designation Name: ");
                        service.addDesignation(sc.nextLine());
                        System.out.println("Designation added");
                        break;

                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
