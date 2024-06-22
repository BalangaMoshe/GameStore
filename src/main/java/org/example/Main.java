package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static GameStoreApp gameStoreApp = new GameStoreApp();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseManager.initialisaitionDatabase();
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewGame();
                    break;
                case 2:
                    deleteGame();
                    break;
                case 3:
                    findGameByName();
                    break;
                case 4:
                    filterGamesByCost();
                    break;
                case 5:
                    break;
                case 6:
                    showGamesSortedByCreationDate();
                    break;
                case 7:
                    showAllGames();
                    break;
                case 8:
                    System.out.println("Вихід...");
                    return;
                default:
                    System.out.println("Невідома команда. Спробуйте ще раз.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Додати нову гру");
        System.out.println("2. Видалити гру");
        System.out.println("3. Пошук гри за назвою");
        System.out.println("4. Фільтрація ігор за ціною");
        System.out.println("5. Фільтрація ігор за типом (не реалізовано)");
        System.out.println("6. Показ всіх ігор, відсортованих за датою додавання");
        System.out.println("7. Перегляд списку всіх доступних ігор");
        System.out.println("8. Вихід");
        System.out.print("Виберіть опцію: ");
    }

    private static void addNewGame() {
        System.out.print("Назва: ");
        String name = scanner.nextLine();

        System.out.print("Дата випуску (yyyy-mm-dd HH:mm:ss): ");
        String releaseDateTimeStr = scanner.nextLine();
        LocalDateTime releaseDateTime = null;
        try {
            releaseDateTime = LocalDateTime.parse(releaseDateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            System.out.println("Неправильний формат дати. Використовуйте формат yyyy-MM-dd HH:mm:ss.");
            return;
        }

        System.out.print("Рейтинг: ");
        double rating = 0;
        while (!scanner.hasNextDouble()) {
            System.out.println("Будь ласка, введіть дійсне число для рейтингу.");
            scanner.next();
        }
        rating = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Вартість: ");
        double cost = 0;
        while (!scanner.hasNextDouble()) {
            System.out.println("Будь ласка, введіть дійсне число для вартості.");
            scanner.next();
        }
        cost = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Опис: ");
        String description = scanner.nextLine();

        LocalDateTime creationDateTime = LocalDateTime.now();
        Game game = new Game(0, name, releaseDateTime.toLocalDate(), rating, cost, description, creationDateTime);
        gameStoreApp.addGame(game);
        System.out.println("Гра додана.");
    }


    private static void deleteGame() {
        System.out.print("ID гри для видалення: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        gameStoreApp.deleteGame(id);
        System.out.println("Гра видалена.");
    }

    private static void findGameByName() {
        System.out.print("Назва гри для пошуку: ");
        String name = scanner.nextLine();
        List<Game> games = gameStoreApp.findGameByName(name);
        for (Game game : games) {
            System.out.println(game);
        }
    }

    private static void filterGamesByCost() {
        System.out.print("Мінімальна вартість: ");
        double minCost = scanner.nextDouble();
        System.out.print("Максимальна вартість: ");
        double maxCost = scanner.nextDouble();
        scanner.nextLine();
        List<Game> games = gameStoreApp.filterGamesByCost(minCost, maxCost);
        for (Game game : games) {
            System.out.println(game);
        }
    }

    private static void showGamesSortedByCreationDate() {
        List<Game> games = gameStoreApp.getAllGames();
        for (Game game : games) {
            System.out.println(game);
        }
    }

    private static void showAllGames() {
        List<Game> games = gameStoreApp.getAllGames();
        for (Game game : games) {
            System.out.println(game);
        }
    }
}
