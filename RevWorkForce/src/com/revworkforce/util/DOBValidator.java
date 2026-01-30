package com.revworkforce.util;

import java.sql.Date;
import java.util.Calendar;

public class DOBValidator {

    public static void validate(Date dob) throws Exception {

        if (dob == null) {
            throw new Exception("Date of Birth cannot be empty");
        }

        Calendar today = Calendar.getInstance();
        Calendar birth = Calendar.getInstance();
        birth.setTime(dob);

        if (birth.after(today)) {
            throw new Exception("Date of Birth cannot be in the future");
        }

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        if (age < 18) {
            throw new Exception("Employee must be at least 18 years old");
        }
    }
}
