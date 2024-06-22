package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GameStoreApp {

    private List<Game> games = new ArrayList<>();

    public void addGame(Game game) {
        String sql = "INSERT INTO games (name, release_date, rating, cost, description, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, game.getName());
            pstmt.setDate(2, Date.valueOf(game.getReleaseDate()));
            pstmt.setDouble(3, game.getRating());
            pstmt.setDouble(4, game.getCost());
            pstmt.setString(5, game.getDescription());
            pstmt.setTimestamp(6, Timestamp.valueOf(LocalDateTime.now()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteGame(int id) {
        String sql = "DELETE FROM Games WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();


            games.removeIf(game -> game.getId() == id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Game> findGameByName(String name) {
        String sql = "SELECT * FROM games WHERE name LIKE ?";
        List<Game> games = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String gameName = rs.getString("name");
                LocalDate releaseDate = rs.getDate("release_date").toLocalDate();
                double rating = rs.getDouble("rating");
                double cost = rs.getDouble("cost");
                String description = rs.getString("description");
                LocalDate creationDate = rs.getDate("creation_date").toLocalDate();

                Game game = new Game(id, gameName, releaseDate, rating, cost, description, creationDate.atStartOfDay());
                games.add(game);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public List<Game> filterGamesByCost(double minCost, double maxCost) {
        String sql = "SELECT * FROM Games WHERE cost BETWEEN ? AND ?";
        List<Game> games = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, minCost);
            pstmt.setDouble(2, maxCost);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                games.add(new Game(rs.getInt("id"), rs.getString("name"), rs.getDate("release_date").toLocalDate(),
                        rs.getDouble("rating"), rs.getDouble("cost"), rs.getString("description"),
                        rs.getDate("creation_date").toLocalDate().atStartOfDay()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }

    public List<Game> getAllGames() {
        String sql = "SELECT * FROM Games";
        List<Game> games = new ArrayList<>();
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                games.add(new Game(rs.getInt("id"), rs.getString("name"), rs.getDate("release_date").toLocalDate(),
                        rs.getDouble("rating"), rs.getDouble("cost"), rs.getString("description"),
                        rs.getDate("creation_date").toLocalDate().atStartOfDay()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
}
