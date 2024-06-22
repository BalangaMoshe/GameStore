package org.exampleGameStore;

import org.example.Game;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGetAllGames extends BaseTest {

    @Test
    public void testGetAllGames() {
        Game game1 = new Game(0, "Test Game 1", LocalDate.now(), 85.0, 19.99, "Опис 1", LocalDate.now().atStartOfDay());
        Game game2 = new Game(0, "Test Game 2", LocalDate.now(), 90.0, 29.99, "Опис 2", LocalDate.now().atStartOfDay());
        Game game3 = new Game(0, "Test Game 3", LocalDate.now(), 80.0, 15.99, "Опис 3", LocalDate.now().atStartOfDay());

        gameStoreApp.addGame(game1);
        gameStoreApp.addGame(game2);
        gameStoreApp.addGame(game3);

        List<Game> games = gameStoreApp.getAllGames();

        assertEquals(3, games.size(), "Очікується три гри в списку");

        assertTrue(games.stream().anyMatch(g -> g.getName().equals("Test Game 1")), "Гра 1 має бути в списку");
        assertTrue(games.stream().anyMatch(g -> g.getName().equals("Test Game 2")), "Гра 2 має бути в списку");
        assertTrue(games.stream().anyMatch(g -> g.getName().equals("Test Game 3")), "Гра 3 має бути в списку");
    }

}
