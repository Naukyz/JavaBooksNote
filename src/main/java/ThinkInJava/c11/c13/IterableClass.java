package ThinkInJava.c11.c13;

import java.util.*;

public class IterableClass implements Iterable<String> {
    protected String[] words = ("And that is how " +
            "we know the Earth to be banana-shaped.").split(" ");

    public Iterable<String> randomized() {
        return new Iterable<String>() {
            @Override
            public Iterator<String> iterator() {
                List<String> a = new ArrayList<>(Arrays.asList(words));
                // List<String> a = Arrays.asList(words);
                Collections.shuffle(a);
                return a.iterator();
            }
        };
    }

    public Iterable<String> reversed() {
        return new Iterable<String>() {
            private int index = words.length - 1;

            @Override
            public Iterator<String> iterator() {
                return new Iterator<String>() {
                    @Override
                    public boolean hasNext() {
                        return index > -1;
                    }

                    @Override
                    public String next() {
                        return words[index--];
                    }

                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterator<String> iterator() {
        return new Iterator<String>() {
            private int index = 0;

            public boolean hasNext() {
                return index < words.length;
            }

            public String next() {
                return words[index++];
            }

            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        IterableClass iterableClass = new IterableClass();
        for (String s : iterableClass.randomized())
            System.out.print(s + " ");
        System.out.println();
        for (String s : iterableClass)
            System.out.print(s + " ");
        System.out.println();
        for (String s : iterableClass.reversed())
            System.out.print(s + " ");
    }
}
