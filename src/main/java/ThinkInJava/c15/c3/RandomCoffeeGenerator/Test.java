package ThinkInJava.c15.c3.RandomCoffeeGenerator;

import ThinkInJava.util.Generator;

import java.util.ArrayList;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/29
 */
public class Test {
    public static void main(String[] args) {
        RandomCoffee a = new RandomCoffee(20);
        for (Coffee c : a) {
            System.out.print(c + " ");
        }

        System.out.println("---");
        ArrayList<Coffee> arrayList = new ArrayList<>();
        int size = 20;
        System.out.println(fillList(arrayList, new RandomCoffee(size), size));

    }

    public static <T> ArrayList<T> fillList(ArrayList<T> arrayList, Generator<T> generator, int size) {

        for (int i = 0; i < size; i++) {
            arrayList.add(generator.next());
        }
        return arrayList;
    }
}
