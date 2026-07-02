package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnection;

public class BookingDAO {

    // ==========================
    // RENT VEHICLE
    // ==========================

    public boolean rentVehicle(int userId, int vehicleId) {

        String sql = """
                INSERT INTO bookings(user_id,vehicle_id,status)
                VALUES(?,?,?)
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);
            ps.setInt(2, vehicleId);
            ps.setString(3, "BOOKED");

            int rows = ps.executeUpdate();

            if (rows > 0) {

                updateVehicleAvailability(vehicleId, false);

                return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================
    // RETURN VEHICLE
    // ==========================

    public boolean returnVehicle(int bookingId, int vehicleId) {

        String sql = """
                UPDATE bookings
                SET status='RETURNED'
                WHERE booking_id=?
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                updateVehicleAvailability(vehicleId, true);

                return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==========================
    // BOOKING HISTORY
    // ==========================

    public void viewBookingHistory(int userId) {

        String sql = """
                SELECT *
                FROM bookings
                WHERE user_id=?
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== BOOKING HISTORY ==========");

            while (rs.next()) {

                System.out.println("----------------------------------");

                System.out.println("Booking ID : " + rs.getInt("booking_id"));
                System.out.println("Vehicle ID : " + rs.getInt("vehicle_id"));
                System.out.println("Status     : " + rs.getString("status"));

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // UPDATE VEHICLE STATUS
    // ==========================

    private void updateVehicleAvailability(int vehicleId, boolean available) {

        String sql = """
                UPDATE vehicles
                SET available=?
                WHERE vehicle_id=?
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, available);
            ps.setInt(2, vehicleId);

            ps.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}