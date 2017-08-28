package ThinkInJava.c15.c10;
//: generics/GenericWriting.java

import java.util.*;

public class GenericWriting {
    static <T> List<T> writeExact(List<T> list, T item) {
        list.add(item);
        return list;
    }

    static List<Apple> apples = new ArrayList<Apple>();
    static List<Fruit> fruit = new ArrayList<Fruit>();

    static void f1() {
        writeExact(apples, new Apple());
        List<Fruit> list = writeExact(fruit, new Apple()); // Error:
        // Incompatible types: found Fruit, required Apple
    }

    static <T> void
    writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {
        writeWithWildcard(apples, new Apple());
        writeWithWildcard(fruit, new Apple());
    }

    public static void main(String[] args) {
        f1();
        f2();
    }
} ///:~
