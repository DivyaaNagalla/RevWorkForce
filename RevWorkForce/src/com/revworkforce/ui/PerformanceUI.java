package com.revworkforce.ui;

import java.util.Scanner;

public class PerformanceUI {

    public static void showMenu(int empId) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- Performance Management ---");
            System.out.println("1. Create Performance Review");
            System.out.println("2. View My Goals");
            System.out.println("3. Update Goal Progress");
            System.out.println("4. View Manager Feedback");
            System.out.println("5. Back");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.println(
                        "Performance review creation – coming soon"
                    );
                    break;

                case 2:
                    System.out.println(
                        "View goals – coming soon"
                    );
                    break;

                case 3:
                    System.out.println(
                        "Update goal progress – coming soon"
                    );
                    break;

                case 4:
                    System.out.println(
                        "Manager feedback – coming soon"
                    );
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid option");
            }

            System.out.println("\nPress Enter to continue...");
            sc.nextLine();
        }
    }
}
