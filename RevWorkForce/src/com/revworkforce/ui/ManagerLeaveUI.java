package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.service.LeaveService;

public class ManagerLeaveUI {

    public static void showMenu(int managerId) {

        Scanner sc = new Scanner(System.in);
        LeaveService service = new LeaveService();

        while (true) {

            System.out.println("\n--- Manager Leave Menu ---");
            System.out.println("1. View Team Leave Requests");
            System.out.println("2. Approve Leave");
            System.out.println("3. Reject Leave");
            System.out.println("4. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1:
                        service.viewTeamLeaveRequests(managerId);
                        break;

                    case 2:
                        System.out.print("Leave ID: ");
                        int aid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Comment: ");
                        service.approveLeave(aid, sc.nextLine());
                        break;

                    case 3:
                        System.out.print("Leave ID: ");
                        int rid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Comment: ");
                        service.rejectLeave(rid, sc.nextLine());
                        break;

                    case 4:
                        return;
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
