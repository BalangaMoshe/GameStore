package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Game {
    private int id;
    private String name;
    private LocalDate releaseDate;
    private double rating;
    private double cost;
    private String description;
    private LocalDate creationDate;

    public Game(int id, String name, LocalDate releaseDate, double rating, double cost, String description, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.cost = cost;
        this.description = description;
        this.creationDate = LocalDate.from(creationDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", releaseDate=" + releaseDate +
                ", rating=" + rating +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
