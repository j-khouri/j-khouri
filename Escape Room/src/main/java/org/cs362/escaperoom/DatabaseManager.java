package org.cs362.escaperoom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String DATABASE_URL = "jdbc:mysql://database-1.ctce6aus49ji.us-east-2.rds.amazonaws.com:3306/EscapeDatabase";
    private static final String DATABASE_USER = "admin";
    private static final String DATABASE_PASSWORD = "CS362_FinalProject";

    public static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS leaderboard (" +
                     "id INT AUTO_INCREMENT PRIMARY KEY," +
                     "name VARCHAR(255) NOT NULL," +
                     "time INT NOT NULL)")) {
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPlayerData(String name, long time) {
        String insertSQL = "INSERT INTO leaderboard(name, time) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setLong(2, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printLeaderboard() {
        String selectSQL = "SELECT name, time FROM leaderboard ORDER BY time ASC";
        try (Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(selectSQL);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Leaderboard:");
            while (rs.next()) {
                System.out.println(rs.getString("name") + ": " + rs.getLong("time") + " seconds");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}