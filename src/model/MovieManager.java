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

    public void showAllMovies() {
        System.out.printf("%-42s | %-6s | %-20s | %-50s%n", "Название", "Год", "Режиссёр", "Актёры");

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

    public List<Movie> searchByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getName().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByActor(String actorName) {
        return movies.stream()
                .filter(movie -> movie.getCast().stream()
                        .anyMatch(actor -> actor.getFullName().equalsIgnoreCase(actorName)))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByDirector(String directorName) {
        return movies.stream()
                .filter(movie -> movie.getDirector().getFullName().equalsIgnoreCase(directorName))
                .collect(Collectors.toList());
    }

    public List<Movie> searchByYear(int year) {
        return movies.stream()
                .filter(movie -> movie.getYear() == year)
                .collect(Collectors.toList());
    }

    public void showUniqueActors() {
        movies.stream()
                .flatMap(movie -> movie.getCast().stream()
                        .map(actor -> actor.getFullName() + " (" + actor.getRole() + " in \"" + movie.getName() + "\")"))
                .distinct()
                .sorted()
                .forEach(System.out::println);
    }


    public void sortMovies(Comparator<Movie> comparator) {
        movies.stream()
                .sorted(comparator)
                .forEach(System.out::println);
    }
}
