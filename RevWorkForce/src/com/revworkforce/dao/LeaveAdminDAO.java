package com.revworkforce.dao;

import java.sql.PreparedStatement;
import com.revworkforce.util.DBUtil;

public class LeaveAdminDAO {

    public void addLeaveType(String name, int quota) throws Exception {

        String sql =
            "INSERT INTO LEAVE_TYPE VALUES (LEAVE_TYPE_SEQ.NEXTVAL, ?, ?)";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setString(1, name);
        ps.setInt(2, quota);
        ps.executeUpdate();
    }

    public void assignLeaveQuota(int empId,
                                 int typeId,
                                 int days) throws Exception {

        String sql =
            "INSERT INTO LEAVE_BALANCE VALUES (?, ?, ?)";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, empId);
        ps.setInt(2, typeId);
        ps.setInt(3, days);
        ps.executeUpdate();
    }

    public void adjustLeaveBalance(int empId,
                                   int typeId,
                                   int balance) throws Exception {

        String sql =
            "UPDATE LEAVE_BALANCE SET AVAILABLE_DAYS=? " +
            "WHERE EMP_ID=? AND LEAVE_TYPE_ID=?";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, balance);
        ps.setInt(2, empId);
        ps.setInt(3, typeId);
        ps.executeUpdate();
    }
}
