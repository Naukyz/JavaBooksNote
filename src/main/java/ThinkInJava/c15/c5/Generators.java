package ThinkInJava.c15.c5;
//: generics/Generators.java
// A utility to use with Generators.

import ThinkInJava.c15.c3.FibonacciGenerator.Fibonacci;
import ThinkInJava.c15.c3.RandomCoffeeGenerator.Coffee;
import ThinkInJava.c15.c3.RandomCoffeeGenerator.RandomCoffee;
import ThinkInJava.util.Generator;

import java.util.ArrayList;
import java.util.Collection;

public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }

    public static void main(String[] args) {
        Collection<Coffee> coffee = fill(new ArrayList<>(), new RandomCoffee(), 4);
        for (Coffee c : coffee) {
            System.out.print(c + ", ");
        }
        System.out.println();
        Collection<Integer> fnumbers = fill(new ArrayList<>(), new Fibonacci(), 12);
        for (int i : fnumbers) {
            System.out.print(i + ", ");
        }
    }
}

/* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
*///:~
