package org.exampleGameStore;

import org.example.Game;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSortGamesByCost extends BaseTest {

    @Test
    public void testSortGamesByCost() {
        Game game1 = new Game(0, "Game 1", LocalDate.now(), 80.0, 19.99, "Description 1", LocalDate.now().atStartOfDay());
        Game game2 = new Game(0, "Game 2", LocalDate.now(), 90.0, 29.99, "Description 2", LocalDate.now().atStartOfDay());
        Game game3 = new Game(0, "Game 3", LocalDate.now(), 85.0, 15.99, "Description 3", LocalDate.now().atStartOfDay());

        gameStoreApp.addGame(game1);
        gameStoreApp.addGame(game2);
        gameStoreApp.addGame(game3);

        List<Game> sortedGames = gameStoreApp.getAllGames();
        Collections.sort(sortedGames, (g1, g2) -> Double.compare(g1.getCost(), g2.getCost()));

        // Перевірка сортування за ціною
        assertEquals(15.99, sortedGames.get(0).getCost(), "Перша гра повинна мати вартість 15.99");
        assertEquals(19.99, sortedGames.get(1).getCost(), "Друга гра повинна мати вартість 19.99");
        assertEquals(29.99, sortedGames.get(2).getCost(), "Третя гра повинна мати вартість 29.99");
    }
}
