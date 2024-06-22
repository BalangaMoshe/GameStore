package org.example;

import java.sql.*;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    public static final String PASSWORT = "Kovalets1993@";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORT);
    }

    public static void initialisaitionDatabase() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Games (" +
                    "id SERIAL PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "release_date DATE," +
                    "rating REAL," +
                    "cost REAL," +
                    "description TEXT," +
                    "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
