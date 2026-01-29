package com.revworkforce.service;

import java.sql.Date;
import com.revworkforce.dao.LeaveDAO;

public class LeaveService {

    private LeaveDAO dao = new LeaveDAO();

    public void applyLeave(int empId,
                           int leaveTypeId,
                           Date fromDate,
                           Date toDate,
                           String reason,
                           int days) throws Exception {

        if (fromDate.after(toDate)) {
            throw new Exception("From date cannot be after To date");
        }

        dao.applyLeave(empId, leaveTypeId, fromDate, toDate, reason, days);
    }

    public void viewLeaveBalance(int empId) throws Exception {
        dao.getLeaveBalance(empId);
    }

    public void viewMyLeaves(int empId) throws Exception {
        dao.getMyLeaves(empId);
    }

    public void viewHolidays() throws Exception {
        dao.getHolidays();
    }

    public void viewMyTeam(int managerId) throws Exception {
        dao.getTeamMembers(managerId);
    }

    public void viewTeamLeaveRequests(int managerId) throws Exception {
        dao.getTeamLeaveRequests(managerId);
    }

    public void approveOrRejectLeave(int leaveId,
                                     String status,
                                     String comment,
                                     int empId) throws Exception {

        if (!status.equals("APPROVED") && !status.equals("REJECTED")) {
            throw new Exception("Invalid status");
        }

        dao.updateLeaveStatus(leaveId, status, comment, empId);
    }

    public void viewTeamCalendar(int managerId) throws Exception {
        dao.getTeamLeaveCalendar(managerId);
    }
}
