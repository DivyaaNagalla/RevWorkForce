package com.revworkforce.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.revworkforce.util.DBUtil;

public class NotificationDAO {

    public void addNotification(int empId, String message) throws Exception {

        String sql =
            "INSERT INTO NOTIFICATION " +
            "(NOTIF_ID, EMP_ID, MESSAGE, IS_READ, CREATED_AT) " +
            "VALUES (NOTIF_SEQ.NEXTVAL, ?, ?, 'N', SYSDATE)";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ps.setString(2, message);

        ps.executeUpdate();
    }

    public void showUnreadNotifications(int empId) throws Exception {

        String sql =
            "SELECT MESSAGE, CREATED_AT " +
            "FROM NOTIFICATION " +
            "WHERE EMP_ID=? AND IS_READ='N' " +
            "ORDER BY CREATED_AT DESC";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);

        ResultSet rs = ps.executeQuery();

        boolean found = false;
        System.out.println("\n--- New Notifications ---");

        while (rs.next()) {
            found = true;
            System.out.println(
                rs.getDate("CREATED_AT") + " | " +
                rs.getString("MESSAGE")
            );
        }

        if (!found) {
            System.out.println("No new notifications");
        }

        markAsRead(empId);
    }

    private void markAsRead(int empId) throws Exception {

        String sql =
            "UPDATE NOTIFICATION SET IS_READ='Y' WHERE EMP_ID=?";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ps.executeUpdate();
    }
}
