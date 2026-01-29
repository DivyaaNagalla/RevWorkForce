package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBUtil;

public class PerformanceDAO {

    public int createReview(int empId,
                            int year,
                            String deliverables,
                            String accomplishments,
                            String improvements,
                            int selfRating) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql =
            "INSERT INTO PERFORMANCE_REVIEW " +
            "(REVIEW_ID, EMP_ID, REVIEW_YEAR, DELIVERABLES, " +
            "ACCOMPLISHMENTS, IMPROVEMENTS, SELF_RATING, STATUS) " +
            "VALUES (REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, ?, ?, 'SUBMITTED')";

        PreparedStatement ps =
            con.prepareStatement(sql, new String[]{"REVIEW_ID"});

        ps.setInt(1, empId);
        ps.setInt(2, year);
        ps.setString(3, deliverables);
        ps.setString(4, accomplishments);
        ps.setString(5, improvements);
        ps.setInt(6, selfRating);
        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        int reviewId = rs.getInt(1);

        String mgrSql =
            "SELECT MANAGER_ID FROM EMPLOYEE WHERE EMP_ID=?";

        PreparedStatement ps2 = con.prepareStatement(mgrSql);
        ps2.setInt(1, empId);
        ResultSet rs2 = ps2.executeQuery();

        if (rs2.next()) {
            int managerId = rs2.getInt(1);

            String notifSql =
                "INSERT INTO NOTIFICATION VALUES " +
                "(NOTIF_SEQ.NEXTVAL, ?, ?, 'N', SYSDATE)";

            PreparedStatement ps3 =
                con.prepareStatement(notifSql);
            ps3.setInt(1, managerId);
            ps3.setString(2,
                "Performance review submitted by Employee ID " + empId);
            ps3.executeUpdate();
        }

        return reviewId;
    }

    public void viewTeamReviews(int managerId) throws Exception {

        String sql =
            "SELECT R.REVIEW_ID, E.EMP_ID, E.NAME, R.REVIEW_YEAR, R.STATUS " +
            "FROM PERFORMANCE_REVIEW R " +
            "JOIN EMPLOYEE E ON R.EMP_ID = E.EMP_ID " +
            "WHERE E.MANAGER_ID=?";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, managerId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getInt(1) + " | " +
                rs.getInt(2) + " | " +
                rs.getString(3) + " | " +
                rs.getInt(4) + " | " +
                rs.getString(5)
            );
        }
    }

    public void giveManagerFeedback(int reviewId,
                                    int empId,
                                    String feedback,
                                    int rating) throws Exception {

        Connection con = DBUtil.getConnection();

        String sql =
            "UPDATE PERFORMANCE_REVIEW " +
            "SET MANAGER_FEEDBACK=?, MANAGER_RATING=?, STATUS='REVIEWED' " +
            "WHERE REVIEW_ID=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, feedback);
        ps.setInt(2, rating);
        ps.setInt(3, reviewId);
        ps.executeUpdate();

        String notifSql =
            "INSERT INTO NOTIFICATION VALUES " +
            "(NOTIF_SEQ.NEXTVAL, ?, ?, 'N', SYSDATE)";

        PreparedStatement ps2 =
            con.prepareStatement(notifSql);
        ps2.setInt(1, empId);
        ps2.setString(2,
            "Manager has reviewed your performance");
        ps2.executeUpdate();
    }

    public void teamPerformanceSummary(int managerId) throws Exception {

        String sql =
            "SELECT E.NAME, R.MANAGER_RATING " +
            "FROM PERFORMANCE_REVIEW R " +
            "JOIN EMPLOYEE E ON R.EMP_ID = E.EMP_ID " +
            "WHERE E.MANAGER_ID=? AND R.MANAGER_RATING IS NOT NULL";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, managerId);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            System.out.println(
                rs.getString(1) + " | Rating: " + rs.getInt(2)
            );
        }
    }
}
