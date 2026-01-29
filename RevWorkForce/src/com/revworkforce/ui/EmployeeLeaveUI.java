package com.revworkforce.ui;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.revworkforce.model.Employee;
import com.revworkforce.service.LeaveService;

public class EmployeeLeaveUI {

    public static void showMenu(Employee emp) {

        Scanner sc = new Scanner(System.in);
        LeaveService service = new LeaveService();

        while (true) {

            System.out.println("\n--- Employee Leave Menu ---");
            System.out.println("1. View Leave Balance");
            System.out.println("2. Apply Leave");
            System.out.println("3. View My Leaves");
            System.out.println("4. View Holidays");
            System.out.println("5. Back");
            System.out.print("Choose option: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {

                switch (ch) {

                    case 1:
                        service.viewLeaveBalance(emp.getEmpId());
                        break;

                    case 2:
                        System.out.print("Leave Type ID (1=CL,2=SL,3=PL): ");
                        int typeId = sc.nextInt();
                        sc.nextLine();

                        SimpleDateFormat sdf =
                            new SimpleDateFormat("yyyy-MM-dd");

                        System.out.print("From Date (yyyy-MM-dd): ");
                        java.util.Date utilFrom =
                            sdf.parse(sc.nextLine());

                        System.out.print("To Date (yyyy-MM-dd): ");
                        java.util.Date utilTo =
                            sdf.parse(sc.nextLine());

                        Date fromDate =
                            new Date(utilFrom.getTime());
                        Date toDate =
                            new Date(utilTo.getTime());

                        System.out.print("Reason: ");
                        String reason = sc.nextLine();

                        int days =
                            (int)((toDate.getTime() - fromDate.getTime())
                            / (1000 * 60 * 60 * 24)) + 1;

                        service.applyLeave(
                                emp.getEmpId(),
                                typeId,
                                fromDate,
                                toDate,
                                reason,
                                days
                        );

                        System.out.println("Leave applied successfully");
                        break;

                    case 3:
                        service.viewMyLeaves(emp.getEmpId());
                        break;

                    case 4:
                        service.viewHolidays();
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
