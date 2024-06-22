package org.exampleGameStore;

import org.example.DatabaseManager;
import org.example.GameStoreApp;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseTest {
    protected GameStoreApp gameStoreApp;

    @BeforeEach
    public void setUp() {
        gameStoreApp = new GameStoreApp(); // робим зачистку
        clearDatabase();
    }

    private void clearDatabase() {
        String sql = "DELETE FROM Games";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}