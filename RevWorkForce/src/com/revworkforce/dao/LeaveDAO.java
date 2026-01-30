package com.revworkforce.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.util.DBUtil;

public class LeaveDAO {

    public void viewLeaveBalance(int empId) throws Exception {

        String sql =
            "SELECT LT.LEAVE_NAME, LB.AVAILABLE_DAYS " +
            "FROM LEAVE_BALANCE LB " +
            "JOIN LEAVE_TYPE LT ON LB.LEAVE_TYPE_ID = LT.LEAVE_TYPE_ID " +
            "WHERE LB.EMP_ID=?";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        System.out.println("\n--- Leave Balance ---");

        while (rs.next()) {
            found = true;
            System.out.println(
                rs.getString("LEAVE_NAME") + " : " +
                rs.getInt("AVAILABLE_DAYS")
            );
        }

        if (!found) {
            System.out.println("No leave balance available");
        }
    }

    public void applyLeave(int empId,
                           int leaveTypeId,
                           Date start,
                           Date end,
                           String reason) throws Exception {

        String sql =
            "INSERT INTO LEAVE_APPLICATION " +
            "(LEAVE_ID, EMP_ID, LEAVE_TYPE_ID, START_DATE, END_DATE, REASON, STATUS, APPLIED_DATE) " +
            "VALUES (LEAVE_APP_SEQ.NEXTVAL, ?, ?, ?, ?, ?, 'PENDING', SYSDATE)";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ps.setInt(2, leaveTypeId);
        ps.setDate(3, start);
        ps.setDate(4, end);
        ps.setString(5, reason);

        ps.executeUpdate();
    }

    public void viewMyLeaves(int empId) throws Exception {

        String sql =
            "SELECT LEAVE_ID, START_DATE, END_DATE, STATUS " +
            "FROM LEAVE_APPLICATION " +
            "WHERE EMP_ID=? " +
            "ORDER BY APPLIED_DATE DESC";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();

        boolean found = false;
        System.out.println("\n--- My Leaves ---");

        while (rs.next()) {
            found = true;
            System.out.println(
                "Leave ID: " + rs.getInt("LEAVE_ID") +
                " | " + rs.getDate("START_DATE") +
                " to " + rs.getDate("END_DATE") +
                " | " + rs.getString("STATUS")
            );
        }

        if (!found) {
            System.out.println("No leave records found");
        }
    }

    public void viewHolidays() throws Exception {

        String sql =
            "SELECT HOLIDAY_DATE, HOLIDAY_NAME " +
            "FROM HOLIDAY ORDER BY HOLIDAY_DATE";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Company Holidays ---");

        while (rs.next()) {
            System.out.println(
                rs.getDate("HOLIDAY_DATE") +
                " | " +
                rs.getString("HOLIDAY_NAME")
            );
        }
    }

    public void cancelLeave(int leaveId, int empId) throws Exception {

        String sql =
            "UPDATE LEAVE_APPLICATION " +
            "SET STATUS='CANCELLED' " +
            "WHERE LEAVE_ID=? AND EMP_ID=? AND STATUS='PENDING'";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, leaveId);
        ps.setInt(2, empId);

        ps.executeUpdate();
    }

    // ---------------- MANAGER ----------------

    public void viewTeamLeaveRequests(int managerId) throws Exception {

        String sql =
            "SELECT L.LEAVE_ID, E.NAME, L.START_DATE, L.END_DATE, L.STATUS " +
            "FROM LEAVE_APPLICATION L " +
            "JOIN EMPLOYEE E ON L.EMP_ID = E.EMP_ID " +
            "WHERE E.MANAGER_ID=? AND L.STATUS='PENDING'";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Team Leave Requests ---");

        while (rs.next()) {
            System.out.println(
                "Leave ID: " + rs.getInt("LEAVE_ID") +
                " | " + rs.getString("NAME") +
                " | " + rs.getDate("START_DATE") +
                " to " + rs.getDate("END_DATE")
            );
        }
    }

    public void approveOrRejectLeave(int leaveId,
                                     String status,
                                     String comment) throws Exception {

        String sql =
            "UPDATE LEAVE_APPLICATION " +
            "SET STATUS=?, MANAGER_COMMENT=? " +
            "WHERE LEAVE_ID=?";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setString(1, status);
        ps.setString(2, comment);
        ps.setInt(3, leaveId);

        ps.executeUpdate();
    }
}
