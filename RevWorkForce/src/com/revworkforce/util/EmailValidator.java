package com.revworkforce.util;

public class EmailValidator {

    private static final String EMAIL_REGEX =
        "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    public static void validate(String email) throws Exception {

        if (email == null) {
            throw new Exception("Email cannot be empty");
        }

        email=email.trim();
        if(email.isEmpty()){
        	throw new Exception("Email cannot be empty");
        }
        if (!email.matches(EMAIL_REGEX)) {
            throw new Exception(
                "Invalid email format. Example: name@gmail.com"
            );
        }
    }
}
