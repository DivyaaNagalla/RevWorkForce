package com.revworkforce.ui;

import com.revworkforce.model.Employee;

public class LeaveUI {

    public static void showMenu(Employee emp, boolean isManager) {

        if (isManager) {
            ManagerLeaveUI.showMenu(emp);
        } else {
            EmployeeLeaveUI.showMenu(emp);
        }
    }
}
