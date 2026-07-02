package services;

import java.util.Scanner;

import dao.UserDAO;
import model.User;
import util.InputValidator;
import util.PasswordUtil;


public class AuthService {

    private final UserDAO userDAO = new UserDAO();

    // ============================
    // REGISTER USER
    // ============================

    public void registerUser(Scanner sc) {

        System.out.println("\n========== USER REGISTRATION ==========");

        String role = selectRole(sc);

        System.out.print("Enter Name : ");
        String name = sc.nextLine();

        // PHONE
        String phone;

        while (true) {

            System.out.print("Enter Phone : ");
            phone = sc.nextLine();

            if (!InputValidator.isValidPhone(phone)) {

                System.out.println("Invalid Phone Number.");
                continue;

            }

            if (userDAO.phoneExists(phone)) {

                System.out.println("Phone already registered.");
                continue;

            }

            break;

        }

        // EMAIL

        String email;

        while (true) {

            System.out.print("Enter Email : ");
            email = sc.nextLine();

            if (!InputValidator.isValidEmail(email)) {

                System.out.println("Invalid Email.");
                continue;

            }

            if (userDAO.emailExists(email)) {

                System.out.println("Email already registered.");
                continue;

            }

            break;

        }

        // PASSWORD

        String password;

        while (true) {

            System.out.print("Enter Password : ");
            password = sc.nextLine();

            System.out.print("Confirm Password : ");
            String confirm = sc.nextLine();

            if (!password.equals(confirm)) {

                System.out.println("Passwords do not match.");
                continue;

            }

            if (!InputValidator.isValidPassword(password)) {

                System.out.println("Password should be minimum 6 characters.");
                continue;

            }

            break;

        }

        // OTP

        int otp = OTPService.generateOTP();

        System.out.println("\nGenerated OTP : " + otp);

        System.out.print("Enter OTP : ");

        int enteredOTP = Integer.parseInt(sc.nextLine());

        if (!OTPService.verifyOTP(enteredOTP)) {

            System.out.println("Incorrect OTP.");

            return;

        }

        User user = new User();

        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setPassword(PasswordUtil.hashPassword(password));
        user.setRole(role);

        boolean saved = userDAO.saveUser(user);

        if (saved) {

            System.out.println("\nRegistration Successful.");

        } else {

            System.out.println("\nRegistration Failed.");

        }

    }

    // ============================
    // LOGIN
    // ============================

    public User loginUser(Scanner sc) {

        System.out.println("\n========== LOGIN ==========");

        System.out.print("Enter Email : ");
        String email = sc.nextLine();

        System.out.print("Enter Password : ");
        String password = sc.nextLine();

        password = PasswordUtil.hashPassword(password);

        User user = userDAO.login(email, password);

        if (user == null) {

            System.out.println("Invalid Email or Password.");

            return null;

        }

        int otp = OTPService.generateOTP();

        System.out.println("\nGenerated OTP : " + otp);

        System.out.print("Enter OTP : ");

        int enteredOTP = Integer.parseInt(sc.nextLine());

        if (!OTPService.verifyOTP(enteredOTP)) {

            System.out.println("Incorrect OTP.");

            return null;

        }

        System.out.println("\nLogin Successful.");

        return user;

    }

    // ============================
    // ROLE
    // ============================

    private String selectRole(Scanner sc) {

        while (true) {

            System.out.println();

            System.out.println("1. Admin");
            System.out.println("2. Customer");

            System.out.print("Select Role : ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    return "ADMIN";

                case "2":
                    return "CUSTOMER";

                default:
                    System.out.println("Invalid Choice.");

            }

        }

    }

}
