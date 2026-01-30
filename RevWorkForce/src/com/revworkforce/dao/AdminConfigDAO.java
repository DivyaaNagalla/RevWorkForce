package com.revworkforce.dao;

import java.sql.PreparedStatement;
import com.revworkforce.util.DBUtil;

public class AdminConfigDAO {

    public void addDepartment(String name) throws Exception {

        String sql =
            "INSERT INTO DEPARTMENT VALUES (DEPT_SEQ.NEXTVAL, ?, 'ACTIVE')";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setString(1, name);
        ps.executeUpdate();
    }

    public void addDesignation(String name) throws Exception {

        String sql =
            "INSERT INTO DESIGNATION VALUES (DESIG_SEQ.NEXTVAL, ?, 'ACTIVE')";

        PreparedStatement ps =
            DBUtil.getConnection().prepareStatement(sql);

        ps.setString(1, name);
        ps.executeUpdate();
    }
}
