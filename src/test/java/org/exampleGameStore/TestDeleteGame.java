package org.exampleGameStore;

import org.example.DatabaseManager;
import org.example.Game;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TestDeleteGame extends BaseTest {


    @Test
    public void testDeleteGame() {
        Game testGame = new Game(0, "Test Game", LocalDate.now(),
                85.0, 19.99, "Test опис", LocalDate.now().atStartOfDay());
        gameStoreApp.addGame(testGame);

        int gameId = getGameIdByName("Test Game");
        assertNotNull(getGameById(gameId));

        gameStoreApp.deleteGame(gameId);

        assertNull(getGameById(gameId));
    }

    private int getGameIdByName(String name) {
        String sql = "SELECT id FROM games WHERE name = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private Game getGameById(int id) {
        String sql = "SELECT * FROM games WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
                double rating = rs.getDouble("rating");
                double cost = rs.getDouble("cost");
                String description = rs.getString("description");
                LocalDateTime creationDate = rs.getTimestamp("creation_date").toLocalDateTime();
                return new Game(id, name, releaseDate, rating, cost, description, creationDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
