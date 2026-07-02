package menu;

import java.util.Scanner;

import model.User;
import services.AuthService;


public class MainMenu {

    private Scanner sc = new Scanner(System.in);
    private AuthService authService = new AuthService();

    public void start() {

        while (true) {

            System.out.println("\n====================================");
            System.out.println(" VEHICLE RENTAL MANAGEMENT SYSTEM ");
            System.out.println("====================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            String choice = sc.nextLine();

            switch (choice) {

            case "1":
                authService.registerUser(sc);
                break;

            case "2":

                User user = authService.loginUser(sc);

                if (user != null) {

                    if (user.getRole().equalsIgnoreCase("ADMIN")) {

                        System.out.println("\n================================");
                        System.out.println(" Welcome Admin : " + user.getName());
                        System.out.println("================================");

                        AdminMenu adminMenu = new AdminMenu(sc);
                        adminMenu.start();

                    } else {

                        System.out.println("\n================================");
                        System.out.println(" Welcome Customer : " + user.getName());
                        System.out.println("================================");

                        System.out.println("Customer Panel Coming in Milestone 5...");
                    }

                }

                break;

            case "3":

                System.out.println("\nThank you for using Vehicle Rental System.");
                sc.close();
                return;

            default:

                System.out.println("Invalid Choice. Try Again.");

            }

        }

    }

}