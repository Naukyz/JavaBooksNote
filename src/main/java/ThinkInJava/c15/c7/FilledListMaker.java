package ThinkInJava.c15.c7;
//: generics/FilledListMaker.java

import java.util.*;

public class FilledListMaker<T> {

    public List<T> create(T t, int n) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < n; i++)
            result.add(t);
        return result;
    }

    public static void main(String[] args) {
        List<String> list = new FilledListMaker<String>().create("Hello", 4);
        System.out.println(list);

    }
} /* Output:
[Hello, Hello, Hello, Hello]
*///:~
