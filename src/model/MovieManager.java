package model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class MovieManager {
    private List<Movie> movies;

    public MovieManager(String filePath) throws IOException {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Movie>>() {}.getType();
        movies = gson.fromJson(new FileReader(filePath), listType);
    }

    // Показать все фильмы
    public void showAllMovies() {
        System.out.printf("%-42s | %-6s | %-20s | %-50s%n", "Название", "Год", "Режиссёр", "Актёры");
        System.out.println("=".repeat(166)); // Разделитель таблицы

        for (Movie movie : movies) {
            String actors = movie.getCast().stream()
                    .map(Actor::getFullName)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("Нет актёров");

            System.out.printf("%-42s | %-6d | %-20s | %-50s%n",
                    movie.getName(),
                    movie.getYear(),
                    movie.getDirector().getFullName(),
                    actors);
        }
    }


    // Поиск по названию фильма
    public List<Movie> searchByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Поиск по актёру
    public List<Movie> searchByActor(String actorName) {
        return movies.stream()
                .filter(movie -> movie.getCast().stream()
                        .anyMatch(actor -> actor.getFullName().equalsIgnoreCase(actorName)))
                .collect(Collectors.toList());
    }

    // Поиск по режиссёру
    public List<Movie> searchByDirector(String directorName) {
        return movies.stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
    }

    // Поиск по году
    public List<Movie> searchByYear(int year) {
        return movies.stream()
                .filter(movie -> movie.getYear() == year)
                .collect(Collectors.toList());
    }

    // Получить всех актёров (уникальных и отсортированных)
    public void showUniqueActors() {
        movies.stream()
                .flatMap(movie -> movie.getCast().stream())
                .map(Actor::getFullName)
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }

    // Сортировка фильмов
    public void sortMovies(Comparator<Movie> comparator) {
        movies.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
