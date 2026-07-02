package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/vehicle_rental_system";

    private static final String USER = "root";
    private static final String PASSWORD = "root"; // Change if needed

    private static Connection connection;

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);

                System.out.println("======================================");
                System.out.println(" Database Connected Successfully");
                System.out.println("======================================");

            }

        } catch (ClassNotFoundException e) {

            System.out.println("MySQL Driver Not Found");
            e.printStackTrace();

        } catch (SQLException e) {

            System.out.println("Database Connection Failed");
            e.printStackTrace();

        }

        return connection;
    }

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();

                System.out.println("Database Connection Closed");

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }

}