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
            System.out.println("2. View Employee Goals");
            System.out.println("3. Give Feedback & Rating");
            System.out.println("4. Team Performance Summary");
            System.out.println("5. Back");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1:
                        service.viewTeamReviews(manager.getEmpId());
                        break;

                    case 2:
                        System.out.print("Review ID: ");
                        int rid = sc.nextInt();
                        service.viewEmployeeGoals(rid);
                        break;

                    case 3:
                        System.out.print("Review ID: ");
                        int reviewId = sc.nextInt();

                        System.out.print("Employee ID: ");
                        int empId = sc.nextInt();

                        sc.nextLine();
                        System.out.print("Feedback: ");
                        String feedback = sc.nextLine();

                        System.out.print("Rating (1-5): ");
                        int rating = sc.nextInt();

                        service.giveFeedback(
                            reviewId, feedback, rating, empId);

                        System.out.println("Feedback submitted");
                        break;

                    case 4:
                        service.viewTeamPerformanceSummary(manager.getEmpId());
                        break;

                    case 5:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

