package task2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<ActiveCat> cats = new ArrayList<>();

        cats.add(new ActiveCat(ActiveCat::jump));
        cats.add(new ActiveCat(ActiveCat::sleep));
        cats.add(new ActiveCat(ActiveCat::eat));
        cats.add(new ActiveCat(ActiveCat::purr));
        cats.add(new ActiveCat(ActiveCat::play));
        cats.add(new ActiveCat(ActiveCat::walk));


        cats.forEach(ActiveCat::doAction);
    }

}