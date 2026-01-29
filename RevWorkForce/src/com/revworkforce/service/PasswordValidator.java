package com.revworkforce.service;

public class PasswordValidator {

    public static void validate(String pwd) throws Exception {

        if (pwd.length() < 8)
            throw new Exception("Password must be at least 8 characters");

        if (!pwd.matches(".*[A-Z].*"))
            throw new Exception("Password must contain uppercase letter");

        if (!pwd.matches(".*[a-z].*"))
            throw new Exception("Password must contain lowercase letter");

        if (!pwd.matches(".*[0-9].*"))
            throw new Exception("Password must contain number");

        if (!pwd.matches(".*[@#$%!].*"))
            throw new Exception("Password must contain special character");
    }
}
