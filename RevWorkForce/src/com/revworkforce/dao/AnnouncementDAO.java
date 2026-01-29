package com.revworkforce.dao;

import java.sql.*;
import com.revworkforce.util.DBUtil;

public class AnnouncementDAO {

    public void viewAnnouncements() throws Exception {

        String sql =
          "SELECT TITLE, MESSAGE, CREATED_DATE FROM ANNOUNCEMENT " +
          "ORDER BY CREATED_DATE DESC";

        PreparedStatement ps =
          DBUtil.getConnection().prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        System.out.println("\n--- Company Announcements ---");
        while (rs.next()) {
            System.out.println(
              rs.getString(1) + " | " +
              rs.getString(2) + " | " +
              rs.getDate(3)
            );
        }
    }
}
