package com.revworkforce.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.util.DBUtil;

public class LeaveDAO {
    public void applyLeave(int empId,
                           int leaveTypeId,
                           Date fromDate,
                           Date toDate,
                           String reason,
                           int days) throws Exception {

        Connection con = DBUtil.getConnection();

        String insertSql =
            "INSERT INTO LEAVE_APPLICATION " +
            "(LEAVE_ID, EMP_ID, LEAVE_TYPE_ID, FROM_DATE, TO_DATE, DAYS, REASON, STATUS) " +
            "VALUES (LEAVE_APP_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'PENDING')";

        PreparedStatement ps = con.prepareStatement(insertSql);
        ps.setInt(1, empId);
        ps.setInt(2, leaveTypeId);
        ps.setDate(3, fromDate);
        ps.setDate(4, toDate);
        ps.setInt(5, days);
        ps.setString(6, reason);
        ps.executeUpdate();

        String mgrSql =
            "SELECT MANAGER_ID FROM EMPLOYEE WHERE EMP_ID=?";

        PreparedStatement ps2 = con.prepareStatement(mgrSql);
        ps2.setInt(1, empId);
        ResultSet rs = ps2.executeQuery();

        if (rs.next()) {
            int managerId = rs.getInt(1);

            String notifSql =
                "INSERT INTO NOTIFICATION " +
                "VALUES (NOTIF_SEQ.NEXTVAL, ?, ?, 'N', SYSDATE)";

            PreparedStatement ps3 = con.prepareStatement(notifSql);
            ps3.setInt(1, managerId);
            ps3.setString(2,
                "New leave request submitted by Employee ID " + empId);
            ps3.executeUpdate();
        }
    }
    public void getLeaveBalance(int empId) throws Exception {

        String sql =
            "SELECT LT.LEAVE_NAME, LB.AVAILABLE_DAYS " +
            "FROM LEAVE_BALANCE LB " +
            "JOIN LEAVE_TYPE LT ON LB.LEAVE_TYPE_ID = LT.LEAVE_TYPE_ID " +
            "WHERE LB.EMP_ID=?";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Leave Balance ---");
        while (rs.next()) {
            System.out.println(
                rs.getString(1) + " : " + rs.getInt(2)
            );
        }
    }

    public void getMyLeaves(int empId) throws Exception {

        String sql =
            "SELECT L.LEAVE_ID, LT.LEAVE_NAME, L.FROM_DATE, L.TO_DATE, L.STATUS " +
            "FROM LEAVE_APPLICATION L " +
            "JOIN LEAVE_TYPE LT ON L.LEAVE_TYPE_ID = LT.LEAVE_TYPE_ID " +
            "WHERE L.EMP_ID=?";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, empId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- My Leaves ---");
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " | " +
                rs.getString(2) + " | " +
                rs.getDate(3) + " to " +
                rs.getDate(4) + " | " +
                rs.getString(5)
            );
        }
    }

       public void getHolidays() throws Exception {

        String sql =
            "SELECT HOLIDAY_DATE, HOLIDAY_NAME " +
            "FROM HOLIDAY ORDER BY HOLIDAY_DATE";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Holiday Calendar ---");
        while (rs.next()) {
            System.out.println(
                rs.getDate(1) + " | " + rs.getString(2)
            );
        }
    }
    public void getTeamMembers(int managerId) throws Exception {

        String sql =
            "SELECT EMP_ID, NAME FROM EMPLOYEE WHERE MANAGER_ID=?";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- My Team ---");
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " - " + rs.getString(2)
            );
        }
    }
    public void getTeamLeaveRequests(int managerId) throws Exception {

        String sql =
            "SELECT L.LEAVE_ID, E.EMP_ID, E.NAME, L.FROM_DATE, L.TO_DATE, L.STATUS " +
            "FROM LEAVE_APPLICATION L " +
            "JOIN EMPLOYEE E ON L.EMP_ID = E.EMP_ID " +
            "WHERE E.MANAGER_ID=? AND L.STATUS='PENDING'";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Team Leave Requests ---");
        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " | " +
                rs.getInt(2) + " | " +
                rs.getString(3) + " | " +
                rs.getDate(4) + " to " +
                rs.getDate(5) + " | " +
                rs.getString(6)
            );
        }
    }
    public void updateLeaveStatus(int leaveId,
                                  String status,
                                  String comment,
                                  int empId) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql =
            "UPDATE LEAVE_APPLICATION " +
            "SET STATUS=?, MANAGER_COMMENT=? " +
            "WHERE LEAVE_ID=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, status);
        ps.setString(2, comment);
        ps.setInt(3, leaveId);
        ps.executeUpdate();

        String notifSql =
            "INSERT INTO NOTIFICATION " +
            "VALUES (NOTIF_SEQ.NEXTVAL, ?, ?, 'N', SYSDATE)";

        PreparedStatement ps2 = con.prepareStatement(notifSql);
        ps2.setInt(1, empId);
        ps2.setString(2, "Your leave request is " + status);
        ps2.executeUpdate();
    }
    public void getTeamLeaveCalendar(int managerId) throws Exception {

        String sql =
            "SELECT E.NAME, L.FROM_DATE, L.TO_DATE, L.STATUS " +
            "FROM LEAVE_APPLICATION L " +
            "JOIN EMPLOYEE E ON L.EMP_ID = E.EMP_ID " +
            "WHERE E.MANAGER_ID=?";

        PreparedStatement ps = DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, managerId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Team Leave Calendar ---");
        while (rs.next()) {
            System.out.println(
                rs.getString(1) + " | " +
                rs.getDate(2) + " to " +
                rs.getDate(3) + " | " +
                rs.getString(4)
            );
        }
    }
    public void getTeamLeaveBalance(int empId) throws Exception {

        getLeaveBalance(empId);
    }
}
