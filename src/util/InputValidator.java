package util;

public class InputValidator {

    public static boolean isValidEmail(String email) {

        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    }

    public static boolean isValidPhone(String phone) {

        return phone.matches("^[6-9]\\d{9}$");

    }

    public static boolean isValidPassword(String password) {

        return password.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$");

    }

}