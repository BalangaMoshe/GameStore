package org.exampleGameStore;

import org.example.Game;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAddGame extends BaseTest {

    @Test
    public void testAddGame() {
        int initialSize = gameStoreApp.getAllGames().size();

        Game game = new Game(0, "Test game", LocalDate.now(),
                85.0, 19.99, "Тест опис", LocalDate.now().atStartOfDay());
        gameStoreApp.addGame(game);

        int newSize = gameStoreApp.getAllGames().size();
        assertEquals(initialSize + 1, newSize, "Гра не була успішно додана...((");
    }
}
