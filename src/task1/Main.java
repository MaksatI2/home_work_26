package task1;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Cat> cats = task1.Cat.makeCats(10);
        task1.Printer.print(cats);

        cats.sort(Comparator.comparing(Cat::getBreed));
        System.out.println("Sorted by breed:");
        task1.Printer.print(cats);

        cats.sort(Comparator.comparing(Cat::getName).thenComparing(Cat::getAge));
        System.out.println("Sorted by name then age:");
        task1.Printer.print(cats);

        Cat.Color colorToRemove = Cat.Color.GRAY;
        cats.removeIf(cat -> cat.getColor() == colorToRemove);
        System.out.println("Removed cats of color " + colorToRemove + ":");
        task1.Printer.print(cats);

        cats.removeIf(cat -> cat.getName().length() == 5);
        System.out.println("Removed cats with name length 5:");
        task1.Printer.print(cats);

    }
}
