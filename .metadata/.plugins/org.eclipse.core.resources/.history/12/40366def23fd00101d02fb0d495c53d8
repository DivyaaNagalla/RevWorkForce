package com.revworkforce.ui;

import java.util.Scanner;
import com.revworkforce.model.Employee;
import com.revworkforce.service.PerformanceService;

public class PerformanceUI {

    public static void submitReview(Employee emp) {

        Scanner sc = new Scanner(System.in);
        PerformanceService service = new PerformanceService();

        System.out.print("Review Year: ");
        int year = sc.nextInt();
        sc.nextLine();

        System.out.print("Key Deliverables: ");
        String del = sc.nextLine();

        System.out.print("Major Accomplishments: ");
        String acc = sc.nextLine();

        System.out.print("Areas of Improvement: ");
        String imp = sc.nextLine();

        System.out.print("Self Rating (1-5): ");
        int rating = sc.nextInt();

        try {
            int reviewId = service.submitReview(
                    emp.getEmpId(),
                    year,
                    del,
                    acc,
                    imp,
                    rating
            );

            System.out.println("Review submitted successfully");
            System.out.println("Review ID : " + reviewId);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
