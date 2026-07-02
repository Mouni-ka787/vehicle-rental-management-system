package menu;

import java.util.Scanner;
import model.Vehicle;
import dao.BookingDAO;
import dao.VehicleDAO;
import model.User;

public class UserMenu {

    private Scanner sc;
    private User user;

    private VehicleDAO vehicleDAO;
    private BookingDAO bookingDAO;

    public UserMenu(Scanner sc, User user) {

        this.sc = sc;
        this.user = user;

        vehicleDAO = new VehicleDAO();
        bookingDAO = new BookingDAO();

    }

    public void start() {

        while (true) {

            System.out.println("\n==================================");
            System.out.println("       CUSTOMER PANEL");
            System.out.println("==================================");
            System.out.println("1. View Available Vehicles");
            System.out.println("2. Rent Vehicle");
            System.out.println("3. Return Vehicle");
            System.out.println("4. Booking History");
            System.out.println("5. Logout");
            System.out.print("Enter Choice : ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

            case 1:

                vehicleDAO.viewAvailableVehicles();

                break;

            case 2:

                rentVehicle();

                break;

            case 3:

                returnVehicle();

                break;

            case 4:

                bookingDAO.viewBookingHistory(user.getId());

                break;

            case 5:

                System.out.println("Logged Out Successfully.");

                return;

            default:

                System.out.println("Invalid Choice.");

            }

        }

    }

    private void rentVehicle() {

        vehicleDAO.viewAvailableVehicles();

        System.out.print("\nEnter Vehicle ID : ");

        int vehicleId = Integer.parseInt(sc.nextLine());

        // Check whether vehicle exists
        Vehicle vehicle = vehicleDAO.searchVehicleById(vehicleId);

        if (vehicle == null) {

            System.out.println("Vehicle Not Found.");

            return;

        }

        // Check whether vehicle is available
        if (!vehicle.isAvailable()) {

            System.out.println("Vehicle Already Rented.");

            return;

        }

        // Rent vehicle
        if (bookingDAO.rentVehicle(user.getId(), vehicleId)) {

            System.out.println("Vehicle Booked Successfully.");

        } else {

            System.out.println("Booking Failed.");

        }

    }

    private void returnVehicle() {

        System.out.print("Enter Booking ID : ");

        int bookingId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter Vehicle ID : ");

        int vehicleId = Integer.parseInt(sc.nextLine());

        if (bookingDAO.returnVehicle(bookingId, vehicleId)) {

            System.out.println("Vehicle Returned Successfully.");

        } else {

            System.out.println("Return Failed.");

        }

    }

}