//: net/mindview/util/BasicGenerator.java
// Automatically create a Generator, given a class
// with a default (no-arg) constructor.
package ThinkInJava.c15.c4;

import ThinkInJava.util.Generator;

public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;

    private BasicGenerator(Class<T> type) {
        this.type = type;
    }

    public T next() {
        try {
            // Assumes type is a public class:
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Produce a Default generator given a type token:
    public static <U> Generator<U> create(Class<U> type) {
        return new BasicGenerator<>(type);
    }
} ///:~
