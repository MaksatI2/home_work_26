import model.MovieManager;
import model.Movie;

import java.io.IOException;
import java.util.Comparator;
import java.util.Scanner;

public class MovieApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            MovieManager manager = new MovieManager("src/data/movies.json");

            while (true) {
                System.out.println("\nВыберите действие:");
                System.out.println("1. Показать все фильмы");
                System.out.println("2. Найти фильмы по названию");
                System.out.println("3. Найти фильмы по актёру");
                System.out.println("4. Найти фильмы по режиссёру");
                System.out.println("5. Найти фильмы по году");
                System.out.println("6. Показать список актёров");
                System.out.println("7. Сортировать фильмы");
                System.out.println("8. Выйти");

                int choice;
                while (true) {
                    System.out.println("\nВведите номер действия:");
                    if (scanner.hasNextInt()) {
                        choice = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Ошибка! Пожалуйста, введите корректное число.");
                        scanner.nextLine();
                    }
                }

                switch (choice) {
                    case 1 -> manager.showAllMovies();
                    case 2 -> {
                        System.out.println("Введите название фильма:");
                        String title = scanner.nextLine();
                        manager.searchByTitle(title).forEach(System.out::println);
                    }
                    case 3 -> {
                        System.out.println("Введите имя актёра:");
                        String actor = scanner.nextLine();
                        manager.searchByActor(actor).forEach(System.out::println);
                    }
                    case 4 -> {
                        System.out.println("Введите имя режиссёра:");
                        String director = scanner.nextLine();
                        manager.searchByDirector(director).forEach(System.out::println);
                    }
                    case 5 -> {
                        System.out.println("Введите год:");
                        int year = scanner.nextInt();
                        manager.searchByYear(year).forEach(System.out::println);
                    }
                    case 6 -> manager.showUniqueActors();
                    case 7 -> {
                        System.out.println("Сортировать по: 1 - Году, 2 - Названию, 3 - Режиссёру");
                        int sortChoice;
                        while (true) {
                            if (scanner.hasNextInt()) {
                                sortChoice = scanner.nextInt();
                                scanner.nextLine();
                                break;
                            } else {
                                System.out.println("Ошибка! Введите корректное число.");
                                scanner.nextLine();
                            }
                        }

                        if (sortChoice == 1) manager.sortMovies(Comparator.comparing(Movie::getYear));
                        else if (sortChoice == 2) manager.sortMovies(Comparator.comparing(Movie::getName));
                        else if (sortChoice == 3)
                            manager.sortMovies(Comparator.comparing(m -> m.getDirector().getFullName()));
                        else
                            System.out.println("Неверный выбор.");
                    }
                    case 8 -> {
                        System.out.println("До свидания!");
                        return;
                    }
                    default -> System.out.println("Неверный ввод. Повторите.");
                }
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}
