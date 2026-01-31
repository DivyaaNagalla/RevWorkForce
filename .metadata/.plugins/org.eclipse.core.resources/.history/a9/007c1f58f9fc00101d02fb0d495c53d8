package com.revworkforce.ui;

import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.LeaveService;

public class ManagerLeaveUI {

    public static void showMenu(Employee manager) {

        Scanner sc = new Scanner(System.in);
        LeaveService service = new LeaveService();

        while (true) {

            System.out.println("\n--- Manager Leave Menu ---");
            System.out.println("1. View My Team");
            System.out.println("2. View Team Leave Requests");
            System.out.println("3. Approve / Reject Leave");
            System.out.println("4. Team Leave Calendar");
            System.out.println("5. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {

                    case 1:
                        service.viewMyTeam(manager.getEmpId());
                        break;

                    case 2:
                        service.viewTeamLeaveRequests(manager.getEmpId());
                        break;

                    case 3:
                        System.out.print("Leave ID: ");
                        int leaveId = sc.nextInt();

                        System.out.print("Employee ID: ");
                        int empId = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Status (APPROVED/REJECTED): ");
                        String status = sc.nextLine();

                        System.out.print("Comment: ");
                        String comment = sc.nextLine();

                        service.approveOrRejectLeave(
                                leaveId, status, comment, empId);

                        System.out.println("Leave updated");
                        break;

                    case 4:
                        service.viewTeamCalendar(manager.getEmpId());
                        break;

                    case 5:
                        return;

                    default:
                        System.out.println("Invalid option");
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
