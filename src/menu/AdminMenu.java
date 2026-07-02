package menu;

import java.util.Scanner;

import dao.VehicleDAO;
import model.Vehicle;

public class AdminMenu {

    private Scanner sc;
    private VehicleDAO vehicleDAO;

    public AdminMenu(Scanner sc) {
        this.sc = sc;
        this.vehicleDAO = new VehicleDAO();
    }

    public void start() {

        while (true) {

            System.out.println("\n==================================");
            System.out.println("         ADMIN PANEL");
            System.out.println("==================================");
            System.out.println("1. Add Vehicle");
            System.out.println("2. View Vehicles");
            System.out.println("3. Search Vehicle");
            System.out.println("4. Update Vehicle");
            System.out.println("5. Delete Vehicle");
            System.out.println("6. Logout");
            System.out.print("Enter Choice : ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    addVehicle();
                    break;

                case 2:
                    vehicleDAO.viewVehicles();
                    break;

                case 3:
                    searchVehicle();
                    break;

                case 4:
                    updateVehicle();
                    break;

                case 5:
                    deleteVehicle();
                    break;

                case 6:
                    System.out.println("Logging out...");
                    return;

                default:
                    System.out.println("Invalid Choice!");

            }

        }

    }

    // ===========================
    // ADD VEHICLE
    // ===========================

    private void addVehicle() {

        Vehicle vehicle = new Vehicle();

        System.out.print("Vehicle Name : ");
        vehicle.setVehicleName(sc.nextLine());

        System.out.print("Brand : ");
        vehicle.setBrand(sc.nextLine());

        System.out.print("Vehicle Type : ");
        vehicle.setVehicleType(sc.nextLine());

        System.out.print("Rent Per Day : ");
        vehicle.setRentPerDay(Double.parseDouble(sc.nextLine()));

        vehicle.setAvailable(true);

        if (vehicleDAO.addVehicle(vehicle)) {

            System.out.println("Vehicle Added Successfully.");

        } else {

            System.out.println("Failed to Add Vehicle.");

        }

    }

    // ===========================
    // SEARCH VEHICLE
    // ===========================

    private void searchVehicle() {

        System.out.print("Enter Vehicle ID : ");

        int id = Integer.parseInt(sc.nextLine());

        Vehicle vehicle = vehicleDAO.searchVehicleById(id);

        if (vehicle == null) {

            System.out.println("Vehicle Not Found.");

            return;

        }

        System.out.println("\nVehicle Details");

        System.out.println("ID : " + vehicle.getVehicleId());
        System.out.println("Name : " + vehicle.getVehicleName());
        System.out.println("Brand : " + vehicle.getBrand());
        System.out.println("Type : " + vehicle.getVehicleType());
        System.out.println("Rent : " + vehicle.getRentPerDay());
        System.out.println("Available : " + vehicle.isAvailable());

    }

    // ===========================
    // UPDATE VEHICLE
    // ===========================

    private void updateVehicle() {

        System.out.print("Enter Vehicle ID : ");

        int id = Integer.parseInt(sc.nextLine());

        Vehicle vehicle = vehicleDAO.searchVehicleById(id);

        if (vehicle == null) {

            System.out.println("Vehicle Not Found.");

            return;

        }

        vehicle.setVehicleId(id);

        System.out.print("New Vehicle Name : ");
        vehicle.setVehicleName(sc.nextLine());

        System.out.print("New Brand : ");
        vehicle.setBrand(sc.nextLine());

        System.out.print("New Vehicle Type : ");
        vehicle.setVehicleType(sc.nextLine());

        System.out.print("New Rent Per Day : ");
        vehicle.setRentPerDay(Double.parseDouble(sc.nextLine()));

        vehicle.setAvailable(true);

        if (vehicleDAO.updateVehicle(vehicle)) {

            System.out.println("Vehicle Updated Successfully.");

        } else {

            System.out.println("Update Failed.");

        }

    }

    // ===========================
    // DELETE VEHICLE
    // ===========================

    private void deleteVehicle() {

        System.out.print("Enter Vehicle ID : ");

        int id = Integer.parseInt(sc.nextLine());

        if (vehicleDAO.deleteVehicle(id)) {

            System.out.println("Vehicle Deleted Successfully.");

        } else {

            System.out.println("Vehicle Not Found.");

        }

    }

}