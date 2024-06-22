package org.exampleGameStore;

import org.example.Game;
import org.example.GameStoreApp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestFindGameByName extends BaseTest {
    private GameStoreApp gameStoreApp;

    @BeforeEach
    public void setUp() {
        gameStoreApp = new GameStoreApp();
        Game game1 = new Game(0, "Test Game 1", LocalDate.now(), 85.0, 19.99, "Test Descrip. 1", LocalDate.now().atStartOfDay());
        Game game2 = new Game(0, "Test Game 2", LocalDate.now(), 90.0, 29.99, "Test Descrip. 2", LocalDate.now().atStartOfDay());
        Game game3 = new Game(0, "Test Game 3", LocalDate.now(), 80.0, 15.99, "Test Descrip. 3", LocalDate.now().atStartOfDay());

        gameStoreApp.addGame(game1);
        gameStoreApp.addGame(game2);
        gameStoreApp.addGame(game3);
    }

    @Test
    public void testFindGameByName() {
        String findName = "Test Game 1";
        List<Game> foundGames = gameStoreApp.findGameByName(findName);

        assertNotNull(foundGames, "Список ігор не повинен бути нульовим");
        assertFalse(foundGames.isEmpty(), "Список ігор не повинен бути пустим.");

        boolean found = false;
        for (Game game : foundGames) {
            if (game.getName().equals(findName)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "гра знайдена за такою назвою -> " + findName);
    }

}
