//: ThinkInJava.c14.c2/pets/ForNameCreator.java
package ThinkInJava.c14.c3.pets;

import java.util.*;

public class ForNameCreator extends PetCreator {
    private static List<Class<? extends Pet>> types = new ArrayList<>();
    //Types that you want to be randomly created:
    private static String[] typeNames = {
            "ThinkInJava.c14.c2.pets.Mutt",
            "ThinkInJava.c14.c2.pets.Pug",
            "ThinkInJava.c14.c2.pets.EgyptianMau",
            "ThinkInJava.c14.c2.pets.Manx",
            "ThinkInJava.c14.c2.pets.Cymric",
            "ThinkInJava.c14.c2.pets.Rat",
            "ThinkInJava.c14.c2.pets.Mouse",
            "ThinkInJava.c14.c2.pets.Hamster"
    };

    @SuppressWarnings("unchecked")
    private static void loader() {
        try {
            for (String name : typeNames) {
                types.add((Class<? extends Pet>) Class.forName(name));
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        loader();
    }

    public List<Class<? extends Pet>> types() {
        return types;
    }
} ///:~
