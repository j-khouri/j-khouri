package org.cs362.escaperoom;

public class DatabaseManagerTest {
    public static void main(String[] args) {
        // Initialize the database and create the leaderboard table
        DatabaseManager.initializeDatabase();
        System.out.println("Database initialized.");

        // Insert sample player data
        DatabaseManager.insertPlayerData("Alice", 120);
        DatabaseManager.insertPlayerData("Bob", 150);
        DatabaseManager.insertPlayerData("Charlie", 90);
        System.out.println("Sample player data inserted.");

        // Print the leaderboard to verify data insertion and retrieval
        DatabaseManager.printLeaderboard();
    }
}