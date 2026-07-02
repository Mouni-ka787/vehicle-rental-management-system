package dao;

import config.DBConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // ==============================
    // Register User
    // ==============================

    public boolean saveUser(User user) {

        String sql = """
                INSERT INTO users
                (name,phone,email,password,role)
                VALUES(?,?,?,?,?)
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getRole());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==============================
    // Check Email
    // ==============================

    public boolean emailExists(String email) {

        String sql = "SELECT * FROM users WHERE email=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==============================
    // Check Phone
    // ==============================

    public boolean phoneExists(String phone) {

        String sql = "SELECT * FROM users WHERE phone=?";

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, phone);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return false;

    }

    // ==============================
    // Login
    // ==============================

    public User login(String email, String password) {

        String sql = """
                SELECT *
                FROM users
                WHERE email=?
                AND password=?
                """;

        try {

            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPhone(rs.getString("phone"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("role"));

                return user;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return null;

    }

}