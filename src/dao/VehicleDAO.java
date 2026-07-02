package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnection;
import model.Vehicle;

public class VehicleDAO {

    // ==========================
    // ADD VEHICLE
    // ==========================

    public boolean addVehicle(Vehicle vehicle) {

        String sql = """
                INSERT INTO vehicles
                (vehicle_name,brand,vehicle_type,rent_per_day,available)
                VALUES(?,?,?,?,?)
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, vehicle.getVehicleName());
            ps.setString(2, vehicle.getBrand());
            ps.setString(3, vehicle.getVehicleType());
            ps.setDouble(4, vehicle.getRentPerDay());
            ps.setBoolean(5, vehicle.isAvailable());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================
    // VIEW ALL VEHICLES
    // ==========================

    public void viewVehicles() {

        String sql = "SELECT * FROM vehicles";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n================ VEHICLES ================");

            while (rs.next()) {

                System.out.println("----------------------------------------");

                System.out.println("Vehicle ID     : " + rs.getInt("vehicle_id"));
                System.out.println("Vehicle Name   : " + rs.getString("vehicle_name"));
                System.out.println("Brand          : " + rs.getString("brand"));
                System.out.println("Type           : " + rs.getString("vehicle_type"));
                System.out.println("Rent Per Day   : " + rs.getDouble("rent_per_day"));
                System.out.println("Available      : " + rs.getBoolean("available"));

            }

            System.out.println("----------------------------------------");

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // SEARCH VEHICLE
    // ==========================

    public Vehicle searchVehicleById(int vehicleId) {

        String sql = "SELECT * FROM vehicles WHERE vehicle_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, vehicleId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return new Vehicle(

                        rs.getInt("vehicle_id"),

                        rs.getString("vehicle_name"),

                        rs.getString("brand"),

                        rs.getString("vehicle_type"),

                        rs.getDouble("rent_per_day"),

                        rs.getBoolean("available"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

    // ==========================
    // UPDATE VEHICLE
    // ==========================

    public boolean updateVehicle(Vehicle vehicle) {

        String sql = """
                UPDATE vehicles
                SET vehicle_name=?,
                    brand=?,
                    vehicle_type=?,
                    rent_per_day=?,
                    available=?
                WHERE vehicle_id=?
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, vehicle.getVehicleName());
            ps.setString(2, vehicle.getBrand());
            ps.setString(3, vehicle.getVehicleType());
            ps.setDouble(4, vehicle.getRentPerDay());
            ps.setBoolean(5, vehicle.isAvailable());
            ps.setInt(6, vehicle.getVehicleId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================
    // DELETE VEHICLE
    // ==========================

    public boolean deleteVehicle(int vehicleId) {

        String sql = "DELETE FROM vehicles WHERE vehicle_id=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, vehicleId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

}