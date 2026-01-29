package com.revworkforce.util;

import java.util.Random;

public class PasswordGenerator {

    private static final String CHARS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";

    public static String generateTempPassword() {

        Random r = new Random();
        StringBuilder pwd = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            pwd.append(CHARS.charAt(r.nextInt(CHARS.length())));
        }
        return pwd.toString();
    }
}
