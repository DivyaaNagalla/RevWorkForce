package com.revworkforce.ui;

import com.revworkforce.util.DBUtil;

public class DBTest {

    public static void main(String[] args) {
        try {
            DBUtil.getConnection();
            System.out.println("DB Connected Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
