package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.PerformanceService;

public class ManagerPerformanceUI {

    public static void showMenu(Employee manager) {

        Scanner sc = new Scanner(System.in);
        PerformanceService service = new PerformanceService();

        while (true) {
            System.out.println("\n--- Manager Performance Menu ---");
            System.out.println("1. View Team Reviews");
            System.out.println("2. Give Feedback & Rating");
            System.out.println("3. Back");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {

                    case 1:
                        service.viewTeamReviews(manager.getEmpId());
                        break;

                    case 2:
                        System.out.print("Enter Review ID: ");
                        int reviewId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Manager Rating (1-5): ");
                        int rating = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Manager Feedback: ");
                        String fb = sc.nextLine();

                        service.giveManagerFeedback(reviewId, rating, fb);
                        break;

                    case 3:
                        return;

                    default:
                        System.out.println("Invalid option");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
