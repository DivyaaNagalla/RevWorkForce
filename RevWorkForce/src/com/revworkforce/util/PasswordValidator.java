package com.revworkforce.util;

public class PasswordValidator {

    public static void validate(String password) throws Exception {

        if (password == null || password.length() < 8)
            throw new Exception("Password must be at least 8 characters");

        boolean upper = false;
        boolean lower = false;
        boolean digit = false;
        boolean special = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) upper = true;
            else if (Character.isLowerCase(ch)) lower = true;
            else if (Character.isDigit(ch)) digit = true;
            else special = true;
        }

        if (!upper || !lower || !digit || !special)
            throw new Exception(
                "Password must contain Uppercase, Lowercase, Digit & Special character");
    }
}
