package ru.job4j.early;

public class PasswordValidator {

    public static String validate(String password) {
        String[] invalidPasswords = new String[] {"qwerty", "12345", "password", "admin", "user"};
        boolean isUpper = false;
        boolean isLower = false;
        boolean isDigit = false;
        boolean isSpecial = false;

        if (password == null) {
            throw new IllegalArgumentException("Password can't be null");
        }
        if (password.length() < 8 || password.length() > 32) {
            throw new IllegalArgumentException("Password should be length [8, 32]");
        }
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                isLower = true;
            }
            if (Character.isUpperCase(c)) {
                isUpper = true;
            }
            if (Character.isDigit(c)) {
                isDigit = true;
            }
            if (!Character.isDigit(c) && !Character.isLetter(c)) {
                isSpecial = true;
            }
        }
        if (!isUpper) {
            throw new IllegalArgumentException("Password should contain at least one uppercase letter");
        }
        if (!isLower) {
            throw new IllegalArgumentException("Password should contain at least one lowercase letter");
        }
        if (!isDigit) {
            throw new IllegalArgumentException("Password should contain at least one figure");
        }
        if (!isSpecial) {
            throw new IllegalArgumentException("Password should contain at least one special symbol");
        }
        for (String invalidPassword : invalidPasswords) {
            if (password.toLowerCase().contains(invalidPassword)) {
                throw new IllegalArgumentException(
                        "Password shouldn't contain substrings: qwerty, 12345, password, admin, user"
                );
            }
        }
        return password;
    }
}