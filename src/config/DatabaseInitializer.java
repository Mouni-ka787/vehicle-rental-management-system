
package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initializeDatabase() {

        try {

            Connection con = DBConnection.getConnection();

            Statement st = con.createStatement();

            String usersTable = """
                    CREATE TABLE IF NOT EXISTS users(
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    phone VARCHAR(15) UNIQUE,
                    email VARCHAR(100) UNIQUE,
                    password VARCHAR(255),
                    role VARCHAR(20),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;

            st.execute(usersTable);

            System.out.println("Users Table Ready");

            String vehicleTable = """
                    CREATE TABLE IF NOT EXISTS vehicles(
                    vehicle_id INT PRIMARY KEY AUTO_INCREMENT,
                    vehicle_name VARCHAR(100),
                    brand VARCHAR(100),
                    vehicle_type VARCHAR(50),
                    rent_per_day DOUBLE,
                    available BOOLEAN DEFAULT TRUE,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """;

            st.execute(vehicleTable);

            System.out.println("Vehicles Table Ready");

            String bookingTable = """
                    CREATE TABLE IF NOT EXISTS bookings(
                    booking_id INT PRIMARY KEY AUTO_INCREMENT,
                    user_id INT,
                    vehicle_id INT,
                    booking_date DATE,
                    return_date DATE,
                    total_amount DOUBLE,
                    status VARCHAR(30),
                    FOREIGN KEY(user_id) REFERENCES users(id),
                    FOREIGN KEY(vehicle_id) REFERENCES vehicles(vehicle_id)
                    )
                    """;

            st.execute(bookingTable);

            System.out.println("Bookings Table Ready");

//            String admin = """
//                    INSERT IGNORE INTO users
//                    (id,name,phone,email,password,role)
//                    VALUES
//                    (1,'Admin',
//                    '9999999999',
//                    'admin@gmail.com',
//                    'admin123',
//                    'ADMIN')
//                    """;
//
//            PreparedStatement ps = con.prepareStatement(admin);
//
//            ps.executeUpdate();
//
//            System.out.println("Default Admin Ready");
            // LOGIN LOGS TABLE
            String logsTable = """
                    CREATE TABLE IF NOT EXISTS login_logs(
                        log_id INT PRIMARY KEY AUTO_INCREMENT,
                        user_id INT,
                        login_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        logout_time TIMESTAMP NULL,
                        status VARCHAR(20),
                        FOREIGN KEY(user_id) REFERENCES users(id)
                    )
                    """;

            st.execute(logsTable);
            System.out.println("Login Logs table ready.");

            st.close();

            System.out.println("\nDatabase Initialization Completed Successfully.");

        }

        catch (SQLException e) {

            e.printStackTrace();

        }

    }

}