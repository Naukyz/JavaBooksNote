package ThinkInJava.c15.c3.RandomCoffeeGenerator;

import ThinkInJava.util.Generator;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by zhao_yukuan@163.com
 * on 2017/4/21
 */
public class RandomCoffee implements Generator<Coffee>, Iterable<Coffee> {

    private Class[] types = {A.class, B.class};

    private int size;

    public RandomCoffee(int size) {
        this.size = size;
    }

    @Override
    public Coffee next() {
        try {
            return (Coffee) types[new Random().nextInt(types.length)].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new RandomCoffeeIterator();
    }

    private class RandomCoffeeIterator implements Iterator<Coffee> {

        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Coffee next() {
            size--;
            return RandomCoffee.this.next();
        }
    }
}

