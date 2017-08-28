package ThinkInJava.c15.c7;
//: generics/ListMaker.java

import java.util.*;

public class ListMaker<T> {
    public List<T> create() {
        return new ArrayList<>();
    }

    public static void main(String[] args) {
        List<String> stringList = new ListMaker<String>().create();
    }
}
